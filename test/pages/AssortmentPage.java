package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;
import play.test.*;

public class AssortmentPage extends FluentPage {
    private String url;


    public AssortmentPage(WebDriver webDriver, int portNumber) {
        super(webDriver);
        this.url = "http://localhost:" + portNumber + "/assortment";
    }
    

    @Override
    public String getUrl() {
        return url;
    }
}