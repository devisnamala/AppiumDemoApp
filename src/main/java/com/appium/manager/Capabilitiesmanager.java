package com.appium.manager;

import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Capabilitiesmanager {
    private PropertyManager propertyManager = new PropertyManager();
    private Properties props;

    public Capabilitiesmanager() {
        try {
            props = propertyManager.getProps();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UiAutomator2Options getAndroidCapabilities() {
        UiAutomator2Options dc = new UiAutomator2Options();
        dc.setCapability("appium:automationName", "UiAutomator2");
        dc.setCapability("appium:MobileCapabilityType.PLATFORM_NAME", "Android");
        dc.setCapability("appium:MobileCapabilityType.DEVICE_NAME", "emulator-5554");
        dc.setCapability("appium:uiautomator2ServerLaunchTimeout", 60000);
        dc.setCapability("appium:app", "/Users/siri/Documents/Platform/platform-tools/android.wdio.native.app.v1.0.8.apk");
//        dc.setCapability("appium:appPackage", "com.saucelabs.mydemoapp.rn");
//        dc.setCapability("appium:appActivity", "com.saucelabs.mydemoapp.rn.MainActivity");
        dc.setCapability("appium:newCommandTimeout", 300);
        System.out.println("Android capabilities configured.");
        return dc;
    }

    public DesiredCapabilities getiOSCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", props.getProperty("iosdeviceName"));
        capabilities.setCapability("udid", props.getProperty("iosudid"));
        capabilities.setCapability("bundleId", props.getProperty("bundleId"));
        capabilities.setCapability("wdaLocalPort", Integer.parseInt(props.getProperty("wdaLocalPort")));
        capabilities.setCapability("usePrebuiltWDA", Boolean.parseBoolean(props.getProperty("usePrebuiltWDA")));
        capabilities.setCapability("newCommandTimeout", Integer.parseInt(props.getProperty("iosnewCommandTimeout")));

        System.out.println("iOS capabilities configured.");
        return capabilities;
    }

    public Object getCapabilities(String platformName) {
        if ("Android".equalsIgnoreCase(platformName)) {
            return getAndroidCapabilities();
        } else if ("iOS".equalsIgnoreCase(platformName)) {
            return getiOSCapabilities();
        } else {
            throw new IllegalArgumentException("Invalid platform name. Choose either 'Android' or 'iOS'.");
        }
    }
}
