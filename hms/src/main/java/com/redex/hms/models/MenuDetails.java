package com.redex.hms.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="redex_menu_details")
public class MenuDetails {

	@Id
	@Column(name="menu_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer menuId;

	@Column(name="menu_caption")
	private String menuCaption;

	@Column(name="menu_view_id")
	private String viewId;

	@Column(name="parent_id")
	private String parentId;

	@Column(name="class_name")
	private String className;

	@Column(name="icon")
	private String icon;

	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(final Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenuCaption() {
		return menuCaption;
	}
	public void setMenuCaption(final String menuCaption) {
		this.menuCaption = menuCaption;
	}
	public String getViewId() {
		return viewId;
	}
	public void setViewId(final String viewId) {
		this.viewId = viewId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(final String parentId) {
		this.parentId = parentId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(final String className) {
		this.className = className;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(final String icon) {
		this.icon = icon;
	}






}
