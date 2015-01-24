package com.dsps.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsps.metier.Allocine;
import com.dsps.metier.PageContent;

public class SSearchResults extends HttpServlet {

	private static final long serialVersionUID = 1668801483934193771L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
 
		PageContent content = new PageContent("http://torrentz.eu/search?q=1080p");
		content.getContent();
		Allocine allocine = new Allocine();
		
		req.setAttribute("allocine", allocine);
		req.setAttribute("sResults",content);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/test.jsp").forward(req, resp);
	}
}
