package share.livelihood.bussiness.controller.file;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import share.base.annotation.NoAuth;
import share.base.result.LiveResult;
import share.base.result.PageList;
import share.livelihood.bussiness.api.service.file.IFileInfoService;
import share.livelihood.bussiness.dto.file.FileInfoDto;
import share.web.ApiController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/livelihood/FileInfo")
@Api(value = "/livelihood/FileInfo", description = "文件管理")
public class FileInfoController extends ApiController
{
    @Autowired
    private IFileInfoService service;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation(value = "文件上传", notes = "文件上传")
    @NoAuth
    public LiveResult<Boolean> upload(@RequestParam("file") MultipartFile multipartFile) {
        return ok(service.addFile(multipartFile));
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "展示图片", notes = "展示图片")
    @NoAuth
    public void imageDisplay(@PathVariable String id, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");  //设置显示图片
            response.setHeader("Cache-Control", "max-age=60480000");
            service.showImage(id, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "文件下载", notes = "文件下载")
    @NoAuth
    public void download(@PathVariable String id, HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream"); //设置图片下载
            response.setHeader("Cache-Control", "max-age=60480000");//设置缓存
            response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" +
                    URLEncoder.encode(id, "UTF-8"));//设置文件名
            service.downloadFile(id, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除文件", notes = "删除文件")
    @NoAuth
    public LiveResult<Boolean> delete(@PathVariable String id) {
        return ok(service.deleteFile(id));
    }

    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取文件信息", notes = "分页获取文件信息")
    @NoAuth
    public LiveResult<PageList<FileInfoDto>> getPage(@RequestParam(required = false) String fileName,
                                                     @RequestParam int pageIndex,
                                                     @RequestParam int pageSize) {
        return ok(service.getPage(fileName, pageIndex, pageSize));
    }
}
