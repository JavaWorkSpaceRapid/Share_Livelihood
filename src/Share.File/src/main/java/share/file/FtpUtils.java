package share.file;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import share.base.exceptions.BaseException;
import share.base.extensions.StringExtension;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class FtpUtils
{

    @Autowired
    FtpPool pool;

    /**
     * 上传指定文件到默认位置
     *
     * @param fileName
     * @param inputStream
     * @return
     */
    public boolean upload(String fileName, InputStream inputStream)
    {
        return this.upload(fileName, null, inputStream);
    }

    /**
     * 上传指定文件到指定位置
     *
     * @param fileName
     * @param currentPath
     * @param inputStream
     * @return
     */
    public boolean upload(String fileName, String currentPath, InputStream inputStream)
    {
        FTPClient ftpClient = pool.getFTPClient();
        try
        {
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode()))
            {
                pool.returnFTPClient(ftpClient);
                return false;
            }
            if (!StringExtension.isNullOrWhiteSpace(currentPath))
            {
                if (!ftpClient.changeWorkingDirectory(currentPath))
                {
                    String[] split = currentPath.split("/");
                    for (int i = 0; i < split.length; i++)
                    {
                        if (ftpClient.changeWorkingDirectory(split[i]))
                        {
                            continue;
                        }
                        else
                        {
                            ftpClient.makeDirectory(split[i]);
                            ftpClient.changeWorkingDirectory(split[i]);
                        }
                    }
                }
            }
            if (!ftpClient.storeFile(new String(fileName.getBytes("utf-8"), "iso-8859-1"), inputStream))
            {
                return false;
            }
            inputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            pool.returnFTPClient(ftpClient);
        }
        return true;
    }

    /**
     * 与默认位置下载指定文件
     *
     * @param fileName
     * @param ops
     * @return
     */
    public boolean download(String fileName, OutputStream ops)
    {
        return this.download(fileName, null, ops);
    }

    /**
     * 与指定位置下载指定文件
     *
     * @param fileName
     * @param currentPath
     * @param ops
     * @return
     */
    public boolean download(String fileName, String currentPath, OutputStream ops)
    {
        FTPClient ftpClient = pool.getFTPClient();
        try
        {
            /** 判断服务是否就绪 */
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode()))
            {
                pool.returnFTPClient(ftpClient);
                return false;
            }
            /** 转移到FTP服务器目录，并判断目录是否存在 */
            if (!StringExtension.isNullOrWhiteSpace(currentPath) && !ftpClient.changeWorkingDirectory(currentPath))
            {
                throw new BaseException("对应目录不存在");
            }
            /** 获取目录下的文件 */
            FTPFile[] fs = ftpClient.listFiles();
            boolean $fileExists = false;
            for (FTPFile ff : fs)
            {
                /** 比对文件名是否存在 */
                String f = new String(ff.getName().getBytes("ISO-8859-1"), "utf-8");
                if (f.equals(fileName))
                {
                    $fileExists = true;
                    ftpClient.retrieveFile(ff.getName(), ops);
                    ops.close();
                    break;
                }
            }
            if (!$fileExists)
            {
                throw new BaseException("对应文件不存在！");
            }
        }
        catch (IOException e)
        {
            throw new BaseException(e.getMessage());
        }
        finally
        {
            pool.returnFTPClient(ftpClient);
        }
        return true;
    }

    /**
     * 与默认位置删除指定文件
     *
     * @param fileName
     * @return
     */
    public boolean delete(String fileName)
    {
        return this.delete(fileName, null);
    }

    /**
     * 与指定位置删除指定文件
     *
     * @param fileName
     * @return
     */
    public boolean delete(String fileName, String currentPath)
    {
        FTPClient ftpClient = pool.getFTPClient();
        try
        {
            /** 判断服务是否就绪 */
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode()))
            {
                pool.returnFTPClient(ftpClient);
                return false;
            }
            /** 转移到FTP服务器目录，并判断目录是否存在 */
            if (!StringExtension.isNullOrWhiteSpace(currentPath) && !ftpClient.changeWorkingDirectory(currentPath))
            {
                throw new BaseException("对应目录不存在");
            }
            /** 获取目录下的文件 */
            FTPFile[] fs = ftpClient.listFiles();
            boolean $fileExists = false;
            for (FTPFile ff : fs)
            {
                /** 比对文件名是否存在 */
                String f = new String(ff.getName().getBytes("ISO-8859-1"), "utf-8");
                if (f.equals(fileName))
                {
                    $fileExists = true;
                    ftpClient.deleteFile(ff.getName());
                    break;
                }
            }
            if (!$fileExists)
            {
                throw new BaseException("对应文件不存在！");
            }
        }
        catch (IOException e)
        {
            throw new BaseException(e.getMessage());
        }
        finally
        {
            pool.returnFTPClient(ftpClient);
        }
        return true;
    }


}
