package edu.ucla.library.libservices.security.shibboleth.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutHandlerServlet
  extends HttpServlet
{
  public void init( ServletConfig config )
    throws ServletException
  {
    super.init( config );
  }

  /**Process the HTTP doGet request.
   */
  public void doGet( HttpServletRequest request, 
                     HttpServletResponse response )
    throws ServletException, IOException
  {
    doPost( request, response );
  }

  /**Process the HTTP doPost request.
   */
  public void doPost( HttpServletRequest request, 
                      HttpServletResponse response )
    throws ServletException, IOException
  {
    if ( isEmpty( request.getHeader("Shib-Session-ID")) )
    {
      response.sendRedirect( request.getParameter( "caller" ) ); 
    }
    else
    {
      response.sendRedirect( 
        //"https://webservices-test.library.ucla.edu/Shibboleth.sso/Logout?return=" 
        "https://webservices.library.ucla.edu/Shibboleth.sso/Logout?return=" 
        + "https://shb.ais.ucla.edu/shibboleth-idp/Logout?" 
        + "entityId=https://webservices.library.ucla.edu/catalog/shibboleth-sp" );
      //response.sendRedirect( "https://webservices.library.ucla.edu/Shibboleth.sso/Logout?return=https%3A%2F%2Fshb.ais.ucla.edu%2Fshibboleth-idp%2Flogout%3fentityId%3dhttps%3A%2F%2Fwebservices.library.ucla.edu%2Fcatalog%2Fshibboleth-sp" );
    }
  }
  private boolean isEmpty(String value)
  {
    return value == null || value.equals("") || value.length() == 0;
  }
}
