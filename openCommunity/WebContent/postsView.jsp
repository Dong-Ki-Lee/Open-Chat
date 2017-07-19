<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="work.model.dto.Posts" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="work.model.dto.PostsPreference" %>
<%@ page import="work.model.dto.Members"%>
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
<style type="text/css">
.posts-heading {
	font-size: 15pt;
	color: #0064b7;
	font-weight: bold;
}

.postsTitle {
	font-size: 17pt;
	color: #0064b7;
	font-weight: bold;
}

.postsTable {
	margin-top: 10px;
}

.list-group-item-text {
	font-size: 13pt;
}
.form-control-4 {
	font-size:11pt;
}
</style>
</head>
<body>
<%
	// 게시글 조회 정보 가져오기
	ArrayList<Posts> posts = (ArrayList<Posts>)request.getAttribute("posts");
	ArrayList<PostsPreference> recommend = (ArrayList<PostsPreference>)request.getAttribute("recommend");
	ArrayList<Members> memberNickname = (ArrayList<Members>)request.getAttribute("memberNickname");
%>
	<div id="ThemeWrap">
		<div id="ThemeHeader">
			<div>
				<img src="img/main_logo.png">
			</div>
			<nav id="ThemeGnb">
			<h2 class="screen_out">홈</h2>
			<ul id="gnb_1dul">
				<li class="gnb_2dli"><a href="noticeBoard.html">공지사항</a></li>
				<li class="gnb_2dli"><a href="postBoard.html">게시판</a></li>
				<li class="gnb_2dli"><a href="faqBoard.html">FAQ</a></li>
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
					<div class="btn-group">
						<table>
							<tr>
								<td width="900px"><font class="postsTitle">전체 게시글 조회</font>
									<select class="form-control-4">
										<option>최신순</option>
										<option>조회순</option>

								</select></td>
								<td align="right" width="200px">
									<!-- <a href="#" class="btn btn-default">정렬</a>
									<a href="#" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown"><span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="#">최신순</a></li>
										<li><a href="#">추천순</a></li>
										
									</ul> 
									--> <a href="postsCreate.jsp" class="btn btn-default">게시글
										생성</a> <!--<button type="button" class="btn btn-default">Left</button>-->
								</td>
							</tr>
						</table>
					</div>
					<br>
					<table class="postsTable">
						<tr>
							<td width="1000px">
									<%
										for (int index = 0; index < posts.size(); index++) {
											
									%>
									<!--  <a href="postsInternalView.jsp" class="list-group-item"> -->
									<form action="controller?action=selectInternalPost" method="post">
									<a href="postsInternalView.jsp" class="list-group-item">
									
										<table>
										<tr>
											<td width="850px">
												<!-- 글제목 -->
												<h4 class="posts-heading" name="postTitle"><%= posts.get(index).getPostTitle() %></h4>
											</td>
											<td width="150px" align="right""><img src="img/good.jpg"
												class="img-circle"></td>
											<td width="150px" align="left">
												<!-- 추천수 -->
												<% for (int rindex = 0; rindex < recommend.size(); rindex++){
													if (posts.get(index).getPostNo() == recommend.get(rindex).getPostNo()) {
														
														
												%>
												<h4 class="posts-heading" name="recommend"><%= recommend.get(rindex).getRecommend() %></h4>
												<%
														break;
													}
												} 
												%>
												
											</td>
										</tr>
										<div class="postsFont">
										<tr>
											<td width="850px">
												<!-- 작성자 -->
												<%
													for (int nindex = 0; nindex < memberNickname.size(); nindex++) {
														if ( posts.get(index).getMemberNo() == memberNickname.get(nindex).getMemberNo()) {
																	
								
												%>
												<p class="list-group-item-text" name="memberNickname"><%= memberNickname.get(nindex).getMemberNickname() %></p>
												<%
														break;
														}
													}
												%>
											</td>
											<td width="150px" align="right">
												<!-- 작성일자 -->
												<p class="list-group-item-text" name="createTime"><%= posts.get(index).getCreateTime() %></p>
											</td>
											<td width="150px" align="right">
												<!-- 조회수 -->
												<p class="list-group-item-text" name="postViews"><%= posts.get(index).getPostViews() %></p>
											</td>
										</tr>
										</div>
									</table>
									</a>
									</form>
									<%
										}
									%>
									</td>

						</tr>


						<tr align="center">

							<td>

								<ul class="pagination pagination-centered">
									<li class="disabled"><a href="#">&laquo;</a></li>
									<li class="active"><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">&raquo;</a></li>
								</ul>
							</td>
						</tr>
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