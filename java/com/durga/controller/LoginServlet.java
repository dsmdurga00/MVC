package com.durga.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.durga.dbConn.DbConnection;
import com.durga.model.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String myname = req.getParameter("username");
		String mypass = req.getParameter("password");
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		try
		{
			Connection con = DbConnection.getConnection();
			
			String select = "select * from mvctable where username=? And password=?";
			PreparedStatement ps = con.prepareStatement(select);
			
			ps.setString(1, myname);
			ps.setString(2, mypass);
			
			    ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				User user = new User();
				
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setCity(rs.getString("city"));
				
				HttpSession session = req.getSession();
				session.setAttribute("session_user", user);
				
				System.out.println("SuccessFully Login..........");
				//out.println("<h2 style='color:green'>Student Login Successfull....</h2>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/welcome.jsp");
				rd.forward(req, res);
			}
			else
			{
				System.out.println("Bnda Fail ho gya");
				out.println("<h2 style='color:red'>Login Failed.............</h2>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/login.html");
				rd.include(req, res);
			}
		}
		catch (Exception e) 
		
		{
		    e.printStackTrace();
		}
		
	}

}
