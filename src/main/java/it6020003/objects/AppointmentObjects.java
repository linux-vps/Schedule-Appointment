package it6020003.objects;

public class AppointmentObjects {
	private int app_id;
	private String app_date;
	private String app_time;
	private String app_status;
	private String app_created_date;
	private String app_modified_date;
	private String app_notes;
	private String app_deleted;
	private int user_id;
	private int doctor_id;
	
	public AppointmentObjects() {
		super();
	}

	
	public int getUser_id() {
		return user_id;
	}


	public int getDoctor_id() {
		return doctor_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}


	public int getApp_id() {
		return app_id;
	}

	public String getApp_date() {
		return app_date;
	}

	public String getApp_time() {
		return app_time;
	}

	public String getApp_status() {
		return app_status;
	}

	public String getApp_created_date() {
		return app_created_date;
	}

	public String getApp_modified_date() {
		return app_modified_date;
	}

	public String getApp_notes() {
		return app_notes;
	}

	public String getApp_deleted() {
		return app_deleted;
	}

	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}

	public void setApp_date(String app_date) {
		this.app_date = app_date;
	}

	public void setApp_time(String app_time) {
		this.app_time = app_time;
	}

	public void setApp_status(String app_status) {
		this.app_status = app_status;
	}

	public void setApp_created_date(String app_created_date) {
		this.app_created_date = app_created_date;
	}

	public void setApp_modified_date(String app_modified_date) {
		this.app_modified_date = app_modified_date;
	}

	public void setApp_notes(String app_notes) {
		this.app_notes = app_notes;
	}

	public void setApp_deleted(String app_deleted) {
		this.app_deleted = app_deleted;
	}

	
}
