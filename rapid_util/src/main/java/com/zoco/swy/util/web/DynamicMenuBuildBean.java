/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.zoco.fbrp.util.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.html.HtmlDropDownMenu;
import org.richfaces.component.html.HtmlMenuGroup;
import org.richfaces.component.html.HtmlMenuItem;
import org.richfaces.component.html.HtmlToolBar;
import org.richfaces.component.html.HtmlToolBarGroup;

import com.zoco.fbrp.util.StringUtil;

/**
 * <pre>
 * 动态菜单。
 * </pre>
 * @author luxiaocheng luxiaocheng@zoco.cn
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class DynamicMenuBuildBean {

	private static final Log log = LogFactory.getLog(DynamicMenuBuildBean.class);
	
	/**
	 * 创建菜单工具栏。
	 * 
	 * @param toolBar HtmlToolBar
	 * @param rootList List<MenuEx>
	 * 
	 * @return HtmlToolBar
	 */
	public HtmlToolBar createMenuToolBar(HtmlToolBar toolBar, List<MenuEx> rootList) {
		//List<MenuEx> rootList = this.initMenuRelation(menuList);
		
		//创建具体的节点
		for (int i = 0; i < rootList.size(); i++) {
			MenuEx menu = rootList.get(i);
			//循环parent_code为空的节点，也就是根节点
			HtmlDropDownMenu dropDownMenu = new HtmlDropDownMenu();//菜单根节点
			dropDownMenu.setId("dropdown_"+menu.getId());
			
			
			HtmlToolBarGroup toolBarGroup = new HtmlToolBarGroup();
			if(!StringUtil.isEmpty(menu.getImageUrl())) {
				
				HtmlPanelGroup htmlPanelGroup = new HtmlPanelGroup();
				
				HtmlGraphicImage graphicImage = new HtmlGraphicImage();
				graphicImage.setUrl(menu.getImageUrl());
				htmlPanelGroup.getChildren().add(graphicImage);
				
				HtmlOutputText htmlOutputText = new HtmlOutputText();
				htmlOutputText.setValue(menu.getName());
				htmlPanelGroup.getChildren().add(htmlOutputText);
				
				dropDownMenu.getFacets().put("label", htmlPanelGroup);
			} else {
				dropDownMenu.setValue(menu.getName());
			}
			
			if(menu.haveSubMenu()) {//如果有子节点
				dropDownMenu = this.createOneMenu(dropDownMenu, menu,null);
			} else {
				this.createRootMenuItem(dropDownMenu, menu);
			}
			toolBarGroup.getChildren().add(dropDownMenu);
			toolBar.getChildren().add(toolBarGroup);
			//toolBar.getChildren().add(dropDownMenu);
		}
		
		return toolBar;
	}
	
	/**
	 * 初始化。
	 * 
	 * @param menuList List<MenuEx>
	 * 
	 * @return List<MenuEx>
	 */
	public List<MenuEx> initMenuRelation(List<MenuEx> menuList) {
		//先找出根节点，并设置好父子关系
		List<MenuEx> rootList = new ArrayList<MenuEx>();
		for (int i = 0; i < menuList.size(); i++) {
			MenuEx menu = (MenuEx)menuList.get(i);
			//循环parent_code为空的节点，也就是根节点
			if(menu.getParentId()==null || "0".equals(menu.getParentId())||"".equals(menu.getParentId().trim())) {
				rootList.add(menu);
			}
			//循环设置菜单之间的父子关系
			for (int j = 0;j < menuList.size(); j++) {
				MenuEx menu1 = menuList.get(j);
				if(i==j){
					continue;
				}
				//设置父子关系
				if(menu1.getParentId()!=null && menu1.getParentId().equals(menu.getId())) {
					menu1.setParentMenu(menu);
					menu.addChildren(menu1);
				}
			}
		}
		return rootList;
	}
	
	/**
	 * 创建菜单。
	 * 
	 * @param dropDownMenu HtmlDropDownMenu
	 * @param menu MenuEx
	 * @param group HtmlMenuGroup
	 * 
	 * @return HtmlDropDownMenu
	 */
	public HtmlDropDownMenu createOneMenu(HtmlDropDownMenu dropDownMenu, MenuEx menu,HtmlMenuGroup group) {
		//先找到最后一个节点，然后再找父节点
		List<MenuEx> childrenVOList = menu.getChildrenList();
		//循环所有子节点，从下往上生成菜单
		for (int i = 0; i < childrenVOList.size(); i++) {
			MenuEx childrenMenu = childrenVOList.get(i);
			//如果该节点没有儿子，那么直接添加成Item
			if(!childrenMenu.haveSubMenu()){
				HtmlMenuItem menuItem = this.createMenuItem(childrenMenu);
				if(group!=null){
					group.getChildren().add(menuItem);
				}else{
					dropDownMenu.getChildren().add(menuItem);
				}
			}else{
				//添加到group中去
				HtmlMenuGroup menuGroup = this.createMenuGroup(childrenMenu);
				if(group!=null){
					group.getChildren().add(menuGroup);
				}else{
					dropDownMenu.getChildren().add(menuGroup);
				}
				//循环添加其子孙
				createOneMenu(dropDownMenu,childrenMenu,menuGroup);
			}
		}
		return dropDownMenu;
	}
	
	/**
	 * 创建htmlMenuGroup。
	 * 
	 * @param menu 菜单VO
	 * 
	 * @return HtmlMenuGroup
	 */
	public HtmlMenuGroup createMenuGroup(MenuEx menu) {
		HtmlMenuGroup group = new HtmlMenuGroup();
		group.setValue(menu.getName());
		group.setId("menu_"+menu.getId());
		if(!StringUtil.isEmpty(menu.getImageUrl())) {
			group.setIcon(menu.getImageUrl());
		}
		return group;
	}
	
	/**
	 * 创建htmlMenuItem。
	 * 
	 * @param menu MenuEx
	 * 
	 * @return HtmlMenuItem
	 */
	public HtmlMenuItem createMenuItem(MenuEx menu) {
		HtmlMenuItem menuItem = new HtmlMenuItem();
		menuItem.setValue(menu.getName());
		menuItem.setId("menu_"+menu.getId());//菜单id
		menuItem.setIcon(menu.getImageUrl());//菜单图标
		menuItem.setData(menu.getId());
		menuItem.setSubmitMode("ajax");
		if(menu.getMenuUrl()!=null) {
 			menuItem.getAttributes().put("menuUrl", menu.getMenuUrl());
 		}
		
 		menuItem.setOnclick("menuItem_click('menu_"+menu.getId()+"','"+menu.getName()+"','"+menu.getMenuUrl()+"','"+menu.getImageUrl()+"');;Event.stop(event);");
 		return menuItem;
	}
	
	private void createRootMenuItem(HtmlDropDownMenu dropDownMenu, MenuEx menu) {
		HtmlPanelGroup htmlPanelGroup = new HtmlPanelGroup();
		
		HtmlGraphicImage graphicImage = new HtmlGraphicImage();
		graphicImage.setUrl(menu.getImageUrl());
		
		HtmlOutputLabel htmlOutputLabel = new HtmlOutputLabel();
		htmlOutputLabel.setValue(menu.getName());
		htmlOutputLabel.setOnclick("menuItem_click('menu_"+menu.getId()+"','"+menu.getName()+"','"+menu.getMenuUrl()+"','"+menu.getImageUrl()+"');;Event.stop(event);");
		htmlOutputLabel.setStyleClass("rich-toolbar-group-outputLabe");
		
		if(!StringUtil.isEmpty(graphicImage.getUrl())) {
			htmlPanelGroup.getChildren().add(graphicImage);
			graphicImage.setOnclick("menuItem_click('menu_"+menu.getId()+"','"+menu.getName()+"','"+menu.getMenuUrl()+"','"+menu.getImageUrl()+"');;Event.stop(event);");
		}
		htmlPanelGroup.getChildren().add(htmlOutputLabel);
		
		dropDownMenu.getFacets().put("label", htmlPanelGroup);
	}
	
}
