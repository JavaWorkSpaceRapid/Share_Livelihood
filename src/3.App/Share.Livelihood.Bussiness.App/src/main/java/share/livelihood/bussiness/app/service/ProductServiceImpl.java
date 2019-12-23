package share.livelihood.bussiness.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import share.base.extensions.StringExtension;
import share.livelihood.bussiness.api.service.IProductService;
import share.livelihood.bussiness.domain.core.ProductImgInfoDomain;
import share.livelihood.bussiness.domain.core.ProductInfomationDomain;
import share.livelihood.bussiness.infrastructure.dao.IProductDao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService
{
    @Autowired
    private IProductDao iProductDao;

    @Override
    public String addProductInfo(ProductInfomationDomain productInfoDomain)
    {
        return iProductDao.addProductInfo(productInfoDomain);
    }

    @Override
    public String updateProductInfoByPid(ProductInfomationDomain productInfoDomain) {
        return iProductDao.updateProductInfoByPid(productInfoDomain);
    }

    @Override
    public List<ProductInfomationDomain> getListProductInfo() {
        return iProductDao.getListProductInfo();
    }

    @Override
    public ProductInfomationDomain getProductInfoByProductId(String productId) {
        return iProductDao.getProductInfoByProductId(productId);
    }

    @Override
    public List<ProductInfomationDomain> getListProductInfoByCId(String categoryId) {
        return iProductDao.getListProductInfoByCId(categoryId);
    }

    @Override
    public void delProductInfoById(String productId) {
        iProductDao.delProductInfoById(productId);
    }


    @Override
    public String addProductImgInfo(ProductImgInfoDomain productImgInfoDomain)
    {
        return iProductDao.addProductImgInfo(productImgInfoDomain);
    }

    @Override
    public String uploadImg(MultipartFile file)
    {
        //1. 接受上传的文件  @RequestParam("file") MultipartFile file
        String destFileName = StringExtension.Empty;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String time = dateFormat.format(new Date());
        String fileName = StringExtension.Empty;
        try {
            String fileImg = file.getOriginalFilename();
            if(fileImg.contains("."))
            {
                fileImg = fileImg.substring(fileImg.lastIndexOf("."));
            }
            else
            {
                fileImg = ".jpg";
            }
            System.out.println(System.currentTimeMillis());
            //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
            fileName = System.currentTimeMillis() + fileImg;
            //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
//            String destFileName = req.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;

            destFileName = "H:\\product_img\\"+time+"\\"+fileName;
            //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            //5.把浏览器上传的文件复制到希望的位置
            file.transferTo(destFile);
            //6.把文件名放在model里，以便后续显示用
//            m.addAttribute("fileName", fileName);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        } catch (IOException e)
        {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        }http://ai27280392.qicp.vip/image/20191101/1572616757001.png
        return "http://27q737961l.wicp.vip/"+time+"/"+fileName;
    }
}
