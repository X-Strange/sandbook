package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;
import play.test.*;

public class OverlapPage extends FluentPage {
    private String url;


    public OverlapPage(WebDriver webDriver, int portNumber) {
        super(webDriver);
        this.url = "http://localhost:" + portNumber + "/overlaps";
    }
    

    @Override
    public String getUrl() {
        return url;
    }
}