import org.junit.*;

import java.util.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import models.Retailer;


public class RetailerModelTest {
    
    @Test
    public void NameTest() {
    	Retailer retailer = new Retailer();
    	retailer.setName("retailer example");
    	assertThat("retailer example" == retailer.getName()); 
    }


    @Test
    public void DescriptionTest() {
    	Retailer retailer = new Retailer();
    	retailer.setDescription("retailer example description");
    	assertThat("retailer example description" == retailer.getDescription()); 
    }
}