package com.bestbuy.bestbuy;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.CategoriesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class CategoriesSteps {
    @Step
    public ValidatableResponse getCategories() {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then()
                .statusCode(200);
    }

    @Step("creating categories with name : {0}, id : {1}")
    public ValidatableResponse createCategoriesList(String name, String id) {
        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(categoriesPojo)
                .when()
                .post(EndPoints.GET_ALL_CATEGORIES)
                .then();
    }

    @Step("Getting categories information by id : {0}")
    public HashMap<String, String> getCategoriesInfoById(String id) {
        return SerenityRest.given().log().all()
                .pathParam("categoriesId", id)
                .when()
                .get(EndPoints.GET_SINGLE_CATEGORIES_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
    }

    @Step("Updating the information with Id : {0},name : {1}, id : {2}")
    public ValidatableResponse updateCategoriesById(String id, String name) {
        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("categoriesId",id)
                .body(categoriesPojo)
                .when()
                .patch(EndPoints.UPDATE_CATEGORIES_BY_ID)
                .then();

    }

    @Step("Deleting categories with caterogies id : {0}")
    public ValidatableResponse deleteCategoriesById(String categoriesId) {
        return SerenityRest.given().log().all()
                .pathParam("categoriesId", categoriesId)
                .when()
                .delete(EndPoints.DELETE_CATEGORIES_BY_ID)
                .then();
    }

    @Step("Getting categories information by id : {0}")
    public ValidatableResponse getCategoriesById(String categoriesId) {
        return SerenityRest.given().log().all()
                .pathParam("categoriesId", categoriesId)
                .when()
                .get(EndPoints.GET_SINGLE_CATEGORIES_BY_ID)
                .then();
    }
}
