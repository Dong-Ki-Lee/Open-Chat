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
            <div><a href="memberPage.jsp"><img src="img/main_logo.png"></a></div>
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
						<th style="text-align:center" colspan="2">	
						<button type="button" class="btn btn-default btn-center">회원정보변경</button>
						</br>
						</br>
							<div class="container">
								<div class="col-lg-3"></div>
								<div class="col-lg-6">
									<div class="jumbotron" style="padding-top: 8px;">
										<form method="post" action="joinAction.jsp">
											<div class="form-group">
												<input type="text" class="form-control" placeholder="이메일" name="memberEmail" maxlength="50">						
											</div>
											<div class="form-group">
												<input type="text" class="form-control" placeholder="닉네임" name="MemberNickname" maxlength="50">			
											</div>
											<div class="form-group">
												<input type="password" class="form-control" placeholder="비밀번호" name="memberPw" maxlength="20">			
											</div>
											<div class="form-group">
												<input type="password" class="form-control" placeholder="비밀번호 확인" name="memberPw" maxlength="20">			
											</div>
											<div class="form-group">
											<input type="submit" class="btn btn-primary btn-sm form-control" value="수정">					 
											</div>
											<input type="submit" class="btn btn-primary btn-sm form-control" value="취소">
										</form>
									</div>
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