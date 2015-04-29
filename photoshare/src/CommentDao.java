package photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date; // for date objects 

/**
 * A data access object (Dao) to handle comment objects.
 * @author Amy Ly (amyly@bu.edu)
 */
public class CommentDao {

	private static final String COUNT_LIKES_ON_PHOTO = "SELECT count(text) FROM picturecomments WHERE text='thisisalike!' AND photoid = ?";
	public int countLikes(int photoid) throws SQLException {
		try {
	      	Connection conn = DbConnection.getConnection(); 
	      	PreparedStatement stmt = conn.prepareStatement(COUNT_LIKES_ON_PHOTO); 

	      	stmt.setInt(1, photoid);
	      	ResultSet rs = stmt.executeQuery();
	      	rs.next();

	      	int numLikes = rs.getInt(1);

	      	stmt.close();
	      	conn.close();

	      	return numLikes;

	    } catch (SQLException e) {
	      	e.printStackTrace();
	      	throw new RuntimeException(e);
	    }
	}
	
	// comments are managed by the picturecomments relation

	// must add comments to photos
	private static final String ADD_COMMENT_STMT = "INSERT INTO picturecomments (ownerid, photoid, text, dateofcomment) VALUES (?, ?, ?, now())";

	public boolean create(int ownerid, int photoid, String text) {
	    try {
	      	Connection conn = DbConnection.getConnection(); // try connecting to the database
	      	PreparedStatement stmt = conn.prepareStatement(ADD_COMMENT_STMT); // prepare the sql statement shown above
	      	stmt.setInt(1, ownerid); // the first question mark, is the email
	      	stmt.setInt(2, photoid);
	      	stmt.setString(3, text);

	      	stmt.executeUpdate();

	      	stmt.close();
	      	conn.close();

	      	return true;

	    } catch (SQLException e) {
	      	e.printStackTrace();
	      	throw new RuntimeException(e);
	    }
	}

	// must get comments to display on the photo page
	private static final String DISPLAY_COMMENTS_STMT = "SELECT ownerid, text, dateofcomment from picturecomments where photoid = ?";

	public List<CommentBean> getCommentsofPicture(int photoid) {
		try {
			Connection c = DbConnection.getConnection();
			PreparedStatement s = c.prepareStatement(DISPLAY_COMMENTS_STMT);
			s.setInt(1, photoid);
			ResultSet r = s.executeQuery();

			List<CommentBean> ret = new ArrayList<CommentBean>();
			while (r.next()) {
				CommentBean a = new CommentBean();
				if (!r.getString(2).equals("thisisalike!")) { // if the comment is not a 'like' -- hah. sorry. we don't want to display comments that are meant to be likes
					a.setOwnerid(r.getInt(1));
					a.setText(r.getString(2));
					a.setDateofcomment(r.getDate(3));

					ret.add(a);
				}
			}
			r.close();
			s.close();
			c.close();

			return ret; // return the list of comment beans
		} catch (SQLException e) {
	      	e.printStackTrace();
	      	throw new RuntimeException(e);
	    }
	}
} // end of the class