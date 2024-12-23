package utilities;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class MobileUtils {


    private static Duration explicitWait = Duration.ofSeconds(Long.parseLong(ConfigurationReader.get("explicitWait")));

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), explicitWait);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public static void waitForVisibility(WebElement e, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public static void scrollFullScreenDOWN() {
        Dimension size = Driver.getDriver().manage().window().getSize();
        int startX = size.width / 2;
        int startY = size.height - 190;
        int endY = 50;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1500), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Driver.getDriver().perform(Collections.singletonList(swipe));
    }

    public static List<String[]> saveElementData(int targetCount) {
        List<String[]> elementData = new ArrayList<>();

        int count = 0;
        boolean targetFound = false;

        while (!targetFound) {
            List<WebElement> visibleElements = Driver.getDriver().findElements(By.id("name"));
            List<WebElement> visiblePrices = Driver.getDriver().findElements(By.id("price"));

            for (int i = 0; i < visibleElements.size(); i++) {
                String name = visibleElements.get(i).getText().trim();
                String price = (i < visiblePrices.size()) ? visiblePrices.get(i).getText().trim() : "Fiyat Yok";

                boolean isAlreadyAdded = elementData.stream().anyMatch(data -> data[0].equals(name));
                if (!isAlreadyAdded) {
                    elementData.add(new String[]{name, price});
                    count++;

                    if (elementData.size() == targetCount) {
                        targetFound = true;
                        break;
                    }
                }
            }

            if (!targetFound) {
                MobileUtils.scrollHalfScreenDOWN();
            }
            //If there are not enough products for the specified number of elements, do not enter an infinite loop.
            if (count==15) break;

        }

        return elementData;
    }

    public static void scrollFullScreenUP() {
            Dimension size = Driver.getDriver().manage().window().getSize();
            int startX = size.width / 2;
            int startY = (int) (size.height * 0.2); // Yukarıdan başlar
            int endY = (int) (size.height * 0.8);   // Aşağıya doğru gider

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence scroll = new Sequence(finger, 0);

            scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            scroll.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startX, endY));
            scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            Driver.getDriver().perform(Collections.singletonList(scroll));
        }



        public static void scrollHalfScreenDOWN() {
        Dimension size = Driver.getDriver().manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 0);

        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(1500), PointerInput.Origin.viewport(), startX, endY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Driver.getDriver().perform(Collections.singletonList(scroll));
    }



    public static void sendTextWithCoordinate(WebElement element, String txt){
        MobileUtils.clickCenterCoordinate(element);
        waitFor(2);
        element.sendKeys(txt);
    }


    public static int[] getElementCenterCoordinates(WebElement element) {
        Rectangle rect = element.getRect();
        int centerX = rect.getX() + (rect.getWidth() / 2);
        int centerY = rect.getY() + (rect.getHeight() / 2);
        System.out.println("centerX = " + centerX);
        System.out.println("centerY = " + centerY);
        return new int[]{centerX, centerY};
    }


    public static void clickCenterCoordinate(WebElement element) {

        int[] elementCenterCoordinates = MobileUtils.getElementCenterCoordinates(element);
        MobileUtils.waitFor(1);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence tapSequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),
                        elementCenterCoordinates[0], elementCenterCoordinates[1]))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Driver.getDriver().perform(Arrays.asList(tapSequence));

        MobileUtils.waitFor(1);
    }


    public static void touchEmptyElement(){
        Dimension size = Driver.getDriver().manage().window().getSize();
        int coordinate1=1;
        int coordinate2=size.height/2;
        Actions actions = new Actions(Driver.getDriver());
        Action touchAction = actions
                .moveByOffset(coordinate1, coordinate2)
                .click()
                .build();

        touchAction.perform();
        MobileUtils.waitFor(1);
    }


}
