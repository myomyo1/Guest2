package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;

@WebServlet("/gs")
public class GusetServlet extends HttpServlet {
	   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//브라우저에서 받은 response, request를 controller에서 받아서 a로 넘긴 것
	String actionName = request.getParameter("a");
	if(actionName.equals("list")) {
		System.out.println("list 진입");
		GuestDao dao = new GuestDao();
		List<GuestVo> glist = dao.showListAll(); //얘를 forword할때 request에 포함시켜줄 것. dao에 showListAll로 꺼내옴. list형태로 보여줘야하므로 list변수만들어서 저장
		request.setAttribute("list", glist); //앞에 "list"는 나중에 getattribute로 받을 이름. 뒤에는 정보. setattribute가 request객체에다가 list객체(정보)를 붙여줌.
		//controller에서 list로 포워드시키는 것.                    =대상
//		RequestDispatcher rd = request.getRequestDispatcher("guestList.jsp"); 
//		rd.forward(request, response); //url로 확인해보면 포워드 시킨 상태로 guestList.jsp화면이 보여짐.
		WebUtil.forward(request, response, "WEB-INF/guestList.jsp");
		
	}else if(actionName.equals("add")) {
		System.out.println("add 진입");
		//name, pass, content 를 파라미터로 받아서 변수에 저장한 것.
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String content = request.getParameter("content");
		//위의 값들을 객체로 만들어서 다오에게 넘겨줘야함
		GuestVo gvo = new GuestVo(name, password, content) ;
		GuestDao gdao = new GuestDao();
		gdao.insert(gvo);
		
//		response.sendRedirect("gs?a=list"); //리다이렉트 여기서 "list.jsp" X , 컨트롤러에서 a값 확인해서 넘기기.
		WebUtil.redirect(request, response,"/guest2/gs?a=list");
		//사용자는 guest2/gs?a=list 주소로만 접근할 수 있다(controller통해서만) 
	}else if(actionName.equals("guestDeleteform")) {
		System.out.println("guestDeleteform 진입");
		
		int no = Integer.parseInt(request.getParameter("no"));
		request.setAttribute("no", no);
//		RequestDispatcher rd = request.getRequestDispatcher("guestDeleteform.jsp"); //"~" : 포워드할 페이지 지정해준 것. 
//		rd.forward(request, response); //url로 확인해보면 포워드 시킨 상태로 guestList.jsp화면이 보여짐.
		WebUtil.forward(request, response, "/WEB-INF/guestDeleteform.jsp");
		//이제 http://localhost:8088/guest2/guestList.jsp X. 
		//사용자요청 : http://localhost:8088/guest2/WEB-INF/guestDeleteform.jsp X.
	}else if(actionName.equals("delete")) {
		System.out.println("delete 진입");
		
		int no = Integer.parseInt(request.getParameter("no"));
		String pw = request.getParameter("password");
		GuestDao dao = new GuestDao();
		dao.delete(no, pw);
//		response.sendRedirect("gs?a=list");
		WebUtil.redirect(request, response, "guest2/gs?a=list");
	}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
