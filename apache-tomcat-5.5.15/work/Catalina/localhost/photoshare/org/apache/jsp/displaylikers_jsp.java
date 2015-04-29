package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import photoshare.CommentBean;
import photoshare.NewUserDao;
import photoshare.CommentDao;
import photoshare.NewUserBean;
import java.sql.Date;
import java.util.List;

public final class displaylikers_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("\t<head><title>Photo Likers</title>\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"photoshare.css\">\n");
      out.write("\t<link href=\"http://fonts.googleapis.com/css?family=Raleway\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("\t</head>\n");
      out.write("\t<body>\n");
      out.write("    ");
 
        String picidstr = request.getParameter("picture_id");
        int picid = Integer.parseInt(picidstr);
        NewUserDao person = new NewUserDao();

        String usersemail;
        int usersid;
        if (request.getUserPrincipal() == null) {
            usersemail = "";
            usersid = 2;
        } else {
            usersemail = request.getUserPrincipal().getName();
            usersid = person.getidFromEmail(usersemail);
        }

    
      out.write("\n");
      out.write("    <h2>People who liked photo ");
      out.print( picid );
      out.write("<br></h2>\n");
      out.write("    Return to <a href=\"picture.jsp?picture_id=");
      out.print( picid );
      out.write("\">the picture</a>.\n");
      out.write("\n");
      out.write("    ");
 
    	List<Integer> likers = person.getLikersIds(picid);
    	for (Integer likerid : likers) {
    
      out.write("\n");
      out.write("    <table>\n");
      out.write("    \t<tr>\n");
      out.write("\t    \t<td>userid</td>\n");
      out.write("\t    \t<td>fullname</td>\n");
      out.write("\t    </tr>\n");
      out.write("\t    <tr>\n");
      out.write("\t    \t<td>");
      out.print( likerid );
      out.write("</td>\n");
      out.write("\t    \t<td>");
      out.print( person.getFullNameFromId(likerid) );
      out.write("</td>\n");
      out.write("\t    </tr>\n");
      out.write("\t</table>\n");
      out.write("\t");

		}
	
      out.write("\n");
      out.write("\t</body>\n");
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
