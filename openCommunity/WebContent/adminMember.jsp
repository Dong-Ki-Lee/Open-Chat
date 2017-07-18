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
                    <li class="gnb_2dli"><a href="adminMember.html">회원관리</a></li>
                    <li class="gnb_2dli"><a href="adminNotice.html">게시판관리</a></li>
                    <li class="gnb_2dli"><a href="adminChart.html">통계</a></li>
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
                        <strong>전체 회원 목록</strong>
                        <select class="form-control-4">
                            <option>회원번호순</option>
                            <option>가입일순</option>
                            <option>마일리지순</option>
                        </select>

                        <table class="table table-bordered">
                            <th> 
                                <td>회원번호</td>
                                <td>닉네임</td>
                                <td>이메일</td>
                                <td>가입일</td>
                                <td>마일리지</td>
                                <td>비고</td>
                            </th>
                            <tr>
                                <td>1</td>
                                <td>1111</td>
                                <td>임지나</td>
                                <td>wlsgk0323@icloud.com</td>
                                <td>17-06-06</td>
                                <td>3400</td>
                                <td><button type="button" class="btn btn-primary-xs">탈퇴</button></td>
                            </tr>
                             <tr>
                                <td>2</td>
                                <td>1345</td>
                                <td>조현우</td>
                                <td>chohw@icloud.com</td>
                                <td>17-01-04</td>
                                <td>10000</td>
                                <td><button type="button" class="btn btn-primary-xs">탈퇴</button></td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>3826</td>
                                <td>정영철</td>
                                <td>yeongcheol@icloud.com</td>
                                <td>17-06-30</td>
                                <td>1000</td>
                                <td><button type="button" class="btn btn-primary-xs">탈퇴</button></td>
                            </tr>
                            <tr>
                                <td>4</td>
                                <td>2294</td>
                                <td>이동기</td>
                                <td>dongki@icloud.com</td>
                                <td>17-04-20</td>
                                <td>30400</td>
                                <td><button type="button" class="btn btn-primary-xs">탈퇴</button></td>
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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>
</html>