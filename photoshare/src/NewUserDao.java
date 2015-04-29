package photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A data access object (DAO) to handle the Users table
 *
 * @author G. Zervas <cs460tf@bu.edu>
 */
public class NewUserDao {

  private static final String GET_LIKERS_STMT = "select ownerid from picturecomments where text='thisisalike!' AND photoid = ?";
  public List<Integer> getLikersIds(int pictureid) throws SQLException {
      try {
      Connection c = DbConnection.getConnection();

      PreparedStatement s = c.prepareStatement(GET_LIKERS_STMT);
      s.setInt(1, pictureid);
      ResultSet r = s.executeQuery();

      List<Integer> likers = new ArrayList<Integer>();
      while (r.next()) {
        likers.add(r.getInt(1));
      }

      s.close();
      r.close();
      c.close();

      return likers; // should return list of user beans and their ids
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }
  // write a function that will count the number of photos a user has from the pictures table
  // and count the number of comments the user has made in the picturecomments table.
  // then add those together


  // this sql statement took me 2 hours to write and i am very proud of it oh my god!!!!!!!!!!!!!
  private static final String GET_TOP_CONTRIBUTORS = "select ownerid, contribution from (select firstquery.ownerid, coalesce(cmtcount,0)+coalesce(piccount,0) as contribution from (select pc.ownerid, count(commentid) as cmtcount from picturecomments pc group by pc.ownerid) as firstquery full outer join (select p.ownerid, count(picture_id) as piccount from pictures as p group by p.ownerid) as secondquery on firstquery.ownerid = secondquery.ownerid) as temp order by contribution desc";

  public List<NewUserBean> getContributors() throws SQLException {
    try {
      Connection c = DbConnection.getConnection();

      PreparedStatement s = c.prepareStatement(GET_TOP_CONTRIBUTORS);
      ResultSet r = s.executeQuery();

      List<NewUserBean> topcontribs = new ArrayList<NewUserBean>();

      int i = 0;
      while (r.next() && i < 10) {
        NewUserBean a = new NewUserBean();  
        a.setUserid(r.getInt(1));
        a.setContribution(r.getInt(2));

        topcontribs.add(a);
        i++;
      }

      s.close();
      r.close();
      c.close();

      return topcontribs; // should return list of user beans of the top 10 contributors. 
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }

  private static final String GET_FULLNAME_STMT = "SELECT firstname, lastname FROM users WHERE user_id = ?";
  public String getFullNameFromId(int userid) throws SQLException {
    try {
      Connection c = DbConnection.getConnection();
      PreparedStatement s = c.prepareStatement(GET_FULLNAME_STMT);
      s.setInt(1, userid);

      ResultSet r = s.executeQuery(); // result set is a cursor in the relation that is returned as a result of the query execution

      r.next();
      String fname = r.getString(1);
      String lname = r.getString(2);

      String fullname = fname + " " + lname;

      r.close(); // turn off the cursor of the result set 
      s.close(); // close the sql statement 
      c.close(); // close the database connection

      return fullname; 

    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }


  private static final String CHECK_EMAIL_STMT = "SELECT " +
      "COUNT(*) FROM Users WHERE email = ?";

  private static final String NEW_USER_STMT = "INSERT INTO " +
      "Users (email, password, firstname, lastname, dob, gender, clocation, hometown, education) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

  // writing a function to get the userid given the users email
  private static final String GET_ID_FROM_EMAIL_STMT = "SELECT user_id FROM users WHERE email = ?";

  public int getidFromEmail(String useremail) throws SQLException {
    try {
      Connection c = DbConnection.getConnection();
      PreparedStatement s = c.prepareStatement(GET_ID_FROM_EMAIL_STMT);
      s.setString(1, useremail);

      ResultSet r = s.executeQuery(); // result set is a cursor in the relation that is returned as a result of the query execution

      r.next();
      int userid = r.getInt(1);

      r.close(); // turn off the cursor of the result set 
      s.close(); // close the sql statement 
      c.close(); // close the database connection

      return userid; // return the list of AlbumBeans to be populated to an HTML page. 

    } catch (SQLException e) { // if shit goes wrong, throw an exception
      e.printStackTrace();
      throw e;
    }
  }


  public boolean create(String email, String password, String firstname, String lastname, String dob, String gender, String clocation, String hometown, String education) { // create method returns boolean

    PreparedStatement stmt = null; // prepared sql statement that will insert into the db
    Connection conn = null; // the connection you try to get to the database
    ResultSet rs = null; // the result
    try {
      conn = DbConnection.getConnection(); // try connecting to the database
      stmt = conn.prepareStatement(CHECK_EMAIL_STMT); // prepare the sql statement shown above
      stmt.setString(1, email); // the first question mark, is the email
      rs = stmt.executeQuery(); // execute the query
      if (!rs.next()) {
        // Theoretically this can't happen, but just in case...
        return false;
      }
      int result = rs.getInt(1); // checks if the email is already in use
      if (result > 0) {
        // This email is already in use
        return false; 
      }
      
      try { stmt.close(); }
      catch (Exception e) { }

      stmt = conn.prepareStatement(NEW_USER_STMT);
      stmt.setString(1, email);
      stmt.setString(2, password);
      stmt.setString(3, firstname);
      stmt.setString(4, lastname);
      stmt.setString(5, dob);
      stmt.setString(6, gender);
      stmt.setString(7, clocation);
      stmt.setString(8, hometown);
      stmt.setString(9, education);

      stmt.executeUpdate();

      return true;

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      if (rs != null) {
        try { rs.close(); }
        catch (SQLException e) { ; }
        rs = null;
      }
      
      if (stmt != null) {
        try { stmt.close(); }
        catch (SQLException e) { ; }
        stmt = null;
      }
      
      if (conn != null) {
        try { conn.close(); }
        catch (SQLException e) { ; }
        conn = null;
      }
    }
  }


  // writing a function to get a users friends

  private static final String GET_USERS_FRIENDS = "SELECT user2 FROM friendship WHERE user1 = ?";
  private static final String GET_FRIEND_INFO = "SELECT firstname, lastname, email FROM users where user_id = ?";

  public List<NewUserBean> loadUsersFriends(int userid) throws SQLException {
    try {
      Connection c = DbConnection.getConnection();
      PreparedStatement s = c.prepareStatement(GET_USERS_FRIENDS);
      s.setInt(1, userid);

      ResultSet r = s.executeQuery(); // result set is a cursor in the relation that is returned as a result of the query execution


      List<NewUserBean> ret = new ArrayList<NewUserBean>();

      while (r.next()) { // while there are friendids that correspond to friends in the first set
        PreparedStatement s2 = c.prepareStatement(GET_FRIEND_INFO); // prepare another statement for each friendid
        s2.setInt(1, r.getInt(1));  // put the friendid from the first result set into the statement to get the friend's info
        ResultSet friendsinfo = s2.executeQuery(); // execute the query to get the fname, lname, email from the friend

        while (friendsinfo.next()) { // while there is a result in the relation for that friendid
            NewUserBean a = new NewUserBean();
            a.setFirstname(friendsinfo.getString(1)); // first name
            a.setLastname(friendsinfo.getString(2)); // last name
            a.setEmail(friendsinfo.getString(3)); // email -- order according to the query get_friend_info

            ret.add(a); // add the bean you just made to da list
        }
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

  // this is to get a list of all the users in the database
  private static final String DISPLAY_ALL_USERS = "SELECT firstname, lastname, email, user_id FROM users";

  public List<NewUserBean> loadAllUsers(int userid) throws SQLException {
    try {
    Connection c = DbConnection.getConnection();
    PreparedStatement s = c.prepareStatement(DISPLAY_ALL_USERS);
    ResultSet r = s.executeQuery();

    List<NewUserBean> ret = new ArrayList<NewUserBean>();

    while (r.next()) {
      if (r.getInt(4) != userid) {
        NewUserBean a = new NewUserBean();
        a.setFirstname(r.getString(1));
        a.setLastname(r.getString(2));
        a.setEmail(r.getString(3));

        ret.add(a);
      }

    }

    r.close();
    s.close();
    c.close();
    return ret;
  }
  catch (SQLException e) {
    e.printStackTrace();
    throw e;
  }
}
 
// write a method that takes in an email, gets the user id from that email, and deletes that user2 in the friendship table 
private static final String DELETE_FRIENDSHIP = "DELETE FROM friendship WHERE user1 = ? AND user2 = ?";

 public void deleteFriend(int userid, int friendid) throws SQLException {
    try {
      Connection c = DbConnection.getConnection();
      PreparedStatement s = c.prepareStatement(DELETE_FRIENDSHIP);
      s.setInt(1, userid);
      s.setInt(2, friendid);

     s.executeUpdate();

      s.close();
      c.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }


// write a method that takes in an email, gets the user id from that email, and adds that user id in the friendship table.
private static final String CREATE_FRIENDSHIP = "INSERT INTO Friendship (user1, user2) VALUES (?, ?)";

public void addFriend(int userid, int friendid) throws SQLException {
    try {
      Connection c = DbConnection.getConnection();
      PreparedStatement s = c.prepareStatement(CREATE_FRIENDSHIP);
      s.setInt(1, userid);
      s.setInt(2, friendid);

      s.executeUpdate();

      s.close();
      c.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }

}
