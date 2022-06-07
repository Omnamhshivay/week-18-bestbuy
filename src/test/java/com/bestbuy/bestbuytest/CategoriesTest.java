package com.bestbuy.bestbuytest;

import com.bestbuy.bestbuy.CategoriesSteps;
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
    public class CategoriesTest extends TestBase {
        static String name = "bhav" + TestUtils.getRandomValue();
        static String id = "abc" + TestUtils.getRandomValue();
        //String categoriesId;

        @Steps
        CategoriesSteps categoriesSteps;

        @Title("This will get all the categories")
        @Test
        public void test001() {
            ValidatableResponse response = categoriesSteps.getCategories().log().all().statusCode(200);
        }

        @Title("This will create new categories")
        @Test
        public void test002() {
            ValidatableResponse response = categoriesSteps.createCategoriesList(name, id);
            response.log().all().statusCode(201);
       /* categoriesId = response.log().all().extract().path("id");
        System.out.println(categoriesId);*/
        }

        @Title("Verify if categories is created")
        @Test
        public void test003() {
            HashMap<String, String> categoriesMap = categoriesSteps.getCategoriesInfoById(id);
            Assert.assertThat(categoriesMap, hasValue(name));
        }

        @Title("Update the information and verify if it is updated")
        @Test
        public void test004() {
            name = name + "_updated";
            categoriesSteps.updateCategoriesById(id,name).log().all().statusCode(200);
            HashMap<String,String>catergoriesMap=categoriesSteps.getCategoriesInfoById(id);
            Assert.assertThat(catergoriesMap,hasValue(name));
        }
        @Title("Deleting categories and verify if it is deleted")
        @Test
        public void test005(){
            categoriesSteps.deleteCategoriesById(id).log().all();
            categoriesSteps.getCategoriesById(id).log().all();
        }
}
