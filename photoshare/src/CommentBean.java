package photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.sql.Date;

/**
 * A bean that handles new comment data
 *
 * @author Amy Ly (amyly@bu.edu)
 */
public class CommentBean {
  private int commentid = 0;
  private int ownerid = 0;
  private int photoid = 0;
  private String text = "";
  private Date dateofcomment = null;

  
  public int getCommentid() {
    return commentid;
  }

  public int getOwnerid() {
    return ownerid;
  }

  public int getPhotoid() {
    return photoid;
  }

  public String getText() {
    return text;
  }

  public Date getDateofcomment() {
    return dateofcomment;
  }

  public void setCommentid(int id) {
    this.commentid = id;
  }

  public void setOwnerid(int id) {
    this.ownerid = id;
  }

  public void setPhotoid(int id) {
    this.photoid = id;
  }

  public void setText(String txt) {
    this.text = txt;
  }

  public void setDateofcomment(Date date) {
    this.dateofcomment = date;
  }
}