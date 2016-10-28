package com.PRMS;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/PRMS_Servlet")
public class PRMS_Servlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
				
		try
		{
			System.out.println("inside try block of servlet");
			//out.println("inside try block of servlet");
			//Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/rapidd_db", "root", "root");
			Connection conn = DBConnection.getConnection();			
			
			System.out.println("Connection successfull");	
			//out.println("<h3>"+"Connection successfull"+"</h3>");
			
			//fetch data from html form
			String relationship = request.getParameter("relationship");		
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String patientid = request.getParameter("patientid");
			String gender = request.getParameter("gender");
			String DOB = request.getParameter("DOB");
			String age = request.getParameter("age");
			String maritalstatus = request.getParameter("maritalstatus");
			String primarylanguage = request.getParameter("primarylanguage");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String country = request.getParameter("country");
			String zipcode = request.getParameter("zipcode");
			String homephone = request.getParameter("homephone");
			String workphone = request.getParameter("workphone");
			String cellphone = request.getParameter("cellphone");
			String employer = request.getParameter("employer");
			String occupation = request.getParameter("occupation");
			String emailid = request.getParameter("emailid");
			String preferredcontactmethod = request.getParameter("contactmethod");
			String consultdoctor = request.getParameter("Dr.name");
			String emergencycontactperson = request.getParameter("emergencycontactperson");
			String emergencycontactpersonname = request.getParameter("emergencycontactpersonname");
			String emergencycontactpersonnumber = request.getParameter("emergencycontactpersonnumber");
						
			String s2 = request.getParameter("s1");
			
			//insert data into database through form
			if(s2.equals("insert"))
			{
				//out.println("inside insert");
				PreparedStatement ps = conn.prepareStatement("insert into patientdetails values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
								
				ps.setString(1, relationship);
				ps.setString(2, firstname);
				ps.setString(3, lastname);
				ps.setString(4, patientid);
				ps.setString(5, gender);
				ps.setString(6, DOB);
				ps.setString(7, age);
				ps.setString(8, maritalstatus);
				ps.setString(9, primarylanguage);
				ps.setString(10, address);
				ps.setString(11, city);
				ps.setString(12, state);
				ps.setString(13, country);
				ps.setString(14, zipcode);
				ps.setString(15, homephone);
				ps.setString(16, workphone);
				ps.setString(17, cellphone);
				ps.setString(18, employer);
				ps.setString(19, occupation);
				ps.setString(20, emailid);
				ps.setString(21, preferredcontactmethod);
				ps.setString(22, consultdoctor);
				ps.setString(23, emergencycontactperson);
				ps.setString(24, emergencycontactpersonname);
				ps.setString(25, emergencycontactpersonnumber);
				ps.setString(26, "no");	
				
				int i = ps.executeUpdate();
				if (i != 0)
				{
					out.println("<body style = text-align:center;padding:100px>");
					out.println("<h3> ....Inserted successfully.... </h3>");
					out.println("<h3> ....You want to Register Again?.... </h3>");
					
					//out.println("<button type = button style = width:200px; height:30px> <a href = http://localhost:8080/HospitalManagementSystem/PatientRegistrationFormV1_7.html> Yes-RegisterAgain </a> </button>");
					//out.println("<button type = button style = width:200px; height:30px> <a href = http://localhost:8080/HospitalManagementSystem/HomepageV1_1.html> NO </a> </button>");
					out.println("<button type = button style = width:200px; height:30px> <a href = PatientRegistrationFormV1_7.html> Yes-RegisterAgain </a> </button>");
					out.println("<button type = button style = width:200px; height:30px> <a href = HomepageV1_1.html> NO </a> </button>");
					
					out.println("</body>");
				}					
				else
				{
					out.println("<body style = text-align:center;padding:100px>");
					out.println("<h3> Inserted failed </h3>");
					out.println("<h3> ....You want to Register Again?.... </h3>");
					//out.println("<button type = button style = width:200px; height:30px> <a href = http://localhost:8080/HospitalManagementSystem/PatientRegistrationFormV1_7.html> Yes-RegisterAgain </a> </button>");
					//out.println("<button type = button style = width:200px; height:30px> <a href = http://localhost:8080/HospitalManagementSystem/HomepageV1_1.html> NO </a> </button>");
					out.println("<button type = button style = width:200px; height:30px> <a href = PatientRegistrationFormV1_7.html> Yes-RegisterAgain </a> </button>");
					out.println("<button type = button style = width:200px; height:30px> <a href = HomepageV1_1.html> NO </a> </button>");
					
					out.println("</body>");
				}
			}
			
			//Search patient based on is_deleted field and Pid
			else if(s2.equals("Search"))
			{	
				//out.println("inside search");
				String query = null;
				String pids=request.getParameter("pid");
				String[] array = pids.split(":");
				String part1 = array[0];
				String part2 = array[1];
				int count=0;
				
				part2 = part2.replace(",","','");				
				if(part1.equals("id")||part1.equals("ID")||part1.equals("Id"))
				{
					query= "select * from patientdetails where is_deleted='no' and PatientID IN('"+part2+"')";
				}
				if(part1.equals("name")||part1.equals("NAME")||part1.equals("Name"))
				{
					query= "select * from patientdetails where is_deleted='no' and FirstName IN('"+part2+"')";
				}
				
				System.out.println("pids"+pids);
				System.out.println("query"+query);
				System.out.println("part1"+part1);	
				System.out.println("part2"+part2);
				if(pids.length()>0)
				{
					
					System.out.println("pids="+pids);
					System.out.println("query::"+query);
					Statement stmt=conn.createStatement();
					ResultSet rset=stmt.executeQuery(query);					
														
					if(rset!=null)
					{
						rset.beforeFirst();
						rset.last();
						count=rset.getRow();
					}
					
					rset.beforeFirst();
					if(count>0)
					{						
						out.println("<h3 style = text-align:center;padding:100px> Searched successfully with ID/Name " + part2 + "</h3>");	
						out.println("<html>");
						out.println("<style>");
						out.println("table,th,td { text-align:center;border-collapse:collapse;border:1px solid black;}");					
						out.println("</style>");						
						out.println("<body>");
						out.println("<table style = width:100%>");
						out.println("<tr>");
						out.println("<th> Relationship </th>");
						out.println("<th> First Name </th>");
						out.println("<th> Last Name </th>");
						out.println("<th> PatientID </th>");
						out.println("<th> Gender </th>");
						out.println("<th> DOB </th>");
						out.println("<th> Age </th>");
						out.println("<th> MaritalStatus </th>");
						out.println("<th> PrimaryLangauge </th>");
						out.println("<th> Address </th>");
						out.println("<th> City </th>");
						out.println("<th> State </th>");
						out.println("<th> Country </th>");
						out.println("<th> Zipcode </th>");
						out.println("<th> Homephone </th>");
						out.println("<th> Workphone </th>");
						out.println("<th> Cellphone </th>");
						out.println("<th> Employer </th>");
						out.println("<th> Occupation </th>");
						out.println("<th> EmailID </th>");
						out.println("<th> PrefferedCOntactMethod </th>");
						out.println("<th> ConsultDoctor </th>");
						out.println("<th> EmergencyContactPerson </th>");
						out.println("<th> EmergencyContactPersonName </th>");
						out.println("<th> EmergencyContactPersonPhoneNumber </th>");
						out.println("</tr>");
							
						while(rset.next())
						{
							out.println("<tr>");
							out.println("<td>" +rset.getString(1)+ "</td>");
							out.println("<td>" +rset.getString(2)+ "</td>");
							out.println("<td>" +rset.getString(3)+ "</td>");
							out.println("<td>" +rset.getString(4)+ "</td>");
							out.println("<td>" +rset.getString(5)+ "</td>");
							out.println("<td>" +rset.getString(6)+ "</td>");
							out.println("<td>" +rset.getString(7)+ "</td>");
							out.println("<td>" +rset.getString(8)+ "</td>");
							out.println("<td>" +rset.getString(9)+ "</td>");
							out.println("<td>" +rset.getString(10)+ "</td>");
							out.println("<td>" +rset.getString(11)+ "</td>");
							out.println("<td>" +rset.getString(12)+ "</td>");
							out.println("<td>" +rset.getString(13)+ "</td>");
							out.println("<td>" +rset.getString(14)+ "</td>");
							out.println("<td>" +rset.getString(15)+ "</td>");
							out.println("<td>" +rset.getString(16)+ "</td>");
							out.println("<td>" +rset.getString(17)+ "</td>");
							out.println("<td>" +rset.getString(18)+ "</td>");
							out.println("<td>" +rset.getString(19)+ "</td>");
							out.println("<td>" +rset.getString(20)+ "</td>");
							out.println("<td>" +rset.getString(21)+ "</td>");
							out.println("<td>" +rset.getString(22)+ "</td>");
							out.println("<td>" +rset.getString(23)+ "</td>");
							out.println("<td>" +rset.getString(24)+ "</td>");
							out.println("<td>" +rset.getString(25)+ "</td>");
							out.println("</tr>");						
						}
						out.println("</table>");
						out.println("<br />");
						out.println("<br />");
						out.println("<h3> ....You want to Search Again?.... </h3>");
						out.println("<html>");
						out.println("<body style = text-align:center;padding:100px>");
						//out.println("<button type = button style = width:200px;height:30px;text-align:center> <a href = http://localhost:8080/HospitalManagementSystem/searchPatient.html> Yes-SearchAgain </a> </button>");
						//out.println("<button type = button style = width:200px; height:30px;text-align:center> <a href = http://localhost:8080/HospitalManagementSystem/HomepageV1_1.html> NO </a> </button>");
						out.println("<button type = button style = width:200px; height:30px> <a href = searchPatient.html> Yes-SearchAgain </a> </button>");
						out.println("<button type = button style = width:200px; height:30px> <a href = HomepageV1_1.html> NO </a> </button>");
						out.println("</body>");
						out.println("<html>");
					} //end if for count	
					else
					{
						out.println("<html>");
			 			out.println("<body style = text-align:center;padding:100px >");			 			
			 			out.println("Patient Record is "+pids+"  ");
			 			out.println("<h2> Please Enter a valid PatientID/Name OR Entered PatientID/Name is not found ...</h2>");
			 			out.println("<h3> ....You want to Search Again?.... </h3>");
			 			//out.println("<a href=http://localhost:8080/Hospital_PatientRecord/searchPatient.html><button style = width:200px; height:30px> Yes-SearchAgain </button> </a>");
			 			//out.println("<button type = button style = width:200px; height:30px> <a href = http://localhost:8080/HospitalManagementSystem/HomepageV1_1.html> NO </a> </button>");
			 			out.println("<a href= searchPatient.html> <button style = width:200px; height:30px> Yes-SearchAgain </button> </a>");
			 			out.println("<button type = button style = width:200px; height:30px> <a href = HomepageV1_1.html> NO </a> </button>");
			 			
			 			out.println("</body>");
			 			out.println("<html>");
					}			
				}//end if for pid.length>0
				else
				{
					out.println("<html>");
			 		out.println("<body style = text-align:center;padding:100px>");			 		
			 		out.println("Patient ID is "+pids+"  ");
			 		out.println("<h2> Please Enter a valid PatientID/Name OR Entered PatientID/Name is not found ...</h2>");
			 		out.println("<h3> ....You want to Search Again?.... </h3>");
			 		//out.println("<a href=http://localhost:8080/HospitalManagementSystem/searchPatient.html><button style = width:200px; height:30px> Yes-SearchAgain </button> </a>");
			 		//out.println("<button type = button style = width:200px; height:30px> <a href = http://localhost:8080/HospitalManagementSystem/HomepageV1_1.html> NO </a> </button>");
			 		out.println("<a href = searchPatient.html><button style = width:200px; height:30px> Yes-SearchAgain </button> </a>");
			 		out.println("<button type = button style = width:200px; height:30px> <a href = HomepageV1_1.html> NO </a> </button>");
			 		out.println("</body>");
			 		out.println("</html>");
				}
			}//end if for search condition
			
			//Update records saved in database
			else if(s2.equals("update"))
			{
				//out.println("inside update");
				String patientId = request.getParameter("pid");
				//String query = "select * from PatientDetails where PatientID = '"+patientId+"' and is_deleted ='no'";
				String query = "select * from patientdetails where PatientID = ? and is_deleted ='no'";
				PreparedStatement pst2 = null;
				Statement stmt = null;
				ResultSet rset = null;
				int count = 0;
				if(patientId.length()>0)
				{
					pst2=conn.prepareStatement(query);
					pst2.setString(1,patientId);
					stmt = conn.createStatement();
					rset = pst2.executeQuery();					
				}					
								
				if(rset!=null)
				{
					rset.beforeFirst();
					rset.last();
					count=rset.getRow();
				}
				//System.out.println("count1="+count);
				rset.beforeFirst();
				if(count>0)
				{
					out.println("<html>");
					out.println("<style>");
					out.println("table,th,td { text-align:center;border-collapse:collapse;border:1px solid black;}");					
					out.println("</style>");
					out.println("<body>");
					out.println("<table style = width:100%>");
		     		out.println("<tr>");
		     			out.println("<th> Relationship </th>");
		     			out.println("<th> FirstName </th>");
		     			out.println("<th> LastName </th>");
		     			out.println("<th> PatientID </th>");
		     			out.println("<th> Gender </th>");
		     			out.println("<th> DOB </th>");
		     			out.println("<th> Age </th>");
		     			out.println("<th> MaritalStatus </th>");
		     			out.println("<th> PrimaryLangauge </th>");
		     			out.println("<th> Address </th>");
		     			out.println("<th> City </th>");
		     			out.println("<th> State </th>");
		     			out.println("<th> Country </th>");
		     			out.println("<th> Zipcode </th>");
		     			out.println("<th> Homephone </th>");
		     			out.println("<th> Workphone </th>");
		     			out.println("<th> Cellphone </th>");
		     			out.println("<th> Employer </th>");
		     			out.println("<th> Occupation </th>");
		     			out.println("<th> EmailID </th>");
		     			out.println("<th> PrefferedCOntactMethod </th>");
		     			out.println("<th> ConsultDoctor </th>");
		     			out.println("<th> EmergencyContactPerson </th>");
						out.println("<th> EmergencyContactPersonName </th>");
						out.println("<th> EmergencyContactPersonPhoneNumber </th>");
		     		 out.println("</tr>");
		     		
		     		 //String i = null;
		     		 int size = rset.getFetchSize();
		     		 System.out.println("size="+size);
		     		 while(rset.next())
					 {
		     		 	out.println("<h3>"+"get string from database"+"</h3>");
		     			 String pid=rset.getString(4);
		     			 	out.println("<tr>");
		     			 		out.println("<td>" +rset.getString(1)+ "</td>");
		     			 		out.println("<td>" +rset.getString(2)+ "</td>");
		     			 		out.println("<td>" +rset.getString(3)+ "</td>");
		     			 		out.println("<td>" +rset.getString(4)+ "</td>");
		     			 		out.println("<td>" +rset.getString(5)+ "</td>");
		     			 		out.println("<td>" +rset.getString(6)+ "</td>");
		     			 		out.println("<td>" +rset.getString(7)+ "</td>");
		     			 		out.println("<td>" +rset.getString(8)+ "</td>");
		     			 		out.println("<td>" +rset.getString(9)+ "</td>");
		     			 		out.println("<td>" +rset.getString(10)+ "</td>");
		     			 		out.println("<td>" +rset.getString(11)+ "</td>");
		     			 		out.println("<td>" +rset.getString(12)+ "</td>");
		     			 		out.println("<td>" +rset.getString(13)+ "</td>");
		     			 		out.println("<td>" +rset.getString(14)+ "</td>");
		     			 		out.println("<td>" +rset.getString(15)+ "</td>");
		     			 		out.println("<td>" +rset.getString(16)+ "</td>");
		     			 		out.println("<td>" +rset.getString(17)+ "</td>");
		     			 		out.println("<td>" +rset.getString(18)+ "</td>");
		     			 		out.println("<td>" +rset.getString(19)+ "</td>");
		     			 		out.println("<td>" +rset.getString(20)+ "</td>");
		     			 		out.println("<td>" +rset.getString(21)+ "</td>");
		     			 		out.println("<td>" +rset.getString(22)+ "</td>");
		     			 		out.println("<td>" +rset.getString(23)+ "</td>");
		     			 		out.println("<td>" +rset.getString(24)+ "</td>");
		     			 		out.println("<td>" +rset.getString(25)+ "</td>");
		     			 		out.println("</tr>");
					  
		     			out.println("<html>");
		     			out.println("<head>");
		     			out.println("</head>");
		     			out.println("<body>");
		     			out.println("<form action=PRMSUpdate_Servlet method=post>");
					
						out.println("<table width=800 align=center border=0>");
						out.println("<br />");
						out.println("<br />");
							//out.println("<tr><td align=center height=65 class=banner ><font=red size=20>Hospital Management</font></td></tr>");
							out.println("<tr><td><div align=center class=brownhead> Update Patient Information </div><br><br>");
							out.println("<table  align='center'  cellpadding=5 bgcolor=skyblue>");
							out.println("");
							
							out.println("<tr><td> Relationship </td><td>"
									+ "<select name = RELATION value="+rset.getString(1)+">"
											+ "<option> Father </option>"
											+ "<option> Mother </option>"
											+ "<option> Son </option>"
											+ "<option> Daughter </option>"
											+ "<option> GrandFather </option>"
											+ "<option> GrandMother </option>"
											+ "<option> Brother </option>"
											+ "<option> Sister </option>"
											+ "<option> Husband </option>"
											+ "<option> Wife </option"
											+ "><option> Other </option>"
									+ "</select></td></tr>");
							out.println("<tr><td> First Name </td><td><input type=text name=FIRSTNAME value="+rset.getString(2)+"></td></tr>");
							out.println("<tr><td> Last Name </td><td><input type=text name=LASTNAME value="+rset.getString(3)+"></td></tr>");
							out.println(" <tr><td> Patient ID </td><td><input type=text name=PATIENTID value="+pid+"></td></tr> ");
							out.println("<tr><td> Gender </td><td>"
									+ "<select name=GENDER value="+rset.getString(5)+">"
											+ "<option> Male </option>"
											+ "<option> Female </option>"
											+ "<option> Other </option>"
									+ "</select></td></tr>");						
							out.println("<tr><td> Date of Birth </td><td> <input type=text name=DOB value="+rset.getString(6)+"></td></tr>");
							out.println("<tr><td> Age </td><td><input type=text name=AGE value="+rset.getString(7)+"></td></tr>");
							out.println("<tr><td> Matital Status </td><td>"
									+ "<select name =MARITIALSTATUS value="+rset.getString(8)+">"
											+ "<option> Single </option>"
											+ "<option> Married </option>"
											+ "<option> Unmarried </option>"
											+ "<option> Widowed </option>"
											+ "<option> Divorced </option>"
											+ "<option> Other </option>"
									+ "</select></td></tr>");
							out.println("<tr><td> Primary Language </td><td>"
									+ "<select name=LANGUAGE value="+rset.getString(9)+">"
											+ "<option> Kannada </option>	"
											+ "<option> English </option>"
											+ "<option> Hindi </option>"
											+ "<option> Telugu </option>"
											+ "<option> Tamil </option>"
											+ "<option> Others </option>"											
									+ "</select></td></tr>");
							out.println("<tr><td> Address </td><td><input type=text name=ADDRESS value="+rset.getString(10)+"></td></tr>");
							out.println("<tr><td> City </td><td><input type=text name=CITY value="+rset.getString(11)+"></td></tr>");
							out.println("<tr><td> State </td><td><input type=text name=STATE value="+rset.getString(12)+"></td></tr>");
							out.println("<tr><td> Country </td><td><input type=text name=COUNTRY value="+rset.getString(13)+"></td></tr>");
							out.println("<tr><td> Postal code </td><td><input type=text name=ZIP value="+rset.getString(14)+"></td></tr>");			
							out.println("<tr><td> Home Phone </td><td><input type=text name=HOMEPHONE value=080 style = width:50px></td><td><input type=text name=HOMEPHONE1 value="+rset.getString(15)+"></td></tr>");
							out.println("<tr><td> Work Phone </td><td><input type=text name=WORKPHONE value="+rset.getString(16)+"></td></tr>");
							out.println("<tr><td> Cell Phone </td><td><input type=text name=cell value=+91 style = width:50px></td><td><input type=text name=CELLPHONE value="+rset.getString(17)+"></td></tr>");
							out.println("<tr><td> Employer </td><td><input type=text name=EMPLOYER value="+rset.getString(18)+"></td></tr>");
							out.println("<tr><td> Occupation </td><td><input type=text name=OCCUPATION value="+rset.getString(19)+"></td></tr>");
							out.println("<tr><td> Email ID </td><td><input type=text name=EMAILID value="+rset.getString(20)+"></td></tr>");
							out.println("<tr><td> Preferred Contact Method </td><td>"
									+ "<select multiple name =CONTACTMETHOD value="+rset.getString(21)+">"
											+ "<option> Email </option>"
											+ "<option> Cell Phone </option>"
											+ "<option> Home Phone </option>"
											+ "<option> Work Phone </option>"
											+ "<option> Text Message </option>"
									+ "</select></td></tr>");
							out.println("<tr><td> Consulting Doctor </td><td> "
									+ "<select name=CONSULTDOCTOR value="+rset.getString(22)+">"
											+ "<option>  Dr Arun Shet, M.D., AB (Hem/Onc)  </option>"
											+ "<option> Dr Rishikesh Kumar, M.D. </option>"
											+ "<option> Dr. Arun Mukesh </option>"
											+ "<option> Dr. Veena shetty </option>"
											+ "<option> Dr. Shelly </option>"
											+ "<option> Dr. Vinod kumar </option>"
									+ "</select></td></tr>");
							out.println("<tr><td> Emergency Contact Person </td><td> "
									+ "<select name=CONTACTPERSON value="+rset.getString(23)+">"
											+ "<option> Father </option>"
											+ "<option> Mother </option>"
											+ "<option> Son </option>"
											+ "<option> Daughter </option>"
											+ "<option> Brother </option>"
											+ "<option> Sister </option>"
											+ "<option> Other </option>"
											+ "<option> Husband </option>"
											+ "<option> Wife </option>"
									+ "</select></td></tr>");
							out.println("<tr><td> Emergency Contact Person Name  </td><td> <input type=text name =CONTACTNAME value="+rset.getString(24)+"></td></tr>");
							out.println("<tr><td> Emergency Contact Person Phone Number </td><td><input type=text name=EMERGENCYPHONE value="+rset.getString(25)+"></td></tr>");
							out.println("<tr align=center><td colspan=2><input type=submit name=Update value=Update></td></tr>");
							out.println(" <div align=center><hr width=100>");
							//out.println("<td colspan=2><a href=http://localhost:8080/HospitalManagementSystem/UpdateRecord.html><button style = width:200px; height:30px; tex-align:center> UpdateAgain </button></a></td>");
							//out.println("<td colspan=2><a href=http://localhost/FirstServlet/Home.html><button> Home </button></a></td>");
							out.println("<td colspan=2><a href = UpdateRecord.html><button style = width:200px; height:30px; tex-align:center> UpdateAgain </button></a></td>");
						out.println("</table>");
						out.println("</form>");
						out.println("</body>");
						out.println("</html>");
					 }
				}
		     	else
				{
					out.println("<html>");
			 		out.println("<body style = text-align:center;padding:100px>");			 		
			 		//out.println("Patient Record is--------"+patientId+"--------- ");
			 		out.println("<h2> Please Enter a valid PatientID/Name OR Entered PatientID/Name is not found ...</h2>");
			 		out.println("<h3> ....You want to Update Again?.... </h3>");
			 		//out.println("<a href=http://localhost:8080/HospitalManagementSystem/UpdateRecord.html><button style = width:200px; height:30px> Yes-UpdateAgain </button> </a>");
			 		//out.println("<button type = button style = width:200px; height:30px> <a href = http://localhost:8080/Hospital_PatientRecord/HomepageV1_1.html> NO </a> </button>");
			 		out.println("<a href = UpdateRecord.html><button style = width:200px; height:30px> Yes-UpdateAgain </button> </a>");
			 		out.println("<button type = button style = width:200px; height:30px> <a href = HomepageV1_1.html> NO </a> </button>");
			 		out.println("</body>");
			 		out.println("<html>");
				}				
				
			}
			
			
			//soft delete multiple row from database using multiple patient id's			
			else if(s2.equals("delete"))
			{	
				//out.println("inside delete");
				String deleteArray[] = request.getParameterValues("PatientID");
				String deleteIds = "";
				
				if(deleteArray != null)
				{
					for(int i=0; i<deleteArray.length; i++)
					{
						deleteIds = deleteIds+deleteArray[i];
						if(i<deleteArray.length-1)
						{
							deleteIds = deleteIds+",";
						}
					}
					
					PreparedStatement ps2 = conn.prepareStatement("update patientdetails set is_deleted='yes' where PatientID in ("+deleteIds+")");
					int row = ps2.executeUpdate();
					if(row > 0)
					{
						out.println("<body style = text-align:center;padding:100px>");
						out.println("<h3> Deleted successfully with patient ID(s) = " + deleteIds + "</h3>");	
						out.println("<h3> ....You want to Delete Again?.... </h3>");						
						//out.println("<button type = button style = width:200px; height:30px> <a href = http://localhost:8080/HospitalManagementSystem/DeleteRecord.html> Yes-DeleteAgain </a> </button>");
						//out.println("<button type = button style = width:200px; height:30px> <a href = http://localhost:8080/HospitalManagementSystem/HomepageV1_1.html> NO </a> </button>");
						out.println("<button type = button style = width:200px; height:30px> <a href = DeleteRecord.html> Yes-DeleteAgain </a> </button>");
						out.println("<button type = button style = width:200px; height:30px> <a href = HomepageV1_1.html> NO </a> </button>");
						out.println("</body>");
					}
					else
					{
						out.println("<body style = text-align:center;padding:100px>");
						out.println("<h3> The patient ID = " + deleteIds + " is not available </h3>");	
						out.println("<h3> ....You want to Delete Again?.... </h3>");
						//out.println("<button type = button style = width:200px; height:30px> <a href = http://localhost:8080/HospitalManagementSystem/DeleteRecord.html> Yes-DeleteAgain </a> </button>");
						//out.println("<button type = button style = width:200px; height:30px> <a href = http://localhost:8080/HospitalManagementSystem/HomepageV1_1.html> NO </a> </button>");
						out.println("<button type = button style = width:200px; height:30px> <a href = DeleteRecord.html> Yes-DeleteAgain </a> </button>");
						out.println("<button type = button style = width:200px; height:30px> <a href = HomepageV1_1.html> NO </a> </button>");						
						out.println("</body>");
					}
				}
				else
				{
					out.println("<body style = text-align:center;padding:100px>");
					out.println("<h3> you have not selected any ids to delete </h3>");
					//out.println("<h3> ....You want to Delete Again?.... </h3>");					
					//out.println("<button type = button style = width:200px; height:30px> <a href = http://localhost:8080/HospitalManagementSystem/HomepageV1_1.html> Exit </a> </button>");
					out.println("<button type = button style = width:200px; height:30px> <a href = HomepageV1_1.html> Back </a> </button>");
					out.println("</body>");
				}
			}	
			conn.close();
			System.out.println("outside try block of servlet");
		} 
		catch(Exception e)
		{
			System.out.println("inside catch block of servlet");
			e.printStackTrace();
			System.out.println(e);
			out.println(e);
			System.out.println("outside catch block of servlet");
		}
	}
}
