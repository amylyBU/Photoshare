package photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import java.sql.*;

/**
 * A bean that handles album data
 *
 * @author Amy Ly (amyly@bu.edu)
 */
public class AlbumBean {
  private int albumid = 0;
  private int ownerid = 0;
  private String name = "";
  private Date dateofcreation;

  public String saySomething() {
    System.out.println("Hello!");
    return "Test";
  }
  
  public int getAlbumid() {
    return albumid;
  }

  public int getOwnerid() {
    return ownerid;
  }

  public String getName() {
    return name;
  }

  public Date getDateofcreation() {
    return dateofcreation;
  }

  public void setAlbumid(int album) {
    this.albumid = album;
  }

  public void setOwnerid(int owner) {
    this.ownerid = owner;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDateofcreation(Date date) {
    this.dateofcreation = date;
  }
}
