<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html>
<html>
	
	
	<head>
	
		<meta charset="utf-8">
		<title>My words</title>
		
		<link type = "text/css" rel = "stylesheet" href = "css/style.css">

	</head>


<body>

<header>
	
	<p>English worlds memorizer</p>
	
</header>
	
	
	
	<div id = "content">
	
		<div id="menu">
		
		<c:url var="startGame" value="MemorizerControllerServlet">
		   	<c:param name = "command" value = "START"/>
		  
		   </c:url>
	
			<ul>
  				<li><a class="active" href="add-word.jsp">Add new word</a></li>
  				<li><a href="${startGame}">Check yourself</a></li>
			</ul>
	
		</div>
	
		<div id="data">
			
			<div id="search">
				----Search----<br>
				<form action ="MemorizerControllerServlet" method = "GET" >
				 	<input type="hidden" name="command" value="SEARCH">
					<input type="text" name="key">
					<input type="submit" value ="search">
				</form>
				
			</div>
			

			<table>
			<tr>
				<th>Number</th>
				<th>English word</th>
				<th>Polish word</th>
				<th>Action</th>
			</tr>
		
		   <% int i = 0; %>
		 
		    <c:forEach var = "word" items = "${wordsList}">
		    	
		    <c:url var="deleteLink" value="MemorizerControllerServlet">
		   	<c:param name = "command" value = "DELETE"/>
		   	<c:param name = "wordId" value = "${word.id}"/>
		  
		   </c:url>
		   
		    <c:url var="updateLink" value="MemorizerControllerServlet">
		   	<c:param name = "wordId" value = "${word.id}"/>
		   	<c:param name = "command" value = "GETONE"/>
		  
		   </c:url>
		   
			  <tr>
			  		<td><%out.println(++i);%></td>
			  		<td>${word.englishWord}</td>
			  		<td>${word.polishWord}</td>
			  		<td>
			  			<a class="btn" href="${updateLink}">Update</a>
			  			|
			  			<a class="btn" href="${deleteLink}" onclick="if ( !(confirm('Are you sure you want to delete this word?')) ) return false;">Delete</a>
			  		</td>
			  		
			  </tr>
			  
			 
			</c:forEach>
			
			  </table>
			
			
			
		</div>
	
	
	</div>
	
	
	
	<footer>
	
				Footer
			
	</footer>
	
</body>


</html>