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
import photoshare.TagBean;
import photoshare.TagDao;
import org.apache.commons.fileupload.FileUploadException;
import java.util.List;
import java.sql.Date;

public final class picture_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<html>\n");
      out.write("\t<head><title>Dynamic picture page</title>\n");
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
      out.write("    <a href=\"index.jsp\">Home</a><br>\n");
      out.write("    <h1>Dynamic html page for picture ");
      out.print( picid );
      out.write("<br></h1>\n");
      out.write("\n");
      out.write("\n");
      out.write("    <img src=\"/photoshare/img?t=1&picture_id=");
      out.print( picid );
      out.write("\"/>\n");
      out.write("    ");
 
        PictureDao pictureDao = new PictureDao();
        Picture p = pictureDao.load(picid); 
        CommentDao commentdao = new CommentDao();
    
      out.write("\n");
      out.write("    <p>Caption: \"");
      out.print( p.getCaption() );
      out.write("\"</p>\n");
      out.write("\n");
      out.write("    <p>\n");
      out.write("\n");
      out.write("    <form action=\"addliketoDB.jsp\" method=\"post\">\n");
      out.write("        <input type=\"hidden\" name=\"picid2\" value=\"");
      out.print( picid );
      out.write("\"/>\n");
      out.write("        <input type=\"hidden\" name=\"like\" value=\"thisisalike!\"/>\n");
      out.write("        <input type=\"submit\" value=\"Like\"/>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <a href=\"displaylikers.jsp?picture_id=");
      out.print( picid );
      out.write("\">Likes:</a> ");
      out.print( commentdao.countLikes(picid) );
      out.write("</a>\n");
      out.write("\n");
      out.write("    </p>\n");
      out.write("\n");
      out.write("    <p>Tags:\n");
      out.write("    ");
 
        TagDao tagdao = new TagDao();
        List<String> photostags = tagdao.getTagsOfPicid(picid);

        for (String phototag : photostags) {

    
      out.write("\n");
      out.write("\n");
      out.write("        <span>");
      out.print( phototag );
      out.write("</span>, \n");
      out.write("\n");
      out.write("    ");
 
        }
    
      out.write("\n");
      out.write("    </p>\n");
      out.write("\n");
      out.write("\n");
      out.write("<h2>Tag this picture</h2>\n");
      out.write("<form action=\"addtagstopicture.jsp\" method=\"post\">\n");
      out.write("<input type=\"hidden\" name=\"picid3\" value=\"");
      out.print( picid );
      out.write("\"/>\n");
      out.write("Separate tags by commas or spaces: <input type=\"text\" name=\"tags\"/>\n");
      out.write("<input type=\"submit\" value=\"Add Tags\"/>\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("\n");
      out.write("    <h2>Add a comment</h2>\n");
      out.write("\n");
      out.write("    <form action=\"addcommenttoDB.jsp\" method=\"post\">\n");
      out.write("        <input type=\"hidden\" name=\"picid\" value='");
      out.print( picid );
      out.write("'/>\n");
      out.write("        <input type=\"text\" name=\"commenttext\" placeholder=\"comment here\"/>\n");
      out.write("        <input type=\"submit\" value=\"Add Comment\"/><br>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <table id=\"commentsection\">\n");
      out.write("        <tr>\n");
      out.write("            <td>owner</td>\n");
      out.write("            <td>dateofcomment</td>\n");
      out.write("            <td>comment</td>\n");
      out.write("        </tr>\n");
      out.write("        ");

            NewUserDao commenter = new NewUserDao();
            List<CommentBean> cmtbeans = commentdao.getCommentsofPicture(picid);
            for (CommentBean cmt : cmtbeans) {
        
      out.write("\n");
      out.write("        <tr>\n");
      out.write("            <td>");
      out.print( commenter.getFullNameFromId(cmt.getOwnerid()) );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( cmt.getDateofcomment() );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( cmt.getText() );
      out.write("</td>\n");
      out.write("        </tr>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("    </table>\n");
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
