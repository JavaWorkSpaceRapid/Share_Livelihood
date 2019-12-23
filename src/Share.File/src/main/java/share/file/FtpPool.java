package share.file;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FtpPool
{

    private static final Logger LOGGER = LoggerFactory.getLogger(FtpPool.class);

    FtpClientFactory factory;

    private final GenericObjectPool<FTPClient> internalPool;

    /**
     * 初始化连接池
     *
     * @param factory
     */
    public FtpPool(@Autowired FtpClientFactory factory)
    {
        this.factory = factory;
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(FtpInfos.maxTotal);
        poolConfig.setMinIdle(FtpInfos.minIdle);
        poolConfig.setMaxIdle(FtpInfos.maxIdle);
        poolConfig.setMaxWaitMillis(FtpInfos.maxWaitMillis);
        this.internalPool = new GenericObjectPool<FTPClient>(factory, poolConfig);
    }

    /**
     * 从连接池中取连接
     *
     * @return
     */
    public FTPClient getFTPClient()
    {
        try
        {
            LOGGER.info("从Ftp连接池中取连接......");
            return internalPool.borrowObject();
        }
        catch (Exception e)
        {
            LOGGER.error("从Ftp连接池中取连接失败！", e);
            return null;
        }
    }

    /**
     * 将链接归还到连接池
     *
     * @param ftpClient
     */
    public void returnFTPClient(FTPClient ftpClient)
    {
        try
        {
            LOGGER.info("将链接归还到Ftp连接池......");
            internalPool.returnObject(ftpClient);
        }
        catch (Exception e)
        {
            LOGGER.error("将链接归还到Ftp连接池失败！", e);
        }
    }

    /**
     * 销毁池子
     */
    public void destroy()
    {
        try
        {
            LOGGER.info("销毁Ftp连接池......");
            internalPool.close();
        }
        catch (Exception e)
        {
            LOGGER.error("销毁Ftp连接池失败！", e);
        }
    }
}
