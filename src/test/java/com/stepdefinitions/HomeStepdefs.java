package com.stepdefinitions;


import com.steps.EndUserSteps;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import net.thucydides.core.annotations.Steps;

public class HomeStepdefs implements En {
    @Steps
    EndUserSteps user;

    @After
    public void afterScenario(){
        user.afterScenario();
    }

    public HomeStepdefs() {
        Given("^user is on CTM energy comparison home$", () -> {
            user.navigatesToCTMEnergyHomePage();
        });

        When("^user finds the postcode \"([^\"]*)\"$", (String postcode) -> {
            user.findsPostcode(postcode);
        });

        And("^user has the bill$", () -> {
            user.hasBill();
        });

        When("^user wants to compare \"([^\"]*)\"$", (String compareWhat) -> {
            user.optsToCompare(compareWhat);
        });

        And("^user provides the \"([^\"]*)\" supplier as \"([^\"]*)\"$", (String type,String supplier) -> {
            user.selectsGasSupplier(type,supplier);
            user.submitsSupplier();
        });

        And("^user does not has the bill$", () -> {
            user.doesNotHasBill();
        });
    }
}
