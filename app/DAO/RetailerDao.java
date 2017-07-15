package DAO;

import models.Retailer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan-leyba on 11/06/14.
 */
public class RetailerDao implements IRetailerDAO {
    @Override
    public List<Retailer> getAllRetailers() {
        List<Retailer> retailerList = new ArrayList<>();

        Retailer kohls = new Retailer();
        kohls.setName("Kohl's");
        kohls.setDescription("Expect Great Things");
        kohls.setImgUrl("/assets/images/kohls-logo.png");
        kohls.setSiteURL("http://www.kohls.com/");

        retailerList.add(kohls);

        Retailer sears = new Retailer();
        sears.setName("Sears");
        sears.setDescription("Shop Your Way");
        sears.setImgUrl("/assets/images/sears-logo.png");
        sears.setSiteURL("http://www.sears.com/");

        retailerList.add(sears);

        Retailer macys = new Retailer();
        macys.setName("Macy's");
        macys.setDescription("The magic of");
        macys.setImgUrl("/assets/images/macys-logo.png");
        macys.setSiteURL("http://www.macys.com/");

        retailerList.add(macys);

        return retailerList;
    }
}
