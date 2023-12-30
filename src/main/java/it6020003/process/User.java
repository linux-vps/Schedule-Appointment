package it6020003.process;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import it6020003.connection.ConnectionPoolImpl;
import it6020003.objects.AppointmentObjects;
import it6020003.objects.UserObject;

public class User {
	// get connection
	private Connection con;
	
	//bo quan ly ket noi cua user
	private ConnectionPoolImpl cp;

	public User() {
		this.cp = new ConnectionPoolImpl();
		
		try {
			this.con = this.cp.getConnection("User");
			
			//kiem tra che do thuc thi tu dong
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false); //huy
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UserObject getUserById(int user_id) {
		UserObject item = new UserObject();
		
		//sql
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tbluser ");
		sql.append("WHERE user_id = ? ");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, user_id);
			
			//lay tap ket qua
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_birthday(rs.getString("user_birthday"));
					item.setUser_phone(rs.getString("user_phone"));
					item.setUser_password(rs.getString("user_password"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_job(rs.getString("user_job"));
					item.setUser_jobarea(rs.getString("user_jobarea"));
					item.setUser_permission(rs.getInt("user_permission"));
					item.setUser_roles(rs.getString("user_roles"));
					item.setUser_logined(rs.getBoolean("user_logined"));
					item.setUser_created_date(rs.getDate("user_created_date"));
					item.setUser_last_modified(rs.getDate("user_last_modified"));
					item.setUser_last_logined(rs.getDate("user_last_logined"));
					item.setUser_parent_id(rs.getInt("user_parent_id"));
					item.setUser_actions(rs.getInt("user_actions"));
					item.setUser_notes(rs.getString("user_notes"));
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			//tro ve trang thai an toan cua ket noi
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return item;
	}
	public UserObject getLoginedUser(String user_email, String user_password) {
		UserObject item = new UserObject();
		
		//sql
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tbluser ");
		sql.append("WHERE user_email = ? and user_password = ? ");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, user_email);
			pre.setString(2, user_password);
			
			//lay tap ket qua
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_birthday(rs.getString("user_birthday"));
					item.setUser_phone(rs.getString("user_phone"));
					item.setUser_password(rs.getString("user_password"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_job(rs.getString("user_job"));
					item.setUser_jobarea(rs.getString("user_jobarea"));
					item.setUser_permission(rs.getInt("user_permission"));
					item.setUser_roles(rs.getString("user_roles"));
					item.setUser_logined(rs.getBoolean("user_logined"));
					item.setUser_created_date(rs.getDate("user_created_date"));
					item.setUser_last_modified(rs.getDate("user_last_modified"));
					item.setUser_last_logined(rs.getDate("user_last_logined"));
					item.setUser_parent_id(rs.getInt("user_parent_id"));
					item.setUser_actions(rs.getInt("user_actions"));
					item.setUser_notes(rs.getString("user_notes"));
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			//tro ve trang thai an toan cua ket noi
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return item;
	}
	
	public ArrayList<UserObject> getUserObjects(UserObject similar, byte total) {
		ArrayList<UserObject> items = new ArrayList<>();
		UserObject item;
		
		//sql
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tbluser ");
		sql.append("");
		sql.append("ORDER BY user_id ASC ");
		sql.append("LIMIT ?");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			
			//set total
			pre.setByte(1, total);
			
			//lay tap ket qua
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_birthday(rs.getString("user_birthday"));
					item.setUser_phone(rs.getString("user_phone"));
					item.setUser_password(rs.getString("user_password"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_job(rs.getString("user_job"));
					item.setUser_jobarea(rs.getString("user_jobarea"));
					item.setUser_permission(rs.getInt("user_permission"));
					item.setUser_roles(rs.getString("user_roles"));
					item.setUser_logined(rs.getBoolean("user_logined"));
					item.setUser_created_date(rs.getDate("user_created_date"));
					item.setUser_last_modified(rs.getDate("user_last_modified"));
					item.setUser_last_logined(rs.getDate("user_last_logined"));
					item.setUser_parent_id(rs.getInt("user_parent_id"));
					item.setUser_actions(rs.getInt("user_actions"));
					item.setUser_notes(rs.getString("user_notes"));
					
					items.add(item);
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			//tro ve trang thai an toan cua ket noi
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		return items;
	}
	
	public ArrayList<UserObject> getAllDoctor(UserObject similar) {
		ArrayList<UserObject> items = new ArrayList<>();
		UserObject item;
		
		//sql
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tbluser ");
		sql.append("WHERE user_roles = 'd' ");
		sql.append("ORDER BY user_id ASC ");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			//lay tap ket qua
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_birthday(rs.getString("user_birthday"));
					item.setUser_phone(rs.getString("user_phone"));
					item.setUser_password(rs.getString("user_password"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_job(rs.getString("user_job"));
					item.setUser_jobarea(rs.getString("user_jobarea"));
					item.setUser_permission(rs.getInt("user_permission"));
					item.setUser_roles(rs.getString("user_roles"));
					item.setUser_logined(rs.getBoolean("user_logined"));
					item.setUser_created_date(rs.getDate("user_created_date"));
					item.setUser_last_modified(rs.getDate("user_last_modified"));
					item.setUser_last_logined(rs.getDate("user_last_logined"));
					item.setUser_parent_id(rs.getInt("user_parent_id"));
					item.setUser_actions(rs.getInt("user_actions"));
					item.setUser_notes(rs.getString("user_notes"));
					
					items.add(item);
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			//tro ve trang thai an toan cua ket noi
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		return items;
	}
	
	public boolean updateUser(UserObject item) {
		LocalDate currentDate = LocalDate.now();

        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Format the current date
        String formattedDate = currentDate.format(formatter);
        Date parsedDate = (Date) formatter.parse(formattedDate);
		item.setUser_last_modified(parsedDate);
		
		//Câu lệnh sql
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tbluser ");
		sql.append("SET ");
		sql.append("user_name = ?, ");
		sql.append("user_fullname = ?, ");
		sql.append("user_birthday = ?, ");
		sql.append("user_phone = ?, ");
		sql.append("user_password = ?, ");
		sql.append("user_email = ?, ");
		sql.append("user_address = ?, ");
		sql.append("user_job = ?, ");
		sql.append("user_jobarea = ?, ");
		sql.append("user_permission = ?, ");
		sql.append("user_roles = ?, ");
		sql.append("user_logined = ?, ");
		sql.append("user_last_modified = ?, ");
		sql.append("user_notes = ? ");
		sql.append("WHERE user_id = ?;"); // update by id

		try {
		    PreparedStatement pre = this.con.prepareStatement(sql.toString());
		    pre.setString(1, item.getUser_name());
		    pre.setString(2, item.getUser_fullname());
		    pre.setString(3, item.getUser_birthday());
		    pre.setString(4, item.getUser_phone());
		    pre.setString(5, item.getUser_password());
		    pre.setString(6, item.getUser_email());
		    pre.setString(7, item.getUser_address());
		    pre.setString(8, item.getUser_job());
		    pre.setString(9, item.getUser_jobarea());
		    pre.setInt(10, item.getUser_permission());
		    pre.setString(11, item.getUser_roles());
		    pre.setBoolean(12, item.isUser_logined());
		    pre.setDate(13, new java.sql.Date(item.getUser_last_modified().getTime()));
		    pre.setString(14, item.getUser_notes());
			//Execute update
			int result = pre.executeUpdate();
			if (result == 0) {
				this.con.rollback();
				return false;
			}
			
			//ghi nhan thuc thi sau cung
			this.con.commit();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return false;
	}
	
	public boolean deleteUser(int userId) {
		//sql
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tbluser ");
		sql.append("WHERE user_id = ?;"); //delete by id
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, userId);
			
			int result = pre.executeUpdate();
			if (result == 0) {
				this.con.rollback();
				return false;
			}
			this.con.commit();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		User u = new User();
		UserObject doctor = u.getUserById(203);
		Appointment a = new Appointment();
//		int count = a.getTotalPatient(203);
//		LocalDate today = LocalDate.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd, MMM yyyy");
//        String formattedDate = today.format(formatter);
		
		
        ArrayList<AppointmentObjects> apps = a.getAppointmentByDoctorId(doctor.getUser_id());
        apps.forEach(app -> {
        	System.out.print(app.getUser_id());
        });
        UserObject user = u.getUserById(1);
		
		System.out.print(user.getUser_fullname());
		
	}

}
