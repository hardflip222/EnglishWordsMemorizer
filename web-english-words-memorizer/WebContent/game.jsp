<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type = "text/css" rel = "stylesheet" href = "css/style.css">
<title>Game</title>
</head>
<body>

<header>
	Game
</header>

	<div id = "content">
	
	
	<p style="text-align: center;" >${w}</p>
	<div style = "text-align: center; margin:auto;">
	<form action ="MemorizerControllerServlet" method = "GET" >
	<input type="hidden" name="command" value="CHECK">
		<input type="text" name="word"/>
		<input type="submit" value = "Check"/>
		</form>
	</div>
	<p style="text-align: center;" >odpowiedz</p>
	
	</div>
<footer>
    footer
</footer>

</body>
</html>