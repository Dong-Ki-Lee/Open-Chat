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
                    
				        <div class="row">
				
				            <div class="col-sm-4 col-lg-4 col-md-4">
				                <div class="thumbnail">
				                    <img src="http://placehold.it/320x150" alt="">
				                    <div class="caption">
				                        <h4 class="pull-right">$24.99</h4>
				                        <h4><a href="#">First Product</a>
				                        </h4>
				                        <p>See more snippets like this online store item at <a target="_blank" href="http://www.bootsnipp.com">Bootsnipp - http://bootsnipp.com</a>.</p>
				                    </div>
				                    <div class="ratings">
				                        <p class="pull-right">15 reviews</p>
				                    </div>
				                </div>
				            </div>
				
				            <div class="col-sm-4 col-lg-4 col-md-4">
				                <div class="thumbnail">
				                    <img src="http://placehold.it/320x150" alt="">
				                    <div class="caption">
				                        <h4 class="pull-right">$64.99</h4>
				                        <h4><a href="#">Second Product</a>
				                        </h4>
				                        <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
				                    </div>
				                    <div class="ratings">
				                        <p class="pull-right">12 reviews</p>
				                    </div>
				                </div>
				            </div>
				
				            <div class="col-sm-4 col-lg-4 col-md-4">
				                <div class="thumbnail">
				                    <img src="http://placehold.it/320x150" alt="">
				                    <div class="caption">
				                        <h4 class="pull-right">$74.99</h4>
				                        <h4><a href="#">Third Product</a>
				                        </h4>
				                        <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
				                    </div>
				                    <div class="ratings">
				                        <p class="pull-right">31 reviews</p>
				                    </div>
				                </div>
				            </div>
				
				
				            <div class="col-sm-4 col-lg-4 col-md-4">
				                <div class="thumbnail">
				                    <img src="http://placehold.it/320x150" alt="">
				                    <div class="caption">
				                        <h4 class="pull-right">$84.99</h4>
				                        <h4><a href="#">Fourth Product</a>
				                        </h4>
				                        <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
				                    </div>
				                    <div class="ratings">
				                        <p class="pull-right">6 reviews</p>
				                    </div>
				                </div>
				            </div>
				
				            <div class="col-sm-4 col-lg-4 col-md-4">
				                <div class="thumbnail">
				                    <img src="http://placehold.it/320x150" alt="">
				                    <div class="caption">
				                        <h4 class="pull-right">$94.99</h4>
				                        <h4><a href="#">Fifth Product</a>
				                        </h4>
				                        <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
				                    </div>
				                    <div class="ratings">
				                        <p class="pull-right">18 reviews</p>
				                    </div>
				                </div>
				            </div>
				
				            <div class="col-sm-4 col-lg-4 col-md-4">
				                <div class="thumbnail">
				                    <img src="http://placehold.it/320x150" alt="">
				                    <div class="caption">
				                        <h4 class="pull-right">$94.99</h4>
				                        <h4><a href="#">Fifth Product</a>
				                        </h4>
				                        <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
				                    </div>
				                    <div class="ratings">
				                        <p class="pull-right">18 reviews</p>
				                    </div>
				                </div>
				            </div>
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