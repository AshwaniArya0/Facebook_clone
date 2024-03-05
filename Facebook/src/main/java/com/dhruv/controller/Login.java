package com.dhruv.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.dhruv.beans.User;
import com.dhruv.database.DBHandler;


public class Login extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		User user=new User(email, password, null);
		DBHandler db=new DBHandler();
		String message=db.checkUser(user);
		if (message.equals("success")) {
			HttpSession session=request.getSession();
			session.setAttribute("User", user);
			session.setAttribute("email", email);
			response.sendRedirect("Welcome");
		}
		else
			response.sendRedirect("index.jsp?lmessage= "+message);
	
	}

}
