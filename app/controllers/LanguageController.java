package controllers;

import play.i18n.Lang;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

/**
 * Created by daniel on 6/7/17.
 */
public class LanguageController extends Controller {

    public Result index(String language) {
        if(language != null && !language.isEmpty()){
            Controller.changeLang(language);
            //Lang.apply(language);
        }
        return ok(index.render());
    }
}