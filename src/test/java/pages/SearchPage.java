package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import utilities.MobileUtils;

import java.util.List;

public class SearchPage extends BasePage{

    @AndroidFindBy(id = "filterArea" )
    public WebElement filterButton;

    @AndroidFindBy(id = "applyFilterTextView" )
    public WebElement applyFilter;

    @AndroidFindBy(id = "sortText" )
    public WebElement sortButton;

    @AndroidFindBy(id = "cellContainer" )
    public List <WebElement> productList;

    @AndroidFindBy(id = "detailBtnTextView" )
    public WebElement productDetailButton;

    @AndroidFindBy(xpath = "//*[@text='Satıcıya Git']" )
    public WebElement sallerButton;



    public SearchPage(AppiumDriver driver) {
        super(driver);
    }


    public void sort(String sortText){
        sortButton.click();
        MobileUtils.waitFor(1);
        clickText(sortText);
    }

    public void selectProductIndex(int count){
        MobileUtils.waitFor(1);
        MobileUtils.scrollToElementAndStop(count);
        MobileUtils.waitFor(1);
        MobileUtils.scrollFullScreenDOWN();
    }

    public void verifyOpenProductDetailPage(){
        MobileUtils.waitFor(1);
        MobileUtils.scrollFullScreenDOWN();
        MobileUtils.waitFor(1);
        Assert.assertTrue(sallerButton.isDisplayed());
    }

}
