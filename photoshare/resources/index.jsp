<%--
  Author: Amy Ly (amyly@bu.edu)
--%>
<%@ page import="photoshare.AlbumDao" %>
<%@ page import="photoshare.AlbumBean" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.NewUserBean" %>
<%@ page import="photoshare.Picture" %>
<%@ page import="photoshare.PictureDao" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="imageUploadBean" class="photoshare.ImageUploadBean">
<jsp:setProperty name="imageUploadBean" property="*"/>
</jsp:useBean>

<html>
    <head><title>Photoshare</title>

    <link rel="stylesheet" type="text/css" href="photoshare.css">
    <link href="http://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">

    </head>

<body>
<h1>Photoshare</h1>
<a href="browsealbums.jsp">Browse</a>| 
<a href="rankings.jsp">View Rankings</a>|
<a href="populartags.jsp">Popular Tags</a><br><br>
<% 
    NewUserDao person = new NewUserDao();
    String usersemail = request.getUserPrincipal().getName();
    int usersid = person.getidFromEmail(usersemail);
%>

Hello <b><code><%= usersemail %></code></b>, click here to <a href="/photoshare/logout.jsp">log out</a>
<br>
Your user id is <%= usersid %>.<br>

<h2>Your Pictures</h2>
<table>
    <tr>
        <td>picture</td>
        <td>caption</td>
        <td>Delete</td>
    </tr>
    <%
        PictureDao pictureDao = new PictureDao();
        List<Integer> usersPicIds = pictureDao.yourPictureIds(usersid); // creates a Integer list of your pictures ids
        for (Integer usersPicId : usersPicIds) {
    %>
    <tr>
        <td><a href="picture.jsp?picture_id=<%= usersPicId %>">
            <img src="/photoshare/img?t=1&picture_id=<%= usersPicId %>"/>
            <% Picture q = pictureDao.load(usersPicId); %>
        </a></td>
        <td><%= q.getCaption() %></td>
        <% 
            if (request.getParameter("action") != null && request.getParameter("action").equals("deleteyourpicture")) {
                int picsId = Integer.parseInt(request.getParameter("picturesID"));
                pictureDao.delete(picsId); // have to write a delete function in pictureDao....
            }
        %>
        <td>
             <form action="index.jsp" method="post">
                <input type="hidden" name="action" value="deleteyourpicture"/> 
                <input type="hidden" name="picturesID" value="<%= q.getId() %>"/>
                <input type="submit" value="Delete"/> <!-- the button you see that says delete -->
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>


<h2>Your Photo albums</h2>
<a href="createnewalbumpage.jsp">Click here to create a new album</a><br><br>

    <% 
        AlbumDao albumDao = new AlbumDao(); // create a new user data access object with its constructor
        List<AlbumBean> albumIds = albumDao.loadUsersAlbums(usersid); // creates int list of album ids
     %>

<table>
    <tr>
        <td>albumid</td>
        <td>ownerid</td>
        <td>name</td>
        <td>dateofcreation</td>
        <td>delete</td>
    </tr>

    <% 
        for (AlbumBean album : albumIds) { // this is a for loop to process all album in the List you defined above called albums
    %>


    <tr> <!-- tr stands for table row, populated with : td , which stands for table data -->
        <td><%= album.getAlbumid() %></td> <!-- call the methods from the AlbumBean File to get stuff from the list of AlbumBeans -->
        <td><%= album.getOwnerid() %></td> <!-- for each album (an AlbumBean object), get the desired fields -->
        <td><a href="album.jsp?albumid=<%= album.getAlbumid() %>"><%= album.getName() %></a></td>
        <td><%= album.getDateofcreation() %></td>
        
        <!-- the below functionality is to delete the said album --> 
        <td> 
            <!-- this form will post yur stuff to the current page, browsealbums.jsp!!! -->
            <form action="index.jsp" method="post">
                <input type="hidden" name="action" value="deletealbum"/> 
                <input type="hidden" name="albumid" value="<%= album.getAlbumid() %>"/>
                <input type="submit" value="Delete"/> <!-- the button you see that says delete -->
            </form>

        </td>

    </tr>
    <%
        }
    %>
</table>

<% 
    if (request.getParameter("action") != null && request.getParameter("action").equals("deletealbum")) { // you are calling the input with the name action and getting the parameter of it.
        Integer albumid = Integer.parseInt(request.getParameter("albumid"));
        albumDao.deleteAlbum(albumid); 
    }
%>

<h2>Your Friends</h2>
<a href="findfriends.jsp">Find friends here</a><br><br>

<!-- display users friends here --> 
    <% 
        List<NewUserBean> yourfriends = person.loadUsersFriends(usersid); // creates int list of album ids
    %>

<table>
    <tr>
        <td>firstname</td>
        <td>lastname</td>
        <td>email</td>
        <td>delete</td>
    </tr>

    <% 
        for (NewUserBean yourfriend : yourfriends) { // this is a for loop to process all album in the List you defined above called albums
    %>

    <tr> <!-- tr stands for table row, populated with : td , which stands for table data -->
        <td><%= yourfriend.getFirstname() %></td> <!-- call the methods from the AlbumBean File to get stuff from the list of AlbumBeans -->
        <td><%= yourfriend.getLastname() %></td> <!-- for each album (an AlbumBean object), get the desired fields -->
        <td><%= yourfriend.getEmail() %></td>
        
        <!-- the below functionality is to delete the said album --> 
        <td> 
            <!-- this form will post yur stuff to the current page, browsealbums.jsp!!! -->
            <form action="index.jsp" method="post">
                <input type="hidden" name="action" value="deletefriend"/> 
                <input type="hidden" name="email" value="<%= yourfriend.getEmail() %>"/>
                <input type="submit" value="Delete"/> <!-- the button you see that says delete -->
            </form>

        </td>
    </tr>
    <%
        }
    %>
</table>

<% 
    if (request.getParameter("action") != null && request.getParameter("action").equals("deletefriend")) { // you are calling the input with the name action and getting the parameter of it.
        NewUserDao f = new NewUserDao();
        String friendsEmail = request.getParameter("email");
        int frienduserid = f.getidFromEmail(friendsEmail);
        person.deleteFriend(usersid, frienduserid); 
    }
%>

</body>
</html>
