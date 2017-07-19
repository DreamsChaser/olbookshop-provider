package cn.com.njit.wd.provider.service;

import cn.com.njit.wd.api.dto.EmpDTO;
import cn.com.njit.wd.api.dto.UserDTO;
import cn.com.njit.wd.api.service.IEmpInfo;
import cn.com.njit.wd.provider.repository.mybatis.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangdi on 2017/3/29.
 *
 */
@Service("iEmpInfoImpl")
public class IEmpInfoImpl implements IEmpInfo{

    @Autowired
    private EmpMapper empMapper;

    @Override
    public EmpDTO queryByName(String name) {
        EmpDTO empDTO = empMapper.queryByName(name);
        return empDTO;
    }

    @Override
    public UserDTO save(EmpDTO empDTO) {
        return null;
    }

}
