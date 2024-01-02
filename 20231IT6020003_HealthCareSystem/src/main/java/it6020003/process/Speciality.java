package it6020003.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it6020003.ConnectionPool;
import it6020003.ConnectionPoolImpl;
import it6020003.objects.SpecialityObject;
import it6020003.objects.UserObject;

public class Speciality {
	
	private Connection con;
	private ConnectionPool cp;
	
	public Speciality() {
		this.cp = new ConnectionPoolImpl();
		try {
			this.con = cp.getConnectionPool("Speciality");
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList <SpecialityObject> getSpecialityObjects(SpecialityObject similar, byte total){		
		ArrayList <SpecialityObject> items = new ArrayList <>();
		SpecialityObject item;
		String sql = "";
		sql += "SELECT * FROM tblspeciality ";
		sql += "";
		sql += "ORDER BY sp_id ASC ";
		sql += "LIMIT ? ";			
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);				
			//Truyền giá trị cho tham số, tổng số bản ghi
			pre.setByte(1, total);				
			ResultSet rs = pre.executeQuery(); // Lấy về tập kết quả
			if (rs != null) {
				while (rs.next()) {
					item = new SpecialityObject();
					
					item.setSp_id(rs.getInt("sp_id"));
					item.setSp_name(rs.getString("sp_name"));
					item.setSp_description(rs.getString("sp_description"));
					item.setSp_notes(rs.getString("sp_notes"));
					item.setSp_language(rs.getString("sp_language"));
					item.setSp_created_date(rs.getString("sp_created_date"));
					item.setSp_modified_date(rs.getString("sp_modified_date"));
					item.setSp_deleted(rs.getBoolean("sp_deleted"));
				   
					// Đưa vào tập hợp
					items.add(item);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			// Trở về trạng thái an toàn của kết nối
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}		
		return items;		
	}	


	public String getDoctorSp(int spId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT sp_name FROM tblspeciality "
				+ "WHERE sp_id = ? ;");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, spId);
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					String name = rs.getString("sp_name");
					return name;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	public static void main(String[] args) {
		Speciality sp = new Speciality();
		User u = new User();
		UserObject doctor = u.getUserById(203);
		String name = sp.getDoctorSp(doctor.getUser_parent_id());
		System.out.print(name);
		
	}

}
