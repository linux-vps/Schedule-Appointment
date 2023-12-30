package it6020003.objects;

import java.sql.Date;

public class UserObject {
	private int user_id;
	private String user_name;
	private String user_fullname;
	private String user_birthday;
	private String user_phone;
	private String user_password;
	private String user_email;
	private String user_address;
	private String user_job;
	private String user_jobarea;
	private int user_permission;
	private String user_roles;
	private boolean user_logined;
	private Date user_created_date;
	private Date user_last_modified;
	private Date user_last_logined;
	private int user_parent_id;
	private int user_actions;
	private String user_notes;
	public UserObject() {
		super();
	}
	public int getUser_id() {
		return user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public String getUser_fullname() {
		return user_fullname;
	}
	public String getUser_birthday() {
		return user_birthday;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public String getUser_password() {
		return user_password;
	}
	public String getUser_email() {
		return user_email;
	}
	public String getUser_address() {
		return user_address;
	}
	public String getUser_job() {
		return user_job;
	}
	public String getUser_jobarea() {
		return user_jobarea;
	}
	public int getUser_permission() {
		return user_permission;
	}
	public String getUser_roles() {
		return user_roles;
	}
	public boolean isUser_logined() {
		return user_logined;
	}
	public Date getUser_created_date() {
		return user_created_date;
	}
	public Date getUser_last_modified() {
		return user_last_modified;
	}
	public Date getUser_last_logined() {
		return user_last_logined;
	}
	public int getUser_parent_id() {
		return user_parent_id;
	}
	public int getUser_actions() {
		return user_actions;
	}
	public String getUser_notes() {
		return user_notes;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public void setUser_fullname(String user_fullname) {
		this.user_fullname = user_fullname;
	}
	public void setUser_birthday(String user_birthday) {
		this.user_birthday = user_birthday;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public void setUser_job(String user_job) {
		this.user_job = user_job;
	}
	public void setUser_jobarea(String user_jobarea) {
		this.user_jobarea = user_jobarea;
	}
	public void setUser_permission(int user_permission) {
		this.user_permission = user_permission;
	}
	public void setUser_roles(String user_roles) {
		this.user_roles = user_roles;
	}
	public void setUser_logined(boolean user_logined) {
		this.user_logined = user_logined;
	}
	public void setUser_created_date(Date user_created_date) {
		this.user_created_date = user_created_date;
	}
	public void setUser_last_modified(Date user_last_modified) {
		this.user_last_modified = user_last_modified;
	}
	public void setUser_last_logined(Date user_last_logined) {
		this.user_last_logined = user_last_logined;
	}
	public void setUser_parent_id(int user_parent_id) {
		this.user_parent_id = user_parent_id;
	}
	public void setUser_actions(int user_actions) {
		this.user_actions = user_actions;
	}
	public void setUser_notes(String user_notes) {
		this.user_notes = user_notes;
	}
	
}
