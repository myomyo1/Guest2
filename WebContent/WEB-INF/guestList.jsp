<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.GuestVo"%>
<%@ page import="java.util.List"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="gs" method="get">
		<%
			//<form action="add.jsp" method="post"> : add.jsp로 직접 부르지않고 controller에서
		%>
		<table border=1 width=500>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="pass"> <input
					type="hidden" name="a" value="add"> <!-- //value="add" : a의 초기값을 지정해주는 것 -->
				</td>
			</tr>
			<tr>
				<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
			</tr>
		</table>
	</form>
	<br />

	<%
		List<GuestVo> list = (List<GuestVo>) request.getAttribute("list"); //"list" 라는 이름을 붙여준 list<GuestVo>객체(=>gList라고했던애)가 옴. //넘어올때 object로 넘어왔으니 리스트로 강제 형변환해줌
		for (GuestVo g : list) {
	%>


	<table width=510 border=1>
		<tr>
			<td><%=g.getNo()%></td>
			<td><%=g.getName()%></td>
			<td><a href="gs?a=guestDeleteform&no=<%=g.getNo()%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4><%=g.getContent()%></td>
		</tr>
	</table>
	<br />
	<%
		}
	%>


</body>
</html>