package share.logging.log4j;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import share.base.SystemHelper;
import share.base.extensions.SystemExtension;

import java.io.File;
import java.util.Arrays;

import static share.base.extensions.StringExtension.isNullOrWhiteSpace;

public final class Log4j {
    static String pattern = "%d{yyyy/MM/dd HH:mm:ss,SSS} %-4r [%t] [%-5p] %c %x - %m%n";
    static final String LOG_LEVEL = "livelihood.log.level";
    static String SERVICE_NAME = null;

    public static void setConsoleLog() {
        PatternLayout patternLayout = new PatternLayout(pattern);
        ConsoleAppender ca = new ConsoleAppender(patternLayout);
        ca.setEncoding("UTF-8");

        String logLevel = SystemHelper.getProperty(LOG_LEVEL);
        if (isDebug()) {
            ca.setThreshold(Level.ALL);
            Logger.getRootLogger().setLevel(Level.ALL);
        } else {
            ca.setThreshold(isNullOrWhiteSpace(logLevel) ? Level.INFO : Level.toLevel(logLevel));
            Logger.getRootLogger().setLevel(isNullOrWhiteSpace(logLevel) ? Level.INFO : Level.toLevel(logLevel));
        }

        Logger.getRootLogger().addAppender(ca);
    }

    public static void removeAppender(String name) {
        Logger.getRootLogger().removeAppender(name);
    }

    public static void setFileLog() {
        SERVICE_NAME = SystemExtension.getServiceName();
        //文件info配置
        PatternLayout jsonLayoutInfo = new PatternLayout(pattern);
        MyDailyRollingFileAppender rfaInfo = null;
        try {
            rfaInfo = new MyDailyRollingFileAppender(jsonLayoutInfo, getLogPath(SERVICE_NAME, "current.log"), "yyyy-MM-dd");
            rfaInfo.setName("rfaInfo");
            String logLevel = SystemHelper.getProperty(LOG_LEVEL);
            rfaInfo.setThreshold(isNullOrWhiteSpace(logLevel) ? Level.INFO : Level.toLevel(logLevel));
            rfaInfo.setEncoding("UTF-8");
        } catch (Exception e) {
        }
        Logger.getRootLogger().addAppender(rfaInfo);
//        }
        //文件error配置
        PatternLayout jsonLayoutError = new PatternLayout(pattern);
        MyDailyRollingFileAppender rfaError = null;
        try {
            rfaError = new MyDailyRollingFileAppender(jsonLayoutError, getLogPath(SERVICE_NAME, "error.log"), "yyyy-MM-dd");
            rfaError.setName("rfaError");
            rfaError.setThreshold(Level.ERROR);
            rfaError.setEncoding("UTF-8");
        } catch (Exception e) {
        }
        Logger.getRootLogger().addAppender(rfaError);
    }

    private static File logBaseFile = null;

    public static String getLogPath(String serviceName, String logFileName) {
        if (SystemExtension.isWindowsOS()) {
            File[] files = File.listRoots();
            if (null == logBaseFile) {
                if (1 == files.length)
                    logBaseFile = files[0];
                else
                    logBaseFile = files[1];
            }
            String logPath = new File(logBaseFile, String.format("logs/%s/%s", serviceName, logFileName)).getPath();
            System.out.println("==============================" + logPath + "==================================");
            return logPath;
        } else {
            String logPath = new File(String.format("/home/zyintel/logs/%s/%s", serviceName, logFileName)).getPath();
            System.out.println("==============================" + logPath + "==================================");
            return logPath;
        }
    }

    static final String DEBUG_KEY = "livelihood.log.debug";
    static final String[] DEBUG_KEYS = new String[]{"1", "true"};

    public static boolean isDebug() {
        String isDebug = SystemHelper.getProperty(DEBUG_KEY);
        if (isNullOrWhiteSpace(isDebug)) return false;
        if (Arrays.asList(DEBUG_KEYS).contains(isDebug.toLowerCase())) return true;
        return false;
    }
}
