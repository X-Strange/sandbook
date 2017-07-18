package controllers;

import DAO.RetailerDao;
import DAO.SourceDao;
import DAO.StyleDao;
import DAO.TelamNewsDao;
import com.github.cleverage.elasticsearch.IndexResults;
import models.*;
import org.elasticsearch.search.facet.terms.TermsFacet;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.FilterForm;
import views.html.*;

import java.util.*;

public class Application extends Controller {


    private static StyleDao styleDao = new StyleDao();

    private static TelamNewsDao telamNewsDao = new TelamNewsDao();

    private static RetailerDao retailerDao = new RetailerDao();

    private static SourceDao sourceDao = new SourceDao();

    private static User user = User.find.byId("1");

    public static Result index() {

        return ok(index.render());
    }

    public static Result dashboard() {

        return ok(dashboard.render());
    }
    public static Result userprofile() {

        return ok(userprofile.render(user));
    }
    public static Result stylecollection(String retailer, String brand, String value) {
        return ok(stylecollection.render(styleDao.getAllStyles(1).getResults(), retailer, brand, value));
    }

    public static Result retailers(){

        return ok(retailers.render(sourceDao.getAllSources(1).getResults()));
    }
    public static Result retailerStyles(String retailer){
        retailer = retailer.toLowerCase();
        retailer = retailer.replace("'" , "\u0027");
        List<String> rtl = new ArrayList<>();
        rtl.add(retailer);
        IndexResults<Style> styleIndexResults;
        styleIndexResults = styleDao.searchStylesByRetailers(1, rtl);
        //List<TermsFacet.Entry> retailers = (List<TermsFacet.Entry>) styleDao.getAllRetailers().getEntries();
        long totalPages = styleIndexResults.getPageNb();
        return ok(styles.render(styleIndexResults.getResults(), 1, totalPages, styleDao.getAllRetailers(), rtl));
    }
    public static Result styledetail(String id) {
        return ok(styledetail.render(styleDao.getById(id)));
    }

    public static Result overlaps() {
        return ok(overlaps.render(styleDao.getAllStyles(1).getResults()));

    }

    public static Result intertopic() {
        return ok(intertopic.render());
    }

    public static Result styles(int sPage) {
        List<Style> stylesList = styleDao.getAllStyles(sPage).getResults();
        //List<TermsFacet.Entry> retailers = (List<TermsFacet.Entry>) styleDao.getAllRetailers().getEntries();
        long totalPages = styleDao.getAllStyles(sPage).getPageNb();
        List<String> list = new ArrayList<>();

        return ok(styles.render(stylesList,sPage, totalPages, styleDao.getAllRetailers(), list));
    }

    public static Result telamNews(int sPage) {
        List<TelamNews> telamNewsList = telamNewsDao.getAllTelamNews(sPage).getResults();
        //List<TermsFacet.Entry> retailers = (List<TermsFacet.Entry>) styleDao.getAllRetailers().getEntries();
        long totalPages = telamNewsDao.getAllTelamNews(sPage).getPageNb();
        List<String> list = new ArrayList<>();

        return ok(telamnews.render(telamNewsList,sPage, totalPages, telamNewsDao.getAllSources(), list));
    }

    public static Result postStyles (int sPage){
        DynamicForm requestData = Form.form().bindFromRequest();
        //List<TermsFacet.Entry> retailers = (List<TermsFacet.Entry>) styleDao.getAllRetailers().getEntries();
        IndexResults<Style> styleIndexResults;
        List<String> selectedRetailers = new ArrayList<>();
        Map<String,String> map = requestData.data();
        int page;
        if (map.get("sPage") != null) {
            String pg = map.get("sPage");
            page = Integer.valueOf(pg);
        } else {
            page = sPage;
        }
        String date = map.get("date");
        String searchString = requestData.get("search");

        if((searchString == null && date == null) || map.get("retailers") != null) {
            if (requestData.data().isEmpty()) {
                flash("error", "Please select a retailer to filter");
                return ok(styles.render(new ArrayList(), 1, null, styleDao.getAllRetailers(), new ArrayList()));
            }
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            do {
                if(!iterator.next().getKey().contains("retailers")) {
                    iterator.remove();
                }
            } while (iterator.hasNext());

            selectedRetailers.addAll(map.values());
            styleIndexResults = styleDao.searchStylesByRetailers(page, selectedRetailers);
        } else {
            if (searchString == null && date.equals("")) {
                flash("error", "Please type to search, select a date or select a retailer.");
                return ok(styles.render(new ArrayList(), 1, null, styleDao.getAllRetailers(), new ArrayList()));
            }
            styleIndexResults = styleDao.searchStyles(searchString, date, page, 10);
        }


        long totalPages = styleIndexResults.getPageNb();

        return ok(styles.render(styleIndexResults.getResults(), page, totalPages, styleDao.getAllRetailers(), selectedRetailers));
    }

