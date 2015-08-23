package com.ku.eecs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ku.eecs.dao.CourseDAO;
import com.ku.eecs.dao.LoginDAO;
import com.ku.eecs.beans.CourseBean;
import com.ku.eecs.beans.UserBean;

/**
 * Servlet implementation class MyDataController
 */
public class MyDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyDataController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//For operations related to course page
		if(request.getParameter("page")!=null && request.getParameter("page").equalsIgnoreCase("courses"))
		{
			// for student options
			//for loading the courses initially.
			if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("loadCourses"))
			{
				CourseDAO dao = new CourseDAO();
				ArrayList<CourseBean> courses = dao.getAllCoursesByUserId(Integer.parseInt(request.getParameter("userId")));
				if(courses.size()==0)
				{
					request.setAttribute("status","initial");
					RequestDispatcher rd = request.getRequestDispatcher("JSP/addNewCourse.jsp");
					rd.forward(request, response);
				}
				else
				{
					request.setAttribute("courses",courses);
					RequestDispatcher rd = request.getRequestDispatcher("JSP/displayAllCourses.jsp");
					rd.forward(request, response);
				}
				
			}
			//for Editing the course details.
			if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("updateCourse"))
			{
				CourseDAO dao = new CourseDAO();
				CourseBean cb = dao.getCourseById(Integer.parseInt(request.getParameter("userId")),Integer.parseInt(request.getParameter("courseId")));
				request.setAttribute("course",cb);
				RequestDispatcher rd = request.getRequestDispatcher("JSP/updateCourse.jsp");
				rd.forward(request, response);
				
			}
			//for deleting the course
			if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("deleteCourse"))
			{
				CourseDAO dao = new CourseDAO();
				dao.deleteCourseById(Integer.parseInt(request.getParameter("userId")),Integer.parseInt(request.getParameter("courseId")));
				int userId = ((UserBean)request.getSession().getAttribute("userBean")).getUserId();
				RequestDispatcher rd = request.getRequestDispatcher("/MyDataServlet?page=courses&operation=loadCourses&userId="+userId);
				rd.forward(request, response);
				
			}
			
			//for Admin options
			if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("retrieveAll"))
			{
				LoginDAO dao = new LoginDAO();
				ArrayList<UserBean> users = dao.getAllUsers();
				request.setAttribute("users",users);
				RequestDispatcher rd = request.getRequestDispatcher("JSP/displayAllUsers.jsp");
				rd.forward(request, response);
				
			}
			
			
		}
		else if(request.getParameter("page")!=null && request.getParameter("page").equalsIgnoreCase("presentations"))
		{
			
		}
		else if(request.getParameter("page")!=null && request.getParameter("page").equalsIgnoreCase("internships"))
		{
			
	
		}
		else if(request.getParameter("page")!=null && request.getParameter("page").equalsIgnoreCase("contactInformation"))
		{
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("page")!=null && request.getParameter("page").equalsIgnoreCase("courses"))
		{
			if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("storeCourse"))
			{
				CourseDAO dao = new CourseDAO();
				CourseBean cb = new CourseBean();
				cb.setUserId(((UserBean)request.getSession().getAttribute("userBean")).getUserId());
				cb.setCourseNumber(request.getParameter("courseNumber"));
				cb.setCourseName(request.getParameter("courseName"));
				cb.setInstructorName(request.getParameter("instructorName".trim()));
				cb.setSemester(request.getParameter("semester"));
				cb.setYear(Integer.parseInt(request.getParameter("year")));
				if(request.getParameter("gpa")!=null && !request.getParameter("gpa").equalsIgnoreCase(""))
					cb.setGpa(Double.parseDouble(request.getParameter("gpa")));
				else
					cb.setGpa(0.0);
				
				int updatedRows = dao.storeCourse(cb);
				if(updatedRows>0)
				{
					request.setAttribute("status","success");
					RequestDispatcher rd = request.getRequestDispatcher("JSP/addNewCourse.jsp");
					rd.forward(request, response);
				}
				else
				{
					request.setAttribute("status","failure");
					RequestDispatcher rd = request.getRequestDispatcher("JSP/addNewCourse.jsp");
					rd.forward(request, response);
				}
				
			}
			else if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("updateCourse"))
			{
				CourseDAO dao = new CourseDAO();
				CourseBean cb = new CourseBean();
				cb.setUserId(((UserBean)request.getSession().getAttribute("userBean")).getUserId());
				cb.setCourseId(Integer.parseInt(request.getParameter("courseId")));
				cb.setCourseNumber(request.getParameter("courseNumber"));
				cb.setCourseName(request.getParameter("courseName"));
				cb.setInstructorName(request.getParameter("instructorName"));
				cb.setSemester(request.getParameter("semester"));
				cb.setYear(Integer.parseInt(request.getParameter("year")));
				cb.setGpa(Double.parseDouble(request.getParameter("gpa")));
				
				int updatedRows = dao.updateCourse(cb);
				if(updatedRows>0)
				{
					request.setAttribute("status","success");
					RequestDispatcher rd = request.getRequestDispatcher("JSP/updateCourse.jsp");
					rd.forward(request, response);
				}
				else
				{
					request.setAttribute("status","failure");
					doGet(request,response);
					RequestDispatcher rd = request.getRequestDispatcher("JSP/updateCourse.jsp");
					rd.forward(request, response);
				}
				
			}
			
		}
		else if(request.getParameter("page")!=null && request.getParameter("page").equalsIgnoreCase("presentations"))
		{
			
		}
		else if(request.getParameter("page")!=null && request.getParameter("page").equalsIgnoreCase("internships"))
		{
			
	
		}
		else if(request.getParameter("page")!=null && request.getParameter("page").equalsIgnoreCase("contactInformation"))
		{
			
		}
	}

}
