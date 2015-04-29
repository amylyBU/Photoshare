<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.TagDao" %>
<%@ page import="photoshare.TagBean" %>
<%@ page import="java.util.List" %>

<html>
<head><title>Popular Tags</title>
<link rel="stylesheet" type="text/css" href="photoshare.css">
<link href="http://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
</head>


<body>

<h1>Popular Tags</h1>
<a href="index.jsp">Home</a>

<% 
	TagDao tagdao = new TagDao();
	List<TagBean> poptags = tagdao.getPopularTags();
%>

<table> 

 	<tr>
 		<td>Tag</td>
 		<td>#photos tagged</td>
 	</tr>

 <% 
 	for (TagBean tag : poptags ) {

 %>

 	<tr> 
		<td><%= tag.getTag() %></td>
		<td><%= tag.getTagcount() %></td>
	</tr>
 <%
 	}
 %>
 </table>