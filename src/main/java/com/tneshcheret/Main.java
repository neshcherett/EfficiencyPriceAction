package com.tneshcheret;

//import com.tneshcheret.dao.ProductDao;

import com.tneshcheret.dao.*;
import com.tneshcheret.entity.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        // расчет эффективности проведения ценовой акции

//        ProductDao productDAO = new ProductDao();
//        productDAO.deleteByID(6);
//        System.out.println(productDAO.getById(2));
//        Product aquarte = new Product();
//        aquarte.setName("Jaffa 1 tomato");
//        aquarte.setPrice(22.64);
//        aquarte.setPrimeCost(10.23);
//        aquarte.setId(4);
//        aquarte.setBrandPackage(new BrandPackage(1, "Jaffa 1"));
//        productDAO.create(aquarte);
//        List<Product> products = productDAO.findAll();
//        System.out.println(products.toString());

//        RetailChainDao retailChainDao = new RetailChainDao();
//        retailChainDao.deleteByID(4);
//        System.out.println(retailChainDao.getById(5));
//        RetailChain billa = new RetailChain();
//        billa.setRegion(new Region(1, "Kyiv"));
//        billa.setName("Billa");
//        billa.setCommercialTerms(0.11);
//        billa.setId(6);
//        retailChainDao.create(billa);
//        List<RetailChain> retailChains = retailChainDao.findAll();
//        System.out.println(retailChains.toString());

//        BrandPackageDao brandPackageDao = new BrandPackageDao();
//        List<BrandPackage> brandPackages= brandPackageDao.findAll();
//        System.out.println(brandPackages);
//        BrandPackage jaffa = new BrandPackage();
//        jaffa.setName("Jaffa 0.2");
//        jaffa.setId(5);
//        brandPackageDao.create(jaffa);
//        System.out.println(brandPackageDao.getById(4));
//        brandPackageDao.deleteById(4);

//        RegionDao regionDao = new RegionDao();
//        List<Region> regions = regionDao.findAll();
//        System.out.println(regions);
//        System.out.println(regionDao.getById(3));
//        Region lviv = new Region();
//        lviv.setName("Lviv");
//        regionDao.create(lviv);
//        regionDao.deleteById(5);

//        LogisticDao logisticDao = new LogisticDao();
//        System.out.println(logisticDao.findAll());
//        System.out.println(logisticDao.getById(5));
//        Logistic logistic = new Logistic();
//        logistic.setRegion(new Region(3, "Dnipro"));
//        logistic.setBrandPackage(new BrandPackage(5, "Jaffa 0.2"));
//        logistic.setDeliveryCost(0.4);
//        logisticDao.create(logistic);
//        logisticDao.deleteById(7);

//        RequestActionDao requestActionDao = new RequestActionDao();
//        System.out.println(requestActionDao.findAll());
//        System.out.println(requestActionDao.getById(2));
//        RequestAction requestAction = new RequestAction();
//        requestAction.setMethodGrantingDiscount(MethodGrantingDiscount.OFINVOICE);
//        int year = 2021;
//        int month = 8;
//        int dayS = 12;
//        int dayE = 26;
//        requestAction.setStartDate(LocalDate.of(year, month, dayS));
//        requestAction.setEndDate(LocalDate.of(year, month, dayE));
//        requestAction.setProduct(aquarte);
//        requestAction.setRetailChain(billa);
//        requestAction.setCostAction(1000);
//        requestAction.setDiscount(0.12);
//        requestActionDao.create(requestAction);
//        requestActionDao.deleteById(3);

//        SeasonalityDao seasonalityDao = new SeasonalityDao();
//        System.out.println(seasonalityDao.findAll());
//        System.out.println(seasonalityDao.getById(1));
//        seasonalityDao.deleteById(2);
//        Seasonality seasonality = new Seasonality();
//        seasonality.setMonthNumber(5);
//        seasonality.setCoefficient(0.6);
//        seasonality.setBrandPackage(jaffa);
//        seasonalityDao.create(seasonality);

//        UserDao userDao = new UserDao();
//        System.out.println(userDao.findAll());
//        System.out.println(userDao.getById(2));
//        userDao.deleteById(2);
//        User user = new User();
//        user.setUserRole(UserRole.ADMIN);
//        user.setPassword("456");
//        user.setLogin("ANALYST2");
//        user.setId(2);
//        userDao.create(user);
    }
}
