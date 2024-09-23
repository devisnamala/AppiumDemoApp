package com.appium.manager;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import java.io.File;
public class ServerManager {
    private static AppiumDriverLocalService service;

    public static void startAppiumServer(){
        if(service==null){
            int port = 4723;
            service= new AppiumServiceBuilder()
                    .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                    .usingPort(port)
                    .build();
            service.start();
            System.out.println("Appium Server started on: " + service.getUrl());
        }
    }

    public static void stopAppiumServer() {
        if (service != null) {
            service.stop();
            System.out.println("Appium Server stopped.");
        }
    }

    public static String getAppiumServerUrl() {
        return service.getUrl().toString();
    }


}
