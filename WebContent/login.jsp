<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Entrar - Events Tickets</title>
</head>
<body>
	<h1>Entrar</h1>
	<%
		String error = request.getParameter("error");
		if (error != null && error.equals("true")) {
			out.println("<div class='error-message'>Usuário ou senha inválido.</div>");
		}
	%>
	<form action="signIn" method="post">
		<div>
			<label for="username">Usuário</label>
			<input type="text" name="username" id="username" />
		</div>
		<div>
			<label for="password">Senha</label>
			<input type="password" name="password" id="password" />
		</div>
		<div>
			<input type="submit" value="Entrar" />
		</div>
	</form>
</body>
</html>