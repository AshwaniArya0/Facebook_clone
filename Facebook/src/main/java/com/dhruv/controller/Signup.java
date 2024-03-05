package com.dhruv.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dhruv.beans.User;
import com.dhruv.database.DBHandler;



public class Signup extends HttpServlet {
	
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String regexStr = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regexStr);
		Matcher matcher = pattern.matcher(email);
		boolean matchFound = matcher.matches();
		if (matchFound) {
		    User user = new User(email, password, name);
		    DBHandler db = new DBHandler();
		    String message = db.Save(user);
		    if (message.equals("success")) {
		        response.sendRedirect("index.jsp?smessage=Data Saved");
		    } 
		} 
		else {
		    System.out.println("Match Not Found");
		    response.sendRedirect("index.jsp?smessage=Please Enter Valid Email");
		}

		
	}

}
