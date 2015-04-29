package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import photoshare.AlbumDao;
import photoshare.AlbumBean;
import photoshare.NewUserDao;
import photoshare.NewUserBean;
import photoshare.Picture;
import photoshare.PictureDao;
import photoshare.CommentBean;
import photoshare.CommentDao;
import photoshare.TagDao;
import photoshare.TagBean;
import java.util.List;

public final class addtagstopicture_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head><title>Adding a New Tag</title></head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");


  String err = null;

  String taginput = request.getParameter("tags");
  TagDao tagdao = new TagDao();

  int photoid;
  String photoidstr = request.getParameter("picid3");
  photoid = Integer.parseInt(photoidstr);

  boolean success = tagdao.createTag(photoid, taginput); 

  if (!success) {
        err = "Could not create tag.";
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
      out.write("\n");
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
