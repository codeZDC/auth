package com.tuzhi.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.tuzhi.auth.domain.Account;
import com.tuzhi.auth.domain.User;
import com.tuzhi.auth.domain.ext.UserExt;

import tk.mybatis.mapper.common.Mapper;

 /**
 * @ClassName:UserMapper
 * @Description:数据层接口
 * @author 郑德超
 * @CreateDate 2018-06-06 15:13:12
 */
public interface IUserMapper extends Mapper<User> {
	
	/**
	 * @title:findUserList
	 * @description: 查询列表
	 * @author 郑德超
	 * @param user
	 * @CreateDate  2018-06-06 15:13:12
	 */
	List<User> findUserList(User user);

	UserExt getUserByUsername(String username);
	
	//添加用户账号和密码
	@Insert("insert into account values (#{id},#{username},#{password},#{userId})")
	int addAccount(Account account);

	@Delete("delete from account where userId = #{userId}")
	int deleteAccountByUserId(String id);
	
	//关联用户角色
	@Insert("insert into user_role (userId,roleId) values (#{param1},#{param2})")
	int linkRole(String userId, String roleId);
	
	@Delete("delete from user_role where userId = #{userId} ")
	int cancelRole(String id);
	
	@Update("update account set username=#{username} where userId = #{userId}")
	int updateAccount(Account account);
	
	@Update("update user_role set roleId = #{param2} where userId=#{param1}")
	int updateLinedRole(String userId,String roleId);
	
	@Update("update account set password=#{param3} where username =#{param1} and password=#{param2}")
	int repassword(String username, String password, String newPassword);
	
}
