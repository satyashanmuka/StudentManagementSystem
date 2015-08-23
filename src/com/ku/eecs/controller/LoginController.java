package com.ku.eecs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ku.eecs.beans.MessageBean;
import com.ku.eecs.beans.UserBean;
import com.ku.eecs.dao.LoginDAO;
import com.ku.eecs.dao.MessageDAO;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("t1");
		String password = request.getParameter("t2");
		try 
		{
			LoginDAO dao = new LoginDAO();
			// validates the user name and password
			if (dao.validateUserCredentials(userName, password))
			{
				HttpSession s1 = request.getSession(true); // creates a session for a user
				
				
				UserBean ub = dao.getUserDetails(userName,password);
				s1.setAttribute("theName", ub.getUserName());
				s1.setAttribute("userBean",ub);
				s1.setAttribute("role",ub.getRole());
				s1.setMaxInactiveInterval(30000);
				
				if(ub.getRole().equalsIgnoreCase("student"))
				{
					MessageDAO messageDAO = new MessageDAO ();
					ArrayList<MessageBean> messages = messageDAO.retrieveAllMessagesByDepartmentId(ub.getDepartmentId());
					s1.setAttribute("messages",messages);
					
				}
				// if success forward to home page, load the user details
				request.getRequestDispatcher("JSP/home_menu.jsp").forward(request,response);

			}
			else 
			{
				// if failure forward to login failure page
				request.getRequestDispatcher("JSP/index_failure.jsp").forward(request, response);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
