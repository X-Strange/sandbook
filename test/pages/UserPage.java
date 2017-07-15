package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;
import play.test.*;

public class UserPage extends FluentPage {
    private String url;


    public UserPage(WebDriver webDriver, int portNumber) {
        super(webDriver);
        this.url = "http://localhost:" + portNumber + "/user";
    }
    

    @Override
    public String getUrl() {
        return url;
    }
}