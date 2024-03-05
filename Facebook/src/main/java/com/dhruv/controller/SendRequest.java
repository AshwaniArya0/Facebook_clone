package com.dhruv.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.dhruv.beans.Friend;
import com.dhruv.database.DBHandler;


public class SendRequest extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session=request.getSession();
		String sender=session.getAttribute("email").toString();
		System.out.println(sender);
		String rec=request.getParameter("rec");
		int stat=0;
		Friend friend=new Friend(0, sender, rec, stat);
		DBHandler db=new DBHandler();
		db.Save(friend);
		response.sendRedirect("Welcome?rmessage= Request Sent");
	}

}
