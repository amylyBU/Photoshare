package photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date; // for date objects 
import java.util.Arrays;

/**
 * A data access object (Dao) to handle Tag objects
 * @author Amy Ly (amyly@bu.edu)
 */
public class TagDao {

	// get pictures of a given tag
	private static final String GET_PICSID_WITH_TAG = "SELECT pictureid FROM picturetags WHERE tag = ?";

	public List<Integer> getPicIdsWithTag(String tag) throws SQLException {
		try {
			Connection c = DbConnection.getConnection();
			PreparedStatement s = c.prepareStatement(GET_PICSID_WITH_TAG);
			s.setString(1, tag);
			ResultSet r = s.executeQuery();

			List<Integer> ret = new ArrayList<Integer>();
			while (r.next()) {
				ret.add(r.getInt(1));
			}

			r.close();
			s.close();
			c.close();

			return ret; // return the list of picids with that tag
		} catch (SQLException e) {
	      	e.printStackTrace();
	      	throw new RuntimeException(e);
	    }
	}

	// get most popular tags (as tag beans)
	private static final String GET_MOST_POPULAR_TAGS = "SELECT tag, COUNT(pictureid) AS tagcount FROM picturetags GROUP BY tag ORDER BY tagcount DESC";

	public List<TagBean> getPopularTags() throws SQLException {
		try {
			Connection c = DbConnection.getConnection();
			PreparedStatement s = c.prepareStatement(GET_MOST_POPULAR_TAGS);
			ResultSet r = s.executeQuery();

			List<TagBean> ret = new ArrayList<TagBean>();
			while (r.next()) {
				TagBean a = new TagBean();
				a.setTag(r.getString(1));
				a.setTagcount(r.getInt(2));
				ret.add(a);
			}

			r.close();
			s.close();
			c.close();

			return ret; // return the list of picids with that tag
		} catch (SQLException e) {
	      	e.printStackTrace();
	      	throw new RuntimeException(e);
	    }
	}

	// create a tag and associate it with a picture
	private static final String CREATE_TAG = "INSERT INTO picturetags (pictureid, tag) VALUES (?, ?)";

	public boolean createTag(int picid, String tag) throws SQLException {
		try {
			Connection c = DbConnection.getConnection();
			PreparedStatement s = c.prepareStatement(CREATE_TAG);

			List<String> tokens = Arrays.asList(tag.split("\\s*,\\s*")); // split the tags
			
			for (String onetag : tokens) { // for each tag in the separated list, execute the query
				s.setInt(1, picid);
				s.setString(2, tag);
				s.executeUpdate();
			}

			s.close();
			c.close();

			return true;

		} catch (SQLException e) {
	      	e.printStackTrace();
	      	throw new RuntimeException(e);
	    }
	}

	// get tags of a given picid
	private static final String GET_TAGS_OF_PIC = "SELECT tag FROM picturetags WHERE pictureid = ?";
	public List<String> getTagsOfPicid(int picid) throws SQLException {
		try {
			Connection c = DbConnection.getConnection();
			PreparedStatement s = c.prepareStatement(GET_TAGS_OF_PIC);
			s.setInt(1, picid);
			ResultSet r = s.executeQuery();

			List<String> ret = new ArrayList<String>();
			while (r.next()) {
				if (r.getString(1) != null) { 
				ret.add(r.getString(1));
				}
			}

			r.close();
			s.close();
			c.close();

			return ret; // return the list of picids with that tag
		} catch (SQLException e) {
	      	e.printStackTrace();
	      	throw new RuntimeException(e);
	    }
	}
	// dont need to delete a tag

} // end of the class