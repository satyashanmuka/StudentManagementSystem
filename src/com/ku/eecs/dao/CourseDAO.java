package com.ku.eecs.dao;

import com.ku.eecs.beans.CourseBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CourseDAO 
{
	Connection con = null;
	Statement stmt=null;
	PreparedStatement pst_S = null;
	PreparedStatement pst_I=null;
	PreparedStatement pst_D=null;
	ResultSet rs1 = null;
	ResultSet rs2 = null;
	int updatedrows=0;

	public ArrayList<CourseBean> getAllCoursesByUserId(int userId) 
	{
		ArrayList<CourseBean> al = new ArrayList<CourseBean>();
		String query="select * from course c INNER JOIN studentcourse s on c.courseId = s.courseId where s.userId=? order by s.year asc";
		try		
		{
			con=DBConnection.getDBConnection();
			pst_S = con.prepareStatement(query);
			pst_S.setInt(1,userId);
			rs1=pst_S.executeQuery();

			while(rs1.next())
			{
				CourseBean ab = new CourseBean();
				ab.setUserId(rs1.getInt("userId"));
				ab.setCourseId(rs1.getInt("courseId"));
				ab.setCourseNumber(rs1.getString("courseNumber"));
				ab.setCourseName(rs1.getString("courseName"));
				ab.setInstructorName(rs1.getString("instructor"));
				ab.setSemester(rs1.getString("semester"));
				ab.setYear(rs1.getInt("year"));
				ab.setGpa(rs1.getDouble("gpa"));
				al.add(ab);
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				pst_S.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return al;
	}

	public int storeCourse(CourseBean cb) 
	{
		PreparedStatement pst_select = null;
		PreparedStatement pst_Insert1 = null;
		PreparedStatement pst_Insert2 = null;
		try {
			// Establishing connection by calling getConnection method from DBConnection class
			con=DBConnection.getDBConnection();
			
			//This query inserts the details of student account with the  student bean values as input parameter
			String query1="select * from course where courseName=?";
			String query2="insert into course(courseNumber,courseName,instructor) values(?,?,?)";
			String query3="insert into studentcourse(userId,courseId,semester,year,gpa) values(?,?,?,?,?)";
			
			
			
			pst_select = con.prepareStatement(query1);
			pst_select.setString(1, cb.getCourseName());
			ResultSet rst1 = pst_select.executeQuery();
			if(rst1.next())
			{
				pst_Insert2 = con.prepareStatement(query3);
				pst_Insert2.setInt(1,cb.getUserId());
				pst_Insert2.setInt(2,rst1.getInt("courseId"));
				pst_Insert2.setString(3,cb.getSemester());
				pst_Insert2.setInt(4,cb.getYear());
				pst_Insert2.setDouble(5,cb.getGpa());
				
				pst_Insert2.executeUpdate();
				
			}
			else
			{
				pst_Insert1 = con.prepareStatement(query2);
				pst_Insert1.setString(1,cb.getCourseNumber());
				pst_Insert1.setString(2,cb.getCourseName());
				pst_Insert1.setString(3,cb.getInstructorName());
				int updatedRows = pst_Insert1.executeUpdate();
				if(updatedRows>0)
				{
					ResultSet rst2 = pst_select.executeQuery();
					while(rst2.next())
					{
						pst_Insert2 = con.prepareStatement(query3);
						pst_Insert2.setInt(1,cb.getUserId());
						pst_Insert2.setInt(2,rst2.getInt("courseId"));
						pst_Insert2.setString(3,cb.getSemester());
						pst_Insert2.setInt(4,cb.getYear());
						pst_Insert2.setDouble(5,cb.getGpa());
						
						updatedrows = pst_Insert2.executeUpdate();
					}
				}
			}
	} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		return updatedrows;
		
	}

	public CourseBean getCourseById(int userId, int courseId) 
	{
		CourseBean ab = new CourseBean();
		String query="select * from studentcourse s Inner Join course c on s.courseId = c.courseId where s.userId=? and s.courseId=?";
		try		
		{
			con=DBConnection.getDBConnection();
			pst_S=con.prepareStatement(query);
			pst_S.setInt(1, userId);
			pst_S.setInt(2,courseId);
			rs1=pst_S.executeQuery();

			while(rs1.next())
			{
				ab.setUserId(rs1.getInt("userId"));
				ab.setCourseId(rs1.getInt("courseId"));
				ab.setCourseNumber(rs1.getString("courseNumber"));
				ab.setCourseName(rs1.getString("courseName"));
				ab.setInstructorName(rs1.getString("instructor"));
				ab.setSemester(rs1.getString("semester"));
				ab.setYear(rs1.getInt("year"));
				ab.setGpa(rs1.getDouble("gpa"));
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				pst_S.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ab;
	}
	public int updateCourse(CourseBean cb) 
	{
		String query3 = "update studentcourse set semester=?,year=?,gpa=? WHERE userId=? and courseId=?";
		try		
		{
			con=DBConnection.getDBConnection();

			pst_I = con.prepareStatement(query3);
			pst_I.setString(1,cb.getSemester());
			pst_I.setInt(2,cb.getYear());
			pst_I.setDouble(3,cb.getGpa());
			
			pst_I.setInt(4,cb.getUserId());
			pst_I.setInt(5,cb.getCourseId());
			
			updatedrows = pst_I.executeUpdate();

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				pst_I.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updatedrows;
	}

	public void deleteCourseById(int userId, int courseId) 
	{
		String query2 = "delete from studentcourse where userId=? and courseId=?";
		try		
		{
			con=DBConnection.getDBConnection();
			pst_D = con.prepareStatement(query2);
			pst_D.setInt(1,userId);
			pst_D.setInt(2,courseId);
			pst_D.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				pst_D.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
