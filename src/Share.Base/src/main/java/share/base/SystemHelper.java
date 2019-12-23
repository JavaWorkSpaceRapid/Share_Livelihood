package share.base;

import lycan.base.exceptions.BaseException;

import java.io.*;
import java.util.Properties;

import static lycan.base.extensions.ObjectExtension.isNotNull;

public class SystemHelper {
    public static final boolean DEBUG_MODE = java.lang.management.ManagementFactory.getRuntimeMXBean().
            getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;


    private static Properties props = new Properties();

    static {
        String filePath = System.getProperty("user.dir") + "/config/application.properties";
        boolean exists = new File(filePath).exists();
        if (exists) {
            try {
                InputStream in = new BufferedInputStream(new FileInputStream(filePath));
                props.load(in);
            } catch (Exception e) {
                throw new BaseException("读取外部配置文件失败！");
            }
        } else {
            InputStream resourceAsStream = SystemHelper.class.getResourceAsStream("/application.properties");
            if (isNotNull(resourceAsStream)) {
                try {
                    props.load(resourceAsStream);
                } catch (IOException e) {
                    throw new BaseException("读取内部配置文件失败！");
                }
            }
        }
    }

    /**
     * 获取属性
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return props == null ? null : props.getProperty(key);
    }

    /**
     * 获取属性
     *
     * @param key          属性key
     * @param defaultValue 属性value
     * @return
     */
    public static String getProperty(String key, String defaultValue) {
        return props == null ? null : props.getProperty(key, defaultValue);
    }

    /**
     * 获取properyies属性
     *
     * @return
     */
    public static Properties getProperties() {
        return props;
    }
}
