package it6020003.process;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import it6020003.connection.ConnectionPoolImpl;
import it6020003.objects.AppointmentObjects;

public class Appointment {
	private Connection con;
	
	private ConnectionPoolImpl cp;
	
	public Appointment() {
		this.cp = new ConnectionPoolImpl();
		
		try {
			this.con = this.cp.getConnection("Appointment");
			if (con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<AppointmentObjects> getAppointmentObjects(AppointmentObjects similar, int total) {
		ArrayList<AppointmentObjects> items = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblappointment ");
		sql.append("ORDER BY app_id ASC ");
		sql.append("LIMIT ?");
		
		AppointmentObjects item;
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, total);
			
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					item = new AppointmentObjects();
					item.setApp_id(rs.getInt("app_id"));
					item.setApp_date(rs.getString("app_date"));
					item.setApp_time(rs.getString("app_time"));
					item.setApp_status(rs.getString("app_status"));
					item.setApp_created_date(rs.getString("app_created_date"));
					item.setApp_modified_date(rs.getString("app_modified_date"));
					item.setApp_notes(rs.getString("app_notes"));
					item.setApp_deleted(rs.getString("app_deleted"));
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_id(rs.getInt("doctor_id"));

					items.add(item);
				}
			}
			
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
		
		
		return items;
	}
	
	public ArrayList<AppointmentObjects> getAppointmentByDoctorId(int doctorId) {
		ArrayList<AppointmentObjects> items = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblappointment ");
		sql.append("WHERE doctor_id = ? and app_status = 'scheduled' ");
		sql.append("ORDER BY app_date ASC ");
		
		AppointmentObjects item;
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, doctorId);
			
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					item = new AppointmentObjects();
					item.setApp_id(rs.getInt("app_id"));
					item.setApp_date(rs.getString("app_date"));
					item.setApp_time(rs.getString("app_time"));
					item.setApp_status(rs.getString("app_status"));
					item.setApp_created_date(rs.getString("app_created_date"));
					item.setApp_modified_date(rs.getString("app_modified_date"));
					item.setApp_notes(rs.getString("app_notes"));
					item.setApp_deleted(rs.getString("app_deleted"));
					item.setUser_id(rs.getInt("user_id"));

