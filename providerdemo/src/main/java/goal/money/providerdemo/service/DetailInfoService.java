package goal.money.providerdemo.service;

import goal.money.providerdemo.dto.DetailInfo;

/**
 * @authorZhouweiping
 * @date10/20
 */
public interface DetailInfoService {
    int insertSelective(DetailInfo record);

    DetailInfo queryDetailByPhoneAndProductId( String phone,long productId);
}
