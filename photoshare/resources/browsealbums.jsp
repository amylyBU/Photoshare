<%@ page import="photoshare.AlbumBean" %>
<%@ page import="photoshare.AlbumDao" %>
<%@ page import="photoshare.Picture" %>
<%@ page import="photoshare.PictureDao" %>
<%@ page import="photoshare.CommentDao" %>
<%@ page import="photoshare.CommentBean" %>
<%@ page import="photoshare.NewUserDao" %>
<%@ page import="photoshare.NewUserBean" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %> <!-- do I even need this import -->
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<title>Browse Albums</title>
<link rel="stylesheet" type="text/css" href="photoshare.css">
<link href="http://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
</head>

<body>
<div id="navigation">
<a href="browsealbums.jsp">Browse</a>|
<a href="index.jsp">Home</a>|
<a href="rankings.jsp">View Rankings</a>
</div>

<h2>Browse all public photoshare albums and photos</h2>
<h3>Albums</h3>

<% 
	AlbumDao dao = new AlbumDao(); // create an AlbumDao because you want to access all the albums from the database
	List<AlbumBean> albums = dao.loadAllAlbums(); // call the loadAllAlbums() method in the AlbumDao.java file
%>

<table> 

 	<tr>
 		<td>albumid</td>
 		<td>ownerid</td>
 		<td>name</td>
 		<td>dateofcreation</td>
 	</tr>

<% 
	for (AlbumBean album : albums) { // this is a for loop to process all album in the List you defined above called albums
%>

<!-- some HTML to organize your albums from the list -->
 	<tr> <!-- tr stands for table row, populated with : td , which stands for table data -->
		<td><%= album.getAlbumid() %></td> <!-- call the methods from the AlbumBean File to get stuff from the list of AlbumBeans -->
		<td><%= album.getOwnerid() %></td> <!-- for each album (an AlbumBean object), get the desired fields -->
		<td><a href="albumpublic.jsp?albumid=<%= album.getAlbumid() %>"><%= album.getName() %></a></td>
		<td><%= album.getDateofcreation() %></td>
	</tr>

<% 
	} // end of the for loop of AlbumBean objects called album
%>

</table>

<h3>All Existing Pictures</h3>
<table>
    <center><tr>
        <td>picture</td>
        <td>Caption</tr>
    </center>
<%
	PictureDao pictureDao = new PictureDao();
    List<Integer> pictureIds = pictureDao.allPicturesIds(); // creates a Integer list of picture ids
    for (Integer pictureId : pictureIds) {
%>
    <tr>
        <td>
            <a href="picture.jsp?picture_id=<%= pictureId %>">
            <img src="/photoshare/img?t=1&picture_id=<%= pictureId %>"/>
            <% Picture p = pictureDao.load(pictureId); %>
            </a>
        </td>
        <td><%= p.getCaption() %></td>
    <%
        }
    %>

</table>

</body>
</html>