<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>Photoshare Login</title>
    <link rel="stylesheet" type="text/css" href="photoshare.css">
    <link href="http://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
</head>


<body>

<div id="navigation">
	<a href="browsealbums.jsp">Browse</a>|
	<a href="rankings.jsp">View Rankings</a>|
	<a href="newuser2.jsp">Sign up</a>
</div>

<h2>Please log in</h2>

<form method="POST" action="j_security_check">
    <table>
        <tr><th>Email</th><td><input type="text" name="j_username"></td></tr>
        <tr><th>Password</th><td><input type="password" name="j_password"></td></tr>
        <tr><td colspan="2" align="right"><input type="submit" value="Login"/></td></tr>
    </table>
</form>

</body>
</html>