package models;

import com.github.cleverage.elasticsearch.Index;
import com.github.cleverage.elasticsearch.Indexable;
import com.github.cleverage.elasticsearch.annotations.IndexType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by tkmaec0 on 5/9/14.
 */
@IndexType(name = "style")
public class Style extends Index{

    private String styleName;
    private String styleDescription;
    private String retailerName;
    private Double originalPrice;
    private String imgUrl;
    private String styleId;
    private String crawlerDate;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private BigDecimal averagePrice;

    public static Finder<Style> find = new Finder<Style>(Style.class);

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String name) {
        this.styleName = name;
    }

    public String getStyleDescription() {
        return styleDescription;
    }

    public void setStyleDescription(String styleDescription) {
        this.styleDescription = styleDescription;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getCrawlerDate() {
        return crawlerDate;
    }

    public void setCrawlerDate(String crawlerDate) {
        this.crawlerDate = crawlerDate;
    }


    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    @Override
    public Map toIndex() {
        HashMap map = new HashMap();
        map.put("styleName", styleName);
        map.put("styleDescription", styleDescription);
        map.put("originalPrice", originalPrice);
        map.put("imgUrl", imgUrl);
        map.put("retailerName", retailerName);
        map.put("styleId", styleId);
        map.put("crawlerDate", crawlerDate);
        return map;
    }

    @Override
    public Indexable fromIndex(Map map) {
        if (map == null) {
            return this;
        }

        this.styleName = (String) map.get("styleName");
        this.styleDescription = (String) map.get("styleDescription");
        try {
            this.originalPrice = (Double) map.get("originalPrice");
        } catch (Exception e ){
            this.originalPrice = Double.valueOf((String) map.get("originalPrice"));
        }
        this.imgUrl = (String) map.get("imgUrl");
        this.retailerName = (String) (map.get("retailerName"));
        this.styleId = (String) map.get("styleId");
        this.crawlerDate = (String) map.get("crawlerDate");
        this.retailerName = retailerName.replace("\\u0027", "'");
        return this;
    }

    @Override
    public String toString() {
        return "Style{" +
                "styleName='" + styleName + '\'' + ", " +
                "styleDescription='" + styleDescription + '\'' +
                "originalPrice='" + originalPrice + '\''+
                "styleId='" + styleId + '\'' +
                '}';
    }

}





