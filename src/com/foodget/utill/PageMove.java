package com.foodget.utill;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PageMove {
	
	public static void forward( HttpServletRequest request, HttpServletResponse response, String path ) throws ServletException, IOException{
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
	}
	
	public static void redirect(HttpServletResponse response, String path) throws IOException{
		response.sendRedirect(path);
	}
	
}
