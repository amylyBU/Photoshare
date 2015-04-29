package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import photoshare.AlbumBean;
import photoshare.AlbumDao;
import photoshare.Picture;
import photoshare.PictureDao;
import photoshare.CommentDao;
import photoshare.CommentBean;
import photoshare.NewUserDao;
import photoshare.NewUserBean;
import org.apache.commons.fileupload.FileUploadException;
import java.util.List;

public final class browsealbums_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write(" <!-- do I even need this import -->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>Browse Albums</title>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"photoshare.css\">\n");
      out.write("<link href=\"http://fonts.googleapis.com/css?family=Raleway\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<div id=\"navigation\">\n");
      out.write("<a href=\"browsealbums.jsp\">Browse</a>|\n");
      out.write("<a href=\"index.jsp\">Home</a>|\n");
      out.write("<a href=\"rankings.jsp\">View Rankings</a>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<h2>Browse all public photoshare albums and photos</h2>\n");
      out.write("<h3>Albums</h3>\n");
      out.write("\n");
 
	AlbumDao dao = new AlbumDao(); // create an AlbumDao because you want to access all the albums from the database
	List<AlbumBean> albums = dao.loadAllAlbums(); // call the loadAllAlbums() method in the AlbumDao.java file

      out.write("\n");
      out.write("\n");
      out.write("<table> \n");
      out.write("\n");
      out.write(" \t<tr>\n");
      out.write(" \t\t<td>albumid</td>\n");
      out.write(" \t\t<td>ownerid</td>\n");
      out.write(" \t\t<td>name</td>\n");
      out.write(" \t\t<td>dateofcreation</td>\n");
      out.write(" \t</tr>\n");
      out.write("\n");
 
	for (AlbumBean album : albums) { // this is a for loop to process all album in the List you defined above called albums

      out.write("\n");
      out.write("\n");
      out.write("<!-- some HTML to organize your albums from the list -->\n");
      out.write(" \t<tr> <!-- tr stands for table row, populated with : td , which stands for table data -->\n");
      out.write("\t\t<td>");
      out.print( album.getAlbumid() );
      out.write("</td> <!-- call the methods from the AlbumBean File to get stuff from the list of AlbumBeans -->\n");
      out.write("\t\t<td>");
      out.print( album.getOwnerid() );
      out.write("</td> <!-- for each album (an AlbumBean object), get the desired fields -->\n");
      out.write("\t\t<td><a href=\"albumpublic.jsp?albumid=");
      out.print( album.getAlbumid() );
      out.write('"');
      out.write('>');
      out.print( album.getName() );
      out.write("</a></td>\n");
      out.write("\t\t<td>");
      out.print( album.getDateofcreation() );
      out.write("</td>\n");
      out.write("\t</tr>\n");
      out.write("\n");
 
	} // end of the for loop of AlbumBean objects called album

      out.write("\n");
      out.write("\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("<h3>All Existing Pictures</h3>\n");
      out.write("<table>\n");
      out.write("    <center><tr>\n");
      out.write("        <td>picture</td>\n");
      out.write("        <td>Caption</tr>\n");
      out.write("    </center>\n");

	PictureDao pictureDao = new PictureDao();
    List<Integer> pictureIds = pictureDao.allPicturesIds(); // creates a Integer list of picture ids
    for (Integer pictureId : pictureIds) {

      out.write("\n");
      out.write("    <tr>\n");
      out.write("        <td>\n");
      out.write("            <a href=\"picture.jsp?picture_id=");
      out.print( pictureId );
      out.write("\">\n");
      out.write("            <img src=\"/photoshare/img?t=1&picture_id=");
      out.print( pictureId );
      out.write("\"/>\n");
      out.write("            ");
 Picture p = pictureDao.load(pictureId); 
      out.write("\n");
      out.write("            </a>\n");
      out.write("        </td>\n");
      out.write("        <td>");
      out.print( p.getCaption() );
      out.write("</td>\n");
      out.write("    ");

        }
    
      out.write("\n");
      out.write("\n");
      out.write("</table>\n");
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
