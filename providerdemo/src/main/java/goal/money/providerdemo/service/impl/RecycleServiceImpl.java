package goal.money.providerdemo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import goal.money.providerdemo.dto.Recycle;
import goal.money.providerdemo.mapper.RecycleMapper;
import goal.money.providerdemo.service.RecycleService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RecycleServiceImpl implements RecycleService {
    @Autowired
    private RecycleMapper recycleMapper;

    @Override
    public int insertSelective(Recycle record) {
        return recycleMapper.insertSelective(record);
    }
}
