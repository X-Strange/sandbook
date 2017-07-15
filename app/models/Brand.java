package models;

import com.github.cleverage.elasticsearch.Index;
import com.github.cleverage.elasticsearch.Indexable;
import com.github.cleverage.elasticsearch.annotations.IndexType;
import views.formdata.FilterForm;

import java.util.*;

/**
 * Created by tkmaec0 on 4/30/14.
 */
@IndexType(name = "brand")
public class Brand {

    private String name;
    private ArrayList values;

    public Brand() {
    }

    public Brand(String name, ArrayList values) {
        this.name = name;
        this.values = values;
    }

    public ArrayList getValues() {
        return values;
    }

    public void setValues(ArrayList values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Map<String, Boolean> makeBrandsMap(FilterForm filterForm) {
        Map<String, Boolean> brandMap = new HashMap<String, Boolean>();
        for (Brand brand : allBrands) {
            brandMap.put(brand.getName(), (filterForm != null && filterForm.brands.contains(brand.getName())));
        }
        return brandMap;
    }

    public static Brand findBrand(String brandName) {
        for (Brand brand : allBrands) {
            if (brandName.equals(brand.getName())) {
                return brand;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String val = toJson();
        return String.format("[\'%s\'%s]", this.name, val);
    }

    private String toJson() {
        String result = "";
        for (int i = 0; i < this.values.size(); i++) {
            result = result + ", " + this.values.get(i); //Integer.toString(this.values[i]);
        }
        return result;
    }


    /**
     * Fake a database of brands.
     */

    private static List<Brand> allBrands = new ArrayList<Brand>();


    /** Instantiate the fake database of hobbies. */

    static {

        ArrayList arrayAdidas = new ArrayList();
        arrayAdidas.add(25);
        arrayAdidas.add(50);
        arrayAdidas.add(78);
        arrayAdidas.add(44);
        arrayAdidas.add(35);
        arrayAdidas.add(64);

        ArrayList arrayAsics = new ArrayList();
        arrayAsics.add(78);
        arrayAsics.add(70);
        arrayAsics.add(40);
        arrayAsics.add(51);
        arrayAsics.add(65);
        arrayAsics.add(35);

        ArrayList arrayConverse = new ArrayList();
        arrayConverse.add(65);
        arrayConverse.add(35);
        arrayConverse.add(40);
        arrayConverse.add(51);
        arrayConverse.add(65);
        arrayConverse.add(37);


        ArrayList arrayCrocs = new ArrayList();
        arrayCrocs.add(40);
        arrayCrocs.add(55);
        arrayCrocs.add(35);
        arrayCrocs.add(51);
        arrayCrocs.add(65);
        arrayCrocs.add(35);

        ArrayList arrayDockers = new ArrayList();
        arrayDockers.add(30);
        arrayDockers.add(35);
        arrayDockers.add(48);
        arrayDockers.add(39);
        arrayDockers.add(65);
        arrayDockers.add(51);

        ArrayList arrayKeds = new ArrayList();
        arrayKeds.add(50);
        arrayKeds.add(45);
        arrayKeds.add(45);
        arrayKeds.add(51);
        arrayKeds.add(65);
        arrayKeds.add(35);

        ArrayList arrayNewBalance = new ArrayList();
        arrayNewBalance.add(50);
        arrayNewBalance.add(45);
        arrayNewBalance.add(32);
        arrayNewBalance.add(75);
        arrayNewBalance.add(54);
        arrayNewBalance.add(61);

        ArrayList arrayNike = new ArrayList();
        arrayNike.add(50);
        arrayNike.add(73);
        arrayNike.add(55);
        arrayNike.add(65);
        arrayNike.add(33);
        arrayNike.add(48);

        ArrayList arrayNunnBush = new ArrayList();
        arrayNunnBush.add(40);
        arrayNunnBush.add(70);
        arrayNunnBush.add(49);
        arrayNunnBush.add(51);
        arrayNunnBush.add(34);
        arrayNunnBush.add(54);

        ArrayList arraySkechers = new ArrayList();
        arraySkechers.add(60);
        arraySkechers.add(30);
        arraySkechers.add(53);
        arraySkechers.add(51);
        arraySkechers.add(65);
        arraySkechers.add(35);


        allBrands.add(new Brand("Adidas", arrayAdidas));
        allBrands.add(new Brand("Asics", arrayAsics));
        allBrands.add(new Brand("Converse", arrayConverse));
        allBrands.add(new Brand("Crocs", arrayCrocs));
        allBrands.add(new Brand("Dockers", arrayDockers));
        allBrands.add(new Brand("Keds", arrayKeds));
        allBrands.add(new Brand("New Balance", arrayNewBalance));
        allBrands.add(new Brand("Nike", arrayNike));
        allBrands.add(new Brand("Nunn Bush", arrayNunnBush));
        allBrands.add(new Brand("Skechers", arraySkechers));
    }

/*    public static Finder<Brand> find = new Finder<Brand>(Brand.class);

    @Override
    public Map toIndex() {
        HashMap map = new HashMap();
        map.put("name", name);
        map.put("values", values);
        return map;
    }

    @Override
    public Indexable fromIndex(Map map) {
        if (map == null) {
            return this;
        }

        this.name = (String) map.get("name");
        this.values = (ArrayList) map.get("values");
        return this;
    }*/
}
