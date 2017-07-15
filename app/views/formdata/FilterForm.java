package views.formdata;

import models.Brand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkmaec0 on 4/30/14.
 */
public class FilterForm {

    public List<String> brands = new ArrayList<String>();

    /**
     * Required for form instantiation.
     */
    public FilterForm() {
    }

    public FilterForm(List<Brand> brands) {
        for (Brand brand : brands) {
            this.brands.add(brand.getName());
        }

    }

}
