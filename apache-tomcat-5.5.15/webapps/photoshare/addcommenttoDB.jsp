<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.CommentBean" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.CommentDao" %>
<%@ page import="java.sql.Date" %>

<html>
<head><title>Adding a New Comment</title></head>

<body>

<%

  NewUserDao commenter = new NewUserDao();
  String err = null;

  String owneremail;
  if (request.getUserPrincipal() == null) {
    owneremail = "";
  } else {
    owneremail = request.getUserPrincipal().getName();
  }

  int ownerid;
  if (owneremail.equals("")) { // no one is logged in,
    ownerid = 2; // for anonymous users
  } else {
    ownerid = commenter.getidFromEmail(owneremail);
  }
  
  CommentDao comment = new CommentDao();
  int photoid;
  String photoidstring = request.getParameter("picid"); 
  photoid = Integer.parseInt(photoidstring);
  String text = request.getParameter("commenttext");
  // auto generate a commentid

  if (!text.equals("")) {  // obviously there has to be a comment to add
      boolean success = comment.create(ownerid, photoid, text);
      if (!success) {
        err = "Couldn't add comment";
      }
  } else {
    err = "Must provide a comment...";
  }

%>

<% if (err != null) { %> <!-- when you make it down here, there are no errors -->
<font color=red><b>Error: <%= err %></b></font>
<p> <a href="browsealbums.jsp">Go Back to browse albums</a>
<% } else { %>

<h2>Success!</h2>

You can now return to <a href="picture.jsp?picture_id=<%= photoid %>">the picture</a>.

<% } %>

</body>
</html>
