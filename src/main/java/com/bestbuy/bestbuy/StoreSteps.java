package com.bestbuy.bestbuy;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoreSteps {

    @Step
    public ValidatableResponse getStores(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then().statusCode(200);
    }
    @Step("Create store with: name{0},type{1},address{2},address2{3},city{4},stata{5},zip{6},lat{7},lng{8},hours{9},services{10}")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city, String state, String zip, int lat, int lng, String hours, HashMap<Object,Object> services){
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);
        return SerenityRest.given().log().all()
                .contentType("application/json")
                .body(storePojo)
                .when()
                .post(EndPoints.GET_ALL_STORES)
                .then();
    }
    @Step
    public HashMap<String, Object> getStoreById(int storeId) {

        return SerenityRest.given().log().all()
                .pathParam("storesId",storeId)
                .when()
                .get(EndPoints.GET_SINGLE_STORES_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
    }
    @Step
    public ValidatableResponse updateStore(int storeId,String name, String type, String address, String address2, String city, String state, String zip, int lat, int lng, String hours, HashMap<Object,Object> services){
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);
        return SerenityRest.given().log().all()
                .contentType("application/json")
                .pathParam("storesId",storeId)
                .body(storePojo)
                .when()
                .put(EndPoints.UPDATE_STORES_BY_ID).then();
    }
    @Step("Delete stores by ID")
    public ValidatableResponse deleteStoreByID(int storesId){
        return SerenityRest.given().log().all()
                .pathParam("storesId",storesId)
                .when()
                .delete(EndPoints.DELETE_STORES_BY_ID)
                .then();
    }
    @Step("getting product information with product id : {0}")
    public ValidatableResponse getStoreId(int storesId){
        return  SerenityRest.given().log().all()
                .pathParam("storesId",storesId)
                .when()
                .get(EndPoints.GET_SINGLE_STORES_BY_ID)
                .then();
    }

}
