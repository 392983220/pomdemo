package goal.money.providerdemo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import goal.money.providerdemo.dto.DetailInfo;
import goal.money.providerdemo.mapper.DetailInfoMapper;
import goal.money.providerdemo.service.DetailInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @authorZhouweiping
 * @date10/20
 */
@Service
public class DetailInfoServiceImpl implements DetailInfoService {
    @Autowired
    private DetailInfoMapper detailInfoMapper;

    @Override
    public int insertSelective(DetailInfo record) {
        return detailInfoMapper.insertSelective(record);
    }

    @Override
    public DetailInfo queryDetailByPhoneAndProductId(String phone, long productId) {
        return detailInfoMapper.queryDetailByPhoneAndProductId(phone,productId);
    }

    @Override
    public void updateBuyQuantity(String phone, long productId, int buyQuantity) {
        detailInfoMapper.updateBuyQuantity(phone,productId,buyQuantity);
    }
}
