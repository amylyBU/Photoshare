<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.AlbumDao" %>
<%@ page import="photoshare.AlbumBean" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.NewUserBean" %>
<%@ page import="photoshare.Picture" %>
<%@ page import="photoshare.PictureDao" %>
<%@ page import="photoshare.CommentBean" %>
<%@ page import="photoshare.CommentDao" %>
<%@ page import="photoshare.TagDao" %>
<%@ page import="photoshare.TagBean" %>
<%@ page import="java.util.List" %>

<html>
<head><title>Adding a New Tag</title></head>

<body>

<%

  String err = null;

  String taginput = request.getParameter("tags");
  TagDao tagdao = new TagDao();

  int photoid;
  String photoidstr = request.getParameter("picid3");
  photoid = Integer.parseInt(photoidstr);

  boolean success = tagdao.createTag(photoid, taginput); 

  if (!success) {
        err = "Could not create tag.";
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

