package models;

import com.github.cleverage.elasticsearch.Index;
import com.github.cleverage.elasticsearch.Indexable;
import com.github.cleverage.elasticsearch.annotations.IndexType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by daniel on 6/30/17.
 */
@IndexType(name = "telam")
public class TelamNews extends Index{

    private String title;
    private String url;
    private String image;
    private String source;
    private String newsDate;
    public static Finder<TelamNews> find = new Finder<>(TelamNews.class);

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    @Override
    public Map toIndex() {
        HashMap map = new HashMap();
        map.put("title", title);
        map.put("url", url);
        map.put("image", image);
        map.put("source", source);
        map.put("newsDate", newsDate);
        return map;
    }

    @Override
    public Indexable fromIndex(Map map) {
        if (map == null) {
            return this;
        }

        this.title = (String) map.get("title");
        this.url = (String) map.get("url");
        if (map.get("image") != null) {
            this.image = (String) map.get("image");
        } else if (map.get("image2") != null) {
            this.image = (String) map.get("image2");
        } else if (map.get("image3") != null) {
            this.image = (String) map.get("image3");
        } else {
            this.image = (String) map.get("image4");
        }
        this.source = (String) map.get("source");
        this.newsDate = (String) map.get("newsDate");
        return this;
    }

    @Override
    public String toString() {
        return "TelamNews{" +
                "title='" + title + '\'' + ", " +
                "url='" + url + '\'' +
                "image='" + image + '\'' +
                "source='" + source + '\'' +
                "newsDate='" + newsDate + '\'' +
            "}";
    }

}





