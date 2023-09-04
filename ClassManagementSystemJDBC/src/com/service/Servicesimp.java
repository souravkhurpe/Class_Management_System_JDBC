package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.model.MyConnection;

public class Servicesimp implements Services {
	Scanner sc = new Scanner(System.in);

	@Override
	public void addCourse() {
		System.out.println("how many course u want to add: ");
		int n = sc.nextInt();
		Connection connection = MyConnection.myConnection();
		try {
			for (int i = 0; i < n; i++) {
				System.out.println("Enter course id:");
				int cid = sc.nextInt();
				System.out.println("Enter course name: ");
				String name = sc.next();

				PreparedStatement ps = connection.prepareStatement("insert into course values (?,?)");
				ps.setInt(1, cid);
				ps.setString(2, name);
				ps.execute();
//				int affected = ps.executeUpdate();
//				System.out.println("Data inserted successfully " + affected + " " + " row affected");
				System.out.println("data inserted successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void getCourse() {
		try {
			Connection c = MyConnection.myConnection();
			PreparedStatement ps = c.prepareStatement("select * from course");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				System.out.println("Course ID: " + rs.getInt(1) + "\n" + "Course Name: " + rs.getString(2) + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addFaculty() {
		System.out.println("how many faculty u want to add: ");
		int n = sc.nextInt();
		Connection connection = MyConnection.myConnection();
		try {
			for (int i = 0; i < n; i++) {
				System.out.println("Enter faculty id:");
				int cid = sc.nextInt();
				System.out.println("Enter faculty name: ");
				String name = sc.next();

				System.out.println("Select course id: ");
				System.out.println();
				getCourse();
				int bid = sc.nextInt();

				PreparedStatement ps = connection.prepareStatement("insert into faculty values (?,?,?)");
				ps.setInt(1, cid);
				ps.setString(2, name);
				ps.setInt(3, bid);
				ps.execute();
//				int affected = ps.executeUpdate();
//				System.out.println("Data inserted successfully " + affected + " " + " row affected");
				System.out.println("data inserted successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void getFaculty() {
		try {
			Connection c = MyConnection.myConnection();
			PreparedStatement ps = c.prepareStatement(
					"select course.cid, course.cname,faculty.fid,faculty.fname from course inner join faculty on course.cid = faculty.cid;");
			ResultSet rs = ps.executeQuery();
//			System.out.println(rs);
			while (rs.next()) {

//				System.out.println("cid: "+ rs.getInt(1));
//				System.out.println("cname: "+ rs.getString(2));
//				System.out.println("fid: "+ rs.getInt(3));
//				System.out.println("fname: "+rs.getString(4));
				System.out.println("Course ID: " + rs.getInt(1) + "\n" + "Course Name: " + rs.getString(2) + "\n"
						+ "Faculty ID: " + rs.getInt(3) + "\n" + "Faculty Name: " + rs.getString(4) + "\n");
				System.out.println("-----------------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addBatch() {
		try {
			Connection connection = MyConnection.myConnection();
			String sql = "insert into batch values(?,?,?)";
			System.out.println("How many batch u want to add: ");
			int n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				System.out.println("Enter batch id: ");
				int bid = sc.nextInt();
				System.out.println("Enter batch name: ");
				String bname = sc.next();

				getFaculty();
				System.out.println("Select faculty id: ");
				int fid = sc.nextInt();

				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, bid);
				ps.setString(2, bname);
				ps.setInt(3, fid);
				ps.execute();

				System.out.println("Batch added successfully");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void getBatch() {
		try {
			Connection connection = MyConnection.myConnection();
			String sql = "select course.cid, course.cname, faculty.fid, faculty.fname, batch.bid, batch.bname from batch inner join faculty on batch.fid = faculty.fid inner join course on faculty.cid = course.cid;";

			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
//		System.out.println(rs);

			while (rs.next()) {

				System.out.println("Course ID: " + rs.getInt(1) + "\n" + "Course Name: " + rs.getString(2) + "\n"
						+ "Faculty ID: " + rs.getInt(3) + "\n" + "Faculty Name: " + rs.getString(4) + "\n"
						+ "Batch ID: " + rs.getInt(5) + "\n" + "Batch Name: " + rs.getString(6) + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addStudent() {
		try {

			Connection connection = MyConnection.myConnection();
			System.out.println("Enter Student ID: ");
			int sid = sc.nextInt();
			System.out.println("Enter student name: ");
			String sname = sc.next();

			getBatch();
			System.out.println("Enter batch ID you want to join");
			int bid = sc.nextInt();
			String sql = "insert into student values (?, ?, ?);";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, sid);
			ps.setString(2, sname);
			ps.setInt(3, bid);

			ps.execute();
			System.out.println("Student details added successfully");
		} catch (Exception e) {

		}

	}

	@Override
	public void getStudent() {
		try {
			Connection connection = MyConnection.myConnection();

			PreparedStatement ps = connection.prepareStatement(
					"select student.sid, student.sname, batch.bid, batch.bname, faculty.fid, faculty.fname, course.cid, course.cname from student inner join batch on student.bid = batch.bid inner join faculty on faculty.fid = batch.fid inner join course on course.cid = faculty.cid;");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("Student ID: " + rs.getInt(1) + "\n" + "Student Name " + rs.getString(2) + "\n"
						+ "Batch ID: " + rs.getInt(3) + "\n" + "Batch Name: " + rs.getString(4) + "\n" + "Faculty ID: "
						+ rs.getInt(5) + "\n" + "Faculty Name: " + rs.getString(6) + "\n" + "Course ID: " + rs.getInt(7)
						+ "\n" + "Course Name: " + rs.getString(8) + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteStudent() {
		getStudent();
		System.out.println();
		System.out.println("Enter Student ID u want to delete: ");
		int sid = sc.nextInt();
		try {
			Connection connection = MyConnection.myConnection();
			PreparedStatement ps = connection.prepareStatement("delete from student where sid = " + sid + ";");
			ps.execute();
			System.out.println("Student removed successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void updateStudent() {
		System.out.println("Enter Student ID from which u want to change name: ");
		int sid = sc.nextInt();
		System.out.println("Enter New Name: ");
		String sname = sc.next();
		try {
		Connection connection = MyConnection.myConnection();
		
		PreparedStatement ps = connection.prepareStatement("update student set sname ='"+sname+"' where sid ="+sid);
		ps.execute();
		System.out.println("Information Updated Successfully");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
