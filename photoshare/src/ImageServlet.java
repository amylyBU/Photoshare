package photoshare;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * A servlet to display images straight from the database
 *
 * @author G. Zervas <cs460tf@bu.edu>
 */
public class ImageServlet extends HttpServlet {
  /**
   * Method to receive get requests from the web server
   * (Passes them onto the doPost method)
   *
   * @param req The HttpServletRequest which contains the
   *            information submitted via get
   * @param res A response containing the required response
   *            data for this request
   */
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    doPost(req, res);
  }

  /**
   * Method to receive and process Post requests from the web server
   *
   * @param req The HttpServletRequest which contains the
   *            information submitted via post
   * @param res A response containing the required response data
   *            for this request
   */
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    int pictureId = Integer.parseInt(req.getParameter("picture_id"));
    PictureDao pictureDao = new PictureDao();
    Picture picture = pictureDao.load(pictureId);

    byte[] data = picture.getData();
    if (req.getParameter("t") != null) {
      data = picture.getThumbdata();
    }
    OutputStream os = res.getOutputStream();
    res.setContentType(picture.getContentType());
    for (int i = 0; i < data.length; i++) {
      os.write(data[i]);
    }
    os.flush();
    os.close();
  }
}
