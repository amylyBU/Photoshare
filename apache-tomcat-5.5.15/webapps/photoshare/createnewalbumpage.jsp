<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>Create a new Album</title>

<link rel="stylesheet" type="text/css" href="photoshare.css">
<link href="http://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
</head>

<!-- the user interface page to create an album -->
<body>

<div id="navigation">
<a href="index.jsp">Go back</a><br>
</div>

<h2>Create a new album</h2>

<form action="addalbumtoDB.jsp" method="post">
  Name of album: <input type="text" name="albumname"/><br>
  <input type="submit" value="Create"/><br/>
</form>


</body>
</html>
