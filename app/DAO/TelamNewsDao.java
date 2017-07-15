package DAO;

import com.github.cleverage.elasticsearch.IndexQuery;
import com.github.cleverage.elasticsearch.IndexResults;
import models.Style;
import models.TelamNews;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.datehistogram.InternalCountDateHistogramFacet;
import org.elasticsearch.search.facet.statistical.StatisticalFacet;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import play.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by daniel on 6/30/17.
 */
public class TelamNewsDao implements ITelamNewsDAO {
    private TermsFacet sources;

    /**
     * Get all telam news from ElasticSearch.
     * @param page for pagination.
     * @return IndexResults
     */
    @Override
    public IndexResults getAllTelamNews(int page) {
        IndexQuery<TelamNews> telamNewsIndexQuery;
        IndexResults<TelamNews> allTelamNewsAndFacetSource = null;
        try {
            telamNewsIndexQuery = TelamNews.find.query();
            telamNewsIndexQuery.addFacet(FacetBuilders.termsFacet("sources").field("source"));
            telamNewsIndexQuery.addFacet(FacetBuilders.dateHistogramFacet("histo-date").field("newsDate").interval("day"));
            telamNewsIndexQuery.size(10);
            telamNewsIndexQuery.from((page - 1) * 10);
            allTelamNewsAndFacetSource = TelamNews.find.search(telamNewsIndexQuery);
            this.sources = allTelamNewsAndFacetSource.facets.facet("sources");

            if (allTelamNewsAndFacetSource != null) {
                InternalCountDateHistogramFacet f = (InternalCountDateHistogramFacet) allTelamNewsAndFacetSource.facets.facets().get(1);
                DateTime dt = new DateTime(f.getEntries().get(f.getEntries().size()-1).getTime()).withZone(DateTimeZone.UTC);
                org.joda.time.format.DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
                RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("newsDate").from(fmt.print(dt)).to(fmt.print(dt));
                telamNewsIndexQuery.setBuilder(rangeQueryBuilder);
                allTelamNewsAndFacetSource = TelamNews.find.search(telamNewsIndexQuery);
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return allTelamNewsAndFacetSource;
    }

    /**
     * Search for telam news matching String text from ElasticSearch
     * @param text string to search.
     * @param date string to search.
     * @param page for pagination.
     * @param size for pagination.
     * @return IndexResults
     */
    @Override
    public IndexResults searchTelamNews(String text, String date, int page, int size) {
        IndexQuery<TelamNews> telamNewsIndexQuery;
        IndexResults<TelamNews> allTelamNewsAndFacetSource = null;
        try {
            telamNewsIndexQuery = TelamNews.find.query();
            telamNewsIndexQuery.setBuilder(QueryBuilders.queryString("*" + text + "*"));
            telamNewsIndexQuery.addFacet(FacetBuilders.termsFacet("sources").field("source"));
            if (date != null) {
                RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("newsDate").from(date).to(date);
                telamNewsIndexQuery.setBuilder(rangeQueryBuilder);
            }
            telamNewsIndexQuery.from((page - 1) * size);
            telamNewsIndexQuery.size(size);
            allTelamNewsAndFacetSource = TelamNews.find.search(telamNewsIndexQuery);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return allTelamNewsAndFacetSource;
    }

    /**
     * Get telam news by String id from ElasticSearch.
     * @param id string
     * @return TelamNews
     */
    @Override
    public TelamNews getById(String id) {
        TelamNews telamNews = null;
        try {
            //style = Style.find.byId(id);
            telamNews = TelamNews.find.byId(id);
/*            IndexQuery<Style> styleIndexQuery = Style.find.query();
            styleIndexQuery.addFacet(FacetBuilders.statisticalFacet("average").field("originalPrice"));
            styleIndexQuery.setBuilder(QueryBuilders.fieldQuery("styleId", id));
            IndexResults<Style> styleIndexResult = Style.find.search(styleIndexQuery);
            style = styleIndexResult.getResults().get(0);
            StatisticalFacet f = (StatisticalFacet) styleIndexResult.facets.facets().get(0);
            style.setMaxPrice(BigDecimal.valueOf(f.getMax()));
            style.setMinPrice(BigDecimal.valueOf(f.getMin()));
            style.setAveragePrice(BigDecimal.valueOf(f.getMean()).setScale(2, BigDecimal.ROUND_DOWN));*/
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return telamNews;
    }

    /**
     * Search styles by retailers
     * @param page for pagination.
     * @param sources selected.
     * @return IndexResults
     */
    @Override
    public IndexResults searchNewsBySources(int page, List<String> sources) {
        IndexQuery<TelamNews> telamNewsIndexQuery = null;
        IndexResults<TelamNews> allNewsAndFacetSource = null;
        try {
            telamNewsIndexQuery = TelamNews.find.query();
            telamNewsIndexQuery.addFacet(FacetBuilders.termsFacet("sources").field("source"));
            telamNewsIndexQuery.addFacet(FacetBuilders.dateHistogramFacet("histo-date").field("newsDate").interval("day"));
            TermsQueryBuilder filterBuilder = QueryBuilders.termsQuery("source", sources);
            telamNewsIndexQuery.setBuilder(filterBuilder);
            telamNewsIndexQuery.size(10);
            telamNewsIndexQuery.from((page - 1) * 10);
            allNewsAndFacetSource = TelamNews.find.search(telamNewsIndexQuery);

            if (allNewsAndFacetSource != null) {
                IndexQuery<TelamNews> filteredIndexQuery = TelamNews.find.query();
                InternalCountDateHistogramFacet f = (InternalCountDateHistogramFacet) allNewsAndFacetSource.facets.facets().get(1);
                DateTime dt = new DateTime(f.getEntries().get(f.getEntries().size()-1).getTime()).withZone(DateTimeZone.UTC);
                org.joda.time.format.DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
                filterBuilder = QueryBuilders.termsQuery("source", sources);
                RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("newsDate").from(fmt.print(dt)).to(fmt.print(dt));
                filteredIndexQuery.setBuilder(QueryBuilders.boolQuery().must(rangeQueryBuilder).must(filterBuilder));
                allNewsAndFacetSource = TelamNews.find.search(filteredIndexQuery);
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return allNewsAndFacetSource;
    }

    /**
     * Get all retailers.
     * @return TermsFacet with all retailers.
     */
    public Map<String,Integer> getAllSources() {
        IndexQuery<TelamNews> telamNewsIndexQuery;
        IndexQuery<TelamNews> telamNewsIndexQuery2;
        Map<String,Integer> allSources = new HashMap<>();
        try {
            /* Get all sources from ES */
            telamNewsIndexQuery = TelamNews.find.query();
            telamNewsIndexQuery.addFacet(FacetBuilders.termsFacet("sources").field("source"));
            IndexResults<TelamNews> telamNewsIndexResults = TelamNews.find.search(telamNewsIndexQuery);
            TermsFacet source;
            TermsFacet sources = telamNewsIndexResults.facets.facet("sources");

            /* Loop for each retailer */
            for (int i = 0; i < sources.getEntries().size(); i++ ) {
                telamNewsIndexQuery2 = TelamNews.find.query();
                telamNewsIndexQuery2.addFacet(FacetBuilders.dateHistogramFacet("histo-date").field("newsDate").interval("day"));
                TermsQueryBuilder filterBuilder = QueryBuilders.termsQuery("source", sources.getEntries().get(i).getTerm().string());
                telamNewsIndexQuery2.setBuilder(filterBuilder);
                IndexResults<TelamNews> telamNewsIndexResults3 = TelamNews.find.search(telamNewsIndexQuery2);

                if (telamNewsIndexResults3 != null) {
                    /* Set facets to get all newsDates*/
                    InternalCountDateHistogramFacet f = (InternalCountDateHistogramFacet) telamNewsIndexResults3.facets.facets().get(0);
                    /* Get last entrie with max time */
                    int size = f.getEntries().size();
                    IndexQuery<TelamNews> filteredIndexQuery = TelamNews.find.query();
                    filteredIndexQuery.addFacet(FacetBuilders.termsFacet("sources").field("source"));
                    DateTime dt = new DateTime(f.getEntries().get(size-1).getTime()).withZone(DateTimeZone.UTC);
                    org.joda.time.format.DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
                    RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("newsDate").from(fmt.print(dt)).to(fmt.print(dt));
                    filteredIndexQuery.setBuilder(QueryBuilders.boolQuery().must(rangeQueryBuilder).must(filterBuilder));
                    IndexResults<TelamNews> telamNewsIndexResultsFiltered = TelamNews.find.search(filteredIndexQuery);
                    source = telamNewsIndexResultsFiltered.facets.facet("sources");
                    if (source.getEntries().size() > 0) {
                        /* Put retailerName and count for each retailer */
                        allSources.put(source.getEntries().get(0).getTerm().string(), source.getEntries().get(0).getCount());
                    }
                }
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        return allSources;
    }
}
