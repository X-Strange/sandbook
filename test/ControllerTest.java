import org.junit.*;
import java.util.*;

import play.test.*;
import play.libs.F.*;
import play.mvc.Results.Status;
import play.mvc.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import controllers.Application;
import static org.fluentlenium.core.filter.FilterConstructor.*;


public class ControllerTest {
    @Test
    public void IndexTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Application app = new Application();
                Status result = (Status) app.index();
                int expectedResult = Results.ok().getWrappedSimpleResult().header().status();
                assertThat(result.getWrappedSimpleResult().header().status() == expectedResult); 
            }
        });
    }


    @Test
    public void DashboardTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Application app = new Application();
                Status result = (Status) app.dashboard();
                int expectedResult = Results.ok().getWrappedSimpleResult().header().status();
                assertThat(result.getWrappedSimpleResult().header().status() == expectedResult); 
            }
        });
    }


    @Test
    public void GetIndexTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Application app = new Application();
                Status result = (Status) app.getIndex();
                int expectedResult = Results.ok().getWrappedSimpleResult().header().status();
                assertThat(result.getWrappedSimpleResult().header().status() == expectedResult); 
            }
        });
    }


    @Test
    public void UserProfileTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Application app = new Application();
                Status result = (Status) app.userprofile();
                int expectedResult = Results.ok().getWrappedSimpleResult().header().status();
                assertThat(result.getWrappedSimpleResult().header().status() == expectedResult); 
            }
        });
    }


    @Test
    public void StyleCollectionTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Application app = new Application();
                Status result = (Status) app.stylecollection("retailer", "brand", "value");
                int expectedResult = Results.ok().getWrappedSimpleResult().header().status();
                assertThat(result.getWrappedSimpleResult().header().status() == expectedResult); 
            }
        });
    }


    @Test
    public void RetailersTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Application app = new Application();
                Status result = (Status) app.retailers();
                int expectedResult = Results.ok().getWrappedSimpleResult().header().status();
                assertThat(result.getWrappedSimpleResult().header().status() == expectedResult); 
            }
        });
    }


    @Test
    public void OverlapTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Application app = new Application();
                Status result = (Status) app.overlaps();
                int expectedResult = Results.ok().getWrappedSimpleResult().header().status();
                assertThat(result.getWrappedSimpleResult().header().status() == expectedResult); 
            }
        });
    }


    @Test
    public void StylesTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Application app = new Application();
                Status result = (Status) app.styles(1);
                int expectedResult = Results.ok().getWrappedSimpleResult().header().status();
                assertThat(result.getWrappedSimpleResult().header().status() == expectedResult); 
            }
        });
    }

}