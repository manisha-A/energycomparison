package com.pages;

import com.Utilities.TarrifType;
import cucumber.api.java.ca.I;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.security.Key;
import java.util.Map;
import java.util.Set;

public class PreferencesPage extends PageObject {

    private final String INTERESTED_TARIFF = "Interested Tarrif";
    private final String PAYMENT_TYPE = "Payment type";
    private final String REFINE_RESULTS = "Refine Results";
    private final String EMAIL_ADDRESS = "Email address";
    private final String EMAIL_SUBMIT_ID = "email-submit";

    @FindBy(id = "email-submit")
    private WebElementFacade goToPrices;

    @FindBy( id= "terms]")
    private WebElementFacade termsConditions;

    @FindBy( id= "Email")
    private WebElement email;

    @FindBy( id= "same-supplier-question")
    private WebElement supplierQues;

    @FindBy( css= "label[for='same-supplier-no']")
    private WebElement sameSupplierNo;

    @FindBy(css = "label[for='pre-select-variable']")
    private WebElement variableTarrif;

    @FindBy( css= "label[for='pre-select-payment-monthly']")
    private WebElementFacade monthlyDirectDebit;

    @FindBy( id= "your-details-view-status")
    private WebElement viewStatus;

    public void enterPreferences(Map<String, String> info) {
        Set<String> keys = info.keySet();
        for(String key:keys){
            switch (key){
                case INTERESTED_TARIFF:
                    if(info.get(INTERESTED_TARIFF).contains(TarrifType.VAR.getTarrif())){
                        variableTarrif.click();
                    }
                    break;
                case PAYMENT_TYPE:
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.id("payment-selection-question")));
                    if(info.get(PAYMENT_TYPE).contains("Monthly direct debit")){
                        monthlyDirectDebit.click();
                    }
                    break;
                case REFINE_RESULTS:
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.id("same-supplier-question")));
                    if(info.get(REFINE_RESULTS).contains("No")){
                        getDriver().findElement(By.cssSelector("label[for='same-supplier-no']")).click();
                    };
                    break;

                case EMAIL_ADDRESS:
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.id("Email")));
                    getDriver().findElement(By.id("Email")).sendKeys(info.get(EMAIL_ADDRESS));
                    break;
            }
        }
    }

    public void confirmsTerms() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.id("terms")));
        getDriver().findElement(By.id("terms-label")).click();
    }

    public void checkPrices() {
        getDriver().findElement(By.id(EMAIL_SUBMIT_ID)).click();
    }

    public void verifyErrorIsShown(String error) {
        assert viewStatus.getText().contains(error);
    }
}
