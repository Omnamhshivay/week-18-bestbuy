package com.bestbuy.bestbuytest;

import com.bestbuy.bestbuy.ServicesSteps;
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
public class ServicesTest extends TestBase {
    static String name = "Apple services"+ TestUtils.getRandomValue();
    static int servicesId;

    @Steps
    ServicesSteps servicesSteps;

    @Title("This will get all the services list")
    @Test
    public void test001() {
        ValidatableResponse response = servicesSteps.getServices().log().all().statusCode(200);
    }

    @Title("This will create new services")
    @Test
    public void test002() {
        HashMap<String, String> services = new HashMap<>();
        ValidatableResponse response = servicesSteps.createServices(name);
        response.log().all().statusCode(201);
        servicesId = response.log().all().extract().path("id");
        System.out.println(servicesId);
    }

    @Title("Verify if the information was added to servcies ")
    @Test
    public void test003() {
        HashMap<String, String> serviceMap = new HashMap<>();
        serviceMap=servicesSteps.getServicesInformationById(servicesId);
        Assert.assertThat(serviceMap,hasValue(name));
    }
    @Title("Update the services and verify information")
    @Test
    public void test004(){
        name=name + "_updated";
        servicesSteps.updateServices(servicesId,name);
        HashMap<String,String> serviceMap=servicesSteps.getServicesInformationById(servicesId);
        Assert.assertThat(serviceMap,hasValue(name));
    }
    @Title("Delete services by Id and verify if the services is deleted")
    @Test
    public void test005(){
        servicesSteps.deleteServices(servicesId).log().all().statusCode(200);
        servicesSteps.getServicesById(servicesId).log().all().statusCode(404);
    }
}
