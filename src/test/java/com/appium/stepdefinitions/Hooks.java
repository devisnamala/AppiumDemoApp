package com.appium.stepdefinitions;

import com.appium.manager.Capabilitiesmanager;
import com.appium.manager.ServerManager;
import com.appium.utils.ScreenshotUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.appium.utils.ScreenshotUtil.cleanAllureResults;

public class Hooks {

    private static AppiumDriver driver;
    private static Capabilitiesmanager capabilitiesManager = new Capabilitiesmanager();

    @BeforeAll
    public static void globalSetUp() {
        cleanAllureResults();
        ServerManager.startAppiumServer();
    }

    @Before
    public void setUp(Scenario scenario) throws MalformedURLException {
        String platformName = "Android"; // or "iOS", dynamically set if needed
        Object capabilities = capabilitiesManager.getCapabilities(platformName);

        if (capabilities instanceof UiAutomator2Options) {
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/"), (UiAutomator2Options) capabilities);
        } else if (capabilities instanceof DesiredCapabilities) {
            driver = new IOSDriver(new URL("http://0.0.0.0:4723/"), (DesiredCapabilities) capabilities);
        } else {
            throw new IllegalArgumentException("Unsupported capabilities type.");
        }

        System.out.println("Driver initialized for platform: " + platformName);
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            String screenshotDirectory = "src/test/resources/results/screenshots";
            Files.createDirectories(Paths.get(screenshotDirectory));

            String sanitizedScenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9.-]", "_");
            String screenshotPath = screenshotDirectory + "/" + sanitizedScenarioName + ".png";

            ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
            screenshotUtil.captureScreenshot(screenshotPath);

            if (Files.exists(Paths.get(screenshotPath))) {
                byte[] screenshotBytes = Files.readAllBytes(Paths.get(screenshotPath));
                Allure.addAttachment("Screenshot",
                        "image/png",
                        new ByteArrayInputStream(screenshotBytes),
                        sanitizedScenarioName + ".png"
                );
            } else {
                System.out.println("Screenshot file not found: " + screenshotPath);
            }
        }

        if (driver != null) {
            driver.quit();
            System.out.println("Driver quit.");
        }
    }

    @AfterAll
    public static void globalTearDown() {
        ServerManager.stopAppiumServer();
    }

    public static AppiumDriver getDriver() {
        return driver;
    }
}
