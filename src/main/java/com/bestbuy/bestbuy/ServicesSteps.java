package com.bestbuy.bestbuy;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ServicesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ServicesSteps {
    @Step
    public ValidatableResponse getServices(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then()
                .statusCode(200);
    }
    @Step("creating services with name : {0}")
    public ValidatableResponse createServices(String name){
        ServicesPojo servicesPojo=new ServicesPojo();
        servicesPojo.setName(name);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(servicesPojo)
                .when()
                .post(EndPoints.GET_ALL_SERVICES)
                .then();
    }
    @Step("Getting services information with id : {0}")
    public HashMap<String,String> getServicesInformationById(int servicesId){
        return SerenityRest.given().log().all()
                .pathParam("servicesId",servicesId)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICES_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
    }
    @Step("Update services with name : {0}")
    public ValidatableResponse updateServices(int servicesId,String name){
        ServicesPojo servicesPojo=new ServicesPojo();
        servicesPojo.setName(name);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("servicesId",servicesId)
                .body(servicesPojo)
                .when()
                .patch(EndPoints.UPDATE_SERVICES_BY_ID)
                .then();
    }
    @Step("Deleting services information with servicesId : {0}")
    public ValidatableResponse deleteServices(int servicesId){
        return SerenityRest.given().log().all()
                .pathParam("servicesId",servicesId)
                .when()
                .delete(EndPoints.DELETE_SERVICES_BY_ID)
                .then();

    }
    @Step("Getting services information with services Id : {0}")
    public ValidatableResponse getServicesById(int servicesId){
        return SerenityRest.given().log().all()
                .pathParam("servicesId",servicesId)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICES_BY_ID)
                .then();
    }
}
