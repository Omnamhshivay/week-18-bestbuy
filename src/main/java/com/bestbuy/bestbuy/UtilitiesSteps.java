package com.bestbuy.bestbuy;

import com.bestbuy.constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UtilitiesSteps {
    @Step
    public ValidatableResponse getUtilitiesVersion(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_UTILITIES_VERSION)
                .then()
                .statusCode(200);
    }
    @Step
    public ValidatableResponse getHealthCheck(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_UTILITIES_HEALTH_CHECK)
                .then()
                .statusCode(200);
    }


}
