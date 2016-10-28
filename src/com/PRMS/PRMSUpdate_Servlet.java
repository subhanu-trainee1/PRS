package com.PRMS;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import javax.servlet.http.HttpServlet;


@WebServlet("/PRMSUpdate_Servlet")
public class PRMSUpdate_Servlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out= response.getWriter();
 		Connection connection=null;
 		Statement stmt =null;
 		int rs=0;
 		response.setContentType("text/html");
 				
 		String relationship = null;
		String firstname = null;
		String lastname = null;		
		String pid = null;
		String gender = null;
		String dob = null;
		String age = null;	
		String maritalstatus = null;
		String planguage = null;
		String address = null;
		String city = null;
		String state = null;
		String country = null;
		String zip = null;
		String homeph = null;		
		String workph = null;
		String cellph = null;
		String employer = null;
		String occupation = null;
		String emailid = null;
		String contactmethod = null;
		String consultDr = null;
		String emergencycontactperson = null;
		String emergencycontactpersonname = null;
		String emenrgencycontactpersonph = null;
		
		//String connectionURL = "jdbc:mysql://127.0.0.1/rapidd_db";;
		PreparedStatement pst=null;
		String query=null;
		try
 		{
			System.out.println("start try block of connection update servlet");
			  //Class.forName("com.mysql.jdbc.Driver");
			  //connection = DriverManager.getConnection(connectionURL, "root", "root"); 
			  connection = DBConnection.getConnection();
				System.out.println("Connection successfull");	
				out.println("<h3>"+"Connection successfull"+"</h3>");
			System.out.println("end try block of connection update servlet");
 		}

 		catch(Exception e)
 		{
 		} 		
		try
		{
			System.out.println("start try block of update servlet");
			stmt = connection.createStatement();
				relationship = request.getParameter("RELATION");
				firstname = request.getParameter("FIRSTNAME");
				lastname = request.getParameter("LASTNAME");
				pid = request.getParameter("PATIENTID");
				gender = request.getParameter("GENDER");
				dob = request.getParameter("DOB");
				age = request.getParameter("AGE");
				maritalstatus = request.getParameter("MARITIALSTATUS");
				planguage = request.getParameter("LANGUAGE");
				address = request.getParameter("ADDRESS"); 
				city = request.getParameter("CITY");
				state = request.getParameter("STATE");
				country = request.getParameter("COUNTRY");
				zip = request.getParameter("ZIP");
				homeph = request.getParameter("HOMEPHONE1");
				workph = request.getParameter("WORKPHONE");
				cellph = request.getParameter("CELLPHONE");
				employer = request.getParameter("EMPLOYER");
				occupation = request.getParameter("OCCUPATION");
				emailid = request.getParameter("EMAILID");
				contactmethod = request.getParameter("CONTACTMETHOD");
				consultDr = request.getParameter("CONSULTDOCTOR");
				emergencycontactperson = request.getParameter("CONTACTPERSON");
				emergencycontactpersonname = request.getParameter("CONTACTNAME");
				emenrgencycontactpersonph = request.getParameter("EMERGENCYPHONE");		 
			 			  
			  query = "update patientdetails set FirstName='"+firstname+"',LastName='"+lastname+"',Relationship='"+relationship+"',Gender='"+gender+"',DOB='"+dob+"',Age='"+age+"',MaritalStatus='"+maritalstatus+"',"
			  		+ "PrimaryLanguage='"+planguage+"',Address='"+address+"',City='"+city+"',State='"+state+"',Country='"+country+"',zipcode='"+zip+"',HomePhone='"+homeph+"',"
			  		+ "WorkPhone='"+workph+"',CellPhone='"+cellph+"',Employer='"+employer+"',Occupation='"+occupation+"',EmailID='"+emailid+"',PreferredContactMethod='"+contactmethod+"',ConsultDoctor='"+consultDr+"',"
			  		+ "EmergencyContactPerson='"+emergencycontactperson+"',EmergencyContactPersonName='"+emergencycontactpersonname+"', EmergencyContactPersonPhoneNumber='"+emenrgencycontactpersonph+"' where PatientID=?";
			  
			  //out.println("<h3>" +"inside query"+ "</h3>");
	 		  pst = connection.prepareStatement(query);
	 		  pst.setString(1, pid);	 		   		 
			  rs=pst.executeUpdate(); 	 		  
			  
	 		  if(rs==0)
			  {
	 			  	out.println("<html>");
					out.println("<body style = text-align:center;padding:100px>");
					out.println(pid+"</br>" );
					out.println("<h3>"+"....Record Is Not Able To Update...."+"</h3>");
					out.println("<h3> ....You want to Update Again?.... </h3>");
					//out.println("<a href=http://localhost:8080/HospitalManagementSystem/UpdateRecord.html><button style = width:200px; height:30px> Yes-UpdateAgain </button></a>");
					//out.println("<button type = button style = width:200px; height:30px> <a href = http://localhost:8080/Hospital_PatientRecord/HomepageV1_1.html> NO </a> </button>");
					out.println("<a href = UpdateRecord.html><button style = width:200px; height:30px; text-align:center> Yes-UpdateAgain </button></a>");
					out.println("<button type = button style = width:200px; height:30px; text-align:center> <a href = HomepageV1_1.html> NO </a> </button>");
					
					out.println("</body>");
					out.println("</html>");
		      }
		      else
		      {
                   out.println("<html>");
                   out.println("<body style = text-align:center;padding:100px>");       			   
       			   out.println("<br>");
       			   out.println("<h3>"+"All is well.........Record "+pid+" is Updated Successfully..."+ "</h3>");
       			   out.println("<h3> ....You want to Search Again?.... </h3>");       			   
       			   //out.println("<a href=http://localhost:8080/HospitalManagementSystem/UpdateRecord.html><button style = width:200px; height:30px> Yes-UpdateAgain </button></a>");
       			   //out.println("<a href=http://localhost:8080/HospitalManagementSystem/HomepageV1_1.html><button style = width:200px; height:30px> NO </button></a>");
       			   out.println("<a href = UpdateRecord.html><button style = width:200px; height:30px; text-align:center> Yes-UpdateAgain </button></a>");
    			   out.println("<a href = HomepageV1_1.html><button style = width:200px; height:30px; text-align:center> NO </button></a>");
       			   out.println("</body>");
       			   out.println("</html>");
		      }	 
	 		  
	 		  connection.close();
	 		 System.out.println("end try block of update servlet");
		 }
 		 
		catch(Exception e)
 		{
			System.out.println("start catch block of update servlet");
 				out.println("<html>");
 				out.println("<body style = text-align:center;padding:100px>");
 				out.println("parameter passing error");
 				out.println("<h4>"+ e +"</h4>"); 				
 				//out.println("<a href=http://localhost:8080/HospitalManagementSystem/UpdateRecord.html><button style = width:200px; height:30px> update-Again </button></a>");
 				//out.println("<a href=http://localhost:8080/HospitalManagementSystem/HomepageV1_1.html><button style = width:200px; height:30px> Exit </button></a>");
 				out.println("<a href = UpdateRecord.html><button style = width:200px; height:30px; text-align:center> update-Again </button></a>");
 				out.println("<a href = HomepageV1_1.html><button style = width:200px; height:30px; text-align:center> Back </button></a>");
 				out.println("</body>");
 				out.println("</html>");
 				System.out.println("end catch block of update servlet");
 		}	
	}

}
