package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;
import play.test.*;

public class DashboardPage extends FluentPage {
    private String url;


    public DashboardPage(WebDriver webDriver, int portNumber) {
        super(webDriver);
        this.url = "http://localhost:" + portNumber + "/dashboard?";
    }
    

    @Override
    public String getUrl() {
        return url;
    }
}