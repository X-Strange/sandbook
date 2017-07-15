package pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;
import play.test.*;

public class ComparisonPage extends FluentPage {
    private String url;


    public ComparisonPage(WebDriver webDriver, int portNumber) {
        super(webDriver);
        this.url = "http://localhost:" + portNumber + "/comparison";
    }
    

    @Override
    public String getUrl() {
        return url;
    }


    public void ClickShowTableButton() {
    	click(find("button[type=button]"));
    }


    public void ClickCheckboxAdidas() {
        click(find("input[id=Adidas]"));        
    }


    public void ClickApplyBrandFilterButton() {
        click(find("button[id=submit]"));
    }
}