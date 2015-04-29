package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import photoshare.TagDao;
import photoshare.TagBean;
import java.util.List;

public final class populartags_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("<head><title>Popular Tags</title>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"photoshare.css\">\n");
      out.write("<link href=\"http://fonts.googleapis.com/css?family=Raleway\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<h1>Popular Tags</h1>\n");
      out.write("<a href=\"index.jsp\">Home</a>\n");
      out.write("\n");
 
	TagDao tagdao = new TagDao();
	List<TagBean> poptags = tagdao.getPopularTags();

      out.write("\n");
      out.write("\n");
      out.write("<table> \n");
      out.write("\n");
      out.write(" \t<tr>\n");
      out.write(" \t\t<td>Tag</td>\n");
      out.write(" \t\t<td>#photos tagged</td>\n");
      out.write(" \t</tr>\n");
      out.write("\n");
      out.write(" ");
 
 	for (TagBean tag : poptags ) {

 
      out.write("\n");
      out.write("\n");
      out.write(" \t<tr> \n");
      out.write("\t\t<td>");
      out.print( tag.getTag() );
      out.write("</td>\n");
      out.write("\t\t<td>");
      out.print( tag.getTagcount() );
      out.write("</td>\n");
      out.write("\t</tr>\n");
      out.write(" ");

 	}
 
      out.write("\n");
      out.write(" </table>");
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
