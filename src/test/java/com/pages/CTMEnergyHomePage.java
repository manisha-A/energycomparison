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
    @FindBy(id = "your-postcode")
    private WebElementFacade postcodeInput;

    @FindBy(id = "find-postcode")
    private WebElementFacade findPostcode;

    @FindBy(id = "have-bill")
    private WebElementFacade haveBill;

    @FindBy(css = "input[value='Gas']")
    private WebElementFacade compareGas;

    @FindBy(id = "goto-your-supplier-details")
    private WebElementFacade next;

    @FindBy(id = "compare-what-electricity")
    private WebElementFacade compareElectricity;

    @FindBy(css = "input[id='compare-what-both']")
    private WebElement compareBoth;

    public void navigate() {
        getDriver().get("https://energy.comparethemarket.com/energy/v2/?AFFCLIE=TSTT");
    }

    public void findsPostcode() {
        findPostcode.click();
        waitFor(ExpectedConditions.invisibilityOf(getDriver().findElement(By.id("find-postcode"))));
    }

    public void entersPostcode(String postcode) {
        postcodeInput.sendKeys(postcode);
    }

    public void userSelectsHasBill() {
        final List<WebElement> radios = getDriver().findElements(By.name("bill-nobill"));

        for (WebElement radio : radios) {
            if (radio.getText().contains("Yes")) {
                radio.click();
            }
        }
    }

    public void compare(String compareOption) {
        switch (compareOption){
            case "Gas & Electricity":
                compareBoth.click();
                break;
        }
//        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.cssSelector("input[id='compare-what-gas']")));
//        waitFor(ExpectedConditions.visibilityOf(getDriver().findElement(By.cssSelector("input[id='compare-what-gas']"))));
//        getDriver().findElement(By.cssSelector("input[id='compare-what-gas']")).click();
//        final List<WebElement> radios = getDriver().findElements(By.name("compare-what-energy"));
//
//        for (WebElement radio : radios) {
//            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", radio);
//            System.out.println("**");
//            System.out.println(radio.getAttribute("value"));
//            System.out.println("**");
//            if (radio.getAttribute("value").equals(compareOption)) {
//                System.out.println("**");
//                System.out.println("inside");
//                System.out.println("**");
//                radio.getAttribute("Selected");
//                Actions a1 = new Actions(getDriver());
//                a1.moveToElement(radio)
//                        .click()
//                        .build()
//                        .perform();
//            }
//        }
    }

    public void submitSupplier() {
        next.click();
    }

    private void hoverAndClick(WebElementFacade element){
        Actions a1 = new Actions(getDriver());
        a1.moveToElement(element)
                .click()
                .build()
                .perform();
    }

    public void selectGasSupplier(String type,String supplier) {
        List<WebElement> radios = null;
        switch (type){
            case "dual":
                Select select = new Select(getDriver().findElement(By.id("sel")));
                select.selectByVisibleText("Breeze");
//                WebElement sse = getDriver().findElement(By.cssSelector("input[id='dual-top-six-sse']"));
//                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", sse);
//                sse.click();
                break;
            case "gas":
                radios = getDriver().findElements(By.name("gas-top-six"));
                break;
        }

//        for (WebElement radio : radios) {
//            System.out.println("********");
//            System.out.println(radio.getText());
//            System.out.println(radio.getAttribute("value"));
//            System.out.println("********");
//            if (radio.getAttribute("value").equals(supplier)) {
//                System.out.println("********");
//                System.out.println(radio.getText());
//                System.out.println("********");
//                radio.click();
//            }
//        }
    }
}
