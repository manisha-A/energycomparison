package com.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.jruby.RubyProcess;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CTMEnergyHomePage extends PageObject {

    private final String DUAL = "dual";
    private final String GAS = "gas";
    private final String ELEC = "electricity";
    private final String CTM_URL = "https://energy.comparethemarket.com/energy/v2/?AFFCLIE=TSTT";
    private final String FIND_POSTCODE = "find-postcode";

    @FindBy(id = "your-postcode")
    private WebElementFacade postcodeInput;

    @FindBy(id = "find-postcode")
    private WebElementFacade findPostcode;

    @FindBy(css = "label[for='have-bill']")
    private WebElementFacade haveBill;

    @FindBy(css = "label[for='compare-what-gas']")
    private WebElementFacade compareGas;

    @FindBy(id = "goto-your-supplier-details")
    private WebElementFacade next;

    @FindBy(css = "label[for='compare-what-electricity']")
    private WebElementFacade compareElectricity;

    @FindBy(css = "input[id='compare-what-both']")
    private WebElement compareBoth;

    @FindBy(id = "gas-energy-suppliers-question")
    private WebElement gasSuppliers;

    @FindBy(id = "elec-energy-suppliers-question")
    private WebElement elecSuppliers;

    @FindBy(css = "label[for='no-bill']")
    private WebElementFacade noBill;

    public void navigate() {
        getDriver().get(CTM_URL);
    }

    public void findsPostcode() {
        findPostcode.click();
        waitFor(ExpectedConditions.invisibilityOf(getDriver().findElement(By.id(FIND_POSTCODE))));
    }

    public void entersPostcode(String postcode) {
        postcodeInput.sendKeys(postcode);
    }

    public void userSelectsHasBill() {
        haveBill.click();
    }

    public void compare(String compareOption) {
        switch (compareOption){
            case "Gas & Electricity":
                compareBoth.click();
                break;
            case "Gas only":
                compareGas.click();
                break;
            case "Electricity only":
                compareElectricity.click();
                break;
        }
    }

    public void submitSupplier() {
        next.click();
    }

    public void selectGasSupplier(String type,String supplier) {
        List<WebElement> radios = null;
        switch (type){
            case DUAL:
                Select select = new Select(getDriver().findElement(By.id("sel")));
                select.selectByVisibleText("Breeze");
                break;
            case GAS:
                radios = gasSuppliers.findElements(By.tagName("label"));
                for(WebElement radio:radios){
                    if(radio.getText().contains(supplier)){
                        radio.click();
                        break;
                    }
                }
                break;
            case ELEC:
                radios = elecSuppliers.findElements(By.tagName("label"));
                for(WebElement radio:radios){
                    if(radio.getText().contains(supplier)){
                        radio.click();
                        break;
                    }
                }
                break;
        }
    }

    public void after() {
        getDriver().quit();
    }

    public void userSelectsNoBill() {
        noBill.click();
    }
}
