import org.junit.*;
import java.util.*;

import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import models.Brand;
import static org.fluentlenium.core.filter.FilterConstructor.*;


public class BrandTest {
    @Test
    public void SetBrandValuesTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Brand brand = new Brand();
                ArrayList array = new ArrayList();
                brand.setValues(array);
                assertThat(array == brand.getValues()); 
            }
        });
    }


    @Test
    public void SetBrandNameTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                Brand brand = new Brand();
                brand.setName("brand name");
                assertThat("brand name" == brand.getName()); 
            }
        });
    }
}