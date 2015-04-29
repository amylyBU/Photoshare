<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.NewUserBean" %>

<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>Find Friends</title>
<link rel="stylesheet" type="text/css" href="photoshare.css">
<link href="http://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
</head>


<body>

<h1>Showing all users</h1>
<a href="index.jsp">Click here to go back</a>

<% 
	NewUserDao currentuser = new NewUserDao();
	String currentuseremail = request.getUserPrincipal().getName();
	int currentuserid = currentuser.getidFromEmail(currentuseremail);

	NewUserDao friend = new NewUserDao();
	List<NewUserBean> friends = friend.loadAllUsers(currentuserid);
%>

<table> 

 	<tr>
 		<td>firstname</td>
 		<td>lastname</td>
 		<td>email</td>
 		<td>addfriend</td>
 	</tr>

 <% 
 	for (NewUserBean user : friends ) {

 %>

 	<tr> <!-- tr stands for table row, populated with : td , which stands for table data -->
		<td><%= user.getFirstname() %></td> <!-- call the methods from the AlbumBean File to get stuff from the list of AlbumBeans -->
		<td><%= user.getLastname() %></td> <!-- for each album (an AlbumBean object), get the desired fields -->
		<td><%= user.getEmail() %></td>

		<td> 
            <!-- this form will post yur stuff to the current page, browsealbums.jsp!!! -->
            <form action="findfriends.jsp" method="post">
                <input type="hidden" name="action" value="add"/> 
                <input type="hidden" name="email" value="<%= user.getEmail() %>"/>
                <input type="submit" value="Add"/> 
            </form>

        </td>
	</tr>
 <%
 	}
 %>
 </table>

 <% 
    if (request.getParameter("action") != null && request.getParameter("action").equals("add")) { // you are calling the input with the name action and getting the parameter of it.
    	String friendemail = request.getParameter("email");
    	int friendid = friend.getidFromEmail(friendemail);
        friend.addFriend(currentuserid, friendid); 
    }

%>


</body>
</html>