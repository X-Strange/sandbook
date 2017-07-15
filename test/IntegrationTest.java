import org.junit.*;

import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;
import pages.*;

public class IntegrationTest {

    /**
     * Verify if the Dashboard Page is rendered correctly
     */
    @Test
    public void DashboardPageRender() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                DashboardPage dashboardPage = new DashboardPage(browser.getDriver(), 3333);
                assertThat(browser.pageSource().contains("Welcome to the Dashboard"));
            }
        });
    }


    /**
     * Verify if the Dashboard Page is rendered correctly
     */
    @Test
    public void UserPageRender() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                UserPage userPage = new UserPage(browser.getDriver(), 3333);
                assertThat(browser.pageSource().contains("Username"));
            }
        });
    }


    /**
     * Verify if the Login Page is rendered correctly
     */
    @Test
    public void LoginPageRender() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                LoginPage loginPage = new LoginPage(browser.getDriver(), 3333);
                assertThat(browser.pageSource().contains("Login"));
            }
        });
    }


    /**
     * Verify if the Comparison Page is rendered correctly
     */
    @Test
    public void ComparisonPageRender() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                ComparisonPage comparisonPage = new ComparisonPage(browser.getDriver(), 3333);
                assertThat(browser.pageSource().contains("Shoe Brands"));
            }
        });
    }


    /**
     * Verify if the Product Details Page is rendered correctly
     */
    @Test
    public void ProductDetailsPageRender() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                ProductDetailsPage productDetailsPage = new ProductDetailsPage(browser.getDriver(), 3333);
                assertThat(browser.pageSource().contains("Product Description"));
            }
        });
    }


    /**
     * Verify if the Assortment Page is rendered correctly
     */
    @Test
    public void AssortmentPageRender() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                AssortmentPage assortmentPage = new AssortmentPage(browser.getDriver(), 3333);
                assertThat(browser.pageSource().contains("Overlap"));
            }
        });
    }


    /**
     * Verify if the Retailer Brand Page is rendered correctly
     */
    @Test
    public void RetailerBrandPageRender() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                RetailerBrandPage retailerBrandPage = new RetailerBrandPage(browser.getDriver(), 3333);
                assertThat(browser.pageSource().contains("Product Data"));
            }
        });
    }


    /**
     * Verify if the Styles Page is rendered correctly
     */
    @Test
    public void StylesPageRender() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                StylesPage stylesPage = new StylesPage(browser.getDriver(), 3333);
                assertThat(browser.pageSource().contains("Product Name"));
            }
        });
    }


    /**
     * Verify if the Overlap Page is rendered correctly
     */
    @Test
    public void OverlapPageRender() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                OverlapPage overlapPage = new OverlapPage(browser.getDriver(), 3333);
                assertThat(browser.pageSource().contains("Overlap"));
            }
        });
    }


    /**************************************************************************************************************/


    /**
     * Verify if the navigation from Login to Dashboard is correctly
     */
    @Test
    public void LoginToDashboardNavigation() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                LoginPage loginPage = new LoginPage(browser.getDriver(), 3333);
                loginPage.clickLoginButton();
                assertThat(browser.pageSource().contains("Welcome to the Dashboard"));
            }
        });
    }


    /**
     * Verify if the navigation from Dashboard to Comparison is correctly
     */
    @Test
    public void DashboardToComparisonNavigation() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                DashboardPage dashboardPage = new DashboardPage(browser.getDriver(), 3333);
                browser.click("Comparison");
                assertThat(browser.pageSource().contains("Shoe Brands"));
            }
        });
    }


    /**
     * Verify if the navigation from Dashboard to Comparison is correctly
     */
    @Test
    public void StylesPageNavigation() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                StylesPage stylesPage = new StylesPage(browser.getDriver(), 3333);
                browser.click("Comparison");
                assertThat(browser.pageSource().contains("Shoe Brands"));
            }
        });
    }


    /**************************************************************************************************************/


    /**
     * Verify if the table is shown correctly.
     */
    @Test
    public void ShowTableButtonWorks() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                ComparisonPage comparisonPage = new ComparisonPage(browser.getDriver(), 3333);
                comparisonPage.ClickShowTableButton();
                assertThat(browser.pageSource().contains("Brand"));
            }
        });
    }


    /**
     * Verify if the table is shown correctly.
     */
    @Test
    public void FilterBrandsCheckboxes() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                ComparisonPage comparisonPage = new ComparisonPage(browser.getDriver(), 3333);
                comparisonPage.ClickCheckboxAdidas();
                comparisonPage.ClickApplyBrandFilterButton();
                assertThat(browser.pageSource().contains("Brands"));
            }
        });
    }
}