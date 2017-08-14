package com.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.yecht.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecentBillPage extends PageObject {

    private final String TARRIF_TYPE = "Tarrif type";
    private final String PAYMENT_TYPE = "Payment type";
    private final String CURRENT_USAGE = "Current usage";
    private final String GAS_MAIN_SOURCE = "Gas main source";
    private final String PRE_PAYMENT_METER = "Pre Payment Meter";
    private final String ECONOMY_7_METER = "Economy 7 Meter";
    private final String ELEC_MAIN_SOURCE = "Electricity main source";

    @FindBy(css = "select[id='gas-tariff-additional-info']")
    private WebElementFacade gasTarrifDropdown;

    @FindBy(css = "select[id='electricity-tariff-additional-info']")
    private WebElementFacade electricityTarrifDropdown;

    @FindBy(css = "select[id='gas-payment-method-dropdown-link']")
    private WebElementFacade gasPaymentDropdown;

    @FindBy(css = "select[id='electricity-payment-method-dropdown-link']")
    private WebElementFacade electricityPaymentDropdown;

    @FindBy(css = "select[id='electricity-usage-dropdown']")
    private WebElementFacade electricitySpendDropdown;

    @FindBy(css = "select[id='gas-spend-dropdown']")
    private WebElementFacade gasSpendDropdown;

    @FindBy(id = "gas-spend")
    private WebElementFacade gasSpendAmount;

    @FindBy(id = "electricity-usage")
    private WebElementFacade electricitySpendAmount;

    @FindBy(id = "goto-your-energy")
    private WebElementFacade goToEnergy;

    @FindBy(id = "gas-usage")
    private WebElementFacade gasUsage;

    @FindBy(css = "select[id='type-of-Gas-bill-usage-dropdown']")
    private WebElementFacade gasUsageTypeDropdown;

    @FindBy(css = "label[for='prepayment-yes']")
    private WebElementFacade prePaymentMeterYes;

    @FindBy(css = "label[for='prepayment-no']")
    private WebElementFacade prePaymentMeterNo;

    @FindBy(css = "label[for='economy-7-yes']")
    private WebElementFacade economyMeterYes;

    @FindBy(css = "label[for='economy-7-no']")
    private WebElementFacade economyMeterNo;

    @FindBy(id = "electricity-current-spend")
    private WebElementFacade electricityCurrentSpendAmount;

    @FindBy(css = "select[id='electricity-current-spend-period']")
    private WebElementFacade electricityCurrentSpendDropdown;

    public void goToEnergyPage(){
        goToEnergy.click();
    }

    public void enterBillingInfo(String type, Map<String, String> billInfo) {
        Set<String> keys = billInfo.keySet();
        switch (type){
            case "Electricity":
                for (String key : keys) {
                    switch (key) {
                        case TARRIF_TYPE:
                            electricityTarrifDropdown.selectByVisibleText(billInfo.get(TARRIF_TYPE));
                            break;
                        case PAYMENT_TYPE:
                            electricityPaymentDropdown.selectByVisibleText(billInfo.get(PAYMENT_TYPE));
                            break;
                        case CURRENT_USAGE:
                            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.id("electricity-usage-question")));
                            String[] usage = billInfo.get(CURRENT_USAGE).split(",");
                            if (usage[0].contains("gbp")) {
                                WebElement pound = getDriver().findElement(By.cssSelector("input[id='poundSpend']"));
                                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", pound);
                                pound.click();
                            }
                            electricitySpendAmount.sendKeys(usage[1]);
                            electricitySpendDropdown.selectByVisibleText(usage[2]);
                            break;
                        case ELEC_MAIN_SOURCE:
                            final List<WebElement> radios = getDriver().findElements(By.name("elec-main-source"));

                            for (WebElement radio : radios) {
                                if (radio.getText().equals(billInfo.get(ELEC_MAIN_SOURCE))) {
                                    radio.click();
                                }
                            }
                            break;
                    }
                }
                break;

            case "Gas":
                for (String key : keys) {
                    switch (key) {
                        case TARRIF_TYPE:
                            gasTarrifDropdown.selectByVisibleText(billInfo.get(TARRIF_TYPE));
                            break;
                        case PAYMENT_TYPE:
                            gasPaymentDropdown.selectByVisibleText(billInfo.get(PAYMENT_TYPE));
                            break;
                        case CURRENT_USAGE:
                            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.id("gas-type-of-bill-question")));
                            String[] usage = billInfo.get(CURRENT_USAGE).split(",");
                            if (usage[0].contains("gbp")) {
                                getDriver().findElement(By.id("poundSpend")).click();
                            }
                            gasUsage.sendKeys(usage[1]);
                            gasUsageTypeDropdown.selectByVisibleText(usage[2]);
                            break;
                        case GAS_MAIN_SOURCE:
                            final List<WebElement> radios = getDriver().findElements(By.name("gas-main-source"));

                            for (WebElement radio : radios) {
                                if (radio.getText().equals(billInfo.get(GAS_MAIN_SOURCE))) {
                                    radio.click();
                                }
                            }
                            break;
                    }
                }
                break;

            case "nobill":
                for (String key : keys) {
                    switch (key) {
                        case PRE_PAYMENT_METER:
                            if(billInfo.get(PRE_PAYMENT_METER).contains("Yes")){
                                prePaymentMeterYes.click();
                            }
                            else{
                                prePaymentMeterNo.click();
                            }
                            break;
                        case ECONOMY_7_METER:
                            if(billInfo.get(ECONOMY_7_METER).contains("Yes")){
                                economyMeterYes.click();
                            }
                            else{
                                economyMeterNo.click();
                            }
                            break;
                        case CURRENT_USAGE:
                            String[] usage = billInfo.get(CURRENT_USAGE).split(",");
                            electricityCurrentSpendAmount.sendKeys(usage[0]);
                            electricityCurrentSpendDropdown.selectByVisibleText(usage[1]);
                            break;
                    }
                }
                break;
        }
    }
}
