<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.AlbumDao" %>
<%@ page import="photoshare.NewUserDao" %>


<html>
<head><title>Adding a New Album</title>
<link rel="stylesheet" type="text/css" href="photoshare.css">
<link href="http://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
</head>

<body>

<% 
  String err = null; // define the error string 
  String albumname = request.getParameter("albumname");
  String useremail = request.getUserPrincipal().getName();

  // get the other parameters needed for album
	if (!albumname.equals("")) { // has to have an album name
	//have checks

	// if all are successful, create the album
 	AlbumDao albumdao = new AlbumDao(); // create a new user data access object with its constructor
  NewUserDao user = new NewUserDao();

    int ownerid = user.getidFromEmail(useremail);
    boolean success = albumdao.create(ownerid, albumname);
       if (!success) {
         err = "Couldn't create album";
       }

	} else {
		err = "You have to provide an album name";
	}

%>

<% if (err != null) { %> <!-- when you make it down here, there are no errors -->
<font color=red><b>Error: <%= err %></b></font>
<p> <a href="createnewalbumpage.jsp">Go Back</a>
<% } else { %>

<h2>Success!</h2>

<p>A new album has been created called <span><%= albumname %></span>.</p>
You can now return to the <a href="index.jsp">main page</a>.

<% } %>

</body>
</html>
