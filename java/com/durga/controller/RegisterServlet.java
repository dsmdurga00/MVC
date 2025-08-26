package com.durga.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.durga.dbConn.DbConnection;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		String myname = req.getParameter("username");
		String mypass = req.getParameter("password");
		String myemail = req.getParameter("email");
		String mycity = req.getParameter("city");
		
		try
		{
			Connection con = DbConnection.getConnection();
			
			String insert = "insert into mvctable values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insert);
			
			ps.setString(1, myname);
			ps.setString(2, mypass);
			ps.setString(3, myemail);
			ps.setString(4, mycity);
			
		int check = ps.executeUpdate();
		if(check>0)
		{
			out.println("<h2 style = 'color: green'>New User Details Add Successfully</h2>");
			System.out.println("Bnda Add Ho gya");
			
			RequestDispatcher rd = req.getRequestDispatcher("/login.html");
			rd.include(req, res);
		}
		else 
		{
			out.println("<h2 style='color:red'>New User Details Not Add due to Some Error</h2>");
			System.out.println("Bnda Add Nhi hua");
			
			RequestDispatcher rd = req.getRequestDispatcher("/register.html");
		}
		
		}
		
		catch (Exception e)
		{
		     e.printStackTrace();
		}
		
	}

}
