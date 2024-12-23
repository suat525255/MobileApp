package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.HomePage;
import utilities.Driver;
import utilities.MobileUtils;

public class HomeDefs {

    HomePage homePage=new HomePage(Driver.getDriver());

    @Given("click {string} button")
    public void clickButton(String text) {
        MobileUtils.waitForVisibility(homePage.guestButton,8);
        homePage.clickText(text);
    }

    @When("search {string}")
    public void search(String product) {
        homePage.searchSelectProduct(product);
    }
}
