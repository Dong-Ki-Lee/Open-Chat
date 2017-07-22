<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="work.model.service.SearchService" %>
<%@ page import="work.model.dto.NoticeBoards" %>
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
	ArrayList<NoticeBoards> list = (ArrayList<NoticeBoards>)request.getAttribute("boardList");
	if(list == null) {
		System.out.println("ddddddddd");
	}
	NoticeBoards dto = null;
%>
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

                    <section id="sbn_idx" class="sbn">
                    
                    	<div class="row">
                    	
                    		<%
								for (int index=0; index < list.size(); index++) {
									dto = list.get(index);
							%>	
							<div class="col-sm-4 col-lg-4 col-md-4">
						        <div class="thumbnail">
						        	<img src="http://placehold.it/320x150" alt=""/>
							        <div class="caption">
							        	<h4 class="pull-right"><%=searchService.getBoardQuantity(dto.getBoardNo()) %></h4>
							        	<h4><a href="bcontroller?action=selectPost&boardNo=<%=dto.getBoardNo()%>"><%=dto.getBoardTitle()%></a>
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
									if(index == 8) {
										%>
										<p>more</p>
										<%
										break;
									}
								}
							%>	
                    		
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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>
</html>