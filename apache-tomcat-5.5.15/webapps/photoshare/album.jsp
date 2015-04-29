<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.AlbumDao" %>
<%@ page import="photoshare.AlbumBean" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.NewUserBean" %>
<%@ page import="photoshare.Picture" %>
<%@ page import="photoshare.PictureDao" %>
<%@ page import="photoshare.CommentBean" %>
<%@ page import="photoshare.CommentDao" %>
<%@ page import="photoshare.TagDao" %>
<%@ page import="photoshare.TagBean" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="java.util.List" %>

<jsp:useBean id="imageUploadBean" class="photoshare.ImageUploadBean">
<jsp:setProperty name="imageUploadBean" property="*"/>
</jsp:useBean>

<html>

	<head><title>Dynamic album page</title>
	<link rel="stylesheet" type="text/css" href="photoshare.css">
	<link href="http://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
	</head>
	<body>
	<% 
		String albumsidstr = request.getParameter("albumid");
		int albumsid = Integer.parseInt(albumsidstr);
		NewUserDao person = new NewUserDao();
	    String usersemail = request.getUserPrincipal().getName();
	    int usersid = person.getidFromEmail(usersemail);
	%>
	<a href="index.jsp">Go back</a><br>
		Dynamic html page for album <%= albumsid %>

		<h2>Upload a new picture</h2>


	<form action="album.jsp?albumid=<%= albumsid %>" enctype="multipart/form-data" method="post"> <!-- will reload the album page -->
	    Filename: <input type="file" name="filename"/><br>
	    Insert a caption: <input type="text" name="caption"/><br>
	    <input type="submit" value="Upload"/><br/>
	</form>

<%
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
%>

<h2>Pictures of this album</h2>
<table>
    <tr>
        <td>picture</td>
        <td>caption</td>
        <td>comments section</td>
    </tr>

<%
	AlbumDao albumdao = new AlbumDao();
    List<Integer> picIdsofAlbum = albumdao.getPicIdsofAlbum(albumsid); // creates a Integer list of picture ids
    for (Integer pixid : picIdsofAlbum) {
%>
    <tr>
        <td>
            <a href="picture.jsp?picture_id=<%= pixid %>">
            <img src="/photoshare/img?t=1&picture_id=<%= pixid %>"/>
            <% Picture p = pictureDao.load(pixid); %>
            </a>
        </td>

        <td><%= p.getCaption() %></td>

        <td> <!-- putting a table of comments in a column.... -->
            <table id="commentsection">
                <tr>
                    <td>owner</td>
                    <td>dateofcomment</td>
                    <td>comment</td>
                </tr>
                <%
                    NewUserDao commenter = new NewUserDao();
                    CommentDao commentdao = new CommentDao();
                    List<CommentBean> cmtbeans = commentdao.getCommentsofPicture(pixid);
                    for (CommentBean cmt : cmtbeans) {
                %>
                <tr>
                    <td><%= commenter.getFullNameFromId(cmt.getOwnerid()) %></td>
                    <td><%= cmt.getDateofcomment() %></td>
                    <td><%= cmt.getText() %></td>
                </tr>
                <%
                    }
                %>
            </table>
        </td>
    </tr>
    <%
        }
    %>

</table>
	</body>
</html>