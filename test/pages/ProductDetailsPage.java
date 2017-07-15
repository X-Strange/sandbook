package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;
import play.test.*;

public class ProductDetailsPage extends FluentPage {
    private String url;


    public ProductDetailsPage(WebDriver webDriver, int portNumber) {
        super(webDriver);
        this.url = "http://localhost:" + portNumber + "/productdetail";
    }
    

    @Override
    public String getUrl() {
        return url;
    }
}