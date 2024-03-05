package com.dhruv.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.dhruv.beans.User;
import com.dhruv.database.DBHandler;


public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		DBHandler db=new DBHandler();
		db.getProfile(email);
		ArrayList<User> profile=db.getProfile(email);
		request.setAttribute("profile", profile);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("ViewProfile.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
