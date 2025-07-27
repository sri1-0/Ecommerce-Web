package com.batteryServe;

import java.io.IOException;
import java.util.List;

import com.batteryController.BatteryDao;
import com.batterybean.BeanClass;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServe
 */
public class ControllServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ControllServe() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		String actionparm = request.getParameter("action");
		System.out.println(path + " " + actionparm);

		if (actionparm != null) {
			if (actionparm.startsWith("update")) {
				int userid = Integer.parseInt(actionparm.split("_")[1]);
				updateuserbyadmin(userid, request, response);
			} else if (actionparm.startsWith("delete")) {
				int userid = Integer.parseInt(actionparm.split("_")[1]);
				deleteuserbyadmin(userid, request, response);
			} else if (actionparm.endsWith("update")) {
				updateUserget(request, response);
				System.out.println("update user called");
			} else if (actionparm.endsWith("display")) {
				getalluser(request, response);
				System.out.println("display user called");
			} else if (actionparm.startsWith("view")) {
				getuserspecific(request, response);
				System.out.println("display user called");
			}
		}

		else {
			switch (path) {
			case "/submitform":
				isValid(request, response);
				break;
			case "/register":
				registerUser(request, response);
				break;
			case "/updateexistinguser":
				int userid = Integer.parseInt(request.getParameter("userid"));
				updateexistinguser(userid, request, response);
				break;
			case "/adminlogin":
				adminlogin(request, response);
				break;
			case "/userupdate":
				System.out.println("inga than");
				int userid1=  Integer.parseInt(request.getParameter("userid"));
				updateuserbyadmin(userid1, request, response);
				break;

			}
		}
	}

	protected void getuserspecific(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BatteryDao dao = new BatteryDao();
		int id = Integer.parseInt(request.getParameter("userid"));
		String user = request.getParameter("username");
		System.out.println(id+" "+user);
		BeanClass bc=dao.getUser(id, user);
		System.out.println("user get");
		request.setAttribute("bean", bc);
		RequestDispatcher rd = request.getRequestDispatcher("batteryPage/Html/userupdate.jsp");
		rd.forward(request, response);
	}

	protected void adminlogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int adminid = Integer.parseInt(request.getParameter("adminid"));
		String adminpass = request.getParameter("adminpass");
		System.out.println(adminid + " " + adminpass);
		BeanClass bean = new BeanClass();
		bean.setAdminid(adminid);
		bean.setAdminpass(adminpass);
		BatteryDao dao = new BatteryDao();
		if (dao.getadmin(bean)) {
			getalluser(request, response);
		} else {
			System.out.println("no admin found");
		}
	}

	protected void deleteuserbyadmin(int userid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BatteryDao dao = new BatteryDao();
		if (dao.deleteuser(userid)) {
			getalluser(request, response);
		}
	}

	protected void updateuserbyadmin(int userid, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		int usernumber = Integer.parseInt(request.getParameter("usernumber"));
		String email = request.getParameter("usermail");
		String pass = request.getParameter("userpass");
		String gen = request.getParameter("usergender");

		System.out.println(userid + "" + username + " " + usernumber + " " + pass + " " + email + " " + gen);

		BeanClass bean = new BeanClass();
		bean.setId(userid);
		bean.setName(username);
		bean.setNumber(usernumber);
		bean.setEmail(email);
		bean.setPass(pass);
		bean.setGender(gen);
		request.setAttribute("bean", bean);

		BatteryDao dao = new BatteryDao();
		boolean res = dao.userupdate(bean);
		if (res) {
			getalluser(request, response);
		} else {

		}

	}

	protected void getalluserforadmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BatteryDao dao = new BatteryDao();
		List<BeanClass> beans = dao.getalluser();
		request.setAttribute("beans", beans);
		if (!beans.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("batteryPage/Html/displayalluserforadmin.jsp");
			rd.forward(request, response);
		} else {
			System.out.println("bean is empty");
		}
	}

	protected void getalluser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BatteryDao dao = new BatteryDao();
		List<BeanClass> beans = dao.getalluser();
		request.setAttribute("beans", beans);
		if (!beans.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("batteryPage/Html/displayalluserforadmin.jsp");
			rd.forward(request, response);
		} else {
			System.out.println("bean is empty");
		}
	}

	protected void updateexistinguser(int userid, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		int usernumber = Integer.parseInt(request.getParameter("usernumber"));
		String email = request.getParameter("usermail");
		String pass = request.getParameter("userpass");
		String gen = request.getParameter("usergender");

		System.out.println(userid + "" + username + " " + usernumber + " " + pass + " " + email + " " + gen);

		BeanClass bean = new BeanClass();
		bean.setId(userid);
		bean.setName(username);
		bean.setNumber(usernumber);
		bean.setEmail(email);
		bean.setPass(pass);
		bean.setGender(gen);
		request.setAttribute("bean", bean);

		BatteryDao dao = new BatteryDao();
		boolean res = dao.userupdate(bean);
		if (res) {
			System.out.println("update done");
			RequestDispatcher rd = request.getRequestDispatcher("batteryPage/Html/updateform.jsp");
			rd.forward(request, response);
			System.out.println("forword done");
		} else {
//			response.sendRedirect("batteryPage/Html/RegesterationPage.html");
			System.out.println("register failed");
		}

	}

	protected void updateUserget(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userid = Integer.parseInt(request.getParameter("userid"));
		String userpass = (String) request.getParameter("password");
		System.out.println(userid + " " + userpass);
		BatteryDao dao = new BatteryDao();
		BeanClass bean = dao.getUser(userid, userpass);
		request.setAttribute("bean", bean);
		RequestDispatcher rd = request.getRequestDispatcher("batteryPage/Html/updateform.jsp");
		rd.forward(request, response);
	}

	protected void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		int usernumber = Integer.parseInt(request.getParameter("number"));
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String gen = request.getParameter("Gender");

		System.out.println(username + " " + usernumber + " " + pass + " " + email + " " + gen);

		BeanClass bean = new BeanClass();
		bean.setName(username);
		bean.setNumber(usernumber);
		bean.setEmail(email);
		bean.setPass(pass);
		bean.setGender(gen);

		request.setAttribute("bean", bean);

		BatteryDao dao = new BatteryDao();
		if (dao.userRegister(bean)) {
			System.out.println("register done");
			response.sendRedirect("batteryPage/Html/PerfectLogin.html");
			System.out.println("forword done");
		} else {
			response.sendRedirect("batteryPage/Html/RegesterationPage.html");
			System.out.println("register failed");
		}

	}

	protected void isValid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userid = Integer.parseInt(request.getParameter("userid"));
		String pass = request.getParameter("password");
		System.out.println(userid + "&&" + pass);
		request.setAttribute("pass", pass);
		request.setAttribute("userid", userid);

		BeanClass bean = new BeanClass();
		bean.setId(userid);
		bean.setPass(pass);

		request.setAttribute("bean", bean);

		BatteryDao dao = new BatteryDao();
		if (dao.isValoid(bean.getId(), bean.getPass())) {
			System.out.println("login done");
			response.sendRedirect("batteryPage/Html/HomePage.html");
			System.out.println("forword done");

		} else {
			response.sendRedirect("batteryPage/Html/loginerorr.jsp");
			System.out.println("logoin failed");
		}
	}

}
