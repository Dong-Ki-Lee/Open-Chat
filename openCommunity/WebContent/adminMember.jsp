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
			<h2 class="screen_out">홈</h2>
			<ul id="gnb_1dul">
				<li class="gnb_2dli"><a href="controller?action=adminMemberPage">회원관리</a></li>
				<li class="gnb_2dli"><a href="controller?action=adminBoardPage">게시판관리</a></li>
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
					<strong>신규 가입 회원 목록</strong>
					<table class="table table-bordered">
						<th>
						<td>회원번호</td>
						<td>닉네임</td>
						<td>가입일</td>
						<td>마일리지</td>
						<td>최종로그인</td>
						<td>비고</td>
						</th>
						<tr>
							<td>1</td>
							<td>1111</td>
							<td>임지나</td>
							<td>17-06-06</td>
							<td>3400</td>
							<td>17-06-06 11:56</td>
							<td><button type="button" class="btn btn-primary-xs">탈퇴</button></td>
						</tr>
						<tr>
							<td>2</td>
							<td>1345</td>
							<td>조현우</td>
							<td>17-01-04</td>
							<td>10000</td>
							<td>17-05-06 11:56</td>
							<td><button type="button" class="btn btn-primary-xs">탈퇴</button></td>
						</tr>
						<tr>
							<td>3</td>
							<td>3826</td>
							<td>정영철</td>
							<td>17-06-30</td>
							<td>1000</td>
							<td>17-06-06 10:56</td>
							<td><button type="button" class="btn btn-primary-xs">탈퇴</button></td>
						</tr>
						<tr>
							<td>4</td>
							<td>2294</td>
							<td>이동기</td>
							<td>17-04-20</td>
							<td>30400</td>
							<td>17-06-01 03:00</td>
							<td><button type="button" class="btn btn-primary-xs">탈퇴</button></td>
						</tr>
					</table>
					<div class="jb-center">
						<ul id="newMemberPag" class="pagination">
							<li class="disabled"><a href="#"><span
									class="glyphicon glyphicon-chevron-left"></span></a></li>
							<li><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li class="active"><a href="#">5</a></li>
							<li><a href="#">6</a></li>
							<li><a href="#">7</a></li>
							<li><a href="#">8</a></li>
							<li><a href="#">9</a></li>
							<li><a href="#">10</a></li>
							<li><a href="#"><span
									class="glyphicon glyphicon-chevron-right"></span></a></li>
						</ul>
					</div>

					<strong>전체 회원 목록</strong> 
					
					<%
						ArrayList<MembersInfo> list = (ArrayList<MembersInfo>)request.getAttribute("memInfolist");
						MembersInfo memInfoDto = null;
					%>
					
					<select class="form-control-4">
						<option>회원번호순</option>
						<option>가입일순</option>
						<option>마일리지순</option>
					</select>

					<table class="table table-bordered">
						<tr>
						<th>회원번호</th>
						<th>닉네임</th>
						<th>이메일</th>
						<th>가입일</th>
						<th>마일리지</th>
						<th>최종 로그인</th>
						<th>비고</th>
						</tr>
						
						<% 
							for (int i=0; i<list.size(); i++) {
								memInfoDto = list.get(i);
						%>
						<tr>
							<td><%= i %></td>
							<td><%= memInfoDto.getMemberNo() %></td>
							<td><%= memInfoDto.getMemberNickname() %></td>
							<td><%= memInfoDto.getMemberEmail() %></td>
							<td><%= memInfoDto.getJoinDate() %></td>
							<td><%= memInfoDto.getMileage() %></td>
							<td><%= memInfoDto.getLastLoginDate() %></td>
							<td><a href="controllre?action=deleteMember&memberNo=<%= memInfoDto.getMemberNo() %>">탈퇴</a></td>
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
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li class="active"><a href="#">5</a></li>
							<li><a href="#">6</a></li>
							<li><a href="#">7</a></li>
							<li><a href="#">8</a></li>
							<li><a href="#">9</a></li>
							<li><a href="#">10</a></li>
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