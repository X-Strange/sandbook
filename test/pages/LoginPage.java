package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;
import play.test.*;

public class LoginPage extends FluentPage {
    private String url;


    public LoginPage(WebDriver webDriver, int portNumber) {
        super(webDriver);
        this.url = "http://localhost:" + portNumber;
    }
    

    @Override
    public String getUrl() {
        return url;
    }


    public void clickLoginButton() {
        click(find("button[type=submit]"));
    }
}