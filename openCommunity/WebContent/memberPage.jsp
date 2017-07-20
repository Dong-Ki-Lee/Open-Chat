<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="work.model.dto.NoticeBoards" %>
<%@ page import="work.model.service.SearchService" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Open Community</title>
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/template.css">
</head>
<body>
<%
	if ((String)session.getAttribute("memberEmail") == null || session.getAttribute("memberNo") == null) {
		request.setAttribute("Message", "로그인 후 이용하시기 바랍니다.");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
		dispatcher.forward(request, response);
		return;
	}
	SearchService searchService = new SearchService();
%>

<%
  	ArrayList<NoticeBoards> subscribeBoard = (ArrayList<NoticeBoards>)request.getAttribute("subscribeBoard");
    ArrayList<NoticeBoards> matchBoard = (ArrayList<NoticeBoards>)request.getAttribute("matchBoard");
%>
    <div id="ThemeWrap">
        <div id="ThemeHeader">
            <div><img src="img/main_logo.png"></div>
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
                        <li><a href="myInfo.html"><img src="img/user_btn.png"></a></li>
                        <li><a href="alarm.html"><img src="img/alarm_btn.png"></a></li>
                    </ul>
                </div>
            </div>

            <div id="mArticle">
                <div id="container">

                    <section id="sbn_idx" class="sbn">

					    <div class="row">
						    <%
							    System.out.println(matchBoard.size());
								if(matchBoard.size() > 3) {

								    System.out.println("ddddddd");
							%>
							<form action="scontroller?action=recommandExtend" method="post" class="col-sm-2 col-lg-2 col-md-2">
						        <input type="submit" class="btn btn-primary" value="추천 게시판">
						    </form>
						    <br></br>
					        <%
								} else {

								    System.out.println("fffffff");
							%>
							<form action="" method="get" class="col-sm-2 col-lg-2 col-md-2">
					        <input type="button" class="btn btn-primary" value="추천 게시판">
					        </form>
					        <br></br>
				        <%
							}
						%>
						</div>
						
						<div class="row">
					    <%
					   	 	NoticeBoards dto = null;
							for (int index = 0; index < matchBoard.size(); index++) {
					
					    		dto = matchBoard.get(index);
						%>
						<div class="col-sm-4 col-lg-4 col-md-4">
					        <div class="thumbnail">
					        	<img src="http://placehold.it/320x150" alt=""/>
						        <div class="caption">
						        	<h4 class="pull-right"><%=searchService.getBoardQuantity(dto.getBoardNo()) %></h4>
						        	<h4><a href="controller?action=selectPost&boardNo=<%=dto.getBoardNo()%>"><%=dto.getBoardTitle()%></a>
						    		</h4>
							    	<p></p>
						    		<p><%=dto.getBoardTag()%></p>
					
					        	</div>
						        <div class="ratings">
						        <p class="pull-right">new post 3</p>
						    	</div>
					    	</div>
					    </div>
					    <%
					    		if(index == 2) {
					    			break;
					    		}
							}
						%>
					
						</div>
					
						<div class="row">
					    <%
							if(matchBoard.size() > 3) {
						%>
							<form action="scontroller?action=subscribeExtend" method="get" class="col-sm-2 col-lg-2 col-md-2">
					        <input type="submit" class="btn btn-primary" value="구독 게시판">
					        </form>
					        <br></br>
					        </div>
				        <%
							} else {
						%>
						<form action="" method="get" class="col-sm-2 col-lg-2 col-md-2">
					        <input type="button" class="btn btn-primary" value="구독 게시판">
					        </form>
					        <br></br>
					        </div>
				        <%
							}
						%>
					
						<div class="row">
					    <%
							for (int index = 0; index < subscribeBoard.size(); index++) {
					
					    		dto = subscribeBoard.get(index);
						%>
						<div class="col-sm-4 col-lg-4 col-md-4">
					        <div class="thumbnail">
					        <img src="http://placehold.it/320x150" alt=""/>
					        <div class="caption">
						        <h4 class="pull-right"><%=searchService.getBoardQuantity(dto.getBoardNo()) %></h4>
						        <h4><a href="controller?action=selectPost&boardNo=<%=dto.getBoardNo()%>"><%=dto.getBoardTitle()%></a>
							    </h4>
							    <p></p>
							    <p><%=dto.getBoardTag()%></p>
							
					        </div>
					        <div class="ratings">
					        	<p class="pull-right">new post 3</p>
					    	</div>
					    	</div>
					    </div>
					    <%
						    	if(index == 2) {
						    		break;
						    	}
							}
						%>
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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>

