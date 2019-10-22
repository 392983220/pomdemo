package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import goal.money.consumerdemo.config.NameAndUrl;
import goal.money.consumerdemo.utils.GetIpAddressUtil;
import goal.money.consumerdemo.utils.RedisUtils;
import goal.money.consumerdemo.utils.result.Pages;
import goal.money.consumerdemo.utils.result.ReturnResult;
import goal.money.consumerdemo.utils.result.ReturnResultUtil;
import goal.money.consumerdemo.vo.PageVo;
import goal.money.providerdemo.dto.ProductInfo;
import goal.money.providerdemo.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation("展示分类后商品")
    @GetMapping(value = "/getGoods")
    public ReturnResult<Pages> getGoods(int startPage,int pageSize, @ApiParam("商品类别") @RequestParam("productCategory") int productCategory, HttpServletRequest request) {
        PageVo pageVo =new PageVo();
        pageVo.setStartPage(startPage);
        pageVo.setPageSize(pageSize);
        List<ProductInfo> productInfos = productInfoService.getGoods(productCategory, pageVo.getStartPage(), pageVo.getPageSize());
        String Default = "默认搜过条件为："+productInfoService.getDefault();
        String key = GetIpAddressUtil.getIpAddr(request);
        Object object=redisUtils.get(key);
        Map<String,Integer> map=null;
        int size=0;
        List list=new ArrayList();
        if (object!=null){
            map = JSONObject.parseObject(redisUtils.get(key).toString(), HashMap.class);
            size=map.size();
        }
        list.add(Default);
        list.add("购物车数量"+size);
        list.add(productInfos);
        Pages pages=new Pages();
        pages.setCurrentPage(startPage);
        pages.setTotalCount(productInfos.size());
        pages.setPagesize(pageSize);
        pages.setCurrList(list);
        return ReturnResultUtil.returnSuccessData(1,"成功",pages);
    }
    @ApiOperation("首页返回商品Url和默认搜索")
    @GetMapping(value = "/showUrl")
    public ReturnResult showUrl(HttpServletRequest request) {
        String key = GetIpAddressUtil.getIpAddr(request);
        List list=new ArrayList();
        int size=0;
        List<ProductInfo> listPro = productInfoService.getAllGoods();
        Map<String,Integer> map =null;
        nameAndUrl.setDefaultMsg("默认搜过商品为："+productInfoService.getDefault());
        Object object=redisUtils.get(key);
               if (object!=null){
                   map = JSONObject.parseObject(object.toString(), HashMap.class);
                   size=map.size();
                   nameAndUrl.setCartNum(size);
               }
        nameAndUrl.setCartNum(size);
        list.add(nameAndUrl);
        list.add(listPro);
        return ReturnResultUtil.returnSuccessData(1,"成功",list);
    }

    @ApiOperation("模糊查询")
    @GetMapping(value = "/checkByName")
    public ReturnResult checkByName(HttpServletRequest request,@ApiParam("模糊查询的字段") @RequestParam("product_name")String product_name ,int startPage,int pageSize) {
        PageVo pageVo =new PageVo();
        pageVo.setStartPage(startPage);
        pageVo.setPageSize(pageSize);
        List<ProductInfo> productInfos=productInfoService.checkByName(product_name,pageVo.getStartPage(),pageVo.getPageSize());
        Pages pages=new Pages();
        pages.setCurrentPage(startPage);
        pages.setPagesize(pageSize);
        pages.setTotalCount(productInfos.size());
        pages.setCurrList(productInfos);
      return  ReturnResultUtil.returnSuccessData(1,"成功",pages);
    }

}