					items.add(item);
				}
			}
			
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
		
		
		return items;
	}
	public ArrayList<AppointmentObjects> getDateAppointmentByDoctorId(int doctorId, String date) {
		ArrayList<AppointmentObjects> items = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblappointment ");
		sql.append("WHERE doctor_id = ? and app_status = 'scheduled' and app_date = ? ");
		sql.append("ORDER BY app_date ASC ");
		
		AppointmentObjects item;
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, doctorId);
			pre.setString(2, date);
			
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					item = new AppointmentObjects();
					item.setApp_id(rs.getInt("app_id"));
					item.setApp_date(rs.getString("app_date"));
					item.setApp_time(rs.getString("app_time"));
					item.setApp_status(rs.getString("app_status"));
					item.setApp_created_date(rs.getString("app_created_date"));
					item.setApp_modified_date(rs.getString("app_modified_date"));
					item.setApp_notes(rs.getString("app_notes"));
					item.setApp_deleted(rs.getString("app_deleted"));
					item.setUser_id(rs.getInt("user_id"));

					items.add(item);
				}
			}
			
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
		
		
		return items;
	}
	public ArrayList<AppointmentObjects> getAppointmentByUserId(int userId) {
		ArrayList<AppointmentObjects> items = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblappointment ");
		sql.append("WHERE user_id = ?");
		sql.append("ORDER BY app_date ASC ");
		
		AppointmentObjects item;
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, userId);
			
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					item = new AppointmentObjects();
					item.setApp_id(rs.getInt("app_id"));
					item.setApp_date(rs.getString("app_date"));
					item.setApp_time(rs.getString("app_time"));
					item.setApp_status(rs.getString("app_status"));
					item.setApp_created_date(rs.getString("app_created_date"));
					item.setApp_modified_date(rs.getString("app_modified_date"));
					item.setApp_notes(rs.getString("app_notes"));
					item.setApp_deleted(rs.getString("app_deleted"));
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_id(rs.getInt("doctor_id"));

					items.add(item);
				}
			}
			
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
		
		
		return items;
	}
	public int getTotalPatient(int doctor_id) {
		int total = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(app_id) FROM tblappointment ");
		sql.append("");
		sql.append("WHERE doctor_id = ?");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, doctor_id);
			
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
                // Retrieve the count value
                total = rs.getInt(1);
			}
			
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
		
		return total;
	}
	public int getTotalPatientDate(String today, int doctor_id) {
		int total = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(user_id) FROM tblappointment ");
		sql.append("");
		sql.append("WHERE app_date = ? AND doctor_id = ?");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, today);
			pre.setInt(2, doctor_id);
			
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
                // Retrieve the count value
                total = rs.getInt(1);
			}
			
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
		
		return total;
	}
	public int getTotalAppointmentDate(String today, int doctor_id) {
		int total = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(app_id) FROM tblappointment ");
		sql.append("");
		sql.append("WHERE app_date = ? AND doctor_id = ?");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, today);
			pre.setInt(2, doctor_id);
			
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
                // Retrieve the count value
                total = rs.getInt(1);
			}
			
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
		
		return total;
	}
	public int getTotalAppointment(int doctor_id) {
		int total = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(app_id) FROM tblappointment ");
		sql.append("");
		sql.append("WHERE doctor_id = ?");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, doctor_id);
			
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
                // Retrieve the count value
                total = rs.getInt(1);
			}
			
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
		
		return total;
	}
	
	public boolean addAppointment (AppointmentObjects item) {
		// sql command
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblappointment(");
		sql.append("app_date, app_time, app_status, app_notes, app_created_date, app_modified_date, user_id, doctor_id) ");
		sql.append("VALUES(?,?,?,?,?,?,?,?)");
		
		//set created date
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = currentDate.format(formatter);
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getApp_date());
		    pre.setString(2, item.getApp_time());
		    pre.setString(3, item.getApp_status());
		    pre.setString(4, item.getApp_notes());
		    pre.setString(5, formattedDate); // Set app_created_date
		    pre.setString(6, formattedDate); // Set app_modified_date
			pre.setInt(7, item.getUser_id());
			pre.setInt(8, item.getDoctor_id());
		    
			//Execute sql
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
	
	public boolean updateAppointment(AppointmentObjects item) {
		//Câu lệnh sql
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblappointment ");
		sql.append("SET ");
		sql.append("app_date = ?, app_time = ?, app_status = ?, app_notes = ?, app_modified_date = ? ");
		sql.append("WHERE app_id = ?"); // update by id
		
		//set date
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = currentDate.format(formatter);
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getApp_date());
		    pre.setString(2, item.getApp_time());
		    pre.setString(3, item.getApp_status());
		    pre.setString(4, item.getApp_notes());
		    pre.setString(4, formattedDate);
		    pre.setInt(6, item.getApp_id());
			
			//Execute sql
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
	
	public boolean deleteAppointment(int app_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tblappointment ");
		sql.append("");
		sql.append("WHERE app_id = ?");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, app_id);
			
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
	
	public ArrayList<AppointmentObjects> getAppointmentFromNow(AppointmentObjects similar, int number_days) {
		ArrayList<AppointmentObjects> items = new ArrayList<>();
		
		//sql
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblappointment ");
		sql.append("WHERE ");
		sql.append("DATEDIFF(Date(now()), Date(STR_TO_DATE(created_date,\\\"%d/%m/%Y\\\")) < ?");
		sql.append("");
		
		return items;
	}
	
	public ArrayList<Integer> getAppPerDay (Date startDate, Date endDate) {
		ArrayList<Integer> numbers = new ArrayList<>();
		
		return numbers;
	}
}
