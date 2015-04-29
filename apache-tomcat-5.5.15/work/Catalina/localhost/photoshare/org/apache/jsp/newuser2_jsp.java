package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class newuser2_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<head><title>Add New User</title>\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"photoshare.css\">\n");
      out.write("<link href=\"http://fonts.googleapis.com/css?family=Raleway\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<h2>Register with photoshare</h2>\n");
      out.write("\n");
      out.write("<form action=\"addusertoDB.jsp\" method=\"post\">\n");
      out.write("  Email: <input type=\"text\" name=\"email\"/><br>\n");
      out.write("  Password: <input type=\"password\" name=\"password1\"/><br>\n");
      out.write("  Re-enter password: <input type=\"password\" name=\"password2\"/><br>\n");
      out.write("  First Name: <input type=\"text\" name=\"firstname\"/><br>\n");
      out.write("  Last Name: <input type=\"text\" name=\"lastname\"/><br>\n");
      out.write("  Date of Birth: <input type=\"text\" name=\"dob\" placeholder=\"mmddyyyy\"/><br> <!-- a string of 8 characters -->\n");
      out.write("  Gender: <input type=\"checkbox\" name=\"gender_F\" value=\"F\">F <input type=\"checkbox\" name=\"gender_M\" value=\"M\"/>M<br> <!-- the problem is you can check off both -->\n");
      out.write("\n");
      out.write("  <!-- the below are optional -->\n");
      out.write("  Current Location: <input type=\"text\" name=\"clocation\"/><br>\n");
      out.write("  Hometown: <input type=\"text\" name=\"hometown\"/><br>\n");
      out.write("  Education: <input type=\"text\" name=\"education\"/><br>\n");
      out.write("\n");
      out.write("  <input type=\"submit\" value=\"Create\"/><br/>\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("Already a member? <a href=\"/photoshare/login.jsp\">Click here to log in</a>\n");
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
