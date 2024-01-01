package it6020003.process;

import java.util.*;

import java.sql.*;

import it6020003.ConnectionPool;
import it6020003.ConnectionPoolImpl;
import it6020003.objects.UserObject;

public class User {
	//Ket noi de lam viec voi csdl
	private Connection con;
	//Bộ quản lý kết nối của riêng User
	private ConnectionPool cp;
	public User() {
		//xác định bộ quản lý kết nối
		this.cp = new ConnectionPoolImpl();	
		// xin kết nối để làm việc
		try {
			this.con = this.cp.getConnectionPool("User");
			// Kiểm tra chế độ thực thi của kết nối
			if (this.con.getAutoCommit()) {
				// Huỷ chế độ thực thi tự động
				this.con.setAutoCommit(false);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList <UserObject> getUserObjects(UserObject similar, byte total){		
			ArrayList <UserObject> items = new ArrayList <>();
			UserObject item;		
			String sql = "";
			sql += "SELECT * FROM tbluser ";
			sql += "";
			sql += "ORDER BY user_name ASC ";
			sql += "LIMIT ? ";			
			try {
				PreparedStatement pre = this.con.prepareStatement(sql);				
				//Truyền giá trị cho tham số, tổng số bản ghi
				pre.setByte(1, total);				
				ResultSet rs = pre.executeQuery(); // Lấy về tập kết quả
				if (rs != null) {
					while (rs.next()) {
						item = new UserObject();			
						item.setUser_id(rs.getInt("user_id"));
						item.setUser_name(rs.getString("user_name"));
						item.setUser_fullname(rs.getString("user_fullname"));
						item.setUser_phone(rs.getString("user_phone"));
						item.setUser_birthday(rs.getString("user_birthday"));
						item.setUser_password(rs.getString("user_password"));
						item.setUser_email(rs.getString("user_email"));
						item.setUser_address(rs.getString("user_address"));
						item.setUser_job(rs.getString("user_job"));
						item.setUser_jobarea(rs.getString("user_jobarea"));
						item.setUser_permission(rs.getInt("user_permission"));
						item.setUser_roles(rs.getString("user_roles"));
						item.setUser_logined(rs.getInt("user_logined"));
						item.setUser_created_date(rs.getString("user_created_date"));
						item.setUser_last_modified(rs.getString("user_last_modified"));
						item.setUser_last_logined(rs.getString("user_last_logined"));
						item.setUser_notes(rs.getString("user_notes"));
						item.setUser_parent_id(rs.getInt("user_parent_id"));
						item.setUser_actions(rs.getInt("user_actions"));				   
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
	public boolean addUser(UserObject user) {
	    StringBuilder sql = new StringBuilder();
	    sql.append("INSERT INTO tbluser(");
	    sql.append("user_name, user_fullname, user_phone, user_birthday, ");
	    sql.append("user_password, user_email, user_address, user_job, ");
	    sql.append("user_jobarea, user_permission, user_roles, user_logined, ");
	    sql.append("user_created_date, user_last_modified, user_last_logined, user_notes, ");
	    sql.append("user_parent_id, user_actions)");
	    sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

	    try {
	        PreparedStatement pre = this.con.prepareStatement(sql.toString());
	        pre.setString(1, user.getUser_name());
	        pre.setString(2, user.getUser_fullname());
	        pre.setString(3, user.getUser_phone());
	        pre.setString(4, user.getUser_birthday());
	        pre.setString(5, user.getUser_password());
	        pre.setString(6, user.getUser_email());
	        pre.setString(7, user.getUser_address());
	        pre.setString(8, user.getUser_job());
	        pre.setString(9, user.getUser_jobarea());
	        pre.setInt(10, user.getUser_permission());
	        pre.setString(11, user.getUser_roles());
	        pre.setInt(12, user.getUser_logined());
	        pre.setString(13, user.getUser_created_date());
	        pre.setString(14, user.getUser_last_modified());
	        pre.setString(15, user.getUser_last_logined());
	        pre.setString(16, user.getUser_notes());
	        pre.setInt(17, user.getUser_parent_id());
	        pre.setInt(18, user.getUser_actions());

	        int result = pre.executeUpdate();
	        if (result == 0) {
	            this.con.rollback();
	            return false;
	        }

	        this.con.commit();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            this.con.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    }
	    return false;
	}

	public boolean updateUser(UserObject user) {
	    StringBuilder sql = new StringBuilder();
	    sql.append("UPDATE tbluser ");
	    sql.append("SET "
	            + "user_name = ?, user_fullname = ?, user_phone = ?, user_birthday = ?, "
	            + "user_password = ?, user_email = ?, user_address = ?, user_job = ?, "
	            + "user_jobarea = ?, user_permission = ?, user_roles = ?, user_logined = ?, "
	            + "user_created_date = ?, user_last_modified = ?, user_last_logined = ?, user_notes = ?, "
	            + "user_parent_id = ?, user_actions = ? ");
	    sql.append(" WHERE user_id = ? ");

	    try {
	        PreparedStatement pre = this.con.prepareStatement(sql.toString());
	        pre.setString(1, user.getUser_name());
	        pre.setString(2, user.getUser_fullname());
	        pre.setString(3, user.getUser_phone());
	        pre.setString(4, user.getUser_birthday());
	        pre.setString(5, user.getUser_password());
	        pre.setString(6, user.getUser_email());
	        pre.setString(7, user.getUser_address());
	        pre.setString(8, user.getUser_job());
	        pre.setString(9, user.getUser_jobarea());
	        pre.setInt(10, user.getUser_permission());
	        pre.setString(11, user.getUser_roles());
	        pre.setInt(12, user.getUser_logined());
	        pre.setString(13, user.getUser_created_date());
	        pre.setString(14, user.getUser_last_modified());
	        pre.setString(15, user.getUser_last_logined());
	        pre.setString(16, user.getUser_notes());
	        pre.setInt(17, user.getUser_parent_id());
	        pre.setInt(18, user.getUser_actions());
	        pre.setInt(19, user.getUser_id());

	        int result = pre.executeUpdate();
	        if (result == 0) {
	            this.con.rollback();
	            return false;
	        }

	        this.con.commit();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            this.con.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    }
	    return false;
	}

	public boolean deleteUser(int user_id_del) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tbluser "
				+ "WHERE user_id = ? ;");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, user_id_del);
			//thực thi
			int result = pre.executeUpdate();
			if (result==0) {
				this.con.rollback();
				return false;
			}		
			//Ghi nhận thực thi sau cùng
			this.con.commit();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//search by id
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
					item.setUser_logined(rs.getInt("user_logined"));
					item.setUser_created_date(rs.getString("user_created_date"));
					item.setUser_last_modified(rs.getString("user_last_modified"));
					item.setUser_last_logined(rs.getString("user_last_logined"));
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
					item.setUser_logined(rs.getInt("user_logined"));
					item.setUser_created_date(rs.getString("user_created_date"));
					item.setUser_last_modified(rs.getString("user_last_modified"));
					item.setUser_last_logined(rs.getString("user_last_logined"));
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
	// Tìm kiếm theo điều kiện 
	public ArrayList<UserObject> searchUserByCondition(String condition) {    
	    ArrayList<UserObject> items = new ArrayList<>();
	    UserObject item;
	    String sql = "SELECT * FROM tbluser WHERE " + condition;
	    try {
	        PreparedStatement pre = this.con.prepareStatement(sql);
	        ResultSet rs = pre.executeQuery(); // Lấy về tập kết quả
	        if (rs != null) {
	            while (rs.next()) {
	                item = new UserObject();
	                item.setUser_id(rs.getInt("user_id"));
	                item.setUser_name(rs.getString("user_name"));
	                item.setUser_fullname(rs.getString("user_fullname"));
	                item.setUser_phone(rs.getString("user_phone"));
	                item.setUser_birthday(rs.getString("user_birthday"));
	                item.setUser_password(rs.getString("user_password"));
	                item.setUser_email(rs.getString("user_email"));
	                item.setUser_address(rs.getString("user_address"));
	                item.setUser_job(rs.getString("user_job"));
	                item.setUser_jobarea(rs.getString("user_jobarea"));
	                item.setUser_permission(rs.getInt("user_permission"));
	                item.setUser_roles(rs.getString("user_roles"));
	                item.setUser_logined(rs.getInt("user_logined"));
	                item.setUser_created_date(rs.getString("user_created_date"));
	                item.setUser_last_modified(rs.getString("user_last_modified"));
	                item.setUser_last_logined(rs.getString("user_last_logined"));
	                item.setUser_notes(rs.getString("user_notes"));
	                item.setUser_parent_id(rs.getInt("user_parent_id"));
	                item.setUser_actions(rs.getInt("user_actions"));

	                // Đưa vào tập hợp
	                items.add(item);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Trở về trạng thái an toàn của kết nối
	        try {
	            this.con.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    }
	    return items;
	}
	// Tìm user theo tên
	public ArrayList<UserObject> searchUserByName(String keyword) {
	    ArrayList<UserObject> items = new ArrayList<>();
	    UserObject item;
	    String sql = "SELECT * FROM tbluser WHERE user_name LIKE ?";
	    try {
	        PreparedStatement pre = this.con.prepareStatement(sql);
	        // Truyền giá trị cho tham số, từ khóa tìm kiếm
	        pre.setString(1, "%" + keyword + "%");
	        ResultSet rs = pre.executeQuery(); // Lấy về tập kết quả
	        if (rs != null) {
	            while (rs.next()) {
	                item = new UserObject();
	                item.setUser_id(rs.getInt("user_id"));
	                item.setUser_name(rs.getString("user_name"));
	                item.setUser_fullname(rs.getString("user_fullname"));
	                item.setUser_phone(rs.getString("user_phone"));
	                item.setUser_birthday(rs.getString("user_birthday"));
	                item.setUser_password(rs.getString("user_password"));
	                item.setUser_email(rs.getString("user_email"));
	                item.setUser_address(rs.getString("user_address"));
	                item.setUser_job(rs.getString("user_job"));
	                item.setUser_jobarea(rs.getString("user_jobarea"));
	                item.setUser_permission(rs.getInt("user_permission"));
	                item.setUser_roles(rs.getString("user_roles"));
	                item.setUser_logined(rs.getInt("user_logined"));
	                item.setUser_created_date(rs.getString("user_created_date"));
	                item.setUser_last_modified(rs.getString("user_last_modified"));
	                item.setUser_last_logined(rs.getString("user_last_logined"));
	                item.setUser_notes(rs.getString("user_notes"));
	                item.setUser_parent_id(rs.getInt("user_parent_id"));
	                item.setUser_actions(rs.getInt("user_actions"));

	                // Đưa vào tập hợp
	                items.add(item);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Trở về trạng thái an toàn của kết nối
	        try {
	            this.con.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    }
	    return items;
	}

	// đếm tổng số đối tượng
	public int countUser() {

	  String sql = "SELECT COUNT(*) FROM tbluser";
	  try {
	    PreparedStatement pre = this.con.prepareStatement(sql);
	    ResultSet rs = pre.executeQuery();
	    rs.next();
	    return rs.getInt(1);
	  } catch (SQLException e) {
	    e.printStackTrace();
	  }
	  return 0;
	}	
	// Đếm theo điều kiện
	public int countProductByCondition(String condition) {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM tbluser WHERE " + condition;
	    try {
	        PreparedStatement pre = this.con.prepareStatement(sql);
	        ResultSet rs = pre.executeQuery();
	        if (rs != null) {
	            while (rs.next()) {
	                count = rs.getInt(1);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Trở về trạng thái an toàn của kết nối
	        try {
	            this.con.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    }
	    return count;
	}
	// Kiểm tra đăng nhập
	public UserObject checkLogin(String username, String password) {
	    UserObject user = null;
	    String sql = "SELECT * FROM tbluser WHERE user_name = ? AND user_password = ?";
	    
	    try {
	        PreparedStatement pre = this.con.prepareStatement(sql);
	        pre.setString(1, username);
	        pre.setString(2, password);

	        ResultSet rs = pre.executeQuery();
	        if (rs != null && rs.next()) {
	            user = new UserObject();
	            user.setUser_id(rs.getInt("user_id"));
	            user.setUser_name(rs.getString("user_name"));
	            user.setUser_fullname(rs.getString("user_fullname"));
	            user.setUser_phone(rs.getString("user_phone"));
	            user.setUser_birthday(rs.getString("user_birthday"));
	            user.setUser_password(rs.getString("user_password"));
	            user.setUser_email(rs.getString("user_email"));
	            user.setUser_address(rs.getString("user_address"));
	            user.setUser_job(rs.getString("user_job"));
	            user.setUser_jobarea(rs.getString("user_jobarea"));
	            user.setUser_permission(rs.getInt("user_permission"));
	            user.setUser_roles(rs.getString("user_roles"));
	            user.setUser_logined(rs.getInt("user_logined"));
	            user.setUser_created_date(rs.getString("user_created_date"));
	            user.setUser_last_modified(rs.getString("user_last_modified"));
	            user.setUser_last_logined(rs.getString("user_last_logined"));
	            user.setUser_notes(rs.getString("user_notes"));
	            user.setUser_parent_id(rs.getInt("user_parent_id"));
	            user.setUser_actions(rs.getInt("user_actions"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return user;
	}
	// Đăng ký tài khoản
	public boolean registerUser(String username, String password, String email) {
	    StringBuilder sql = new StringBuilder();
	    sql.append("INSERT INTO tbluser(");
	    sql.append("user_name, user_password, user_email)");
	    sql.append("VALUES(?,?,?)");

	    try {
	        PreparedStatement pre = this.con.prepareStatement(sql.toString());
	        pre.setString(1, username);
	        pre.setString(2, password);
	        pre.setString(3, email);

	        int result = pre.executeUpdate();
	        if (result == 0) {
	            this.con.rollback();
	            return false;
	        }

	        this.con.commit();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            this.con.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    }
	    return false;
	}
    // Kiểm tra xem username đã tồn tại chưa
    public boolean checkUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM tbluser WHERE user_name = ?";
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            if (rs != null && rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra xem email đã tồn tại chưa
    public boolean checkEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM tbluser WHERE user_email = ?";
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            if (rs != null && rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	public static void main(String[] args) {
		//Tao doi tuong lam viec voi User
		User user = new User();
		Scanner scanner = new Scanner(System.in);
		//Tao doi tuong user moi
		UserObject nu = new UserObject();	
		while (true) {
			System.out.println("\n\n");
            System.out.println("=========== MENU QUẢN LÝ user ===========");
            System.out.println("1. Thêm user");
            System.out.println("2. Sửa user");
            System.out.println("3. Xoá user");
            System.out.println("4. Tìm kiếm theo tên");
            System.out.println("5. Tìm kiếm theo điền kiện");
            System.out.println("6. Đếm số lượng theo điều kiện");
            System.out.println("7. Hiển thị danh sách user hiện tại");
            System.out.println("8. Đăng nhập");
            System.out.println("9. Đăng ký");
            System.out.println("0. Thoát");
            System.out.println("===================================================");
            System.out.println("\n");
            System.out.print("Chọn chức năng (0-7): ");                 
            int choice = scanner.nextInt();
            scanner.nextLine(); // Loại bỏ ký tự new line
            System.out.println("\n\n");
            switch (choice) {
                case 1:
                    // Thêm user         
                    System.out.print("Nhập tên user: ");
                    String userNameAdd = scanner.nextLine();
                    nu.setUser_name(userNameAdd);
                    System.out.print("Nhập tên user full name: ");
                    String userFullNameAdd = scanner.nextLine();
                    nu.setUser_fullname(userFullNameAdd);
                    System.out.print("Nhập SĐT: ");
                    String phoneAdd = scanner.nextLine();
                    nu.setUser_phone(phoneAdd);
                    System.out.print("Nhập mật khẩu: ");
                    String userPasswordAdd = scanner.nextLine();
                    nu.setUser_password(userPasswordAdd);
                    scanner.nextLine(); // Loại bỏ ký tự new line
                    
                    // Set các thuộc tính khác tương tự như trên
                    System.out.println("\n\n");
                    if (user.addUser(nu)) {
                        System.out.println("Thêm user thành công!");
                    } else {
                        System.out.println("Thêm user ---KHÔNG THÀNH CÔNG---!");
                    }
                    System.out.println("\n\n");
                    break;
                case 2:
                	// Sửa user
                	System.out.print("Nhập id của user cần sửa: ");
                	short user_ID_Update = scanner.nextShort();
                	scanner.nextLine(); // Loại bỏ ký tự new line
                	nu.setUser_id(user_ID_Update);              	
                    System.out.print("Nhập tên user: ");
                    String user_Name_Update = scanner.nextLine();
                    nu.setUser_name(user_Name_Update);
                    System.out.print("Nhập tên user full name: ");
                    String userFullNameUpdate = scanner.nextLine();
                    nu.setUser_fullname(userFullNameUpdate);
                    System.out.print("Nhập SĐT: ");
                    String phoneUpdate = scanner.nextLine();
                    nu.setUser_phone(phoneUpdate);
                    System.out.print("Nhập mật khẩu: ");
                    String userPasswordUpdate = scanner.nextLine();
                    nu.setUser_password(userPasswordUpdate);
                    scanner.nextLine(); // Loại bỏ ký tự new line
                    System.out.println("\n\n");
                    if (user.updateUser(nu)) {
                        System.out.println("Sửa user thành công!");
                    } else {
                        System.out.println("Sửa user ---KHÔNG THÀNH CÔNG---!");
                    }
                    System.out.println("\n\n");
                    break;
                case 3:
                	// Xoá user
                	System.out.print("Nhập id của user cần xoá: ");
                	short user_ID_Del = scanner.nextShort();
                	nu.setUser_id(user_ID_Del);
                	System.out.println("\n\n");
            		if(!user.deleteUser(user_ID_Del)) {
            			System.out.println("Xoá user" + user_ID_Del + "---KHÔNG THÀNH CÔNG---!");
	        		} else {
	        			System.out.println("Xoá user thành công!");
	        		}
            		System.out.println("\n\n");
                    break;
                case 4:
                	// Tìm kiếm theo tên
            	    // Tìm kiếm các User có ps_name chứa từ khoá là String
                    System.out.print("Nhập tên user cần tìm kiếm: ");
                    String user_Name_Search = scanner.nextLine();
            		ArrayList<UserObject> search = user.searchUserByName(user_Name_Search);           		
            		if (search == null) {
            			System.out.println("Tìm kiếm user " + user_Name_Search + " ---KHÔNG THÀNH CÔNG---!");
            		} else {
                		//In ra man hinh
                		System.out.println("\n\n");
                		search.forEach(item -> {
                			System.out.println(item);
                		});
                		System.out.println("\n\n");
            		}            		
                	break;
                case 5:       
            		// Tìm kiếm theo điều kiện   
                    System.out.print("Nhập điều kiện: ");
                    System.out.print("SELECT COUNT(*) FROM tbluser WHERE ");
                    String user_Condittion = scanner.nextLine();
            		ArrayList<UserObject> search_condition = user.searchUserByCondition(user_Condittion);
            		if (search_condition == null) {
            			System.out.println("Tìm user thoả mãn '" + search_condition + "' ---KHÔNG THÀNH CÔNG---!");
            		} else {
                		System.out.println("\n\n");
                		search_condition.forEach(item -> {
                			System.out.println(item);
                		});
                		System.out.println("\n\n");
            		}
                	break;
                case 6:
            		// Đếm theo điều kiện String "chuỗi điều kiện"
                	System.out.print("Nhập điều kiện: ");
                    System.out.print("SELECT COUNT(*) FROM tbluser WHERE ");
                    String Condition = scanner.nextLine();
            		int productCount = user.countProductByCondition(Condition);	
            		if(productCount == 0) {
            			System.out.println("---KHÔNG THÀNH CÔNG KHI ĐẾM SỐ LƯỢNG THEO ĐIỀU KIỆN---");
            		}
            		else {
            			System.out.println("Số user thoả mãn điều kiện "+Condition+" là: ");
            			System.out.println(productCount);
            		}
                	break;              	
                case 7:
                    // Hiển thị danh sách
            		ArrayList<UserObject> items = user.getUserObjects(null, (byte)10);
            		//In ra man hinh
            		items.forEach(item -> {
            			System.out.println(item);
            		});           		
                    break;
                case 8:
                    // Kiểm tra đăng nhập
                    System.out.print("Nhập tên đăng nhập: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Nhập mật khẩu: ");
                    String loginPassword = scanner.nextLine();
                    UserObject loggedInUser = user.checkLogin(loginUsername, loginPassword);

                    if (loggedInUser != null) {
                        System.out.println("Đăng nhập thành công!");
                        // Thực hiện các hành động sau khi đăng nhập thành công
                    } else {
                        System.out.println("Đăng nhập thất bại. Tên đăng nhập hoặc mật khẩu không đúng.");
                    }
                    break;
                case 9:
                    // Đăng ký tài khoản mới
                    System.out.print("Nhập tên đăng nhập mới: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Nhập mật khẩu mới: ");
                    String newPassword = scanner.nextLine();
//                    System.out.print("Nhập họ tên: ");
//                    String newFullName = scanner.nextLine();
//                    System.out.print("Nhập số điện thoại: ");
//                    String newPhone = scanner.nextLine();
                    System.out.print("Nhập địa chỉ email: ");
                    String newEmail = scanner.nextLine();

                    if (user.registerUser(newUsername, newPassword, newEmail)) {
                        System.out.println("Đăng ký thành công!");
                    } else {
                        System.out.println("Đăng ký thất bại. Có lỗi xảy ra.");
                    }
                    break;

                case 0:
                    System.out.println("Thoát chương trình. Cảm ơn!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Chọn sai. Vui lòng chọn lại.");
                    break;
            }
		}
	}
}

