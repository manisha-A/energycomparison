package com.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Map;
import java.util.Set;

public class PreferencesPage extends PageObject {
    @FindBy(id = "email-submit")
    private WebElementFacade goToPrices;

    @FindBy( id= "terms]")
    private WebElementFacade termsConditions;

    @FindBy( id= "Email")
    private WebElement email;

    @FindBy( id= "same-supplier-question")
    private WebElement supplierQues;

    @FindBy( css= "input[id='same-supplier-no']")
    private WebElement sameSupplierNo;

    @FindBy( css= "input[id='pre-select-variable']")
    private WebElement variableTarrif;

    @FindBy( css= "input[id='pre-select-payment-monthly']")
    private WebElement monthlyDirectDebit;

    @FindBy( id= "your-details-view-status")
    private WebElement viewStatus;

    public void enterPreferences(Map<String, String> info) {
        Set<String> keys = info.keySet();
        for(String key:keys){
            switch (key){
                case "Interested Tarrif":
                    if(info.get("Interested Tarrif").contains("Variable tarrif")){
                        variableTarrif.click();
                    }
                    break;
                case "Payment Type":
                    if(info.get("Payment Type").contains("Monthly direct debit")){
                        monthlyDirectDebit.click();
                    }
                    break;
            }
        }
    }

    public void confirmsTerms() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.id("terms")));
        getDriver().findElement(By.id("terms-label")).click();
    }

    public void checkPrices() {
        getDriver().findElement(By.id("email-submit")).click();
    }

    public void verifyErrorIsShown(String error) {
        assert viewStatus.getText().contains(error);
    }
}
