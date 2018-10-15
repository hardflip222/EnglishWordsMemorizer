<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type = "text/css" rel = "stylesheet" href = "css/style.css">
<title>Add new world</title>
</head>
<body>

<header>
Add new world
</header>


	<div id = "content">
            
    
    					  	<div id = "newWordForm" >
        				<form action ="MemorizerControllerServlet" method = "GET" >
        				<input type="hidden" name="command" value = "ADD"/>
            	
         					 English word:  	<input type= "text" name="englishWord"/>
            				<br>
         					 Polish word : 		<input type= "text" name = "polishWord" />
            				<br>
            					<input type="submit" value="Add new word">
            
            			</form>
        				</div>
    
            
               
	</div>


<footer>
  footer
</footer>

</body>
</html>