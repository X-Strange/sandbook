package models;

import views.formdata.FilterForm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkmaec0 on 5/5/14.
 */
public class FilterRequest {

    private List<Brand> brands = new ArrayList<Brand>();

    public FilterRequest() {
    }

    public String toString() {
        return String.format("%s", this.brands);
    }

    public static FilterRequest makeInstance(FilterForm filterForm) {
        FilterRequest filterRequest = new FilterRequest();
        for (String brand : filterForm.brands) {
            filterRequest.brands.add(Brand.findBrand(brand));
        }
        return filterRequest;
    }
}
