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
import org.apache.commons.fileupload.FileUploadException;
import java.util.List;

public final class rankings_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("<head><title>Top Contributors</title>\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"photoshare.css\">\n");
      out.write("\t<link href=\"http://fonts.googleapis.com/css?family=Raleway\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("\t<style>\n");
      out.write("\t\tp {\n");
      out.write("\t\t\tcolor: gray;\n");
      out.write("\t\t\tfont-size: 10px;\n");
      out.write("\t\t\tfont-style: italic;\n");
      out.write("\t\t}\n");
      out.write("\t</style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<a href=\"index.jsp\">Home</a><br>\n");
      out.write("\t<h1>Showing TOP Contributors!</h1>\n");
      out.write("\t<p>*contribution points are calculated by the number photos they own and the number of comments they have left</p>\n");
      out.write("\n");
 
	NewUserDao user = new NewUserDao();
	List<NewUserBean> contribs = user.getContributors();

      out.write("\n");
      out.write("\n");
      out.write("<table> \n");
      out.write("\n");
      out.write(" \t<tr>\n");
      out.write(" \t\t<td>Userid</td>\n");
      out.write(" \t\t<td>Usersname</td>\n");
      out.write(" \t\t<td>Contribution Points</td>\n");
      out.write(" \t</tr>\n");
      out.write("\n");
      out.write(" ");
 
 	for (NewUserBean c : contribs ) {

 
      out.write("\n");
      out.write("\n");
      out.write(" \t<tr> <!-- tr stands for table row, populated with : td , which stands for table data -->\n");
      out.write("\t\t<td>");
      out.print( c.getUserid() );
      out.write("</td> <!-- call the methods from the AlbumBean File to get stuff from the list of AlbumBeans -->\n");
      out.write("\t\t<td>");
      out.print( user.getFullNameFromId( c.getUserid() ) );
      out.write("</td> <!-- for each album (an AlbumBean object), get the desired fields -->\n");
      out.write("\t\t<td>");
      out.print( c.getContribution() );
      out.write("</td>\n");
      out.write("\t</tr>\n");
      out.write(" ");

 	}
 
      out.write("\n");
      out.write(" </table>\n");
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
