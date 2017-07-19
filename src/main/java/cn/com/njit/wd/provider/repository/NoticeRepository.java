package cn.com.njit.wd.provider.repository;

import cn.com.njit.wd.provider.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by wangdi on 2017/5/17.
 */
@Repository
public interface NoticeRepository extends JpaRepository<Notice,String>{

    @Modifying
    @Query("update Notice n set n.noticeTitle=?1,n.noticeContent=?2,n.noticeTime=?3 where n.noticeId=?4")
    void updateNotice(String noticeTitle,String noticeContent,String noticeTime,String noticeId);
}
