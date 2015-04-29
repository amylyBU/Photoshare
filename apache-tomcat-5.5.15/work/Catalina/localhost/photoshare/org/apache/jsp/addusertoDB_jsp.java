package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import photoshare.NewUserDao;

public final class addusertoDB_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head><title>Adding New User</title></head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
 
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


      out.write('\n');
      out.write('\n');
 if (err != null) { 
      out.write(" <!-- when you make it down here, there are no errors -->\n");
      out.write("<font color=red><b>Error: ");
      out.print( err );
      out.write("</b></font>\n");
      out.write("<p> <a href=\"newuser2.jsp\">Go Back</a>\n");
 }
   else { 
      out.write("\n");
      out.write("\n");
      out.write("<h2>Success!</h2>\n");
      out.write("\n");
      out.write("<p>A new user has been created with email ");
      out.print( email );
      out.write(".\n");
      out.write("You can now return to the <a href=\"login.jsp\">login page</a>.\n");
      out.write("\n");
 } 
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
