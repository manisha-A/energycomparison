package com.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResultsPage extends PageObject {
    private final String OVERLAY_ID = "interstitial-overlay";
    private final String RESULTS_CLASS = "your-results-with-filters";
    public void isOnResultsPage() {
        waitFor(ExpectedConditions.invisibilityOf(getDriver().findElement(By.id(OVERLAY_ID))));
        assert getDriver().findElement(By.className(RESULTS_CLASS)).isDisplayed();
    }
}
