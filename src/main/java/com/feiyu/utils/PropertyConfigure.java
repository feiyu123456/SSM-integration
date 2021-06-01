package com.feiyu.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyConfigure extends PropertyPlaceholderConfigurer {
    private static Map<String, Object> ctxPropertiesMap;
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,
                                     Properties props) throws BeansException {
        super.processProperties(beanFactory, props);

        ctxPropertiesMap = new HashMap<String, Object>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }
    public static Object getProperty(String name) {
        return ctxPropertiesMap.get(name);
    }
    public static void putAll(Map<String,Object> configMap) {
        ctxPropertiesMap.putAll(configMap);
    }
    public static void put(String key, String value) {
        ctxPropertiesMap.put(key,value);
    }
}
