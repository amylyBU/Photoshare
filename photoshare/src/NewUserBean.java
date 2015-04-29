package photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * A bean that handles new user data
 *
 * @author G. Zervas <cs460tf@bu.edu>
 */
public class NewUserBean {
  private String firstname = "";
  private String lastname = "";
  private String email = "";
  private String password1 = "";
  private String password2 = "";
  private int userid = 0;
  private int contribution = 0;

  public int getContribution() {
    return contribution;
  }

  public void setContribution(int contrib) {
    this.contribution = contrib;
  }

  public int getUserid() {
    return userid;
  }

  public void setUserid(int uid) {
    this.userid = uid;
  }
  
  public String saySomething() {
    System.out.println("Hello!");
    return "Test";
  }
  public String getFirstname() { // you added this
    return firstname;
  }

  public String getLastname() { // you added this method
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword1() {
    return password1;
  }

  public String getPassword2() {
    return password2;
  }

  public void setFirstname(String fname) { // you added this method
    this.firstname = fname;
  }

  public void setLastname(String lname) { // you added this method
    this.lastname = lname;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword1(String password1) {
    this.password1 = password1;
  }

  public void setPassword2(String password2) {
    this.password2 = password2;
  }
}
