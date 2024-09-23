package com.appium.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private static Properties props = new Properties();

    public Properties getProps() throws IOException {
        InputStream is = null;
        String propsFileName = "config.properties";

        if (props.isEmpty()) {
            try {
                is = getClass().getClassLoader().getResourceAsStream(propsFileName);
                if (is != null) {
                    props.load(is);
                } else {
                    throw new RuntimeException("Cannot find 'config.properties' file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }
        return props;
    }
}
