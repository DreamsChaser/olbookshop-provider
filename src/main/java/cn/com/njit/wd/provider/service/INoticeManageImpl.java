package cn.com.njit.wd.provider.service;

import cn.com.njit.wd.api.dto.NoticeDTO;
import cn.com.njit.wd.api.dto.NoticeResDTO;
import cn.com.njit.wd.api.service.INoticeManage;
import cn.com.njit.wd.provider.domain.Notice;
import cn.com.njit.wd.provider.repository.NoticeRepository;
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
 * Created by wangdi on 2017/5/17.
 */
@Service("iNoticeManageImpl")
public class INoticeManageImpl implements INoticeManage{

    @Autowired
    NoticeRepository noticeRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addNotice(NoticeDTO noticeDTO) {
        Notice notice = new Notice();
        notice = BeanMapper.map(noticeDTO,Notice.class);
        noticeRepository.save(notice);
    }

    @Override
    public NoticeResDTO queryAll(NoticeDTO noticeDTO) {
        Sort sort=new Sort(new Sort.Order(Sort.Direction.DESC,"noticeTime"));
        NoticeResDTO noticeResDTO = new NoticeResDTO();
        Page<Notice> page = noticeRepository.findAll(new PageRequest(noticeDTO.getCurrPage()-1,noticeDTO.getPageSize(),sort));
        List<Notice> noticeList = page.getContent();
        noticeResDTO.setNoticeDTOList(BeanMapper.mapList(noticeList,NoticeDTO.class));
        noticeResDTO.setCount(page.getTotalPages());
        return noticeResDTO;
    }

    @Override
    public NoticeDTO queryById(NoticeDTO noticeDTO) {
        Notice notice = noticeRepository.findOne(noticeDTO.getNoticeId());
        NoticeDTO dto = BeanMapper.map(notice,NoticeDTO.class);
        return dto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void modifyNotice(NoticeDTO noticeDTO) {
        noticeRepository.updateNotice(noticeDTO.getNoticeTitle(),noticeDTO.getNoticeContent(),noticeDTO.getNoticeTime(),noticeDTO.getNoticeId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteNotice(NoticeDTO noticeDTO) {
        noticeRepository.delete(noticeDTO.getNoticeId());
    }
}
