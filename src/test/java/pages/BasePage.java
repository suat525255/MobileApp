package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import java.time.Duration;


public abstract class BasePage {




    public BasePage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void clickText(String text){
        Driver.getDriver().findElement(By.xpath("//*[@text='" + text + "']")).click();
    }
}