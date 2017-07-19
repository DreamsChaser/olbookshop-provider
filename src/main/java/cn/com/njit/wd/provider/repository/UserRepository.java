package cn.com.njit.wd.provider.repository;

import cn.com.njit.wd.api.dto.UserDTO;
import cn.com.njit.wd.provider.domain.User;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by wangdi on 2017/3/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User,String>{

    @Query("select u from User u where u.userName =:userName")
    User queryByName(@Param("userName") String userName);

    @Query("select u from User u where u.userName =:userName and u.userPassword =:userPassword")
    User queryOne(@Param("userName") String userName,@Param("userPassword")String userPassword);

    @Modifying
    @Query("update User u set u.userBalance =:userBalance where u.userId =:userId" )
    Integer updateUserBalance(@Param("userBalance")String userBalance,@Param("userId")String userId);

    @Modifying
    @Query("update User u set u.userName =?1,u.userPassword=?2,u.userType=?3," +
            "u.userAddr=?4,u.userMobile=?5,u.userEmail=?6 where u.userId=?7")
    void updateUserInfoById(String userName,String userPassword,String userType,String userAddr,String userMobile,String userEmail,String userId);

    @Modifying
    @Query("update User u set u.userType=:userType where u.userId=:userId")
    void updateUserTypeById(@Param("userType")String userType,@Param("userId")String userId);
}
