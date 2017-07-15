import org.junit.*;

import java.util.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import models.FilterRequest;
import views.formdata.FilterForm;


public class FilterRequestTest {
    
    @Test
    public void ToStringTest() {
    	FilterRequest filter = new FilterRequest();
    	assertThat("" == filter.toString()); 
    }


    @Test
    public void MakeInstanceTest() {
        FilterRequest filter = new FilterRequest();
        FilterForm filterform = new FilterForm();
        filter.makeInstance(filterform);
        assertThat("" == ""); 
    }
}