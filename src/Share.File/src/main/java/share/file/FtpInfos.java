package share.file;


import share.base.SystemHelper;
import share.base.extensions.StringExtension;

public class FtpInfos
{
    /**
     * ftp服务IP
     */
    public static String ftpHost;
    /**
     * ftp服务端口
     */
    public static int ftpPort;
    /**
     * ftp服务用户名
     */
    public static String ftpUserName;
    /**
     * ftp服务密码
     */
    public static String ftpPassWord;
    /**
     * ftp服务根路径
     */
    public static String ftpRootPath;
    /**
     * ftp当前服务根路径
     */
    public static String ftpCloudPath;
    /**
     * 连接池总数
     */
    public static int maxTotal;
    /**
     * 连接池最小空闲数
     */
    public static int minIdle;
    /**
     * 连接池最大空闲数
     */
    public static int maxIdle;
    /**
     * 最长等待时间
     */
    public static int maxWaitMillis;

    static
    {
        ftpHost = StringExtension.isNullOrWhiteSpace(SystemHelper.getProperty("livelihood.ftp.host")) ? "192.168.191.41" : SystemHelper.getProperty("livelihood.ftp.host");
        ftpPort = StringExtension.isNullOrWhiteSpace(SystemHelper.getProperty("livelihood.ftp.port")) ? 21 : Integer.parseInt(SystemHelper.getProperty("livelihood.ftp.host"));
        ftpUserName = StringExtension.isNullOrWhiteSpace(SystemHelper.getProperty("livelihood.ftp.user")) ? "vsftpd" : SystemHelper.getProperty("livelihood.ftp.user");
        ftpPassWord = StringExtension.isNullOrWhiteSpace(SystemHelper.getProperty("livelihood.ftp.password")) ? "zyintel" : SystemHelper.getProperty("livelihood.ftp.password");
        ftpCloudPath = StringExtension.isNullOrWhiteSpace(SystemHelper.getProperty("livelihood.ftp.cloudpath")) ? "Certificate" : SystemHelper.getProperty("livelihood.ftp.cloudpath");
        ftpRootPath = "/";
        maxTotal = StringExtension.isNullOrWhiteSpace(SystemHelper.getProperty("livelihood.ftp.maxTotal")) ? 10 : Integer.parseInt(SystemHelper.getProperty("livelihood.ftp.maxTotal"));
        minIdle = StringExtension.isNullOrWhiteSpace(SystemHelper.getProperty("livelihood.ftp.minIdel")) ? 0 : Integer.parseInt(SystemHelper.getProperty("livelihood.ftp.minIdel"));
        maxIdle = StringExtension.isNullOrWhiteSpace(SystemHelper.getProperty("livelihood.ftp.maxIdle")) ? 5 : Integer.parseInt(SystemHelper.getProperty("livelihood.ftp.maxIdle"));
        maxWaitMillis = StringExtension.isNullOrWhiteSpace(SystemHelper.getProperty("livelihood.ftp.maxWaitMillis")) ? 30000 : Integer.parseInt(SystemHelper.getProperty("livelihood.ftp.maxWaitMillis"));

    }
}
