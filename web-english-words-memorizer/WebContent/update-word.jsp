<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type = "text/css" rel = "stylesheet" href = "css/style.css">
<title>Update word</title>
</head>
<body>

<header>
Update word
</header>


	<div id = "content">
    
    
    					  	<div id = "newWordForm" >
        				<form action ="MemorizerControllerServlet" method = "GET" >
        				<input type="hidden" name="command" value = "UPDATE"/>
        				<input type="hidden" name="idWord" value = "${updatedWord.id}"/>
            	
         					 English word:	<input type= "text" name="englishWord" value = "${updatedWord.englishWord}"/>
            				<br>
         					 Polish word : 		<input type= "text" name = "polishWord" value = "${updatedWord.polishWord}"  />
            				<br>
            					<input type="submit" value="Update word">
            
            			</form>
            			
            			<a class="btn" href="MemorizerControllerServlet">Go back</a>
        				</div>
    
            
               
	</div>


<footer>
  footer
</footer>

</body>
</html>