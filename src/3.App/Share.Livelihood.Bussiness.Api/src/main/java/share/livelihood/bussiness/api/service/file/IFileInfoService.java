package share.livelihood.bussiness.api.service.file;

import org.springframework.web.multipart.MultipartFile;
import share.base.result.PageList;
import share.livelihood.bussiness.dto.file.FileInfoDto;

import java.io.OutputStream;

public interface IFileInfoService {
    boolean addFile(MultipartFile multipartFile);

    void showImage(String id, OutputStream outputStream);

    void downloadFile(String id, OutputStream outputStream);

    boolean deleteFile(String id);

    PageList<FileInfoDto> getPage(String fileName, int pageIndex, int pageSize);
}
