<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width-device-with", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>회원가입페이지입니다.</title>
</head>
<body>

<!-- 로그인전 메뉴 -->
	<nav class="navbar navbar-default">
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse=1">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-taggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">로그인/회원가입</a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li class "active"><a href="join.jsp">회원가입</a></li>					
					</ul>
				</li>
			</ul>	
		</div>
	</nav>
	<div class="container">
		<div class="col-lg-3"></div>
		<div class="col-lg-6">
			<div class="jumbotron" style="padding-top: 8px;">
				<form method="post" action="mcontroller?action=joinSave">
					<h3 style="text-align : center;"> 회원가입 </h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이메일" name="memberEmail" maxlength="50">
						<p class="help-block">로그인 계정으로 사용할 이메일을 입력하세요</p>							
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
					<input type="submit" class="btn btn-primary form-control" value="회원가입">				 
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	</div>
</body>
</html>