/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arackiralama.web;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import arackiralama.dao.AracDAO;
import arackiralama.dao.AracTeslimDAO;
import arackiralama.dao.KiralamaDAO;
import arackiralama.dao.MusteriDAO;
import arackiralama.dao.UserDAO;
import arackiralama.model.Arac;
import arackiralama.model.AracTeslim;
import arackiralama.model.Kiralama;
import arackiralama.model.Musteri;
import arackiralama.model.User;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class UserServlet
 */

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private AracDAO aracDAO;
	private MusteriDAO musteriDAO;
	private KiralamaDAO kiralamaDAO;
	private AracTeslimDAO aracTeslimDAO;
	private static final String LOGGED_USER = "loggedUser";

	public void init() {
		userDAO = new UserDAO();
		aracDAO=new AracDAO();
		musteriDAO=new MusteriDAO();
		kiralamaDAO=new KiralamaDAO();
		aracTeslimDAO=new AracTeslimDAO();
	}
	protected boolean isValidLoginExist(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute(LOGGED_USER);
		if(user!= null) {
			if(user!= null)
				request.setAttribute("logged", user);
			return true;
		}
		else {
			return false;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			if(isValidLoginExist(request) == false) {
                             System.out.println(action);
				switch (action) {
					case "/loginUser":
						loginAccount(request, response);
						break;
					case "/uyeOl":
						showUyeOl(request, response);
						break;
					case "/insertUyeOl":
						insertUyeOl(request, response);
						break;
					default:
						diplayLoginPage(request, response);
						break;
				}
			}
			else {
				switch (action) {
				case "/cikisYap":
					cikisYap(request, response);
					break;
				case "/profilSayfam":
					showProfilSayfam(request, response);
					break;
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertUser(request, response);
					break;
				case "/delete":
					deleteUser(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updateUser(request, response);
					break;
				case "/home":
					showHome(request, response);
					break;
				case "/newArac":
					showAracNewForm(request, response);
					break;
				case "/insertArac":
					insertArac(request, response);
					break;
				case "/deleteArac":
					deleteArac(request, response);
					break;
				case "/editArac":
					showAracEditForm(request, response);
					break;
				case "/updateArac":
					updateArac(request, response);
					break;
				case "/listArac":
					listArac(request, response);
					break;
				case "/listUser":
					showHome(request, response);
					break;
				case "/newMusteri":
					showMusteriNewForm(request, response);
					break;
				case "/insertMusteri":
					insertMusteri(request, response);
					break;
				case "/deleteMusteri":
					deleteMusteri(request, response);
					break;
				case "/editMusteri":
					showMusteriEditForm(request, response);
					break;
				case "/updateMusteri":
					updateMusteri(request, response);
					break;
				case "/listMusteri":
					listMusteri(request, response);
					break;					
				case "/newKiralama":
					showKiralamaNewForm(request, response);
					break;
				case "/insertKiralama":
					insertKiralama(request, response);
					break;
				case "/deleteKiralama":
					deleteKiralama(request, response);
					break;
				case "/editKiralama":
					showKiralamaEditForm(request, response);
					break;
				case "/updateKiralama":
					updateKiralama(request, response);
					break;
				case "/listKiralama":
					listKiralama(request, response);
					break;
				case "/profilim":
					showProfilimForm(request, response);
					break;
				case "/updateProfilim":
					updateProfilim(request, response);
					break;
				case "/listAracTeslim":
					listAracTeslim(request, response);
					break;
				case "/insertAracTeslim":
					insertAracTeslim(request, response);
					break;
				default:
					showHome(request, response);
					break;
				}
			}

		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (ClassNotFoundException ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		List<User> listUser = userDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		User newUser = new User(name, password,firstName,lastName);
		userDAO.insertUser(newUser);
		response.sendRedirect("list");
	}
	private void insertUyeOl(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		User newUser = new User(name, password,firstName,lastName);
		userDAO.insertUser(newUser);
		response.sendRedirect("loginUser");
	}
	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		User user = new User(id,name, password,firstName,lastName);
		userDAO.updateUser(user);
		response.sendRedirect("list");
	}
	private void updateProfilim(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		User user = new User(id,name, password,firstName,lastName);
		userDAO.updateUser(user);
		response.sendRedirect("home");
	}
	private void showProfilimForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		User user=(User)request.getSession().getAttribute("loggedUser");
		int id = user.getId();
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/profilim.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("list");

	}
	private void diplayLoginPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.getRequestDispatcher("login-form.jsp").forward(request, response);
	
	}

	private void loginAccount(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String sifre = request.getParameter("password");

			User user = new UserDAO().authtenticateUser(name, sifre);
			if(user == null) {
				request.setAttribute("error", "Email veya parola bilgisi yanlış girildi! ");
				diplayLoginPage(request, response);
			}
			else {
				request.getSession().setAttribute(LOGGED_USER, user);
				request.removeAttribute("error");
				response.sendRedirect("home");
			}
	}

	private void cikisYap(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		request.getSession().setAttribute(LOGGED_USER, null);
		diplayLoginPage(request, response);
	}
	private void showProfilSayfam(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("profil-form.jsp");
		dispatcher.forward(request, response);
	}
	private void showHome(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}
	private void showUyeOl(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("uye-ol.jsp");
		dispatcher.forward(request, response);
	}
	
	private void listArac(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		List<Arac> listArac = aracDAO.selectAllArac();
		request.setAttribute("listArac", listArac);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/arac-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showAracNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/arac-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showAracEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		Arac existingUser = aracDAO.selectArac(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/arac-form.jsp");
		request.setAttribute("arac", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertArac(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		request.setCharacterEncoding("UTF-8");
	
		String plakaNo = request.getParameter("plakaNo");
		String model = request.getParameter("model");
		String marka = request.getParameter("marka");
		String renk = request.getParameter("renk");
		Arac newArac = new Arac(plakaNo,model,marka,renk);
		aracDAO.insertArac(newArac);
		response.sendRedirect("listArac");
	}

	private void updateArac(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String plakaNo = request.getParameter("plakaNo");
		String model = request.getParameter("model");
		String marka = request.getParameter("marka");
		String renk = request.getParameter("renk");
		Arac arac = new Arac(id, plakaNo, model,marka,renk);
		aracDAO.updateArac(arac);
		response.sendRedirect("listArac");
	}

	private void deleteArac(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		aracDAO.deleteArac(id);
		response.sendRedirect("listArac");

	}
	private void listMusteri(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		List<Musteri> listMusteri = musteriDAO.selectAllMusteri();
		request.setAttribute("listMusteri", listMusteri);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/musteri-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showMusteriNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/musteri-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showMusteriEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		Musteri existingUser = musteriDAO.selectMusteri(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/musteri-form.jsp");
		request.setAttribute("musteri", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertMusteri(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		request.setCharacterEncoding("UTF-8");
		String ad = request.getParameter("ad");
		String soyad = request.getParameter("soyad");
		String telefon = request.getParameter("telefon");
		String adres = request.getParameter("adres");
		Musteri newMusteri = new Musteri(ad,soyad,telefon,adres);
		musteriDAO.insertMusteri(newMusteri);
		response.sendRedirect("MusteriServlet/list");
	}

	private void updateMusteri(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String ad = request.getParameter("ad");
		String soyad = request.getParameter("soyad");
		String telefon = request.getParameter("telefon");
		String adres = request.getParameter("adres");
		Musteri musteri = new Musteri(id, ad, soyad,telefon,adres);
		musteriDAO.updateMusteri(musteri);
		response.sendRedirect("MusteriServlet/list");
	}

	private void deleteMusteri(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		musteriDAO.deleteMusteri(id);
		response.sendRedirect("MusteriServlet/list");

	}
	
	private void listKiralama(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		List<Kiralama> listKiralama = kiralamaDAO.selectAllKiralama();
		request.setAttribute("listKiralama", listKiralama);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/kiralama-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showKiralamaNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<Arac> aracListesi = new AracDAO().selectAllArac();
		List<Musteri> musteriListesi = new MusteriDAO().selectAllMusteri();
		request.setAttribute("listArac", aracListesi);
		request.setAttribute("listMusteri", musteriListesi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/kiralama-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showKiralamaEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		Kiralama existingUser = kiralamaDAO.selectKiralama(id);
		List<Arac> aracListesi = new AracDAO().selectAllArac();
		List<Musteri> musteriListesi = new MusteriDAO().selectAllMusteri();
		request.setAttribute("listArac", aracListesi);
		request.setAttribute("listMusteri", musteriListesi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/kiralama-form.jsp");
		request.setAttribute("kiralama", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertKiralama(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException,ServletException, ClassNotFoundException {
		request.setCharacterEncoding("UTF-8");
		Date baslangicTarihi = Date.valueOf(request.getParameter("baslangicTarihi"));
		Date bitisTarihi = Date.valueOf(request.getParameter("bitisTarihi"));
		Integer aracId = Integer.parseInt(request.getParameter("arac"));
		Integer musteriId = Integer.parseInt(request.getParameter("musteri"));
		Arac arac = new AracDAO().selectArac(aracId);
		Musteri musteri = new MusteriDAO().selectMusteri(musteriId);
		List<Kiralama> kiralist=kiralamaDAO.selectAllKiraArac(aracId,baslangicTarihi);
		if(kiralist.size()>0) {
			request.setAttribute("error", "Araç seçilen tarihte kiradadır.! ");
			showKiralamaNewForm(request, response);
		}else {
			Kiralama newKiralama = new Kiralama(arac,musteri,baslangicTarihi,bitisTarihi);
			kiralamaDAO.insertKiralama(newKiralama);
			response.sendRedirect("listKiralama");
		}
		
	}

	private void updateKiralama(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		Date baslangicTarihi = Date.valueOf(request.getParameter("baslangicTarihi"));
		Date bitisTarihi = Date.valueOf(request.getParameter("bitisTarihi"));
		Integer aracId = Integer.parseInt(request.getParameter("arac"));
		Integer musteriId = Integer.parseInt(request.getParameter("musteri"));
		Arac arac = new AracDAO().selectArac(aracId);
		Musteri musteri = new MusteriDAO().selectMusteri(musteriId);
		Kiralama kiralama = new Kiralama(arac,musteri,baslangicTarihi,bitisTarihi);
		kiralamaDAO.updateKiralama(kiralama);
		response.sendRedirect("listKiralama");
	}

	private void deleteKiralama(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		kiralamaDAO.deleteKiralama(id);
		response.sendRedirect("listKiralama");
	}
	private void listAracTeslim(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		List<AracTeslim> listAracTeslim = aracTeslimDAO.selectAllAracTeslim();
		request.setAttribute("listAracTeslim", listAracTeslim);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/aracTeslim-list.jsp");
		dispatcher.forward(request, response);
	}
	private void insertAracTeslim(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		kiralamaDAO.deleteKiralama(id);
		int aracId = Integer.parseInt(request.getParameter("aracid"));
		User user=(User)request.getSession().getAttribute("loggedUser");
		Arac arac = new AracDAO().selectArac(aracId);
		AracTeslim aracTeslim = new AracTeslim(arac,user,java.sql.Date.valueOf(java.time.LocalDate.now()));
		aracTeslimDAO.insertAracTeslim(aracTeslim);
		response.sendRedirect("listAracTeslim");
	}

}

