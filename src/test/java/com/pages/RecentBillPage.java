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

    public void goToEnergyPage(){
        goToEnergy.click();
    }

    public void enterBillingInfo(String type, Map<String, String> billInfo) {
        Set<String> keys = billInfo.keySet();
        switch (type){
            case "Electricity":
                for (String key : keys) {
                    switch (key) {
                        case "Tarrif type":
                            electricityTarrifDropdown.selectByVisibleText(billInfo.get("Tarrif type"));
                            break;
                        case "Payment type":
                            electricityPaymentDropdown.selectByVisibleText(billInfo.get("Payment type"));
                            break;
                        case "Current Annually usage":
                            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.id("electricity-usage-question")));
                            String[] usage = billInfo.get("Current Annually usage").split(",");
                            if (usage[0].contains("gbp")) {
                                WebElement pound = getDriver().findElement(By.cssSelector("input[id='poundSpend']"));
                                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", pound);
                                pound.click();
                            }
                            electricitySpendAmount.sendKeys(usage[1]);
                            electricitySpendDropdown.selectByVisibleText(usage[2]);
                            break;
                        case "Gas main source":
                            final List<WebElement> radios = getDriver().findElements(By.name("gas-main-source"));

                            for (WebElement radio : radios) {
                                if (radio.getText().equals(billInfo.get("Gas main source"))) {
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
                        case "Tarrif type":
                            gasTarrifDropdown.selectByVisibleText(billInfo.get("Tarrif type"));
                            break;
                        case "Payment type":
                            gasPaymentDropdown.selectByVisibleText(billInfo.get("Payment type"));
                            break;
                        case "Current Annually usage":
                            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.id("gas-type-of-bill-question")));
                            String[] usage = billInfo.get("Current Annually usage").split(",");
                            if (usage[0].contains("gbp")) {
                                getDriver().findElement(By.id("poundSpend")).click();
                            }
                            gasUsage.sendKeys(usage[1]);
                            gasUsageTypeDropdown.selectByVisibleText(usage[2]);
                            break;
                        case "Gas main source":
                            final List<WebElement> radios = getDriver().findElements(By.name("gas-main-source"));

                            for (WebElement radio : radios) {
                                if (radio.getText().equals(billInfo.get("Gas main source"))) {
                                    radio.click();
                                }
                            }
                            break;
                    }
                }
                break;
        }
    }
}
