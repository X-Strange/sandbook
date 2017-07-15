package models;

import com.github.cleverage.elasticsearch.Index;
import com.github.cleverage.elasticsearch.Indexable;
import com.github.cleverage.elasticsearch.annotations.IndexType;
import scala.util.parsing.combinator.testing.Str;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tkmaec0 on 5/12/14.
 */
@IndexType(name="retailer")
public class Retailer extends Index {

    private String name;
    private String description;
    private String imgUrl;
    private String siteURL;

    public String getSiteURL() {        return siteURL;    }

    public void setSiteURL(String siteURL) {        this.siteURL = siteURL;    }

    public String getImgUrl() {        return imgUrl;    }

    public void setImgUrl(String imgUrl) {        this.imgUrl = imgUrl;    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Map toIndex() {
        HashMap map = new HashMap();
        map.put("name", name);
        map.put("description", description);
        map.put("imgUrl",imgUrl);
        map.put("siteURL",siteURL);
        return map;
    }

    @Override
    public Indexable fromIndex(Map map) {
        if (map == null) {
            return this;
        }

        this.name = (String) map.get("name");
        this.description = (String) map.get("description");
        this.imgUrl=(String) map.get("imgUrl");
        this.siteURL=(String) map.get("siteURL");
        return this;
    }
}
