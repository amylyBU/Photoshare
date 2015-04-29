<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="photoshare.AlbumDao" %>
<%@ page import="photoshare.AlbumBean" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.NewUserBean" %>
<%@ page import="photoshare.Picture" %>
<%@ page import="photoshare.PictureDao" %>
<%@ page import="photoshare.CommentBean" %>
<%@ page import="photoshare.CommentDao" %>
<%@ page import="photoshare.TagBean" %>
<%@ page import="photoshare.TagDao" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>

<html>
	<head><title>Dynamic picture page</title>
	<link rel="stylesheet" type="text/css" href="photoshare.css">
	<link href="http://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
	</head>
	<body>
    <% 
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

    %>
    <a href="index.jsp">Home</a><br>
    <h1>Dynamic html page for picture <%= picid %><br></h1>


    <img src="/photoshare/img?t=1&picture_id=<%= picid %>"/>
    <% 
        PictureDao pictureDao = new PictureDao();
        Picture p = pictureDao.load(picid); 
        CommentDao commentdao = new CommentDao();
    %>
    <p>Caption: "<%= p.getCaption() %>"</p>

    <p>

    <form action="addliketoDB.jsp" method="post">
        <input type="hidden" name="picid2" value="<%= picid %>"/>
        <input type="hidden" name="like" value="thisisalike!"/>
        <input type="submit" value="Like"/>
    </form>

    <a href="displaylikers.jsp?picture_id=<%= picid %>">Likes:</a> <%= commentdao.countLikes(picid) %></a>

    </p>

    <p>Tags:
    <% 
        TagDao tagdao = new TagDao();
        List<String> photostags = tagdao.getTagsOfPicid(picid);

        for (String phototag : photostags) {

    %>

        <span><%= phototag %></span>, 

    <% 
        }
    %>
    </p>


<h2>Tag this picture</h2>
<form action="addtagstopicture.jsp" method="post">
<input type="hidden" name="picid3" value="<%= picid %>"/>
Separate tags by commas or spaces: <input type="text" name="tags"/>
<input type="submit" value="Add Tags"/>
</form>


    <h2>Add a comment</h2>

    <form action="addcommenttoDB.jsp" method="post">
        <input type="hidden" name="picid" value='<%= picid %>'/>
        <input type="text" name="commenttext" placeholder="comment here"/>
        <input type="submit" value="Add Comment"/><br>
    </form>

    <table id="commentsection">
        <tr>
            <td>owner</td>
            <td>dateofcomment</td>
            <td>comment</td>
        </tr>
        <%
            NewUserDao commenter = new NewUserDao();
            List<CommentBean> cmtbeans = commentdao.getCommentsofPicture(picid);
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
	</body>
</html>