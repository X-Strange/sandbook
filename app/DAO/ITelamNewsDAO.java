package DAO;

import com.github.cleverage.elasticsearch.IndexResults;
import models.Style;
import models.TelamNews;

import java.util.List;

/**
 * Created by daniel on 6/30/17.
 */
public interface ITelamNewsDAO {

    IndexResults getAllTelamNews(int page);

    IndexResults searchTelamNews(String text, String date, int page, int size);

    TelamNews getById(String id);

    IndexResults searchNewsBySources(int page, List<String> retailer);

}
