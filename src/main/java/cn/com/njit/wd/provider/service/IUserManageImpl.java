package cn.com.njit.wd.provider.service;

import cn.com.njit.wd.api.dto.UserDTO;
import cn.com.njit.wd.api.service.IUserManage;
import cn.com.njit.wd.provider.domain.User;
import cn.com.njit.wd.provider.repository.UserRepository;
import cn.com.njit.wd.provider.util.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by wangdi on 2017/3/17.
 */
@Service("iUserManageImpl")
public class IUserManageImpl implements IUserManage{
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDTO> queryAll(){
        List<User> list = userRepository.findAll();
        List<UserDTO> userDTOList = BeanMapper.mapList(list,UserDTO.class);
        return userDTOList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public UserDTO insert(UserDTO userDTO){
        if (StringUtils.isEmpty(userDTO.getUserType())){
            userDTO.setUserType("01");
        }
        User user = BeanMapper.map(userDTO,User.class);
        User u = userRepository.save(user);
        return BeanMapper.map(u,UserDTO.class);
    }

    @Override
    public boolean queryByName(UserDTO userDTO) {
        User user = userRepository.queryByName(userDTO.getUserName());
        if (user == null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public UserDTO queryOne(UserDTO userDTO) {
        UserDTO userResDTO = null;
        User user = userRepository.queryOne(userDTO.getUserName(),userDTO.getUserPassword());
        if (user != null) {
            userResDTO = BeanMapper.map(user, UserDTO.class);
        }
        return userResDTO;
    }

    @Override
    public UserDTO queryById(UserDTO userDTO) {
        UserDTO userResDTO = null;
        if (!StringUtils.isEmpty(userDTO.getUserId())) {
            User user = userRepository.findOne(userDTO.getUserId());
            userResDTO = BeanMapper.map(user,UserDTO.class);
        }
        return userResDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateUserBalance(UserDTO userDTO) {
        userRepository.updateUserBalance(userDTO.getUserBalance(),userDTO.getUserId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateUserInfoById(UserDTO userDTO) {
         userRepository.updateUserInfoById(userDTO.getUserName(),userDTO.getUserPassword(),userDTO.getUserType(),userDTO.getUserAddr(),
                userDTO.getUserMobile(),userDTO.getUserEmail(),userDTO.getUserId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateUserTypeById(UserDTO userDTO) {
        userRepository.updateUserTypeById(userDTO.getUserType(), userDTO.getUserId());
    }
}
