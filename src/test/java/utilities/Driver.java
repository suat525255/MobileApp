package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Driver {

    static AppiumDriver driver;

    private Driver() {
    }

    public static AppiumDriver getDriver() {

        if (driver == null) {
            String phone = System.getProperty("phone") != null ? System.getProperty("phone") : ConfigurationReader.get("phone");
            switch (phone) {
                case "emulator":
                    UiAutomator2Options options = new UiAutomator2Options();
                    options.setPlatformName("Android");
                    options.setPlatformVersion(ConfigurationReader.get("versionEmulator"));
                    options.setDeviceName(ConfigurationReader.get("deviceNameEmulator"));
                    options.setCapability("appPackage", ConfigurationReader.get("appPackage"));
                    options.setCapability("appActivity", ConfigurationReader.get("appActivity"));
                    options.setAutomationName("UiAutomator2");
                    options.setCapability("autoGrantPermissions", "true");
                    options.setCapability("autoAcceptAlerts", "true");

                    try {
                        driver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), options);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            switch (phone) {
                case "RealAndroid":
                    UiAutomator2Options options = new UiAutomator2Options();
                    options.setPlatformName("Android");
                    options.setPlatformVersion(ConfigurationReader.get("versionRealPhone"));
                    options.setDeviceName(ConfigurationReader.get("deviceNameRealPhone"));
                    options.setUdid(ConfigurationReader.get("samsung#udid"));
                    options.setCapability("appPackage", ConfigurationReader.get("appPackage"));
                    options.setCapability("appActivity", ConfigurationReader.get("appActivity"));
                    options.setAutomationName("UiAutomator2");
                    options.setCapability("autoGrantPermissions", "true");
                    options.setCapability("autoAcceptAlerts", "true");

                    try {
                        driver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), options);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            switch (phone) {
                case "SimulatorIphone":
                    UiAutomator2Options options = new UiAutomator2Options();

                    options.setPlatformName("IOS");
                    options.setPlatformVersion(ConfigurationReader.get("versionSimulator"));
                    options.setDeviceName(ConfigurationReader.get("deviceNameSimulator"));
                    options.setUdid("8A5F8C22-CE21-4625-8CA5-8E3934FB3210"); // iphone 11
                    options.setAvdLaunchTimeout(Duration.ofSeconds(50));
                    options.setAutomationName(AutomationName.IOS_XCUI_TEST);
                    options.setApp("/Users/suataydin/Library/Developer/Xcode/DerivedData/WebDriverAgent-bhgahdawmthikkavrbmkoclgjmhy/Build/Products/Debug-iphonesimulator/akakce.ipa");
                    options.setCapability("xcodeOrgId", "XFCM9Y23R4");
                    options.setCapability("autoGrantPermissions", "true");
                    options.setCapability("autoAcceptAlerts", "true");

                    try {
                        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    break;

            }
            switch (phone) {
                case "RealIphone":
                    UiAutomator2Options options = new UiAutomator2Options();

                    options.setPlatformName("IOS");
                    options.setPlatformVersion(ConfigurationReader.get("versionSimulator"));
                    options.setDeviceName(ConfigurationReader.get("deviceNameSimulator"));
                    options.setUdid("00008101-0016488E1105001E"); // suat iphone 12

                    options.setAutomationName(AutomationName.IOS_XCUI_TEST);
                    options.setCapability("xcodeSigningId", "iPhone Developer");
                    options.setCapability("bundleId", "com.akakce.akakce.ios");
                    options.setCapability("noReset", "false");
                    options.setCapability("full-reset", "true");

                    try {
                        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
            }
        }
        return driver;
    }
    public static void closeDriver () {

        driver.quit();
        driver = null;
    }

}