package com.stepdefinitions;

import com.steps.EndUserSteps;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import net.thucydides.core.annotations.Steps;

public class SearchResultsStepdefs implements En {
    @Steps
    EndUserSteps user;

    public SearchResultsStepdefs() {
        Then("^user should be on results page$", () -> {
            user.isUserOnResultsPage();
        });
    }
}
