package cn.com.njit.wd.provider.service;

import cn.com.njit.wd.api.dto.TradeInfoDTO;
import cn.com.njit.wd.api.dto.TradeInfoResDTO;
import cn.com.njit.wd.api.service.ITradeManage;
import cn.com.njit.wd.provider.domain.TradeInfo;
import cn.com.njit.wd.provider.repository.TradeRepository;
import cn.com.njit.wd.provider.util.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangdi on 2017/5/16.
 */
@Service("iTradeManageImpl")
public class ITradeManageImpl implements ITradeManage{

    @Autowired
    TradeRepository tradeRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addTradeInfo(TradeInfoDTO tradeInfoDTO) {
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo = BeanMapper.map(tradeInfoDTO,TradeInfo.class);
        tradeRepository.save(tradeInfo);
    }

    @Override
    public TradeInfoResDTO queryByPage(TradeInfoDTO tradeInfoDTO) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"tradeTime"));
        TradeInfoResDTO tradeInfoResDTO = new TradeInfoResDTO();
        Page<TradeInfo> page = tradeRepository.queryByUserId(tradeInfoDTO.getUserId(),new PageRequest(tradeInfoDTO.getCurrPage()-1,tradeInfoDTO.getPageSize(),sort));
        List<TradeInfo> tradeInfoList = page.getContent();
        tradeInfoResDTO.setTradeInfoDTOList(BeanMapper.mapList(tradeInfoList,TradeInfoDTO.class));
        tradeInfoResDTO.setCount(page.getTotalPages());
        return tradeInfoResDTO;
    }
}
