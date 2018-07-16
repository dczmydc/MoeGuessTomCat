package com.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB {
	public static void main(String[] args) {
		//myzhong11/javaEE/MoeguessExperiments
		//experiments for sqlite-jdbc
		Connection conn = null;
		try {
			String url = "jdbc:sqlite:test.db";
			conn = DriverManager.getConnection(url);
			System.out.println("Connected");
			Statement statement = conn.createStatement();
		    statement.setQueryTimeout(30);  // set timeout to 30 sec.
		    statement.executeUpdate("drop table if exists person");
		    statement.executeUpdate("create table person (id integer, name string)");
		    statement.executeUpdate("insert into person values(1, 'leo')");
		    statement.executeUpdate("insert into person values(2, 'yui')");
		    ResultSet rs = statement.executeQuery("select * from person");
		    while(rs.next()) {
		    	System.out.println("name = " + rs.getString("name"));
		        System.out.println("id = " + rs.getInt("id"));
		    }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Finished.");
	}
}
