package com.ku.eecs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ku.eecs.beans.MessageBean;

public class MessageDAO 
{
	Connection con = null;
	Statement stmt=null;
	PreparedStatement pst_S = null;
	PreparedStatement pst_I=null;
	PreparedStatement pst_D=null;
	ResultSet rs1 = null;
	ResultSet rs2 = null;
	int updatedrows=0;

	public ArrayList<MessageBean> retrieveAllMessages() 
	{
		ArrayList<MessageBean> al = new ArrayList<MessageBean>();
		String query="select * from message m inner join department d on m.departmentId = d.departmentId order by postedDate asc";
		try		
		{
			con=DBConnection.getDBConnection();
			stmt=con.createStatement();
			rs1=stmt.executeQuery(query);

			while(rs1.next())
			{
				MessageBean mb = new MessageBean();
				mb.setMessageId(rs1.getInt("messageId"));
				mb.setContent(rs1.getString("content"));
				mb.setSubject(rs1.getString("subject"));
				mb.setPostedDate(rs1.getDate("postedDate").toString());
				mb.setDepartmentId(rs1.getInt("departmentId"));
				mb.setDepartmentName(rs1.getString("departmentName"));
				
				al.add(mb);
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return al;
	}

	public void createNewMessage(MessageBean mb) 
	{
		try {
			// Establishing connection by calling getConnection method from DBConnection class
			con=DBConnection.getDBConnection();

			//This query inserts the details of student account with the  student bean values as input parameter
			String query2="insert into message(subject,content,departmentId) values(?,?,?)";

			// insertion is performed only when student is present in student main table
			// prepared statement is used to execute the query repeatedly
			pst_I=con.prepareStatement(query2);
			pst_I.setString(1,mb.getSubject());
			pst_I.setString(2,mb.getContent());
			pst_I.setInt(3,mb.getDepartmentId());
			updatedrows=pst_I.executeUpdate();
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{
				pst_I.close();
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		}

	}

	public ArrayList<MessageBean> retrieveAllMessagesByDepartmentId(int departmentId) {
		ArrayList<MessageBean> al = new ArrayList<MessageBean>();
		String query="select * from message m inner join department d on m.departmentId = d.departmentId where d.departmentId =? order by postedDate desc";
		try		
		{
			con=DBConnection.getDBConnection();
			pst_S=con.prepareStatement(query);
			pst_S.setInt(1,departmentId);
			rs1=pst_S.executeQuery();

			while(rs1.next())
			{
				MessageBean mb = new MessageBean();
				mb.setMessageId(rs1.getInt("messageId"));
				mb.setContent(rs1.getString("content"));
				mb.setSubject(rs1.getString("subject"));
				mb.setPostedDate(rs1.getDate("postedDate").toString());
				mb.setDepartmentId(rs1.getInt("departmentId"));
				mb.setDepartmentName(rs1.getString("departmentName"));
				
				al.add(mb);
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

}
