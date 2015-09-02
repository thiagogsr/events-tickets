<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Entrar - Events Tickets</title>

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="vendor/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css" />

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-4 col-sm-offset-4">
				<h1>Entrar</h1>
				<%
					String error = request.getParameter("error");
					if (error != null && error.equals("true")) {
						out.println("<div class='alert alert-danger' role='alert'>Usuário ou senha inválido.</div>");
					}
				%>
				<form action="signIn" method="post">
					<div class="form-group">
						<label for="username">Usuário</label> <input type="text"
							name="username" id="username" class="form-control" />
					</div>
					<div class="form-group">
						<label for="password">Senha</label> <input type="password"
							name="password" id="password" class="form-control" />
					</div>
					<div class="form-group">
						<input type="submit" value="Entrar"
							class="btn btn-primary form-control" /> 
						<a href="faces/SignUp.xhtml">Cadastre-se</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>