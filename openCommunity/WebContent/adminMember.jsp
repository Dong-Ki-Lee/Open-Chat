<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="work.model.dto.Members" %>
<%@ page import="work.model.dto.MembersInfo" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>템플릿</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/template.css">
<link rel="stylesheet" type="text/css" href="css/admin.css">
</head>
<body>

<%
	String memberEmail = (String)session.getAttribute("memberEmail");
	int memberNo = (int)session.getAttribute("memberNo");
	if (memberEmail == null || memberNo == 0) {
		request.setAttribute("Message", "로그인 후 이용하시기 바랍니다.");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
		dispatcher.forward(request, response);
	}
%>

	<div id="ThemeWrap">
		<div id="ThemeHeader">
			<div>
				<img src="img/main_logo.png">
			</div>
			<nav id="ThemeGnb">
			<h2 class="screen_out"><a href="adminMember.jsp">홈</a></h2>
			<ul id="gnb_1dul">
				<li class="gnb_2dli"><a href="adminMember.jsp">회원관리</a></li>
				<li class="gnb_2dli"><a href="controller?action=adminBoard">게시판관리</a></li>
				<li class="gnb_2dli"><a href="adminChart.jsp">통계</a></li>
			</ul>
			</nav>
		</div>

		<div id="ThemeContent">
			<div class="site_sch">
				<form>
					<input type="text" class="form-control" name="stx" id="sch_stx">
					<button type="submit" class="btn_search">
						<i class="fa fa-search"><strong class="screen_out">검색</strong></i>
					</button>
				</form>
			</div>

			<div class="inner_topcontent">
				<div class="info_utility">
					<ul id="tnb">
						<li><a href="myInfo.html"><img src="img/user_btn.png"></a></li>
						<li><a href="alarm.html"><img src="img/alarm_btn.png"></a></li>
					</ul>
				</div>
			</div>

			<div id="mArticle">
				<div id="container">
					<section id="sbn_idx" class="sbn"> 
					<strong>회원 목록</strong> 
					
					<%
						ArrayList<MembersInfo> list = (ArrayList<MembersInfo>)request.getAttribute("membersInfolist");
						MembersInfo memberInfoDto = null;
					%>
					
					<select class="form-control-4">
						<option>신규가입회원</option>
						<option>회원번호순</option>
						<option>가입일순</option>
						<option>마일리지순</option>
					</select>

					<table class="table table-bordered">
						<tr>
						<th>No.</th>
						<th>회원번호</th>
						<th>닉네임</th>
						<th>이메일</th>
						<th>가입일</th>
						<th>마일리지</th>
						<th>최종 로그인</th>
						<th>비고</th>
						</tr>
						<% 
							for (int i=1; i<=list.size(); i++) {
								memberInfoDto = list.get(i-1);
						%>
						<tr>
							<td><%= i %></td>
							<td><%= memberInfoDto.getMemberNo() %></td>
							<td><%= memberInfoDto.getMemberNickname() %></td>
							<td><%= memberInfoDto.getMemberEmail() %></td>
							<td><%= memberInfoDto.getJoinDate() %></td>
							<td><%= memberInfoDto.getMileage() %></td>
							<td><%= memberInfoDto.getLastLoginDate() %></td>
							<td><a href="controller?action=deleteMember&memberNo=<%= memberInfoDto.getMemberNo() %>">탈퇴</a></td>
						</tr>
						
						<%
							}
						%>
						
					</table>

					<div class="jb-center">
						<ul id="memberPag" class="pagination">
							<li class="disabled"><a href="#"><span
									class="glyphicon glyphicon-chevron-left"></span></a></li>
							<li><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li class="active"><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">6</a></li>

							<li><a href="#"><span
									class="glyphicon glyphicon-chevron-right"></span></a></li>
						</ul>
					</div>

					</section>
					<div class="main_contents"></div>

				</div>
			</div>
		</div>

		<div id="ThemeFooter">
			<div class="inner_footer">
				<div class="info_service">회사소개 개인정보취급방침 서비스이용약관</div>
				Copyright © All rights reserved.
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>