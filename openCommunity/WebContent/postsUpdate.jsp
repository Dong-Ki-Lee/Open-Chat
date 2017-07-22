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
	margin-top: 10px
}

.list-group-item-text {
	font-size: 13pt;
}
</style>
</head>
<body>

	<div id="ThemeWrap">
        <div id="ThemeHeader">
            <div><a href="mcontroller?action=home"><img src="img/main_logo.png"></a></div>
            <nav id="ThemeGnb">
                <h2 class="screen_out"><a href="mcontroller?action=home">홈</a></h2>
                <ul id="gnb_1dul">
                    <li class="gnb_2dli"><a href="noticeBoard.jsp">공지사항</a></li>
                    <li class="gnb_2dli"><a href="postBoard.jsp">게시판</a></li>
                    <li class="gnb_2dli"><a href="faqBoard.jsp">FAQ</a></li>
                </ul>
            </nav>
            
            <div id="aaa">
            <form method="post" action="mcontroller?action=logout">
            	<input type="button" class="btn btn-success" value="Login" disabled="disabled">
            	<input type="submit" class="btn btn-success" value="Logout">
            	</form>
            </div>
        </div>

        <div id="ThemeContent">
            <div class="site_sch">
                <form action="scontroller?action=searchBoard" method="post">
                    <input type="text" class="form-control" name="searchTag" id="sch_stx">
                    <button type="submit" class="btn_search">
                        <i class="fa fa-search"><strong class="screen_out">검색</strong></i>
                    </button>
                </form>
            </div>

			<div class="inner_topcontent">
				<div class="info_utility">
					<ul id="tnb">
						<li><a href="mcontroller?action=myInfoPage"><img src="img/user_btn.png"></a></li>
						<li><a href="alarm.html"><img src="img/alarm_btn.png"></a></li>
					</ul>
				</div>
			</div>

			<div id="mArticle">
				<div id="container">

					<section id="sbn_idx" class="sbn"> <!-- 게시글 제목 작성자 내용 모두 가져오기 -->
					<form action="bcontroller?action=postUpdate" method="post">
						<table border="1" class="internalFirst" width="1000px">
							<tr>
								<td class="posts-heading" align="center" width="300px"><label
									class="posts-heading" for="title">게시글 제목</label></td>
								<td><input type="text" class="form-control"
									name="postTitle" id="title"></td>
							</tr>
							<tr>
								<td class="posts-heading" align="center" width="300px"><font
									class="posts-heading">작성자</font></td>
								<!-- 작성자 받아오기 -->
								<td><font name="memberName" class="list-group-item-text">작성자</font></td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="panel panel-default">
										<div class="panel-heading">
											<p class="panel-title posts-heading">게시글 내용</p>
										</div>
										<div class="panel-body">
											<textarea class="form-control col-sm-5" rows="5"
												name="postContent"></textarea>
										</div>
									</div>
								</td>
							</tr>

							<tr align="center">
								<td colspan="2">
									<!-- 수정이벤트 --> <a href="postsInternalView.jsp"
									class="btn btn-default">수정</a> <!-- 취소 이벤트 --> <a
									href="postsInternalView.jsp" class="btn btn-default">취소</a> <!--  
							<a href="postsView.jsp"><input type="reset" class="btn btn-default" value="취소"></a>
							-->
								</td>
							</tr>
						</table>
					</form>
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