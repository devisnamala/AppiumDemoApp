package com.appium.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {

    private AppiumDriver driver;

    public ScreenshotUtil(AppiumDriver driver) {
        this.driver = driver;
    }

    public void captureScreenshot(String filePath) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenshot.toPath(), Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        public static void cleanAllureResults() {
            File allureResultsDir = new File("allure-results"); // Adjust the path as necessary
            if (allureResultsDir.exists()) {
                for (File file : allureResultsDir.listFiles()) {
                    file.delete();
                }
            }
        }


}
