package com.tuzhi.auth.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuzhi.auth.base.BaseDomain;


/**
 * @ClassName:LogLogin
 * @Description:的实体类
 * @author 郑德超
 * @CreateDate 2018-06-13 15:55:40
 */
@Table(name = "t_log_login")
public class LogLogin extends BaseDomain {
	
    /**id**/
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**登录账号**/
    private String username;

    /**登录IP**/
    private String ip;

    /**登录时间**/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")  //取日期时使用  
    private java.util.Date loginTime;

    /**id**/
    public Integer getId(){
        return id;
    }
    /**id**/
    public void setId(Integer id){
        this.id= id;
    }
    /**登录账号**/
    public String getUsername(){
        return username;
    }
    /**登录账号**/
    public void setUsername(String username){
        this.username= username;
    }
    /**登录IP**/
    public String getIp(){
        return ip;
    }
    /**登录IP**/
    public void setIp(String ip){
        this.ip= ip;
    }
    /**登录时间**/
    public java.util.Date getLoginTime(){
        return loginTime;
    }
    /**登录时间**/
    public void setLoginTime(java.util.Date loginTime){
        this.loginTime= loginTime;
    }

	
}
