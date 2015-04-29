package photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
/**
 * A data access object (Dao) to handle album objects
 *
 * @author Amy Ly (amyly@bu.edu)
 */
public class AlbumDao {
	// write a method to load all the pictures of a given album id...

	private static final String GET_PICS_OF_ALBUM_STMT = "SELECT pictureid FROM albumcontains WHERE albumid = ?";

	public List<Integer> getPicIdsofAlbum(int albumid) throws SQLException {
		try {
			Connection c = DbConnection.getConnection();
			PreparedStatement s = c.prepareStatement(GET_PICS_OF_ALBUM_STMT);
			s.setInt(1, albumid); 
			ResultSet r = s.executeQuery(); // result set is a cursor in the relation that is returned as a result of the query execution
			
			List<Integer> ret = new ArrayList<Integer>();
			while (r.next()) {
				ret.add(r.getInt(1));
			}

			r.close();
			s.close();
			c.close();

			return ret;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private static final String LOAD_USERS_ALBUMS_STMT = "SELECT " + "\"albumid\", \"ownerid\", \"name\", \"dateofcreation\" FROM albums WHERE \"ownerid\" = ?";

	// before you call this method you must always convert the email string to the owner id
	public List<AlbumBean> loadUsersAlbums(int ownerid) throws SQLException {
		try {
			Connection c = DbConnection.getConnection();
			PreparedStatement s = c.prepareStatement(LOAD_USERS_ALBUMS_STMT);
			s.setInt(1, ownerid); 
			ResultSet r = s.executeQuery(); // result set is a cursor in the relation that is returned as a result of the query execution
			List<AlbumBean> ret = new ArrayList<AlbumBean>();

			while (r.next()) {
				AlbumBean a = new AlbumBean();
				a.setAlbumid(r.getInt(1));
				a.setOwnerid(r.getInt(2));
				a.setName(r.getString(3));
				a.setDateofcreation(r.getDate(4));

				ret.add(a);
			}
			r.close();
			s.close();
			c.close();
			return ret;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private static final String DELETE_FROM_ALBUMCONTAINS_STMT = "DELETE FROM albumcontains WHERE albumid = ?";
	private static final String DELETE_ALBUM_STMT = "DELETE FROM albums WHERE albumid = ?";
	private static final String DELETE_FROM_PICTURES = "DELETE FROM pictures WHERE picture_id = ?";
	private static final String DELETE_FROM_PICTURESCOMMENTS = "DELETE FROM picturecomments WHERE photoid = ?";

	public void deleteAlbum(Integer albumid) throws SQLException {
		try {
			// first delete reference to the album and its photos in the albumcontains relation
			Connection c = DbConnection.getConnection();

			// second delete all comments associated with the pictures in that album
			// nothing else refers to the pictures anymore, so you can: 
			// third delete the photos from pictures relation
			List<Integer> picidsinalbum = new ArrayList<Integer>();
			picidsinalbum = getPicIdsofAlbum(albumid); 

			PreparedStatement h = c.prepareStatement(DELETE_FROM_ALBUMCONTAINS_STMT);
			h.setInt(1, albumid);
			h.executeUpdate();
			h.close();

			PreparedStatement m = c.prepareStatement(DELETE_FROM_PICTURESCOMMENTS);
			PreparedStatement g = c.prepareStatement(DELETE_FROM_PICTURES);
			for (Integer picid : picidsinalbum) {
				m.setInt(1, picid);
				m.executeUpdate();
				g.setInt(1, picid); 
				g.executeUpdate();
			}
			m.close();
			g.close();

			// finally delete the emptied album
			PreparedStatement s = c.prepareStatement(DELETE_ALBUM_STMT);
			s.setInt(1, albumid);
			s.executeUpdate();
			s.close(); 

			c.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private static final String LOAD_ALL_ALBUMS_STMT = "SELECT \"albumid\", \"ownerid\", \"name\", \"dateofcreation\" FROM albums ORDER BY \"dateofcreation\" DESC";

	public List<AlbumBean> loadAllAlbums() throws SQLException { // take in no parameters
		try {
			Connection c = DbConnection.getConnection();
			PreparedStatement s = c.prepareStatement(LOAD_ALL_ALBUMS_STMT);
			ResultSet r = s.executeQuery(); // result set is a cursor in the relation that is returned as a result of the query execution

			List<AlbumBean> ret = new ArrayList<AlbumBean>(); // a list of beans....
			while (r.next()) { // while an album exists in the relation called albums
				AlbumBean a = new AlbumBean(); // create a bean object for it and call it 'A'.
				a.setAlbumid(r.getInt(1)); // with the AlbumBean methods, set the fields of 'A' in the order of the parameters of the sql statement
				a.setOwnerid(r.getInt(2)); 
				a.setName(r.getString(3));
				a.setDateofcreation(r.getDate(4));

				ret.add(a); // add the bean to the list
			}

			r.close(); // turn off the cursor of the result set 
			s.close(); // close the sql statement 
			c.close(); // close the database connection

			return ret; // return the list of AlbumBeans to be populated to an HTML page. 

		} catch (SQLException e) { // if shit goes wrong, throw an exception
			e.printStackTrace();
			throw e;
		}
	}

	private static final String NEW_ALBUM_STMT = "INSERT INTO albums (ownerid, name, dateofcreation) VALUES (?, ?, now())";

	public boolean create(int ownerid, String name) { // THERE IS A PROBLEM WITH THIS METHOD IT IS THROWING AN EXCEPTION
		PreparedStatement stmt = null; // prepared sql statement that will insert into the db
    	Connection conn = null; // the connection you try to get to the database
	    try {
	      	conn = DbConnection.getConnection(); // try connecting to the database
	      	stmt = conn.prepareStatement(NEW_ALBUM_STMT); // prepare the sql statement shown above
	      	stmt.setInt(1, ownerid); // the first question mark, is the email
	      	stmt.setString(2, name);

	      	stmt.executeUpdate(); // execute the query

	      	stmt.close();
	      	conn.close();

	      	return true;

	    } catch (SQLException e) {
	      	e.printStackTrace();
	      	throw new RuntimeException(e);
	    }
   	}


} // end of the class