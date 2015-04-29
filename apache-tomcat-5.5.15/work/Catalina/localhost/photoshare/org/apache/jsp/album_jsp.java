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
import org.apache.commons.fileupload.FileUploadException;
import java.util.List;

public final class album_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\t<head><title>Dynamic album page</title>\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"photoshare.css\">\n");
      out.write("\t<link href=\"http://fonts.googleapis.com/css?family=Raleway\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("\t</head>\n");
      out.write("\t<body>\n");
      out.write("\t");
 
		String albumsidstr = request.getParameter("albumid");
		int albumsid = Integer.parseInt(albumsidstr);
		NewUserDao person = new NewUserDao();
	    String usersemail = request.getUserPrincipal().getName();
	    int usersid = person.getidFromEmail(usersemail);
	
      out.write("\n");
      out.write("\t<a href=\"index.jsp\">Go back</a><br>\n");
      out.write("\t\tDynamic html page for album ");
      out.print( albumsid );
      out.write("\n");
      out.write("\n");
      out.write("\t\t<h2>Upload a new picture</h2>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t<form action=\"album.jsp?albumid=");
      out.print( albumsid );
      out.write("\" enctype=\"multipart/form-data\" method=\"post\"> <!-- will reload the album page -->\n");
      out.write("\t    Filename: <input type=\"file\" name=\"filename\"/><br>\n");
      out.write("\t    Insert a caption: <input type=\"text\" name=\"caption\"/><br>\n");
      out.write("\t    <input type=\"submit\" value=\"Upload\"/><br/>\n");
      out.write("\t</form>\n");
      out.write("\n");

    PictureDao pictureDao = new PictureDao();
    TagDao tagdao = new TagDao();

        try {
            Picture picture = imageUploadBean.upload(request);
            int ownersid = usersid;

            if (picture != null) {
                pictureDao.save(picture, albumsid, usersid);
            }
            
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

      out.write("\n");
      out.write("\n");
      out.write("<h2>Pictures of this album</h2>\n");
      out.write("<table>\n");
      out.write("    <tr>\n");
      out.write("        <td>picture</td>\n");
      out.write("        <td>caption</td>\n");
      out.write("        <td>comments section</td>\n");
      out.write("    </tr>\n");
      out.write("\n");

	AlbumDao albumdao = new AlbumDao();
    List<Integer> picIdsofAlbum = albumdao.getPicIdsofAlbum(albumsid); // creates a Integer list of picture ids
    for (Integer pixid : picIdsofAlbum) {

      out.write("\n");
      out.write("    <tr>\n");
      out.write("        <td>\n");
      out.write("            <a href=\"picture.jsp?picture_id=");
      out.print( pixid );
      out.write("\">\n");
      out.write("            <img src=\"/photoshare/img?t=1&picture_id=");
      out.print( pixid );
      out.write("\"/>\n");
      out.write("            ");
 Picture p = pictureDao.load(pixid); 
      out.write("\n");
      out.write("            </a>\n");
      out.write("        </td>\n");
      out.write("\n");
      out.write("        <td>");
      out.print( p.getCaption() );
      out.write("</td>\n");
      out.write("\n");
      out.write("        <td> <!-- putting a table of comments in a column.... -->\n");
      out.write("            <table id=\"commentsection\">\n");
      out.write("                <tr>\n");
      out.write("                    <td>owner</td>\n");
      out.write("                    <td>dateofcomment</td>\n");
      out.write("                    <td>comment</td>\n");
      out.write("                </tr>\n");
      out.write("                ");

                    NewUserDao commenter = new NewUserDao();
                    CommentDao commentdao = new CommentDao();
                    List<CommentBean> cmtbeans = commentdao.getCommentsofPicture(pixid);
                    for (CommentBean cmt : cmtbeans) {
                
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>");
      out.print( commenter.getFullNameFromId(cmt.getOwnerid()) );
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( cmt.getDateofcomment() );
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( cmt.getText() );
      out.write("</td>\n");
      out.write("                </tr>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("            </table>\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("    ");

        }
    
      out.write("\n");
      out.write("\n");
      out.write("</table>\n");
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
