package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import photoshare.AlbumDao;
import photoshare.NewUserDao;

public final class addalbumtoDB_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<head><title>Adding a New Album</title>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"photoshare.css\">\n");
      out.write("<link href=\"http://fonts.googleapis.com/css?family=Raleway\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
 
  String err = null; // define the error string 
  String albumname = request.getParameter("albumname");
  String useremail = request.getUserPrincipal().getName();

  // get the other parameters needed for album
	if (!albumname.equals("")) { // has to have an album name
	//have checks

	// if all are successful, create the album
 	AlbumDao albumdao = new AlbumDao(); // create a new user data access object with its constructor
  NewUserDao user = new NewUserDao();

    int ownerid = user.getidFromEmail(useremail);
    boolean success = albumdao.create(ownerid, albumname);
       if (!success) {
         err = "Couldn't create album";
       }

	} else {
		err = "You have to provide an album name";
	}


      out.write('\n');
      out.write('\n');
 if (err != null) { 
      out.write(" <!-- when you make it down here, there are no errors -->\n");
      out.write("<font color=red><b>Error: ");
      out.print( err );
      out.write("</b></font>\n");
      out.write("<p> <a href=\"createnewalbumpage.jsp\">Go Back</a>\n");
 } else { 
      out.write("\n");
      out.write("\n");
      out.write("<h2>Success!</h2>\n");
      out.write("\n");
      out.write("<p>A new album has been created called <span>");
      out.print( albumname );
      out.write("</span>.</p>\n");
      out.write("You can now return to the <a href=\"index.jsp\">main page</a>.\n");
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
