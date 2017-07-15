package DAO;

import com.github.cleverage.elasticsearch.IndexResults;
import models.Source;
import models.TelamNews;

import java.util.List;

/**
 * Created by daniel on 6/30/17.
 */
public interface ISourcesDAO {

    IndexResults getAllSources(int page);

    Source getById(String id);


}
