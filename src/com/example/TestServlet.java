package com.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public TestServlet() {
		super();
	}
	
	public void init() throws ServletException {
		message = "Hello world.";
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1> GET " + message + "</h1>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1> POST " + message + "</h1>");
		System.out.println("REQ LEN " + request.getContentLength());
		// Read from request
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		data = data.replaceAll("%25", "%");
		System.out.println("YOUR ANSWER:");
		String [] answers = data.split("&");
		for(int i = 0; i < answers.length; i++) {
			String[] contents = answers[i].split("=");
			System.out.println(contents[0] + ": " + contents[1]);
	    }
	    //call func to save answer
	}

	public void destroy() {}
	
}
