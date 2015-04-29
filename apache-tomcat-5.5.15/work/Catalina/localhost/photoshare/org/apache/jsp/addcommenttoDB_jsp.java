package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import photoshare.CommentBean;
import photoshare.NewUserDao;
import photoshare.CommentDao;
import java.sql.Date;

public final class addcommenttoDB_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head><title>Adding a New Comment</title></head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");


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


      out.write('\n');
      out.write('\n');
 if (err != null) { 
      out.write(" <!-- when you make it down here, there are no errors -->\n");
      out.write("<font color=red><b>Error: ");
      out.print( err );
      out.write("</b></font>\n");
      out.write("<p> <a href=\"browsealbums.jsp\">Go Back to browse albums</a>\n");
 } else { 
      out.write("\n");
      out.write("\n");
      out.write("<h2>Success!</h2>\n");
      out.write("\n");
      out.write("You can now return to <a href=\"picture.jsp?picture_id=");
      out.print( photoid );
      out.write("\">the picture</a>.\n");
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
