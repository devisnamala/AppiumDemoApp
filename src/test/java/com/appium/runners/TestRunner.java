package com.appium.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
    @CucumberOptions(
            features = "src/test/resources/features",
            glue = {"com.appium.stepdefinitions"},
            plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
            tags="@smoke",
            monochrome = true
    )
    public class TestRunner {
    }
