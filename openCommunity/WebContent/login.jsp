<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width-device-with", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>����Ŀ�´�Ƽ�Դϴ�.</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbor-header">
			<a class="nabar-brand" href="index.jsp">Open Community Web Site</a>
  		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse=1">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-taggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">�α���/ȸ������</a>
					<ul class="dropdown-menu">
						<li class "active"><a href="login.jsp">�α���</a></li>
						<li><a href="join.jsp">ȸ������</a></li>					
					</ul>
				</li>
			</ul>	
		</div>
	</nav>
	<div class="container">
		<div class="col-lg-3"></div>
		<div class="col-lg-6">
			<div class="jumbotron" style="padding-top: 8px;">
				<form method="post" action="loginAction.jsp">
					<h3 style="text-align : center;"> �α��� </h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="���̵�" name="userId" maxlength="20">			
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="��й�ȣ" name="userPassword" maxlength="20">			
					</div>
					<input type="submit" class="btn btn-primary form-control" value="�α���">
					<input type="submit" class="btn btn-primary form-control" value="��й�ȣã��">				 

				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	</div>
</body>
</html>