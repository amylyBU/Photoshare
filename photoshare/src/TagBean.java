package photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * A bean that handles new comment data
 *
 * @author Amy Ly (amyly@bu.edu)
 */

public class TagBean {
  private int pictureid;
  private int tagid;
  private String tag;
  private int tagcount;

  public int getTagcount() {
    return tagcount;
  }

  public void setTagcount(int tagcount) {
    this.tagcount = tagcount;
  }

  public int getPictureid() {
    return pictureid;
  }

  public int getTagid() {
    return tagid;
  }
  
  public String getTag() {
    return tag;
  }

  public void setPictureid(int id) {
    this.pictureid = id;
  }

  public void setTagid(int id) {
    this.tagid = id;
  }
  public void setTag(String s) {
    this.tag = s;
  }
}