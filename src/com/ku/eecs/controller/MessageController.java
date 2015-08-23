package com.ku.eecs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ku.eecs.beans.DepartmentBean;
import com.ku.eecs.beans.MessageBean;
import com.ku.eecs.dao.AccountRequestDAO;
import com.ku.eecs.dao.MessageDAO;

/**
 * Servlet implementation class MessageController
 */
public class MessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//To load all the available messaged initially and display the messages page
		if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("retrieveAll"))
		{
			MessageDAO dao = new MessageDAO();
			ArrayList<MessageBean> messages = dao.retrieveAllMessages();
			request.setAttribute("messages", messages);
			RequestDispatcher rd = request.getRequestDispatcher("JSP/displayMessages.jsp");
			rd.forward(request, response);
		
		}
		else if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("loadCreateMessagePage"))
		{
			AccountRequestDAO dao = new AccountRequestDAO();
			ArrayList<DepartmentBean> departmentNames = dao.getDepartmentNames();
			request.setAttribute("departmentNames", departmentNames);
			RequestDispatcher rd = request.getRequestDispatcher("JSP/createMessage.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//To Store the new message
		MessageDAO dao = new MessageDAO();
		MessageBean mb = new MessageBean();
		mb.setSubject(request.getParameter("subject"));
		mb.setContent(request.getParameter("content"));
		mb.setDepartmentId(Integer.parseInt(request.getParameter("department")));
		dao.createNewMessage(mb);
		ArrayList<MessageBean> messages = dao.retrieveAllMessages();
		request.setAttribute("messages", messages);
		RequestDispatcher rd = request.getRequestDispatcher("JSP/displayMessages.jsp");
		rd.forward(request, response);
		
		
	}

}
