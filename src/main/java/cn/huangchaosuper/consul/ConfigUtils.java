package cn.huangchaosuper.consul;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by chaohuang on 07/02/2017.
 */
public class ConfigUtils {

    private static Properties props;

    public static void initProperties(String propertiesFileName) {
        props = getProperties(propertiesFileName);
    }

    public static String getProperty(String key,String defaultValue){
        if (null != props && AgentUtils.isNotBlank(key)) {
            return props.getProperty(key,defaultValue);
        }
        return defaultValue;
    }

    public static String getProperty(String key) {
        if (null != props && AgentUtils.isNotBlank(key)) {
            return props.getProperty(key);
        }
        return null;
    }

    private static Properties getProperties(String propertiesFileName) {
        Properties properties = new Properties(System.getProperties());
        //if (AgentUtils.isBlank(propertiesFileName)) return properties;
        InputStream input = null;
        String propPath = "consul-agent.properties";
        try {
            input = ConfigUtils.class.getClassLoader().getResourceAsStream(
                    propPath);
            properties.load(input);
        } catch (Exception e) {
            System.err.println("the properties not found @" + propPath);
        } finally {
            AgentUtils.closeQuietly(input);
        }
        if (AgentUtils.isNotBlank(propertiesFileName)) {
            try {
                input = new FileInputStream(new File(propertiesFileName));
                properties.load(input);
            } catch (Exception e) {
                System.err.println(e);
            } finally {
                AgentUtils.closeQuietly(input);
            }
        }
        return properties;
    }

}