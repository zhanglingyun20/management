package com.management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.management.common.Page;
import com.management.model.Users;
import com.management.model.vo.UserTypeSelect;

public interface UsersMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);
    
    Users selectByAccount(@Param("account") String account);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
    
    int updateByAccountSelective(Users record);
    
    Users selectByAccountAndPwd(@Param("account") String account,@Param("password") String password);
    
    List<Users> findUsersByAccountAndUsername(Page<Users> page,@Param("user")Users user);
    
    List<UserTypeSelect> getUserByUserType(@Param("userType") String userType);
    
    int updateUserStateByIds(@Param("state") String state,@Param("ids")List<Integer> ids);
}