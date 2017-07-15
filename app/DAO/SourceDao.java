package DAO;

import com.github.cleverage.elasticsearch.IndexResults;
import models.Retailer;
import models.Source;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 04/07/17.
 */
public class SourceDao implements ISourcesDAO {

    @Override
    public IndexResults getAllSources(int page) {
        IndexResults<Source> allSources = Source.find.all();
        return allSources;
    }

    @Override
    public Source getById(String id) {
        return Source.find.byId(id);
    }
}
