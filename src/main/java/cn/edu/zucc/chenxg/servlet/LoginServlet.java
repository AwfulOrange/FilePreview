package cn.edu.zucc.chenxg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.chenxg.DAO.UserInfoDAO;
import cn.edu.zucc.chenxg.model.Userinfo;

/**
 * Servlet implementation class LoginServelt
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		UserInfoDAO userdao = new UserInfoDAO();
		Userinfo user = userdao.getUserInfoByUserID(userid);
		session.setAttribute("username", user.getUsername());
//		session.setAttribute("username", "Amber Chen");
		if(password.equals(user.getUserPassword())){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
//		doGet(request, response);
	}

}
