package com.appium.manager;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ScreenshotHelper {

    public static String captureScreenshot(AppiumDriver driver, String testName) {
        // Take screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationPath = System.getProperty("user.dir") + "/src/test/resources/results/screenshots/" + testName + ".png";

        try {
            // Create directories if they don't exist
            Files.createDirectories(Paths.get(destinationPath).getParent());
            // Copy the screenshot to the destination path
            Files.copy(screenshot.toPath(), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }

        return destinationPath;
    }
}