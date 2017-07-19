<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<!--
댓글 수정 이프문 -->.btnUpdate {
	visibility: visible;
}
/*.btnUpdate{visibility:hidden;}*/
.posts-heading {
	font-size: 15pt;
	color: #0064b7;
	font-weight: bold;
}
.threebtn {
	font-size: 20pt;
	color: #0064b7;
	font-weight: bold;
}

.list-group-item-text {
	font-size: 13pt;
}

font.list-group-item-text {
	font-size: 13pt;
	color: #0064b7;
	font-weight: bold;
}
</style>
</head>
<body>
<%
	String postTitle = (String)request.getAttribute("postTitle");
	String memberNickname = (String)request.getAttribute("memberNickname");
	int postViews = (int)request.getAttribute("postViews");
	String createTime = (String)request.getAttribute("createTime");
	int recommend = (int)request.getAttribute("recommend");
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
					<table class="internalFirst" border="1" width="1000px">
						<tr>
							<td width="820px"><font class="list-group-item-text">Open
									Community</font></td>
							<td align="right">
								<!-- 수정버튼 클릭시 받아와서 수정 조회수, 좋아요수.. 그대로 --> 
								<a href="postsUpdate.jsp" class="btn btn-default">
									수정
								</a> 
								
								<!-- 삭제버튼 클릭시 되묻고 삭제 -->
								<a href="postsView.jsp" class="btn btn-default">삭제</a> <a
								href="postsView.jsp" class="btn btn-default">목록</a>
							</td>

						</tr>
						<tr>
							<td colspan="2" width="1000px">
								<table>
									<tr>
										<td width="850px">
											<h4 class="posts-heading"><%= postTitle %></h4>
										</td>
										<td width="150px" align="right""><img src="img/good.jpg"
											class="img-circle"></td>
										<td width="150px" colspan="2" align="left">
											<h4 class="posts-heading"><%= recommend %></h4>
										</td>
									</tr>
									<tr>
										<td width="850px">
											<p class="list-group-item-text"><%= memberNickname %></p>
										</td>
										<td width="150px" align="right">
											<p class="list-group-item-text"><%= createTime %></p>
										</td>
										<td width="150px" align="right">
											<p class="list-group-item-text"><%= postViews %></p>
										</td>
									</tr>
								</table>
							</td>
							<td></td>
						</tr>
						<tr>
							<td colspan="2" class="list-group-item-text">
								<p>글내용</p>
							</td>
							<td></td>
						</tr>
						<tr>
							<td colspan="2" class="threebtn" align="center"><a href="#"
								class="btn btn-default"><img src="img/like.png" /> 추천수
									&nbsp;</a> <a href="#" class="btn btn-default"><img
									src="img/dislike.png" /> 반대수</a></td>
							<td></td>
						</tr>
						<!-- for문 댓글계수만큼 -->
						<tr>
							<td>
								<table>
									<tr>
										<td width="100px" rowspan="2"><img src="img/user.jpg"
											class="img-circle"></td>
										<td width="100%">
											<h4 class="posts-heading">사용자이름</h4>
										</td>
									</tr>
									<tr>
										<td width="100%">
											<p class="list-group-item-text">내용</p>
										</td>
									</tr>
								</table>
							</td>
							<td>
								<!-- 본인거일경우 수정버튼 삭제버튼 추가 --> <a href="#"
								class="btn btn-default btnUpdate">수정</a>

							</td>

						</tr>
						<tr>
							<td>
								<table >
									<tr>
										<td width="100px" rowspan="2"><img src="img/user.jpg"
											class="img-circle"></td>
										<td width="100%">
											<h4 class="posts-heading">사용자이름</h4>
										</td>
									</tr>
									<tr>
										<td width="100%"><input type="text" class="form-control"
											id="content"></td>
									</tr>
								</table>
							</td>
							<td><a href="#" class="btn btn-default">등록</a></td>
						</tr>

					</table>

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