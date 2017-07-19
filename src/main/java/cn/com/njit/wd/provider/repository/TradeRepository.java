package cn.com.njit.wd.provider.repository;

import cn.com.njit.wd.provider.domain.TradeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by wangdi on 2017/5/16.
 */
@Repository
public interface TradeRepository extends JpaRepository<TradeInfo,String> {

    @Query("SELECT t from TradeInfo t where t.userId=:userId")
    Page<TradeInfo> queryByUserId(@Param("userId")String userId , Pageable pageable);
}
