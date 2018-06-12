package com.tuzhi.auth.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tuzhi.auth.base.BaseDomain;


/**
 * @ClassName:Resource
 * @Description:的实体类
 * @author 郑德超
 * @CreateDate 2018-06-11 09:53:53
 */
@Table(name = "t_resource")
public class Resource extends BaseDomain {
	
    /**ID**/
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**路径名称**/
    private String name;

    /**url(/开头)**/
    private String url;

    /**菜单路径/按钮路径**/
    private String isMenuUrl;

    /**所属菜单id**/
    private Integer menuId;

    /**排序**/
    private Integer orderBy;

    /**ID**/
    public Integer getId(){
        return id;
    }
    /**ID**/
    public void setId(Integer id){
        this.id= id;
    }
    /**路径名称**/
    public String getName(){
        return name;
    }
    /**路径名称**/
    public void setName(String name){
        this.name= name;
    }
    /**url(/开头)**/
    public String getUrl(){
        return url;
    }
    /**url(/开头)**/
    public void setUrl(String url){
        this.url= url;
    }
    /**菜单路径/按钮路径**/
    public String getIsMenuUrl(){
        return isMenuUrl;
    }
    /**菜单路径/按钮路径**/
    public void setIsMenuUrl(String isMenuUrl){
        this.isMenuUrl= isMenuUrl;
    }
    /**所属菜单id**/
    public Integer getMenuId(){
        return menuId;
    }
    /**所属菜单id**/
    public void setMenuId(Integer menuId){
        this.menuId= menuId;
    }
    /**排序**/
    public Integer getOrderBy(){
        return orderBy;
    }
    /**排序**/
    public void setOrderBy(Integer orderBy){
        this.orderBy= orderBy;
    }

	
}
