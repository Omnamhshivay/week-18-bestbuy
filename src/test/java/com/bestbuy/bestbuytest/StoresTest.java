package com.bestbuy.bestbuytest;

import com.bestbuy.bestbuy.StoreSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoresTest extends TestBase {
    static String name = "Robinhood" + TestUtils.getRandomValue();
    static String type = "smallbox";
    static String address = "105 County grove B2";
    static String address2 = "";
    static String city = "Roseville";
    static String state = "NY";
    static String zip = "34523";
    static double lat = 56.87364;
    static double lng = -67.563452;
    static String hours = "Mon: 10-9; Tue 10-9;";
    static HashMap<Object, Object> services;
    static int storeId;
    @Steps
    StoreSteps storeSteps;

    @Title("This will get all store list")
    @Test
    public void test001() {
        ValidatableResponse response = storeSteps.getStores().log().all().statusCode(200);

    }

    @Title("This will create new store")
    @Test
    public void test002() {
        HashMap<Object, Object> services = new HashMap<>();
        ValidatableResponse response = storeSteps.createStore(name, type, address, address2, city, state, zip, (int) lat, (int) lng, hours, services);
        response.log().all().statusCode(201);
        storeId = response.log().all().extract().path("id");
        System.out.println(storeId);

    }

    @Title("This will get store by id")
    @Test
    public void test003(){
        HashMap<String,?> storeMap=storeSteps.getStoreById(storeId);
        Assert.assertThat(storeMap, hasValue(name));
    }
    @Title("Update the product information and verify updated information")
    @Test
    public void test004(){
        HashMap<Object, Object> services = new HashMap<>();
        name = name + "_updated";
        storeSteps.updateStore(storeId,name,type,address,address2,city,state,zip, (int) lat, (int) lng,hours,services).log().all().statusCode(200);
        HashMap<String,Object> storeMap = storeSteps.getStoreById(storeId);
        Assert.assertThat(storeMap,hasValue(name));
    }
    @Title("Delete store")
    @Test
    public void test005(){
        storeSteps.deleteStoreByID(storeId).statusCode(200);
        storeSteps.getStoreId(storeId).statusCode(200);
    }
}

