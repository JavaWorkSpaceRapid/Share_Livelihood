package share.file;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import share.base.exceptions.BaseException;

import java.io.IOException;

@Component
public class FtpClientFactory implements PooledObjectFactory<FTPClient>
{

    private static final Logger LOGGER = LoggerFactory.getLogger(FtpClientFactory.class);

    /**
     * 创建连接到池中
     *
     * @return
     */
    @Override
    public PooledObject<FTPClient> makeObject()
    {
        /** 创建客户端实例 */
        FTPClient ftpClient = new FTPClient();
        return new DefaultPooledObject<>(ftpClient);
    }

    /**
     * 销毁连接，当连接池空闲数量达到上限时，调用此方法销毁连接
     */
    @Override
    public void destroyObject(PooledObject<FTPClient> pooledObject)
    {
        FTPClient ftpClient = pooledObject.getObject();
        try
        {
            ftpClient.logout();
            if (ftpClient.isConnected())
            {
                ftpClient.disconnect();
            }
        }
        catch (IOException e)
        {
            LOGGER.error("Could not disconnect from server.", e);
            throw new BaseException("Could not disconnect from server.");
        }
    }

    /**
     * 链接状态检查
     *
     * @param pooledObject
     * @return
     */
    @Override
    public boolean validateObject(PooledObject<FTPClient> pooledObject)
    {
        FTPClient ftpClient = pooledObject.getObject();
        try
        {
            return ftpClient.sendNoOp();
        }
        catch (IOException e)
        {
            LOGGER.error("链接状态检查失败！", e);
            return false;
        }
    }

    /**
     * 初始化连接
     *
     * @param pooledObject
     * @throws Exception
     */
    @Override
    public void activateObject(PooledObject<FTPClient> pooledObject) throws Exception
    {
        FTPClient ftpClient = pooledObject.getObject();
        ftpClient.connect(FtpInfos.ftpHost, FtpInfos.ftpPort);
        ftpClient.login(FtpInfos.ftpUserName, FtpInfos.ftpPassWord);
        ftpClient.changeWorkingDirectory(FtpInfos.ftpCloudPath);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    }


    /**
     * 钝化连接，使链接变为可用状态
     *
     * @param pooledObject
     */
    @Override
    public void passivateObject(PooledObject<FTPClient> pooledObject)
    {
        FTPClient ftpClient = pooledObject.getObject();
        try
        {
            ftpClient.changeWorkingDirectory(FtpInfos.ftpCloudPath);
            ftpClient.logout();
            if (ftpClient.isConnected())
            {
                ftpClient.disconnect();
            }
        }
        catch (IOException e)
        {
            LOGGER.error("Could not disconnect from server.", e);
            throw new BaseException("Could not disconnect from server.");
        }
    }
}
