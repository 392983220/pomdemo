package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import goal.money.consumerdemo.config.NameAndUrl;
import goal.money.consumerdemo.utils.result.Pages;
import goal.money.consumerdemo.utils.result.ReturnResult;
import goal.money.consumerdemo.utils.result.ReturnResultUtil;
import goal.money.consumerdemo.vo.PageVo;
import goal.money.providerdemo.dto.ProductInfo;
import goal.money.providerdemo.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @authorzhangshen
 * @date10/22
 */
@Api(tags = "首页")
@RestController
@RequestMapping(value = "/home")
public class ProductInfoController {
    @Reference
    private ProductInfoService productInfoService;

    @Autowired
    private NameAndUrl nameAndUrl;

    @ApiOperation("展示分类后商品")
    @GetMapping(value = "/getGoods")
    public ReturnResult getGoods(PageVo pageVo, int productCategory, HttpServletRequest request) {
        List<ProductInfo> productInfos = productInfoService.getGoods(productCategory, pageVo.getStartPage(), pageVo.getPageSize());
        String Default = productInfoService.getDefault();
        return ReturnResultUtil.returnSuccessData(1,"成功，默认搜索条件是:"+Default,productInfos);
    }
    @ApiOperation("首页返回商品Url和默认搜索")
    @GetMapping(value = "/showUrl")
    public ReturnResult showUrl() {
        List<ProductInfo> listPro = productInfoService.getAllGoods();
        nameAndUrl.setDefaultMsg(productInfoService.getDefault());
        List list=new ArrayList();
        list.add(nameAndUrl);
        list.add(listPro);
        return ReturnResultUtil.returnSuccessData(1,"成功",list);
    }
}
