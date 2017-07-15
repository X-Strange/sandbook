import org.junit.*;
import java.util.Date;
import java.math.BigDecimal;

import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import models.Style;


public class StyleTest {
    @Test
    public void SetStyleNameTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Style style = new Style();
                style.setStyleName("test style");
                assertThat("test style" == style.getStyleName()); 
            }
        });
    }


    @Test
    public void SetStyleDescriptionTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Style style = new Style();
                style.setStyleDescription("test description");
                assertThat("test description" == style.getStyleDescription()); 
            }
        });
    }


    @Test
    public void SetStyleRetailerTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Style style = new Style();
                style.setRetailerName("test retailer");
                assertThat("test retailer" == style.getRetailerName()); 
            }
        });
    }


    @Test
    public void SetStyleOriginalPriceTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Style style = new Style();
                style.setOriginalPrice(123.4);
                assertThat(123.4 == style.getOriginalPrice()); 
            }
        });
    }


    @Test
    public void SetStyleImageUrlTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Style style = new Style();
                style.setImgUrl("imgUrl");
                assertThat("imgUrl" == style.getImgUrl()); 
            }
        });
    }


    @Test
    public void SetStyleIdTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Style style = new Style();
                style.setStyleId("Id11");
                assertThat("Id11" == style.getStyleId()); 
            }
        });
    }


    @Test
    public void SetStyleCrawlDateTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Style style = new Style();
                String date = new Date();
                style.setCrawlerDate(date);
                assertThat(date == style.getCrawlerDate());
            }
        });
    }


    @Test
    public void SetStyleMaxPriceTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Style style = new Style();
                BigDecimal price = new BigDecimal(0.666);
                style.setMaxPrice(price);
                assertThat(price == style.getMaxPrice()); 
            }
        });
    }


    @Test
    public void SetStyleMinPriceTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Style style = new Style();
                BigDecimal price = new BigDecimal(0.666);
                style.setMinPrice(price);
                assertThat(price == style.getMinPrice()); 
            }
        });
    }


    @Test
    public void SetStyleAveragePriceTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Style style = new Style();
                BigDecimal price = new BigDecimal(0.666);
                style.setAveragePrice(price);
                assertThat(price == style.getAveragePrice()); 
            }
        });
    }


    @Test
    public void StyleToStringTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Style style = new Style();
                assertThat("Style{styleName='" + "" + '\'' + ", " +
                "styleDescription='" + "" + '\'' +
                "originalPrice='" + "" + '\''+
                "styleId='" + "" + '\'' +
                '}' == style.toString()); 
            }
        });
    }

}