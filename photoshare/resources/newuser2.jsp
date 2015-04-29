<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head><title>Add New User</title>

<link rel="stylesheet" type="text/css" href="photoshare.css">
<link href="http://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
</head>

<body>

<h2>Register with photoshare</h2>

<form action="addusertoDB.jsp" method="post">
  Email: <input type="text" name="email"/><br>
  Password: <input type="password" name="password1"/><br>
  Re-enter password: <input type="password" name="password2"/><br>
  First Name: <input type="text" name="firstname"/><br>
  Last Name: <input type="text" name="lastname"/><br>
  Date of Birth: <input type="text" name="dob" placeholder="mmddyyyy"/><br> <!-- a string of 8 characters -->
  Gender: <input type="checkbox" name="gender_F" value="F">F <input type="checkbox" name="gender_M" value="M"/>M<br> <!-- the problem is you can check off both -->

  <!-- the below are optional -->
  Current Location: <input type="text" name="clocation"/><br>
  Hometown: <input type="text" name="hometown"/><br>
  Education: <input type="text" name="education"/><br>

  <input type="submit" value="Create"/><br/>
</form>

Already a member? <a href="/photoshare/login.jsp">Click here to log in</a>

</body>
</html>
