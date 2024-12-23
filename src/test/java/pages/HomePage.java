package pages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utilities.Driver;
import utilities.MobileUtils;


public class HomePage extends BasePage{


    @AndroidFindBy(id = "searchTextView" )
    public WebElement searchBox;

    @AndroidFindBy(xpath = "(//*[@resource-id='com.akakce.akakce:id/searchTextView'])[2]" )
    public WebElement searchBoxInput;

    public HomePage(AppiumDriver driver) {
        super(driver);
    }


    public void searchSelectProduct(String product){
        MobileUtils.waitFor(3);
        searchBox.click();
        MobileUtils.waitFor(1);
        searchBoxInput.sendKeys(product);
        MobileUtils.waitFor(1);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
        MobileUtils.waitFor(2);
    }
}

