package com.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResultsPage extends PageObject {
    public void isOnResultsPage() {
        waitFor(ExpectedConditions.invisibilityOf(getDriver().findElement(By.id("interstitial-overlay"))));
        assert getDriver().findElement(By.className("your-results-with-filters")).isDisplayed();
    }
}
