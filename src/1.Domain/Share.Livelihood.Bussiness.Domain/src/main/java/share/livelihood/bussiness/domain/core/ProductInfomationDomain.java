package share.livelihood.bussiness.domain.core;

import lombok.Data;
import share.domain.EntityBase;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/11/21 21:21
 */
@Data
public class ProductInfomationDomain extends EntityBase
{
    /**
     * 商品编号
     */
    private String product_id;

    /**
     * 用户Id
     */
    private String user_id;

    /**
     * 分类Id
     */
    private String category_id;

    /**
     * 商品名称
     */
    private String product_name;
    /**
     * 商品数量
     */
    private String product_num;
    /**
     * 商品价格
     */
    private String product_price;
    /**
     * 会员价格
     */
    private double member_price;
    /**
     * 促销价格
     */
    private double sales_price;
    /**
     * 商品产地
     */
    private String product_area;

    /**
     * 主图片链接
     */
    private String img_src;

    /**
     * 单位
     */
    private String product_unit;

    //每份多少张券
    private int one_jin;

    /**
     * 对应的商品图片
     */
    private List<ProductImgInfoDomain> productImgInfoDomainList;

    /**
     * 商品描述
     */
    private String product_desc;
}
