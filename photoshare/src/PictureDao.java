package photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A data access object (DAO) to handle picture objects
 *
 * @author G. Zervas <cs460tf@bu.edu>
 * Modified by Amy Ly <amyly@bu.edu>
 */
public class PictureDao {

	private static final String DELETE_FROM_PICTURES_STMT = "DELETE FROM pictures WHERE picture_id = ?";
	private static final String DELETE_FROM_ALBUMCONTAINS_STMT = "DELETE FROM albumcontains WHERE pictureid = ?";
	private static final String DELETE_PICCOMMENTS_STMT = "DELETE FROM picturecomments WHERE photoid = ?";

	// in order to delete a picture you have to delete it from the pictures relation and you have to delete it from the albumcontains relation
	public void delete(int picid) throws SQLException {
		try {
			Connection c = DbConnection.getConnection();

			PreparedStatement s0 = c.prepareStatement(DELETE_PICCOMMENTS_STMT);
			s0.setInt(1, picid);
			s0.executeUpdate();
			s0.close();
			
			PreparedStatement s2 = c.prepareStatement(DELETE_FROM_ALBUMCONTAINS_STMT);
			s2.setInt(1, picid);
			s2.executeUpdate();
			s2.close();

			PreparedStatement s = c.prepareStatement(DELETE_FROM_PICTURES_STMT);
			s.setInt(1, picid);
			s.executeUpdate();
			s.close();
			
			c.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

  private static final String LOAD_PICTURE_STMT = "SELECT " +
      "\"caption\", \"imgdata\", \"thumbdata\", \"size\", \"content_type\" FROM Pictures WHERE \"picture_id\" = ?";

  public Picture load(int id) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		Picture picture = null;
    try {
		conn = DbConnection.getConnection();
		stmt = conn.prepareStatement(LOAD_PICTURE_STMT);
      	stmt.setInt(1, id);
		rs = stmt.executeQuery();
      if (rs.next()) {
        picture = new Picture();
        picture.setId(id);
        picture.setCaption(rs.getString(1));
        picture.setData(rs.getBytes(2));
        picture.setThumbdata(rs.getBytes(3));
        picture.setSize(rs.getLong(4));
        picture.setContentType(rs.getString(5));
      }

			rs.close();
			rs = null;
		
			stmt.close();
			stmt = null;
			
			conn.close();
			conn = null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { ; }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { ; }
				stmt = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { ; }
				conn = null;
			}
		}

		return picture;
	}

	private static final String SAVE_PICTURE_STMT = "INSERT INTO " +
      "Pictures (\"caption\", \"imgdata\", \"thumbdata\", \"size\", \"content_type\", \"ownerid\") VALUES (?, ?, ?, ?, ?, ?) RETURNING picture_id";

 	private static final String SAVE_ALBUMCONTAINS_STMT = "INSERT INTO albumcontains (albumid, pictureid) VALUES (?, ?)";

	public void save(Picture picture, int albumid, int ownerid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int picid = 0; // apparently needs to be initialized.... 
		// when saving, first add the picture to the pictures table. THEN associate that picture to the album in which it was created in the albumcontains table 
		try {
			conn = DbConnection.getConnection();
			stmt = conn.prepareStatement(SAVE_PICTURE_STMT);
			stmt.setString(1, picture.getCaption());
			stmt.setBytes(2, picture.getData());
			stmt.setBytes(3, picture.getThumbdata());
			stmt.setLong(4, picture.getSize());
			stmt.setString(5, picture.getContentType());
			stmt.setInt(6, ownerid);
			rs = stmt.executeQuery();
			while (rs.next()) {
				picid = rs.getInt(1);// must get the generated pic id that was just stored in the database
			}

			stmt.close();
			stmt = null;

			stmt = conn.prepareStatement(SAVE_ALBUMCONTAINS_STMT);
			stmt.setInt(1, albumid);
			stmt.setInt(2, picid); // picture.getId() does not work because the id is generated via sequence and hasn't finished ( i guess) 
			stmt.executeUpdate();

			stmt.close();
			stmt = null;

			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		finally {
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { ; }
				stmt = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { ; }
				conn = null;
			}
		}
	}

	private static final String USERS_PICTURE_IDS_STMT = "SELECT picture_id FROM Pictures WHERE ownerid = ?";
	public List<Integer> yourPictureIds(int userid) throws SQLException {

		try {
			Connection c = DbConnection.getConnection();
			PreparedStatement s = c.prepareStatement(USERS_PICTURE_IDS_STMT);
			s.setInt(1, userid);
			ResultSet r = s.executeQuery();

			List<Integer> picturesIds = new ArrayList<Integer>();

			while (r.next()) {
				picturesIds.add(r.getInt(1));
			}

			r.close();
			c.close();
			s.close();

			return picturesIds;
		}
		catch (SQLException e) {
			e.printStackTrace();
	 		throw e;
		}

	}

	private static final String ALL_PICTURE_IDS_STMT = "SELECT \"picture_id\" FROM Pictures ORDER BY \"picture_id\" DESC";

	public List<Integer> allPicturesIds() { // the method that's called to get all the picture id's from the picture table...
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		List<Integer> picturesIds = new ArrayList<Integer>();
		try {
			conn = DbConnection.getConnection();
			stmt = conn.prepareStatement(ALL_PICTURE_IDS_STMT); // sql statement 
			rs = stmt.executeQuery();
			while (rs.next()) {
				picturesIds.add(rs.getInt(1));
			}

			rs.close();
			rs = null;

			stmt.close();
			stmt = null;

			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { ; }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { ; }
				stmt = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { ; }
				conn = null;
			}
		}
		return picturesIds;
	}
}
