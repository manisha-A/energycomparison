package com.stepdefinitions;

import com.steps.EndUserSteps;
import cucumber.api.DataTable;
import cucumber.api.java8.En;
import net.thucydides.core.annotations.Steps;

import java.util.Map;

public class RecentBillStepDefs implements En {
    @Steps
    EndUserSteps user;

    public RecentBillStepDefs() {
        When("^user provides the recent \"([^\"]*)\" bill information as:$", (String type,DataTable recentBillInfo) -> {
            Map<String,String> billInfo = recentBillInfo.asMap(String.class,String.class);
            user.entersRecentBillInfo(type,billInfo);
        });

        And("^user submits billing info$", () -> {
            user.submitsBillingInfo();
        });

    }
}
