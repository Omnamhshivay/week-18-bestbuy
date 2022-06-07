package com.bestbuy.bestbuytest;

import com.bestbuy.bestbuy.UtilitiesSteps;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

public class UtilitiesTest extends TestBase {
    @Steps
    UtilitiesSteps utilitiesSteps;

    @Title("This will get the utilities version")
    @Test
    public void test001(){
        ValidatableResponse response=utilitiesSteps.getUtilitiesVersion().log().all().statusCode(200);


    }

    @Title("This will get the utilities healthcheck")
    @Test
    public void test002(){
        utilitiesSteps.getHealthCheck().log().all().statusCode(200);
    }
}
