package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.Driver;
import utilities.MobileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        List<String[]> result = MobileUtils.saveElementData(10);

            String[] tenthItem = result.get(9); // 10. öğe (0 tabanlı olduğu için 9)

        Driver.getDriver().findElement(By.xpath("//*[@text='" + tenthItem[0] + "']")).click();

        MobileUtils.waitFor(1);
        MobileUtils.scrollHalfScreenDOWN();
    }

    public void verifyOpenProductDetailPage(){
        MobileUtils.waitFor(1);
        MobileUtils.scrollHalfScreenDOWN();
        MobileUtils.waitForVisibility(sallerButton,1);
        Assert.assertTrue(sallerButton.isDisplayed());
    }

    public void verifySortPrice(String sortType){
        List<String[]> productData = MobileUtils.saveElementData(7);

        if(sortType.equals("En Yüksek Fiyat")){
            verifyPrices(productData, "descending");
        }else {
            verifyPrices(productData, "ascending");

        }
    }

    public static void verifyPrices(List<String[]> productData, String order) {
        List<Double> prices = new ArrayList<>();
        for (String[] product : productData) {
            String priceText = product[1].replaceAll("[^\\d.]", "");
            if (!priceText.isEmpty()) {
                prices.add(Double.parseDouble(priceText));
            }
        }

        for (int i = 0; i < prices.size() - 1; i++) {
            if (order.equals("ascending")) {
                if (prices.get(i) > prices.get(i + 1)) {
                    Assert.fail();
                    break;
                }
            } else {
                if (prices.get(i) < prices.get(i + 1)) {
                    Assert.fail();
                    break;
                }
            }
        }


    }


}
