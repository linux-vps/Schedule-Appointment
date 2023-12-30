package it6020003.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import it6020003.objects.AppointmentObjects;
import it6020003.objects.UserObject;
import it6020003.process.Appointment;
import it6020003.process.User;

@WebServlet("/doctorhome")
public class DoctorDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public DoctorDashboard() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		Appointment a = new Appointment();
		User u = new User();
		
		UserObject doctor = u.getUserById(203);
		PrintWriter out = response.getWriter();
		
		request.setAttribute("doctor_name", doctor.getUser_fullname());
		request.setAttribute("doctor_phone", doctor.getUser_phone());
		request.setAttribute("doctor_jobarea", doctor.getUser_jobarea());
		
		request.setAttribute("total_patient", a.getTotalPatient(doctor.getUser_id()));
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
		request.setAttribute("today_patient", a.getTotalPatientDate(formattedDate, doctor.getUser_id()));
		request.setAttribute("today_appointment", a.getTotalAppointmentDate(formattedDate, doctor.getUser_id()));
		
		//set upcoming appointment
    	ArrayList<AppointmentObjects> apps = a.getAppointmentByDoctorId(doctor.getUser_id());
        request.setAttribute("upcoming_app", this.showUpcomingAppointment(apps, request));
        
        //set today appointment
//        ArrayList<AppointmentObjects> apps1 = a.getDateAppointmentByDoctorId(doctor.getUser_id(), formattedDate);
//        request.setAttribute("today_apps", this.showTodayAppointment(apps1));

        // Forward the request to the JSP
        request.getRequestDispatcher("doctor-dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private StringBuilder showUpcomingAppointment(ArrayList<AppointmentObjects> apps, HttpServletRequest request) {
		StringBuilder out = new StringBuilder();
		User u = new User();
		apps.forEach(app -> {
			
			UserObject user = u.getUserById(app.getUser_id());
			
			out.append("<tr>");
			out.append("<td>");
			out.append("<h2 class=\"table-avatar\">");
			out.append("<a href=\"\"");
			out.append("class=\"avatar avatar-sm mr-2\"><img");
			out.append("class=\"avatar-img rounded-circle\"");
			out.append("src=\"assets/img/patients/patient6.jpg\" ");
			out.append("alt=\"User Image\"></a>");
			out.append("<a href=\"\">"+ user.getUser_fullname() +"<span>"+ user.getUser_phone() +"</span></a>");
			out.append("</h2>");
			out.append("</td>");
			out.append("<td>"+ app.getApp_date() +"<span");
			out.append("class=\"d-block text-info\">  "+ app.getApp_time() +"</span>");
			out.append("</td>");
			out.append("<td>"+ app.getApp_notes() +"</td>");
			out.append("<td><!-- Type of user --></td>");
			out.append("<td class=\"text-right\">");
			out.append("<div class=\"table-action\">");
			out.append("<a data-toggle=\"modal\" href=\"#view"+app.getApp_id()+"\" ");
			out.append("class=\"btn btn-sm bg-info-light\">");
			out.append("<i class=\"far fa-eye\"></i> View");
			out.append("</a>");
//			out.append("<a href=\"javascript:void(0);\"");
//			out.append("class=\"btn btn-sm bg-success-light\">");
//			out.append("<i class=\"fas fa-check\"></i> Accept");
//			out.append("</a>");
//			out.append("<a href=\"javascript:void(0);\"");
//			out.append("class=\"btn btn-sm bg-danger-light\">");
//			out.append("<i class=\"fas fa-times\"></i> Cancel");
//			out.append("</a>");
			out.append("</div>");
			out.append("</td>");
			out.append("</tr>");
			out.append(this.viewAppointment(app));
		});
		return out;
	}
	
	private StringBuilder viewAppointment(AppointmentObjects app) {
		StringBuilder out = new StringBuilder();
		
		out.append("<div class=\"modal fade\" id=\"view"+app.getApp_id()+"\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" aria-hidden=\"true\" tabindex=\"-1\">");
		out.append("<div class=\"modal-dialog modal-dialog-centered\">");
		out.append("<div class=\"modal-content\">");
		out.append("<div class=\"modal-header\">");
		out.append("<h5 class=\"modal-title\">Appointment Details</h5>");
		out.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("</div>");
		out.append("<div class=\"modal-body\">");
		out.append("<div class=\"form-content p-2\">");
		out.append("<h4 class=\"modal-title\">Information</h4>");
		out.append("<p class=\"mb-4\">"+app.getApp_date()+"</p>");
		out.append("<p class=\"mb-4\">"+app.getApp_time()+"</p>");

		out.append("</div>");
		out.append("<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Close</button>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		return out;
	}
	
	private StringBuilder showTodayAppointment(ArrayList<AppointmentObjects> apps) {
		StringBuilder out = new StringBuilder();
		User u = new User();
		apps.forEach(app -> {
			UserObject user = u.getUserById(app.getUser_id());
			
				out.append("<tr>");
				out.append("<td>");
				out.append("<h2 class=\"table-avatar\">");
				out.append("<a href=\"patient-profile.html\"");
				out.append("class=\"avatar avatar-sm mr-2\"><img");
				out.append("class=\"avatar-img rounded-circle\"");
				out.append("src=\"assets/img/patients/patient6.jpg\"");
				out.append("alt=\"User Image\"></a>");
				out.append("<a href=\"\">"+ user.getUser_fullname() +"<span>"+ user.getUser_phone() +"</span></a>");
				out.append("</h2>");
				out.append("</td>");
				out.append("<td>"+ app.getApp_date() +"<span");
				out.append("class=\"d-block text-info\">  "+ app.getApp_time() +"</span>");
				out.append("</td>");
				out.append("<td>"+ app.getApp_notes() +"</td>");
				out.append("<td><!-- Type of user --></td>");
				out.append("<td class=\"text-right\">");
				out.append("<div class=\"table-action\">");
				out.append("<a data-toggle=\"modal\" href=\"#view"+app.getApp_id()+"\" ");
				out.append("class=\"btn btn-sm bg-info-light\">");
				out.append("<i class=\"far fa-eye\"></i> View");
				out.append("</a>");
				out.append("</div>");
				out.append("</td>");
				out.append("</tr>");
				
				//modal view
				out.append(this.viewAppointment(app));
		});
		
		return out;
	}

}
