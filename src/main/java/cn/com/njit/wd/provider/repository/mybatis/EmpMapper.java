package cn.com.njit.wd.provider.repository.mybatis;

import cn.com.njit.wd.api.dto.EmpDTO;
import cn.com.njit.wd.api.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wangdi on 2017/3/27.
 */
@Mapper
public interface EmpMapper {
    EmpDTO queryByName(String name);
    Integer save(EmpDTO empDTO);
}
