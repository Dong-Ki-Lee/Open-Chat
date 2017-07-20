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
</head>
<body>

    <div id="ThemeWrap">
        <div id="ThemeHeader">
            <div><a href="memberPageAfterLogin.jsp"><img src="img/main_logo.png"></a></div>
            <nav id="ThemeGnb">
                <h2 class="screen_out"><a href="memberPage.jsp">홈</a></h2>
                <ul id="gnb_1dul">
                    <li class="gnb_2dli"><a href="noticeBoard.jsp">공지사항</a></li>
                    <li class="gnb_2dli"><a href="postBoard.jsp">게시판</a></li>
                    <li class="gnb_2dli"><a href="faqBoard.jsp">FAQ</a></li>
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
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
				<table class="mypage_intro">
					<th>
						<td>&nbsp;My Page</td>
					</th>
				</table>
			<br/>			            
				<table class="table table-bordered" width = "50">
					<tr style="text-align:center" height="100">
						<td><img src="img/user_btn.png"></td>
						<td>5500</td>
						<td>120</td>
						<td>40</td>
						<td>180</td>
						<td>83위</td>
					</tr>
					<tr style="text-align:center" height="50">
						<td>조현우님</td>
						<td>마일리지</td>
						<td>등록게시글수</td>
						<td>댓글수</td>
						<td>방문수</td>
						<td>활동랭킹</td>	
					</tr>
				</table>

		    <br/>
				<table class="mypage_intro">
					<th>
						<td>&nbsp;개인정보변경&nbsp;/&nbsp;새활동기록보기</td>
					</th>
				</table>
		    <br/>						
				<table class="table table-bordered"  width = "50">
					<tr style="text-align:center" height="100">
						<td><button type="button" class="btn btn-default">회원정보변경</button>
						</td>
						<td><button type="button" class="btn btn-default">내 활동기록보기</button>
						</td>
					</tr>
					<tr height="50">
						<th style="text-align:left" colspan="2">	
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-default">게시물조회</button>
							<div id="container">
								</br>
								<table class="table table-bordered">
									<th>번호
									<td>게시판제목</td>
									<td>조회수</td>
									<td>조회날짜</td>
									</th>
									<tr>
										<td>1</td>
										<td>해외여행</td>
										<td>129</td>
										<td>2017-01-01</td>
									</tr>
									<tr>
										<td>2</td>
										<td>바다</td>
										<td>521</td>
										<td>2017-01-02</td>
									</tr>
									<tr>
										<td>3</td>
										<td>오버워치</td>
										<td>894</td>
										<td>2017-01-03</td>
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
						    </div>																
						</th>
					</tr>

					<tr height="50">
						<th style="text-align:left" colspan="2">	
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-default">댓글조회　</button>
							<div id="container">
								</br>
								<table class="table table-bordered">
									<th>댓글번호
									<td>댓글제목</td>
									<td>댓글게시날짜</td>
									</th>
									<tr>
										<td>1</td>
										<td>아이참</td>
										<td>2017-01-01</td>
									</tr>
									<tr>
										<td>2</td>
										<td>에이참</td>
										<td>2017-01-02</td>
									</tr>
									<tr>
										<td>3</td>
										<td>어이참</td>
										<td>2017-01-03</td>
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
						    </div>																
						</th>
					</tr>
				</table>    
        <div id="ThemeFooter">
            <div class="inner_footer">
                <div class="info_service">회사소개 개인정보취급방침 서비스이용약관</div>
                Copyright © All rights reserved.
            </div>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>
</html>