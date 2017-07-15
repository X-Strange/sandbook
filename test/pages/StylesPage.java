package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;
import play.test.*;

public class StylesPage extends FluentPage {
    private String url;


    public StylesPage(WebDriver webDriver, int portNumber) {
        super(webDriver);
        this.url = "http://localhost:" + portNumber + "/styles?sPage=1";
    }
    

    @Override
    public String getUrl() {
        return url;
    }
}