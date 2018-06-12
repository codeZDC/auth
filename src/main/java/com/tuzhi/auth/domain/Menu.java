package com.tuzhi.auth.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tuzhi.auth.base.BaseDomain;


/**
 * @ClassName:Menu
 * @Description:的实体类
 * @author 郑德超
 * @CreateDate 2018-06-08 09:06:41
 */
@Table(name = "t_menu")
public class Menu extends BaseDomain {
	
    /**ID**/
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**菜单名称**/
    private String name;

    /**菜单class属性(图标)**/
    private String ico;

    /**描述**/
    private String description;

    /**父级id**/
    private Integer pid;

    /**排序号**/
    private Integer orderBy;

    /**ID**/
    public Integer getId(){
        return id;
    }
    /**ID**/
    public void setId(Integer id){
        this.id= id;
    }
    /**菜单名称**/
    public String getName(){
        return name;
    }
    /**菜单名称**/
    public void setName(String name){
        this.name= name;
    }
    /**菜单class属性(图标)**/
    public String getIco(){
        return ico;
    }
    /**菜单class属性(图标)**/
    public void setIco(String ico){
        this.ico= ico;
    }
    /**描述**/
    public String getDescription(){
        return description;
    }
    /**描述**/
    public void setDescription(String description){
        this.description= description;
    }
    /**父级id**/
    public Integer getPid(){
        return pid;
    }
    /**父级id**/
    public void setPid(Integer pid){
        this.pid= pid;
    }
    /**排序号**/
    public Integer getOrderBy(){
        return orderBy;
    }
    /**排序号**/
    public void setOrderBy(Integer orderBy){
        this.orderBy= orderBy;
    }

	
}
