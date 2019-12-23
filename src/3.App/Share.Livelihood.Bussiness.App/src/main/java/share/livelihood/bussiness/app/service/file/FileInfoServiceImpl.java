package share.livelihood.bussiness.app.service.file;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import share.base.exceptions.BaseException;
import share.base.extensions.ObjectExtension;
import share.base.extensions.StringExtension;
import share.base.result.PageList;
import share.file.FtpUtils;
import share.livelihood.bussiness.api.service.file.IFileInfoService;
import share.livelihood.bussiness.app.mapper.FileInfoMapper;
import share.livelihood.bussiness.domain.core.file.FileInfoDomain;
import share.livelihood.bussiness.domain.utils.IBaseValue;
import share.livelihood.bussiness.dto.file.FileInfoDto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Service
public class FileInfoServiceImpl implements IFileInfoService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(FileInfoServiceImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private FileInfoMapper mapper;

    @Autowired
    private FtpUtils ftpUtils;

    @Override
    public boolean addFile(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String $id = new StringBuilder(DigestUtils.md5Hex(this.getName(fileName))).append(".").append(this.getType(fileName)).toString();
        FileInfoDomain fileInfoDomain = mongoTemplate.findOne(new Query(Criteria.where(IBaseValue._id).is($id)), FileInfoDomain.class);
        if (ObjectExtension.isNotNull(fileInfoDomain)) {
            LOGGER.error("对应文件已存在，请修确认后添加！");
            throw new BaseException("对应文件已存在，请修确认后添加！");
        }
        try {
            InputStream inputStream = multipartFile.getInputStream();
//            FtpUtil.uploadFile($id, inputStream);
            ftpUtils.upload($id, inputStream);
        } catch (IOException e) {
            LOGGER.error("获取数据流失败！");
            throw new BaseException("获取数据流失败！");
        }
        FileInfoDomain fileInfoDomainNew = new FileInfoDomain($id, fileName, multipartFile.getSize(), multipartFile.getName(), multipartFile.getContentType());
        mongoTemplate.save(fileInfoDomainNew);
        return true;
    }

    @Override
    public void showImage(String id, OutputStream outputStream) {
        FileInfoDomain fileInfoDomain = mongoTemplate.findOne(new Query(Criteria.where(IBaseValue._id).is(id)), FileInfoDomain.class);
        if (ObjectExtension.isNull(fileInfoDomain)) {
            LOGGER.error("对应图片不存在！");
            throw new BaseException("对应图片不存在！");
        }
        if (!fileInfoDomain.getContentType().startsWith(IBaseValue.image)) {
            LOGGER.error("该文件非图片无法展示！");
            throw new BaseException("该文件非图片无法展示！");
        }
//        FtpUtil.downloadFile(id, outputStream);
        ftpUtils.download(id, outputStream);
    }

    @Override
    public void downloadFile(String id, OutputStream outputStream) {
        FileInfoDomain fileInfoDomain = mongoTemplate.findOne(new Query(Criteria.where(IBaseValue._id).is(id)), FileInfoDomain.class);
        if (ObjectExtension.isNull(fileInfoDomain)) {
            LOGGER.error("对应图片不存在！");
            throw new BaseException("对应图片不存在！");
        }
//        FtpUtil.downloadFile(id, outputStream);
        ftpUtils.download(id, outputStream);
    }

    @Override
    public boolean deleteFile(String id) {
        FileInfoDomain fileInfoDomain = mongoTemplate.findOne(new Query(Criteria.where(IBaseValue._id).is(id)), FileInfoDomain.class);
        if (ObjectExtension.isNull(fileInfoDomain)) {
            LOGGER.error("对应图片不存在！");
            throw new BaseException("对应图片不存在！");
        }
//        FtpUtil.deleteFile(id);
        ftpUtils.delete(id);
        mongoTemplate.findAndRemove(new Query(Criteria.where(IBaseValue._id).is(id)), FileInfoDomain.class);
        return true;
    }

    @Override
    public PageList<FileInfoDto> getPage(String fileName, int pageIndex, int pageSize) {
        Query query;
        if (StringExtension.isNullOrWhiteSpace(fileName)) {
            query = new Query();
        } else {
            query = new Query(Criteria.where(IBaseValue.fileName).regex(".*" + fileName + ".*", "i"));
        }

        long count = mongoTemplate.count(query, FileInfoDomain.class);
        query.skip((pageIndex - 1) * pageSize);
        query.limit(pageSize);
        List<FileInfoDomain> fileInfoDomains = mongoTemplate.find(query, FileInfoDomain.class);
        PageList<FileInfoDto> pageList = new PageList<>(pageIndex, pageSize, count);
        pageList.setList(mapper.asFileInfoDto(fileInfoDomains));
        return pageList;
    }

    private String getName(String fileName) {
        String[] split = fileName.split("\\.");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < split.length - 1; i++) {
            stringBuilder.append(split[i]);
            if (i < split.length - 2) {
                stringBuilder.append(".");
            }
        }
        return stringBuilder.toString();
    }

    private String getType(String fileName) {
        String[] split = fileName.split("\\.");
        return split[split.length - 1];
    }
}
