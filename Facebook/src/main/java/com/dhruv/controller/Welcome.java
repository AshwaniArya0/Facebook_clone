package com.dhruv.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.dhruv.beans.Friend;
import com.dhruv.beans.Wpost;
import com.dhruv.database.DBHandler;


public class Welcome extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DBHandler handler=new DBHandler();
		HttpSession session=request.getSession();
		String email=session.getAttribute("email").toString();
		
		
		
		ArrayList<Friend> pfriends=handler.getPendingRequest(email);
		request.setAttribute("pfriends", pfriends);
		
		ArrayList<Wpost> wpost=handler.getposts(email);
		request.setAttribute("wpost", wpost);
		
		
		ArrayList<Friend> friends=handler.getFriends(email);
		request.setAttribute("friends", friends);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("Welcome.jsp");
		dispatcher.forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}

}
