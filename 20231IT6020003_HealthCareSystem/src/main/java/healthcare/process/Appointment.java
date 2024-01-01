package healthcare.process;

import java.util.*;

import java.sql.*;
import healthcare.objects.*;
import healthcare.*;
import healthcare.ConnectionPool;
import healthcare.ConnectionPoolImpl;

public class Appointment {
	//Ket noi de lam viec voi csdl
	private Connection con;
	//Bộ quản lý kết nối của riêng User
	private ConnectionPool cp;
	public Appointment() {
		//xác định bộ quản lý kết nối
		this.cp = new ConnectionPoolImpl();	
		// xin kết nối để làm việc
		try {
			this.con = this.cp.getConnectionPool("Appointment");
			// Kiểm tra chế độ thực thi của kết nối
			if (this.con.getAutoCommit()) {
				// Huỷ chế độ thực thi tự động
				this.con.setAutoCommit(false);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Thêm appointment
    public boolean addAppointment(AppointmentObject appointment) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO tblappointment(");
        sql.append("app_date, app_time, app_status, app_created_date, ");
        sql.append("app_modified_date, app_notes, app_deleted, app_user_id, app_doctor_image, app_doctor_name)");
        sql.append("VALUES(?,?,?,?,?,?,?,?,?,?)");

        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, appointment.getApp_date());
            pre.setString(2, appointment.getApp_time());
            pre.setString(3, appointment.getApp_status());
            pre.setString(4, appointment.getApp_created_date());
            pre.setString(5, appointment.getApp_modified_date());
            pre.setString(6, appointment.getApp_notes());
            pre.setBoolean(7, appointment.isApp_deleted());
            pre.setInt(8, appointment.getApp_user_id());
            pre.setString(9, appointment.getApp_doctor_image());
            pre.setString(10, appointment.getApp_doctor_name());
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

    // Sửa appointment
    public boolean updateAppointment(AppointmentObject appointment) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE tblappointment ");
        sql.append("SET "
                + "app_date = ?, app_time = ?, app_status = ?, app_created_date = ?, "
                + "app_modified_date = ?, app_notes = ?, app_deleted = ? ,app_user_id = ? , app_doctor_image = ?, app_doctor_name = ?");
        sql.append(" WHERE app_id = ? ");

        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, appointment.getApp_date());
            pre.setString(2, appointment.getApp_time());
            pre.setString(3, appointment.getApp_status());
            pre.setString(4, appointment.getApp_created_date());
            pre.setString(5, appointment.getApp_modified_date());
            pre.setString(6, appointment.getApp_notes());
            pre.setBoolean(7, appointment.isApp_deleted());
            pre.setInt(8, appointment.getApp_id());
            pre.setInt(8, appointment.getApp_user_id());
            pre.setString(9, appointment.getApp_doctor_image());
            pre.setString(10, appointment.getApp_doctor_name());

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
 // Hàm huỷ appointment
    public boolean cancelAppointment(int appointmentId) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE tblappointment ");
        sql.append("SET app_status = 'Canceled' ");
        sql.append("WHERE app_id = ?");

        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, appointmentId);

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
    // Xoá appointment
    public boolean deleteAppointment(int app_id_del) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM tblappointment "
                + "WHERE app_id = ? ;");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, app_id_del);

            int result = pre.executeUpdate();
            if (result == 0) {
                this.con.rollback();
                return false;
            }

            this.con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách appointments
    public ArrayList<AppointmentObject> getAppointmentObjects() {
        ArrayList<AppointmentObject> items = new ArrayList<>();
        AppointmentObject item;
        String sql = "SELECT * FROM tblappointment";
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    item = new AppointmentObject();
                    item.setApp_id(rs.getInt("app_id"));
                    item.setApp_date(rs.getString("app_date"));
                    item.setApp_time(rs.getString("app_time"));
                    item.setApp_status(rs.getString("app_status"));
                    item.setApp_created_date(rs.getString("app_created_date"));
                    item.setApp_modified_date(rs.getString("app_modified_date"));
                    item.setApp_notes(rs.getString("app_notes"));
                    item.setApp_deleted(rs.getBoolean("app_deleted"));
                    item.setApp_user_id(rs.getInt("app_user_id"));
                    item.setApp_doctor_image(rs.getString("app_doctor_image"));
                    item.setApp_doctor_name(rs.getString("app_doctor_name"));

                    items.add(item);
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
        return items;
    }
 // Hàm lấy dữ liệu cuộc hẹn từ cơ sở dữ liệu cho một người dùng cụ thể
    public ArrayList<Integer> getAppointmentStatusCountByUserId(int userId) {
        ArrayList<Integer> statusCounts = new ArrayList<>();
        
        // Khởi tạo mảng chứa số lượng cho từng trạng thái
        int[] counts = new int[3];
        
        String sql = "SELECT app_status, COUNT(*) FROM tblappointment WHERE app_user_id = ? GROUP BY app_status";
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setInt(1, userId);
            ResultSet rs = pre.executeQuery();
            
            // Khởi tạo mảng trạng thái để lưu trữ Confirmed, Pending, Canceled
            String[] statusArray = {"Confirmed", "Pending", "Canceled"};
            
            // Khởi tạo mảng đếm index tương ứng với Confirmed (0), Pending (1), Canceled (2)
            int[] statusIndex = {0, 1, 2};

            // Khởi tạo map để lưu trữ index của từng trạng thái
            Map<String, Integer> statusIndexMap = new HashMap<>();
            for (int i = 0; i < statusArray.length; i++) {
                statusIndexMap.put(statusArray[i], statusIndex[i]);
            }

            // Xử lý kết quả từ ResultSet
            while (rs.next()) {
                String status = rs.getString("app_status");
                int count = rs.getInt(2);
                int index = statusIndexMap.get(status);
                counts[index] = count;
            }

            // Thêm số lượng vào mảng kết quả
            for (int count : counts) {
                statusCounts.add(count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statusCounts;
    }

    // Lấy danh sách appointments theo điều kiện (user_id)
    public ArrayList<AppointmentObject> getAppointmentsByUserId(int user_id) {
        ArrayList<AppointmentObject> items = new ArrayList<>();
        AppointmentObject item;
        String sql = "SELECT * FROM tblappointment WHERE app_user_id = ?";
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
	        // Truyền giá trị cho tham số
	        pre.setInt(1,user_id );
            ResultSet rs = pre.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    item = new AppointmentObject();
                    item.setApp_id(rs.getInt("app_id"));
                    item.setApp_date(rs.getString("app_date"));
                    item.setApp_time(rs.getString("app_time"));
                    item.setApp_status(rs.getString("app_status"));
                    item.setApp_created_date(rs.getString("app_created_date"));
                    item.setApp_modified_date(rs.getString("app_modified_date"));
                    item.setApp_notes(rs.getString("app_notes"));
                    item.setApp_deleted(rs.getBoolean("app_deleted"));
                    item.setApp_user_id(rs.getInt("app_user_id"));
                    item.setApp_doctor_image(rs.getString("app_doctor_image"));
                    item.setApp_doctor_name(rs.getString("app_doctor_name"));

                    items.add(item);
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
        return items;
    }
    // Đếm số lượng appointments
    public int countAppointments() {
        String sql = "SELECT COUNT(*) FROM tblappointment";
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
    public static void main(String[] args) {
        Appointment appointment = new Appointment();
        Scanner scanner = new Scanner(System.in);
        AppointmentObject newAppointment = new AppointmentObject();

        while (true) {
            System.out.println("\n\n");
            System.out.println("=========== MENU QUẢN LÝ APPOINTMENT ===========");
            System.out.println("1. Thêm appointment");
            System.out.println("2. Sửa appointment");
            System.out.println("3. Xoá appointment");
            System.out.println("4. Hiển thị danh sách appointments");
            System.out.println("5. Hiển thị danh sách appointments theo User ID");
            System.out.println("6. Đếm số lượng appointments");
            System.out.println("0. Thoát");
            System.out.println("===================================================");

            System.out.print("Chọn chức năng (0-6): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            System.out.println("\n\n");
            switch (choice) {
                case 1:
                    // Thêm appointment
                    System.out.print("Nhập ngày appointment (yyyy-MM-dd): ");
                    String appDateAdd = scanner.nextLine();
                    newAppointment.setApp_date(appDateAdd);

                    System.out.print("Nhập giờ appointment (HH:mm:ss): ");
                    String appTimeAdd = scanner.nextLine();
                    newAppointment.setApp_time(appTimeAdd);

                    System.out.print("Nhập trạng thái appointment: ");
                    String appStatusAdd = scanner.nextLine();
                    newAppointment.setApp_status(appStatusAdd);

                    System.out.print("Nhập ngày tạo appointment (yyyy-MM-dd): ");
                    String appCreatedDateAdd = scanner.nextLine();
                    newAppointment.setApp_created_date(appCreatedDateAdd);

                    System.out.print("Nhập ngày sửa appointment (yyyy-MM-dd): ");
                    String appModifiedDateAdd = scanner.nextLine();
                    newAppointment.setApp_modified_date(appModifiedDateAdd);

                    System.out.print("Nhập ghi chú appointment: ");
                    String appNotesAdd = scanner.nextLine();
                    newAppointment.setApp_notes(appNotesAdd);

                    System.out.print("Nhập trạng thái xoá appointment (true/false): ");
                    boolean appDeletedAdd = scanner.nextBoolean();
                    newAppointment.setApp_deleted(appDeletedAdd);

                    System.out.print("Nhập User ID của bệnh nhân: ");
                    int appUserIdAdd = scanner.nextInt();
                    newAppointment.setApp_user_id(appUserIdAdd);

                    System.out.print("Nhập đường dẫn ảnh của bác sĩ: ");
                    scanner.nextLine(); // Đọc bỏ dòng new line còn lại
                    String appDoctorImageAdd = scanner.nextLine();
                    newAppointment.setApp_doctor_image(appDoctorImageAdd);

                    System.out.print("Nhập tên của bác sĩ: ");
                    String appDoctorNameAdd = scanner.nextLine();
                    newAppointment.setApp_doctor_name(appDoctorNameAdd);

                    System.out.println("\n\n");
                    if (appointment.addAppointment(newAppointment)) {
                        System.out.println("Thêm appointment thành công!");
                    } else {
                        System.out.println("Thêm appointment ---KHÔNG THÀNH CÔNG---!");
                    }
                    System.out.println("\n\n");
                    break;
                case 2:
                    // Sửa appointment
                    System.out.print("Nhập id của appointment cần sửa: ");
                    int appIDUpdate = scanner.nextInt();
                    scanner.nextLine();

                    newAppointment.setApp_id(appIDUpdate);

                    System.out.print("Nhập ngày appointment mới (yyyy-MM-dd): ");
                    String appDateUpdate = scanner.nextLine();
                    newAppointment.setApp_date(appDateUpdate);

                    System.out.print("Nhập giờ appointment mới (HH:mm:ss): ");
                    String appTimeUpdate = scanner.nextLine();
                    newAppointment.setApp_time(appTimeUpdate);

                    System.out.print("Nhập trạng thái appointment mới: ");
                    String appStatusUpdate = scanner.nextLine();
                    newAppointment.setApp_status(appStatusUpdate);

                    System.out.print("Nhập ngày tạo appointment mới (yyyy-MM-dd): ");
                    String appCreatedDateUpdate = scanner.nextLine();
                    newAppointment.setApp_created_date(appCreatedDateUpdate);

                    System.out.print("Nhập ngày sửa appointment mới (yyyy-MM-dd): ");
                    String appModifiedDateUpdate = scanner.nextLine();
                    newAppointment.setApp_modified_date(appModifiedDateUpdate);

                    System.out.print("Nhập ghi chú appointment mới: ");
                    String appNotesUpdate = scanner.nextLine();
                    newAppointment.setApp_notes(appNotesUpdate);

                    System.out.print("Nhập trạng thái xoá appointment mới (true/false): ");
                    boolean appDeletedUpdate = scanner.nextBoolean();
                    newAppointment.setApp_deleted(appDeletedUpdate);

                    System.out.print("Nhập User ID của bệnh nhân mới: ");
                    int appUserIdUpdate = scanner.nextInt();
                    newAppointment.setApp_user_id(appUserIdUpdate);

                    System.out.print("Nhập đường dẫn ảnh của bác sĩ mới: ");
                    scanner.nextLine(); // Đọc bỏ dòng new line còn lại
                    String appDoctorImageUpdate = scanner.nextLine();
                    newAppointment.setApp_doctor_image(appDoctorImageUpdate);

                    System.out.print("Nhập tên của bác sĩ mới: ");
                    String appDoctorNameUpdate = scanner.nextLine();
                    newAppointment.setApp_doctor_name(appDoctorNameUpdate);

                    System.out.println("\n\n");
                    if (appointment.updateAppointment(newAppointment)) {
                        System.out.println("Sửa appointment thành công!");
                    } else {
                        System.out.println("Sửa appointment ---KHÔNG THÀNH CÔNG---!");
                    }
                    System.out.println("\n\n");
                    break;
                case 3:
                    // Xoá appointment
                    System.out.print("Nhập id của appointment cần xoá: ");
                    int appIDDelete = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("\n\n");
                    if (!appointment.deleteAppointment(appIDDelete)) {
                        System.out.println("Xoá appointment " + appIDDelete + "---KHÔNG THÀNH CÔNG---!");
                    } else {
                        System.out.println("Xoá appointment thành công!");
                    }
                    System.out.println("\n\n");
                    break;
                case 4:
                    // Hiển thị danh sách appointments
                    ArrayList<AppointmentObject> appointmentItems = appointment.getAppointmentObjects();
                    appointmentItems.forEach(item -> {
                        System.out.println(item);
                    });
                    break;
                case 5:
                    // Hiển thị danh sách appointments theo User ID
                    System.out.print("Nhập User ID để hiển thị danh sách appointments: ");
                    int userIdToShow = scanner.nextInt();
                    ArrayList<AppointmentObject> appointmentsByUserId = appointment.getAppointmentsByUserId(userIdToShow);
                    appointmentsByUserId.forEach(item -> {
                        System.out.println(item);
                    });
                    break;
                case 6:
                    // Đếm số lượng appointments
                    int appCount = appointment.countAppointments();
                    System.out.println("Số lượng appointments: " + appCount);
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
   