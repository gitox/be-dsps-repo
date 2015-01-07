package com.dsps.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsps.beans.SearchResultsBean;
import com.dsps.metier.Content;

public class SearchResultsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1668801483934193771L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Content content = new Content();
		SearchResultsBean sResults = new SearchResultsBean();
		sResults.setMmItems(content.getUrlContent());
		req.setAttribute("sResults",sResults);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/test.jsp").forward(req, resp);
	}
}
