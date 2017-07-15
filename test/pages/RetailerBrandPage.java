package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;
import play.test.*;

public class RetailerBrandPage extends FluentPage {
    private String url;


    public RetailerBrandPage(WebDriver webDriver, int portNumber) {
        super(webDriver);
        this.url = "http://localhost:" + portNumber + "/productcollection/Target/Asics/65";
    }
    

    @Override
    public String getUrl() {
        return url;
    }
}