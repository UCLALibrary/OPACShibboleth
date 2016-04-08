package shibboleth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucla.library.libservices.security.shibboleth.utility.CookieFinder;

public class StartServlet
  extends HttpServlet
{
  private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

  public void init( ServletConfig config )
    throws ServletException
  {
    super.init(config);
  }

  /**Process the HTTP doGet request.
   */
  public void doGet( HttpServletRequest request, 
                     HttpServletResponse response )
    throws ServletException, IOException
  {
    response.setContentType(CONTENT_TYPE);
    response.addCookie( new Cookie( "my.test.cookie", "PAGE=pbLogon&PID=bRBj_NTw0L7VScVtNRSxFgwu1&SEQ=20091029085604" ) );
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>StartServlet</title></head>");
    out.println("<body>");
    out.println("<form method=\"POST\" action=\"startservlet\">");
    out.println("<input type=\"submit\" value=\"Go Ahead, Click Me!\">");
    out.println("</form>");
    out.println("</body></html>");
    out.close();
  }

  /**Process the HTTP doPost request.
   */
  public void doPost( HttpServletRequest request, 
                      HttpServletResponse response )
    throws ServletException, IOException
  {
    CookieFinder finder;
    finder = new CookieFinder();
    
    finder.setCookieName("my.test.cookie");
    finder.setCookies( request.getCookies() );
    
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>StartServlet</title></head>");
    out.println("<body>");
    out.println("<p>The servlet has received a POST. This is the reply.</p>");
    if ( finder.findCookie() )
    {
      out.println("<p>Cookie found; value = " + finder.getCookieValue() + "</p>");
    }
    else
    {
      out.println("<p>Cookie not found</p>");
    }
    out.println("</body></html>");
    out.close();
  }
}
