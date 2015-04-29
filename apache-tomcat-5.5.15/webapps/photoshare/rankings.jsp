<%--
  Author: Amy Ly (amyly@bu.edu)
--%>
<%@ page import="photoshare.AlbumDao" %>
<%@ page import="photoshare.AlbumBean" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.NewUserBean" %>
<%@ page import="photoshare.Picture" %>
<%@ page import="photoshare.PictureDao" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>Top Contributors</title>
	<link rel="stylesheet" type="text/css" href="photoshare.css">
	<link href="http://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
	<style>
		p {
			color: gray;
			font-size: 10px;
			font-style: italic;
		}
	</style>
</head>
<body>
	<a href="index.jsp">Home</a><br>
	<h1>Showing TOP Contributors!</h1>
	<p>*contribution points are calculated by the number photos they own and the number of comments they have left</p>

<% 
	NewUserDao user = new NewUserDao();
	List<NewUserBean> contribs = user.getContributors();
%>

<table> 

 	<tr>
 		<td>Userid</td>
 		<td>Usersname</td>
 		<td>Contribution Points</td>
 	</tr>

 <% 
 	for (NewUserBean c : contribs ) {

 %>

 	<tr> <!-- tr stands for table row, populated with : td , which stands for table data -->
		<td><%= c.getUserid() %></td> <!-- call the methods from the AlbumBean File to get stuff from the list of AlbumBeans -->
		<td><%= user.getFullNameFromId( c.getUserid() ) %></td> <!-- for each album (an AlbumBean object), get the desired fields -->
		<td><%= c.getContribution() %></td>
	</tr>
 <%
 	}
 %>
 </table>

</body>
</html>