package DAO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import models.Style;

import org.apache.commons.collections.map.HashedMap;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.datehistogram.InternalCountDateHistogramFacet;
import org.elasticsearch.search.facet.statistical.StatisticalFacet;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import play.Logger;

import com.github.cleverage.elasticsearch.IndexQuery;
import com.github.cleverage.elasticsearch.IndexResults;

/**
 * Created by tkmaec0 on 5/27/14.
 */
public class StyleDao implements IStyleDAO {

    private TermsFacet retailers;

    /**
     * Get all styles from ElasticSearch.
     * @param page for pagination.
     * @return IndexResults
     */
    @Override
    public IndexResults getAllStyles(int page) {
        IndexQuery<Style> styleIndexQuery = null;
        IndexResults<Style> allStylesAndFacetRetailer = null;
        try {
            styleIndexQuery = Style.find.query();
            styleIndexQuery.addFacet(FacetBuilders.termsFacet("retailers").field("retailerName"));
            styleIndexQuery.addFacet(FacetBuilders.dateHistogramFacet("histo-date").field("crawlerDate").interval("day"));
            styleIndexQuery.size(10);
            styleIndexQuery.from((page - 1) * 10);
            allStylesAndFacetRetailer = Style.find.search(styleIndexQuery);
            this.retailers = allStylesAndFacetRetailer.facets.facet("retailers");

            if (allStylesAndFacetRetailer != null) {
                InternalCountDateHistogramFacet f = (InternalCountDateHistogramFacet) allStylesAndFacetRetailer.facets.facets().get(1);
                DateTime dt = new DateTime(f.getEntries().get(f.getEntries().size()-1).getTime()).withZone(DateTimeZone.UTC);
                org.joda.time.format.DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
                RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("crawlerDate").from(fmt.print(dt)).to(fmt.print(dt));
                styleIndexQuery.setBuilder(rangeQueryBuilder);
                allStylesAndFacetRetailer = Style.find.search(styleIndexQuery);
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return allStylesAndFacetRetailer;
    }

    /**
     * Search for styles matching String text from ElasticSearch
     * @param text string to search.
     * @param date string to search.
     * @param page for pagination.
     * @param size for pagination.
     * @return IndexResults
     */
    @Override
    public IndexResults searchStyles(String text, String date, int page, int size)  {
        IndexQuery<Style> styleIndexQuery = null;
        IndexResults<Style> allStylesAndFacetRetailer = null;
        try {
            styleIndexQuery = Style.find.query();
            styleIndexQuery.setBuilder(QueryBuilders.queryString("*" + text + "*"));
            styleIndexQuery.addFacet(FacetBuilders.termsFacet("retailers").field("retailerName"));
            if (date != null) {
                RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("crawlerDate").from(date).to(date);
                styleIndexQuery.setBuilder(rangeQueryBuilder);
            }
            styleIndexQuery.from((page - 1) * size);
            styleIndexQuery.size(size);
            allStylesAndFacetRetailer = Style.find.search(styleIndexQuery);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return allStylesAndFacetRetailer;
    }

    /**
     * Get style by String id from ElasticSearch.
     * @param id string
     * @return Style
     */
    @Override
    public Style getById(String id) {
        Style style = null;
        try {
            //style = Style.find.byId(id);
            IndexQuery<Style> styleIndexQuery = Style.find.query();
            styleIndexQuery.addFacet(FacetBuilders.statisticalFacet("average").field("originalPrice"));
            styleIndexQuery.setBuilder(QueryBuilders.fieldQuery("styleId", id));
            IndexResults<Style> styleIndexResult = Style.find.search(styleIndexQuery);
            style = styleIndexResult.getResults().get(0);
            StatisticalFacet f = (StatisticalFacet) styleIndexResult.facets.facets().get(0);
            style.setMaxPrice(BigDecimal.valueOf(f.getMax()));
            style.setMinPrice(BigDecimal.valueOf(f.getMin()));
            style.setAveragePrice(BigDecimal.valueOf(f.getMean()).setScale(2, BigDecimal.ROUND_DOWN));
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return style;
    }

    /**
     * Search styles by retailers
     * @param page for pagination.
     * @param retailers selected.
     * @return IndexResults
     */
    @Override
    public IndexResults searchStylesByRetailers(int page, List<String> retailers) {
        IndexQuery<Style> styleIndexQuery = null;
        IndexResults<Style> allStylesAndFacetRetailer = null;
        try {
            styleIndexQuery = Style.find.query();
            styleIndexQuery.addFacet(FacetBuilders.termsFacet("retailers").field("retailerName"));
            styleIndexQuery.addFacet(FacetBuilders.dateHistogramFacet("histo-date").field("crawlerDate").interval("day"));
            TermsQueryBuilder filterBuilder = QueryBuilders.termsQuery("retailerName", retailers);
            styleIndexQuery.setBuilder(filterBuilder);
            styleIndexQuery.size(10);
            styleIndexQuery.from((page - 1) * 10);
            allStylesAndFacetRetailer = Style.find.search(styleIndexQuery);

            if (allStylesAndFacetRetailer != null) {
                IndexQuery<Style> filteredIndexQuery = Style.find.query();
                InternalCountDateHistogramFacet f = (InternalCountDateHistogramFacet) allStylesAndFacetRetailer.facets.facets().get(1);
                DateTime dt = new DateTime(f.getEntries().get(f.getEntries().size()-1).getTime()).withZone(DateTimeZone.UTC);
                org.joda.time.format.DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
                filterBuilder = QueryBuilders.termsQuery("retailerName", retailers);
                RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("crawlerDate").from(fmt.print(dt)).to(fmt.print(dt));
                filteredIndexQuery.setBuilder(QueryBuilders.boolQuery().must(rangeQueryBuilder).must(filterBuilder));
                allStylesAndFacetRetailer = Style.find.search(filteredIndexQuery);
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return allStylesAndFacetRetailer;
    }

    /**
     * Get all retailers.
     * @return TermsFacet with all retailers.
     */
    public Map<String,Integer> getAllRetailers() {
        IndexQuery<Style> styleIndexQuery;
        IndexQuery<Style> styleIndexQuery2;
        Map<String,Integer> allRetailers = new HashMap<>();
        try {
            /* Get all retailers from ES */
            styleIndexQuery = Style.find.query();
            styleIndexQuery.addFacet(FacetBuilders.termsFacet("retailers").field("retailerName"));
            IndexResults<Style> styleIndexResults = Style.find.search(styleIndexQuery);
            TermsFacet retailer;
            TermsFacet retailers = styleIndexResults.facets.facet("retailers");

            /* Loop for each retailer */
            for (int i = 0; i < retailers.getEntries().size(); i++ ) {
                styleIndexQuery2 = Style.find.query();
                styleIndexQuery2.addFacet(FacetBuilders.dateHistogramFacet("histo-date").field("crawlerDate").interval("day"));
                TermsQueryBuilder filterBuilder = QueryBuilders.termsQuery("retailerName", retailers.getEntries().get(i).getTerm().string());
                styleIndexQuery2.setBuilder(filterBuilder);
                IndexResults<Style> styleIndexResults3 = Style.find.search(styleIndexQuery2);

                if (styleIndexResults3 != null) {
                    /* Set facets to get all crawlerDates*/
                    InternalCountDateHistogramFacet f = (InternalCountDateHistogramFacet) styleIndexResults3.facets.facets().get(0);
                    /* Get last entrie with max time */
                    int size = f.getEntries().size();
                    IndexQuery<Style> filteredIndexQuery = Style.find.query();
                    filteredIndexQuery.addFacet(FacetBuilders.termsFacet("retailers").field("retailerName"));
                    DateTime dt = new DateTime(f.getEntries().get(size-1).getTime()).withZone(DateTimeZone.UTC);
                    org.joda.time.format.DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
                    RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("crawlerDate").from(fmt.print(dt)).to(fmt.print(dt));
                    filteredIndexQuery.setBuilder(QueryBuilders.boolQuery().must(rangeQueryBuilder).must(filterBuilder));
                    IndexResults<Style> styleIndexResultsFiltered = Style.find.search(filteredIndexQuery);
                    retailer = styleIndexResultsFiltered.facets.facet("retailers");
                    if (retailer.getEntries().size() > 0) {
                        /* Put retailerName and count for each retailer */
                        allRetailers.put(retailer.getEntries().get(0).getTerm().string(), retailer.getEntries().get(0).getCount());
                     }
                }
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return allRetailers;
    }
}
