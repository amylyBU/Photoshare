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

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      photoshare.ImageUploadBean imageUploadBean = null;
      synchronized (_jspx_page_context) {
        imageUploadBean = (photoshare.ImageUploadBean) _jspx_page_context.getAttribute("imageUploadBean", PageContext.PAGE_SCOPE);
        if (imageUploadBean == null){
          imageUploadBean = new photoshare.ImageUploadBean();
          _jspx_page_context.setAttribute("imageUploadBean", imageUploadBean, PageContext.PAGE_SCOPE);
          out.write('\n');
          org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("imageUploadBean"), request);
          out.write('\n');
        }
      }
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head><title>Photoshare</title>\n");
      out.write("\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"photoshare.css\">\n");
      out.write("    <link href=\"http://fonts.googleapis.com/css?family=Raleway\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<h1>Photoshare</h1>\n");
      out.write("<a href=\"browsealbums.jsp\">Browse</a>| \n");
      out.write("<a href=\"rankings.jsp\">View Rankings</a>|\n");
      out.write("<a href=\"populartags.jsp\">Popular Tags</a><br><br>\n");
 
    NewUserDao person = new NewUserDao();
    String usersemail = request.getUserPrincipal().getName();
    int usersid = person.getidFromEmail(usersemail);

      out.write("\n");
      out.write("\n");
      out.write("Hello <b><code>");
      out.print( usersemail );
      out.write("</code></b>, click here to <a href=\"/photoshare/logout.jsp\">log out</a>\n");
      out.write("<br>\n");
      out.write("Your user id is ");
      out.print( usersid );
      out.write(".<br>\n");
      out.write("\n");
      out.write("<h2>Your Pictures</h2>\n");
      out.write("<table>\n");
      out.write("    <tr>\n");
      out.write("        <td>picture</td>\n");
      out.write("        <td>caption</td>\n");
      out.write("        <td>Delete</td>\n");
      out.write("    </tr>\n");
      out.write("    ");

        PictureDao pictureDao = new PictureDao();
        List<Integer> usersPicIds = pictureDao.yourPictureIds(usersid); // creates a Integer list of your pictures ids
        for (Integer usersPicId : usersPicIds) {
    
      out.write("\n");
      out.write("    <tr>\n");
      out.write("        <td><a href=\"picture.jsp?picture_id=");
      out.print( usersPicId );
      out.write("\">\n");
      out.write("            <img src=\"/photoshare/img?t=1&picture_id=");
      out.print( usersPicId );
      out.write("\"/>\n");
      out.write("            ");
 Picture q = pictureDao.load(usersPicId); 
      out.write("\n");
      out.write("        </a></td>\n");
      out.write("        <td>");
      out.print( q.getCaption() );
      out.write("</td>\n");
      out.write("        ");
 
            if (request.getParameter("action") != null && request.getParameter("action").equals("deleteyourpicture")) {
                int picsId = Integer.parseInt(request.getParameter("picturesID"));
                pictureDao.delete(picsId); // have to write a delete function in pictureDao....
            }
        
      out.write("\n");
      out.write("        <td>\n");
      out.write("             <form action=\"index.jsp\" method=\"post\">\n");
      out.write("                <input type=\"hidden\" name=\"action\" value=\"deleteyourpicture\"/> \n");
      out.write("                <input type=\"hidden\" name=\"picturesID\" value=\"");
      out.print( q.getId() );
      out.write("\"/>\n");
      out.write("                <input type=\"submit\" value=\"Delete\"/> <!-- the button you see that says delete -->\n");
      out.write("            </form>\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("    ");

        }
    
      out.write("\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("\n");
      out.write("<h2>Your Photo albums</h2>\n");
      out.write("<a href=\"createnewalbumpage.jsp\">Click here to create a new album</a><br><br>\n");
      out.write("\n");
      out.write("    ");
 
        AlbumDao albumDao = new AlbumDao(); // create a new user data access object with its constructor
        List<AlbumBean> albumIds = albumDao.loadUsersAlbums(usersid); // creates int list of album ids
     
      out.write("\n");
      out.write("\n");
      out.write("<table>\n");
      out.write("    <tr>\n");
      out.write("        <td>albumid</td>\n");
      out.write("        <td>ownerid</td>\n");
      out.write("        <td>name</td>\n");
      out.write("        <td>dateofcreation</td>\n");
      out.write("        <td>delete</td>\n");
      out.write("    </tr>\n");
      out.write("\n");
      out.write("    ");
 
        for (AlbumBean album : albumIds) { // this is a for loop to process all album in the List you defined above called albums
    
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    <tr> <!-- tr stands for table row, populated with : td , which stands for table data -->\n");
      out.write("        <td>");
      out.print( album.getAlbumid() );
      out.write("</td> <!-- call the methods from the AlbumBean File to get stuff from the list of AlbumBeans -->\n");
      out.write("        <td>");
      out.print( album.getOwnerid() );
      out.write("</td> <!-- for each album (an AlbumBean object), get the desired fields -->\n");
      out.write("        <td><a href=\"album.jsp?albumid=");
      out.print( album.getAlbumid() );
      out.write('"');
      out.write('>');
      out.print( album.getName() );
      out.write("</a></td>\n");
      out.write("        <td>");
      out.print( album.getDateofcreation() );
      out.write("</td>\n");
      out.write("        \n");
      out.write("        <!-- the below functionality is to delete the said album --> \n");
      out.write("        <td> \n");
      out.write("            <!-- this form will post yur stuff to the current page, browsealbums.jsp!!! -->\n");
      out.write("            <form action=\"index.jsp\" method=\"post\">\n");
      out.write("                <input type=\"hidden\" name=\"action\" value=\"deletealbum\"/> \n");
      out.write("                <input type=\"hidden\" name=\"albumid\" value=\"");
      out.print( album.getAlbumid() );
      out.write("\"/>\n");
      out.write("                <input type=\"submit\" value=\"Delete\"/> <!-- the button you see that says delete -->\n");
      out.write("            </form>\n");
      out.write("\n");
      out.write("        </td>\n");
      out.write("\n");
      out.write("    </tr>\n");
      out.write("    ");

        }
    
      out.write("\n");
      out.write("</table>\n");
      out.write("\n");
 
    if (request.getParameter("action") != null && request.getParameter("action").equals("deletealbum")) { // you are calling the input with the name action and getting the parameter of it.
        Integer albumid = Integer.parseInt(request.getParameter("albumid"));
        albumDao.deleteAlbum(albumid); 
    }

      out.write("\n");
      out.write("\n");
      out.write("<h2>Your Friends</h2>\n");
      out.write("<a href=\"findfriends.jsp\">Find friends here</a><br><br>\n");
      out.write("\n");
      out.write("<!-- display users friends here --> \n");
      out.write("    ");
 
        List<NewUserBean> yourfriends = person.loadUsersFriends(usersid); // creates int list of album ids
    
      out.write("\n");
      out.write("\n");
      out.write("<table>\n");
      out.write("    <tr>\n");
      out.write("        <td>firstname</td>\n");
      out.write("        <td>lastname</td>\n");
      out.write("        <td>email</td>\n");
      out.write("        <td>delete</td>\n");
      out.write("    </tr>\n");
      out.write("\n");
      out.write("    ");
 
        for (NewUserBean yourfriend : yourfriends) { // this is a for loop to process all album in the List you defined above called albums
    
      out.write("\n");
      out.write("\n");
      out.write("    <tr> <!-- tr stands for table row, populated with : td , which stands for table data -->\n");
      out.write("        <td>");
      out.print( yourfriend.getFirstname() );
      out.write("</td> <!-- call the methods from the AlbumBean File to get stuff from the list of AlbumBeans -->\n");
      out.write("        <td>");
      out.print( yourfriend.getLastname() );
      out.write("</td> <!-- for each album (an AlbumBean object), get the desired fields -->\n");
      out.write("        <td>");
      out.print( yourfriend.getEmail() );
      out.write("</td>\n");
      out.write("        \n");
      out.write("        <!-- the below functionality is to delete the said album --> \n");
      out.write("        <td> \n");
      out.write("            <!-- this form will post yur stuff to the current page, browsealbums.jsp!!! -->\n");
      out.write("            <form action=\"index.jsp\" method=\"post\">\n");
      out.write("                <input type=\"hidden\" name=\"action\" value=\"deletefriend\"/> \n");
      out.write("                <input type=\"hidden\" name=\"email\" value=\"");
      out.print( yourfriend.getEmail() );
      out.write("\"/>\n");
      out.write("                <input type=\"submit\" value=\"Delete\"/> <!-- the button you see that says delete -->\n");
      out.write("            </form>\n");
      out.write("\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("    ");

        }
    
      out.write("\n");
      out.write("</table>\n");
      out.write("\n");
 
    if (request.getParameter("action") != null && request.getParameter("action").equals("deletefriend")) { // you are calling the input with the name action and getting the parameter of it.
        NewUserDao f = new NewUserDao();
        String friendsEmail = request.getParameter("email");
        int frienduserid = f.getidFromEmail(friendsEmail);
        person.deleteFriend(usersid, frienduserid); 
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
