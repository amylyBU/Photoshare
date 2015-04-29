package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("<head><title>Photoshare Login</title>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"photoshare.css\">\n");
      out.write("    <link href=\"http://fonts.googleapis.com/css?family=Raleway\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div id=\"navigation\">\n");
      out.write("\t<a href=\"browsealbums.jsp\">Browse</a>|\n");
      out.write("\t<a href=\"rankings.jsp\">View Rankings</a>|\n");
      out.write("\t<a href=\"newuser2.jsp\">Sign up</a>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<h2>Please log in</h2>\n");
      out.write("\n");
      out.write("<form method=\"POST\" action=\"j_security_check\">\n");
      out.write("    <table>\n");
      out.write("        <tr><th>Email</th><td><input type=\"text\" name=\"j_username\"></td></tr>\n");
      out.write("        <tr><th>Password</th><td><input type=\"password\" name=\"j_password\"></td></tr>\n");
      out.write("        <tr><td colspan=\"2\" align=\"right\"><input type=\"submit\" value=\"Login\"/></td></tr>\n");
      out.write("    </table>\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
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
