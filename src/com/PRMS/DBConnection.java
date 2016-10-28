package com.PRMS;

import java.sql.*;
import java.util.*;
import java.io.*;

//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;

public class DBConnection 
{
	public static Connection getConnection()
	{
		
		Connection con = null;
		
		try
		{	
			System.out.println("inside try block of GetConnection");
			Properties props = new Properties();
			Thread currentThread = Thread.currentThread();
			ClassLoader contextClassLoader = currentThread.getContextClassLoader();
			InputStream propertiesStream = contextClassLoader.getResourceAsStream("db.properties");
			
			System.out.println(props.getProperty("DB_DRIVER_CLASS"));
			System.out.println(props.getProperty("DB_URL"));
			System.out.println(props.getProperty("DB_USERNAME"));
			System.out.println(props.getProperty("DB_PASSWORD"));
			
			if (propertiesStream != null) 
			{
			  props.load(propertiesStream);
			  
			} 
			else 
			{
			  // Properties file not found!
			}
			Class.forName(props.getProperty("DB_DRIVER_CLASS"));
			System.out.println("driver registered in getconnection()");
			con = DriverManager.getConnection(props.getProperty("DB_URL"), 
											props.getProperty("DB_USERNAME"), 
											props.getProperty("DB_PASSWORD"));
			
			System.out.println("driver loaded in getconnection()");
			System.out.println("outside try block of GetConnection");
		}
		catch(Exception e)
		{
			System.out.println("inside catch block of GetConnection");
			e.printStackTrace();
			System.out.println(e);
			//out.println(e);
			System.out.println("outside catch block of GetConnection");
		}
		System.out.println(con);
		return con;
	}
}
