package models;

import com.github.cleverage.elasticsearch.Index;
import com.github.cleverage.elasticsearch.Indexable;
import com.github.cleverage.elasticsearch.annotations.IndexType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daniel on 2/07/17.
 */
@IndexType(name="source")
public class Source extends Index {

    private String name;
    private String keyword;
    private String description;
    private String imageUrl;
    private String url;
    public static Finder<Source> find = new Finder<Source>(Source.class);

    public String getUrl() {        return url;    }

    public void setUrl(String url) {        this.url = url;    }

    public String getImageUrl() {        return imageUrl;    }

    public void setImageUrl(String imageUrl) {        this.imageUrl = imageUrl;    }

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


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public Map toIndex() {
        HashMap map = new HashMap();
        map.put("name", name);
        map.put("keyword", keyword);
        map.put("description", description);
        map.put("imageUrl",imageUrl);
        map.put("url",url);
        return map;
    }

    @Override
    public Indexable fromIndex(Map map) {
        if (map == null) {
            return this;
        }

        this.name = (String) map.get("name");
        this.keyword = (String) map.get("keyword");
        this.description = (String) map.get("description");
        this.imageUrl=(String) map.get("imageUrl");
        this.url=(String) map.get("url");
        return this;
    }
}
