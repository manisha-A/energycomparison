package com.steps;

import com.pages.CTMEnergyHomePage;
import com.pages.PreferencesPage;
import com.pages.RecentBillPage;
import net.thucydides.core.annotations.Step;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {
    CTMEnergyHomePage energyhomepage;
    RecentBillPage billpage;
    PreferencesPage preferencespage;

    @Step
    public void navigatesToCTMEnergyHomePage() {
        energyhomepage.navigate();
    }

    @Step
    public void findsPostcode(String postcode) {
        energyhomepage.entersPostcode(postcode);
        energyhomepage.findsPostcode();
    }

    @Step
    public void hasBill() {
        energyhomepage.userSelectsHasBill();
    }

    @Step
    public void optsToCompare(String compareWhat) {
        energyhomepage.compare(compareWhat);
    }

    @Step
    public void submitsSupplier() {
        energyhomepage.submitSupplier();
    }

    @Step
    public void selectsGasSupplier(String type,String supplier) {
        energyhomepage.selectGasSupplier(type,supplier);
    }

    @Step
    public void entersRecentBillInfo(String type,Map<String, String> billInfo) {
        billpage.enterBillingInfo(type, billInfo);
        billpage.goToEnergyPage();
    }

    @Step
    public void submitsBillingInfo() {
        billpage.goToEnergyPage();
    }

    @Step
    public void entersPreferences(Map<String, String> info) {
        preferencespage.enterPreferences(info);
    }

    @Step
    public void confirmsTnC() {
        preferencespage.confirmsTerms();
    }

    @Step
    public void goToPrices() {
        preferencespage.checkPrices();
    }

    @Step
    public void verifyError(String error) {
        preferencespage.verifyErrorIsShown(error);
    }
}