package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import goal.money.consumerdemo.custom.CurrentUser;
import goal.money.consumerdemo.custom.LoginRequired;
import goal.money.consumerdemo.utils.result.ReturnResult;
import goal.money.consumerdemo.utils.result.ReturnResultUtil;
import goal.money.consumerdemo.vo.UserVo;
import goal.money.providerdemo.dto.CartInfo;
import goal.money.providerdemo.dto.DetailInfo;
import goal.money.providerdemo.service.CartService;
import goal.money.providerdemo.service.DetailInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @authorZhouweiping
 * @date10/20
 */
@RestController
@Api(tags = "商品详情表")
@RequestMapping(value = "detailInfo")
public class DetailController {
    @Reference
    private DetailInfoService detailInfoService;
    @Reference
    private CartService cartService;

    @ApiOperation(value = "生成商品详情表")
    @GetMapping(value = "insertDetail")
    @LoginRequired
    public ReturnResult insertDetail(@CurrentUser UserVo userVo, long cartId) {
        CartInfo cartInfo = cartService.selectByPrimaryKey(cartId);
        DetailInfo detailInfo = detailInfoService.queryDetailByPhoneAndProductId(userVo.getPhone(), cartInfo.getProductId());
        if (detailInfo == null) {
            detailInfo = new DetailInfo();
            detailInfo.setPhone(userVo.getPhone());
            detailInfo.setBuyQuantity(cartInfo.getBuyQuantity());
            detailInfo.setPriceMultiplyQuantity(cartInfo.getPriceMultiplyQuantity());
            detailInfo.setProductColor(cartInfo.getProductColor());
            detailInfo.setProductName(cartInfo.getProductName());
            detailInfo.setProductPrice(cartInfo.getProductPrice());
            detailInfo.setProductDescribe(cartInfo.getProductDescribe());
            detailInfo.setProductId(cartInfo.getProductId());
            detailInfoService.insertSelective(detailInfo);
        } else {
            detailInfo.setBuyQuantity(cartInfo.getBuyQuantity() + detailInfo.getBuyQuantity());
            detailInfoService.updateBuyQuantity(userVo.getPhone(),detailInfo.getProductId(),detailInfo.getBuyQuantity());
        }
        return ReturnResultUtil.returnSuccessData(1, "生成商品详情表", detailInfo);
    }


}
