package com.tuzhi.auth.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tuzhi.auth.base.BaseDomain;


/**
 * @ClassName:Role
 * @Description:的实体类
 * @author 郑德超
 * @CreateDate 2018-06-11 14:58:03
 */
@Table(name = "role")
public class Role extends BaseDomain {
	
    /**id**/
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    /**name**/
    private String name;

    /**type**/
    private Integer type;

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
    /**name**/
    public String getName(){
        return name;
    }
    /**name**/
    public void setName(String name){
        this.name= name;
    }
    /**type**/
    public Integer getType(){
        return type;
    }
    /**type**/
    public void setType(Integer type){
        this.type= type;
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
