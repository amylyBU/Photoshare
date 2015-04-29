<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.CommentBean" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.CommentDao" %>
<%@ page import="photoshare.NewUserBean" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.List" %>

<html>
	<head><title>Photo Likers</title>
	<link rel="stylesheet" type="text/css" href="photoshare.css">
	<link href="http://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
	</head>
	<body>
    <% 
        String picidstr = request.getParameter("picture_id");
        int picid = Integer.parseInt(picidstr);
        NewUserDao person = new NewUserDao();

        String usersemail;
        int usersid;
        if (request.getUserPrincipal() == null) {
            usersemail = "";
            usersid = 2;
        } else {
            usersemail = request.getUserPrincipal().getName();
            usersid = person.getidFromEmail(usersemail);
        }

    %>
    <h2>People who liked photo <%= picid %><br></h2>
    Return to <a href="picture.jsp?picture_id=<%= picid %>">the picture</a>.

    <% 
    	List<Integer> likers = person.getLikersIds(picid);
    	for (Integer likerid : likers) {
    %>
    <table>
    	<tr>
	    	<td>userid</td>
	    	<td>fullname</td>
	    </tr>
	    <tr>
	    	<td><%= likerid %></td>
	    	<td><%= person.getFullNameFromId(likerid) %></td>
	    </tr>
	</table>
	<%
		}
	%>
	</body>
</html>