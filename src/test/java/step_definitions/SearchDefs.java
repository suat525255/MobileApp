package step_definitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.SearchPage;
import utilities.Driver;
import utilities.MobileUtils;

public class SearchDefs {

    SearchPage searchPage=new SearchPage(Driver.getDriver());

    @And("click filter button")
    public void clickFilterButton() {
        searchPage.filterButton.click();
    }

    @And("select filter {string}")
    public void selectFilter(String text) {
        searchPage.clickText(text);
    }

    @And("click filter apply button")
    public void clickFilterApplyButton() {
        searchPage.applyFilter.click();
    }

    @And("sort {string}")
    public void sort(String sortText) {
        searchPage.sort(sortText);
    }

    @And("select product index {int}")
    public void selectProductIndex(int count) {
        searchPage.selectProductIndex(count);
    }

    @And("click product Detail")
    public void clickProductDetail() {
        searchPage.productDetailButton.click();
    }

    @Then("verify open product detail page")
    public void verifyOpenProductDetailPage() {
        searchPage.verifyOpenProductDetailPage();
    }

    @Then("verify price sort {string}")
    public void verifyPriceSort(String sortType) {
        searchPage.verifySortPrice(sortType);
        MobileUtils.waitFor(1);
        MobileUtils.scrollFullScreenUP();
        MobileUtils.waitFor(3);
        MobileUtils.scrollFullScreenUP();
        MobileUtils.waitFor(1);

    }
}
