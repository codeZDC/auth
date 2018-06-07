package com.tuzhi.auth.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tuzhi.auth.base.BaseDomain;


/**
 * @ClassName:User
 * @Description:的实体类
 * @author 郑德超
 * @CreateDate 2018-06-07 09:21:43
 */
@Table(name = "user")
public class User extends BaseDomain {
	
    /**id**/
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    /**realname**/
    private String realname;

    /**sex**/
    private Integer sex;

    /**status**/
    private Integer status;

    /**tel**/
    private String tel;

    /**registtime**/
    private java.util.Date registtime;

    /**birth**/
    private java.util.Date birth;

    /**email**/
    private String email;

    /**description**/
    private String description;

    /**id**/
    public String getId(){
        return id;
    }
    /**id**/
    public void setId(String id){
        this.id= id;
    }
    /**realname**/
    public String getRealname(){
        return realname;
    }
    /**realname**/
    public void setRealname(String realname){
        this.realname= realname;
    }
    /**sex**/
    public Integer getSex(){
        return sex;
    }
    /**sex**/
    public void setSex(Integer sex){
        this.sex= sex;
    }
    /**status**/
    public Integer getStatus(){
        return status;
    }
    /**status**/
    public void setStatus(Integer status){
        this.status= status;
    }
    /**tel**/
    public String getTel(){
        return tel;
    }
    /**tel**/
    public void setTel(String tel){
        this.tel= tel;
    }
    /**registtime**/
    public java.util.Date getRegisttime(){
        return registtime;
    }
    /**registtime**/
    public void setRegisttime(java.util.Date registtime){
        this.registtime= registtime;
    }
    /**birth**/
    public java.util.Date getBirth(){
        return birth;
    }
    /**birth**/
    public void setBirth(java.util.Date birth){
        this.birth= birth;
    }
    /**email**/
    public String getEmail(){
        return email;
    }
    /**email**/
    public void setEmail(String email){
        this.email= email;
    }
    /**description**/
    public String getDescription(){
        return description;
    }
    /**description**/
    public void setDescription(String description){
        this.description= description;
    }

	
}
