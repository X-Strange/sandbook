package DAO;

import com.github.cleverage.elasticsearch.IndexResults;
import models.Style;

import java.util.List;

/**
 * Created by tkmaec0 on 5/26/14.
 */
public interface IStyleDAO {

    IndexResults getAllStyles(int page);

    IndexResults searchStyles(String text, String date, int page, int size);

    Style getById(String id);

    IndexResults searchStylesByRetailers(int page, List<String> retailer);
}