    public static Result postTelamNews (int sPage){
        DynamicForm requestData = Form.form().bindFromRequest();
        //List<TermsFacet.Entry> retailers = (List<TermsFacet.Entry>) styleDao.getAllRetailers().getEntries();
        IndexResults<TelamNews> telamNewsIndexResults;
        List<String> selectedSources = new ArrayList<>();
        Map<String,String> map = requestData.data();
        int page;
        if (map.get("sPage") != null) {
            String pg = map.get("sPage");
            page = Integer.valueOf(pg);
        } else {
            page = sPage;
        }
        String date = map.get("date");
        String searchString = requestData.get("search");
        if (searchString == null && date == null || map.get("sources") != null) {
            if (requestData.data().isEmpty()) {
                flash("error", "Please type to search, select a date or select a retailer.");
                return ok(telamnews.render(new ArrayList(), 1, null, telamNewsDao.getAllSources(), new ArrayList()));
            }
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            do {
                if(!iterator.next().getKey().contains("sources")) {
                    iterator.remove();
                }
            } while (iterator.hasNext());

            selectedSources.addAll(map.values());
            telamNewsIndexResults = telamNewsDao.searchNewsBySources(page, selectedSources);
        } else {
            if (searchString == null && date.equals("")) {
                flash("error", "Please type to search, select a date or select a retailer.");
                return ok(styles.render(new ArrayList(), 1, null, telamNewsDao.getAllSources(), new ArrayList()));
            }
            telamNewsIndexResults = telamNewsDao.searchTelamNews(searchString, date, page, 10);
        }
        //telamNewsIndexResults = telamNewsDao.searchTelamNews(searchString, null, page, 10);


        long totalPages = telamNewsIndexResults.getPageNb();

        return ok(telamnews.render(telamNewsIndexResults.getResults(), page, totalPages, telamNewsDao.getAllSources(), selectedSources));
    }

    /**
     * Returns the page where the form is filled, or an empty form
     * @return The page containing the form and data.
     */
    public static Result getIndex() {
        FilterForm filterForm = new FilterForm();
        Form<FilterForm> formData = Form.form(FilterForm.class).fill(filterForm);
        return ok(comparison.render(sourceDao.getAllSources(1).getResults(),
                formData,
                Brand.makeBrandsMap(filterForm),
                ""
        ));
    }

    /**
     * Process a form submission.
     * First we bind the HTTP POST data to an instance of FilterForm.
     * The binding process will invoke the FilterForm.validate() method.
     * If errors are found, re-render the page, displaying the error data.
     * If errors not found, render the page with the good data.
     * @return The index page with the results of validation.
     */
    public static Result postIndex() {

        // Get the submitted form data from the request object, and run validation.
        Form<FilterForm> formData = Form.form(FilterForm.class).bindFromRequest();

        if (formData.hasErrors()) {
            // Don't call formData.get() when there are errors, pass 'null' to helpers instead.
            flash("error", "Please correct errors above.");
            return badRequest(comparison.render(sourceDao.getAllSources(1).getResults(), formData,
                    Brand.makeBrandsMap(null),
                    "Error"
            ));
        }
        else {
            // Convert the formData into a FilterRequest model instance.
            FilterRequest filterRequest = FilterRequest.makeInstance(formData.get());
            flash("success", "" + filterRequest);
            return ok(comparison.render(sourceDao.getAllSources(1).getResults(), formData,
                    Brand.makeBrandsMap((formData.get())
            ), filterRequest.toString()));
        }
    }

}

