/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arackiralama.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import arackiralama.dao.AracDAO;
import arackiralama.model.Arac;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class AracServlet
 */
@WebServlet("/AracServlet/")
public class AracServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AracDAO aracDAO;
	
	public void init() {
		aracDAO = new AracDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getRequestURI();
		action = action.substring(action.lastIndexOf("/")).toLowerCase();
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertArac(request, response);
				break;
			case "/delete":
				deleteArac(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateArac(request, response);
				break;
			default:
				listArac(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (ClassNotFoundException ex) {
                Logger.getLogger(AracServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	private void listArac(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Arac> listArac = aracDAO.selectAllArac();
		request.setAttribute("listArac", listArac);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/arac-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/arac-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Arac existingUser = aracDAO.selectArac(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/arac-form.jsp");
		request.setAttribute("arac", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertArac(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		String plakaNo = request.getParameter("plakaNo");
		String model = request.getParameter("model");
		String marka = request.getParameter("marka");
		String renk = request.getParameter("renk");
		Arac newArac = new Arac(plakaNo,model,marka,renk);
		aracDAO.insertArac(newArac);
		response.sendRedirect("AracServlet/list");
	}

	private void updateArac(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String plakaNo = request.getParameter("plakaNo");
		String model = request.getParameter("model");
		String marka = request.getParameter("marka");
		String renk = request.getParameter("renk");
		Arac arac = new Arac(id, plakaNo, model,marka,renk);
		aracDAO.updateArac(arac);
		response.sendRedirect("AracServlet/list");
	}

	private void deleteArac(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		aracDAO.deleteArac(id);
		response.sendRedirect("AracServlet/list");

	}


}

