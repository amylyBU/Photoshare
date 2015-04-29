package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import photoshare.NewUserDao;
import photoshare.NewUserBean;
import org.apache.commons.fileupload.FileUploadException;
import java.util.List;

public final class findfriends_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("<head><title>Find Friends</title>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"photoshare.css\">\n");
      out.write("<link href=\"http://fonts.googleapis.com/css?family=Raleway\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<h1>Showing all users</h1>\n");
      out.write("<a href=\"index.jsp\">Click here to go back</a>\n");
      out.write("\n");
 
	NewUserDao currentuser = new NewUserDao();
	String currentuseremail = request.getUserPrincipal().getName();
	int currentuserid = currentuser.getidFromEmail(currentuseremail);

	NewUserDao friend = new NewUserDao();
	List<NewUserBean> friends = friend.loadAllUsers(currentuserid);

      out.write("\n");
      out.write("\n");
      out.write("<table> \n");
      out.write("\n");
      out.write(" \t<tr>\n");
      out.write(" \t\t<td>firstname</td>\n");
      out.write(" \t\t<td>lastname</td>\n");
      out.write(" \t\t<td>email</td>\n");
      out.write(" \t\t<td>addfriend</td>\n");
      out.write(" \t</tr>\n");
      out.write("\n");
      out.write(" ");
 
 	for (NewUserBean user : friends ) {

 
      out.write("\n");
      out.write("\n");
      out.write(" \t<tr> <!-- tr stands for table row, populated with : td , which stands for table data -->\n");
      out.write("\t\t<td>");
      out.print( user.getFirstname() );
      out.write("</td> <!-- call the methods from the AlbumBean File to get stuff from the list of AlbumBeans -->\n");
      out.write("\t\t<td>");
      out.print( user.getLastname() );
      out.write("</td> <!-- for each album (an AlbumBean object), get the desired fields -->\n");
      out.write("\t\t<td>");
      out.print( user.getEmail() );
      out.write("</td>\n");
      out.write("\n");
      out.write("\t\t<td> \n");
      out.write("            <!-- this form will post yur stuff to the current page, browsealbums.jsp!!! -->\n");
      out.write("            <form action=\"findfriends.jsp\" method=\"post\">\n");
      out.write("                <input type=\"hidden\" name=\"action\" value=\"add\"/> \n");
      out.write("                <input type=\"hidden\" name=\"email\" value=\"");
      out.print( user.getEmail() );
      out.write("\"/>\n");
      out.write("                <input type=\"submit\" value=\"Add\"/> \n");
      out.write("            </form>\n");
      out.write("\n");
      out.write("        </td>\n");
      out.write("\t</tr>\n");
      out.write(" ");

 	}
 
      out.write("\n");
      out.write(" </table>\n");
      out.write("\n");
      out.write(" ");
 
    if (request.getParameter("action") != null && request.getParameter("action").equals("add")) { // you are calling the input with the name action and getting the parameter of it.
    	String friendemail = request.getParameter("email");
    	int friendid = friend.getidFromEmail(friendemail);
        friend.addFriend(currentuserid, friendid); 
    }


      out.write("\n");
      out.write("\n");
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
