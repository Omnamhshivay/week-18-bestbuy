package com.bestbuy.bestbuytest;


import com.bestbuy.bestbuy.ProductSteps;
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
public class ProductsTest extends TestBase {
    static String name = "IT" + TestUtils.getRandomValue();
    static String type = "Automation" + TestUtils.getRandomValue();
    static int price = 100;
    static int shipping = 20;
    static String upc = "Api Testing";
    static String description = "Testing";
    static String manufacturer = "cucumber Testing";
    static String model = "Junit Testing";
    static String url = "TestNG Testing";
    static String image = "Api Testing";
    static  int productId;

    @Steps
    ProductSteps productSteps;

    @Title("This will give total products")
    @Test
    public void test001(){
        productSteps.getAllProduct().log().all().statusCode(200);

    }
    @Title("This will add product in to product list")
    @Test
    public void test002(){
        ValidatableResponse response =productSteps.postProduct(name,type,price,shipping,upc,description,manufacturer,model,url,image);
       productId= response.log().all().extract().path("id");
    }
    @Title("verify if Product is created")
    @Test
    public void test003(){

        HashMap<String,Object> productMap=productSteps.getProductInformationById(productId);
        Assert.assertThat(productMap, hasValue(name));
       /* productId=(int)productMap.get("id");
        System.out.println(productId);
*/
    }
    @Title("Update the product information and verify updated information")
    @Test
    public void test004(){
        name = name + "_updated";
        productSteps.updateProduct(productId,name,type,price,shipping,upc,description,manufacturer,model,url,image).log().all().statusCode(200);
        HashMap<String,Object> productMap = productSteps.getProductInformationById(productId);
        Assert.assertThat(productMap,hasValue(name));

    }
    @Title("Delete the product and verify if the product is deleted")
    @Test
    public void test005(){
        productSteps.deleteProductById(productId).log().all().statusCode(200);
        productSteps.getProductById(productId).log().all().statusCode(404);

    }


    }

