package com.stepdefinitions;

import com.steps.EndUserSteps;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Map;

public class PreferencesStepdefs implements En {
    @Steps
    EndUserSteps user;

    public PreferencesStepdefs() {
        And("^user provides the preferences as:$", (DataTable preferences) -> {
            Map<String,String> billInfo = preferences.asMap(String.class,String.class);
            user.entersPreferences(billInfo);
        });

        When("^user confirms the terms and conditions$", () -> {
            user.confirmsTnC();
        });

        And("^compares the prices$", () -> {
            user.goToPrices();
        });
        Then("^user should see error \"([^\"]*)\"$", (String error) -> {
            user.verifyError(error);
        });
    }
}
