<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.NewUserDao" %>

<html>
<head><title>Adding New User</title></head>

<body>

<% 
  String err = null; // define the error string 
  String firstname = request.getParameter("firstname");
  String lastname = request.getParameter("lastname");
  String email = request.getParameter("email"); // request the parameter from the form
  String password1 = request.getParameter("password1");
  String password2 = request.getParameter("password2");
  String dob = request.getParameter("dob"); // check if the date object is stored correctly.

  // the following are optional parameters
  String female = request.getParameter("gender_F"); // deal with these later
  String male = request.getParameter("gender_M");
  String clocation = request.getParameter("clocation");
  String hometown = request.getParameter("hometown");
  String education = request.getParameter("education");

   if (!email.equals("")) { // check if the user provided an email

     if (!password1.equals(password2)) { // then check if the two passwords match
       err = "Both password strings must match";
     }
     else if (password1.length() < 4) {
       err = "Your password must be at least four characters long";
     } 

    else if (firstname.equals("") || lastname.equals("")) { // if the user provided a first name and last name
      err = "Please enter your full name";
    }
    else if (dob == null) { // must provide all the above fields
      err = "please provide your date of birth";
    }

    else if (dob.length() != 8) {
      err = "dob formatted incorrectly";
    }
     else {
       // We have valid inputs, try to create the user
       NewUserDao newUserDao = new NewUserDao(); // create a new user data access object with its constructor

       String gender = null;
       if (female != null) {
        gender = female;
       } else {
        gender = male;
       }
       boolean success = newUserDao.create(email, password1, firstname, lastname, dob, gender, clocation, hometown, education); // pass in your variable parameters. check newuserdao.java
       if (!success) {
         err = "Couldn't create user (that email may already be in use)";
       }
     }
   } else {
	 err = "You have to provide an email";

   }

%>

<% if (err != null) { %> <!-- when you make it down here, there are no errors -->
<font color=red><b>Error: <%= err %></b></font>
<p> <a href="newuser2.jsp">Go Back</a>
<% }
   else { %>

<h2>Success!</h2>

<p>A new user has been created with email <%= email %>.
You can now return to the <a href="login.jsp">login page</a>.

<% } %>

</body>
</html>
