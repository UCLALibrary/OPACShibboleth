package edu.ucla.library.libservices.security.shibboleth.web;

import edu.ucla.library.libservices.security.shibboleth.utility.CookieFinder;
import edu.ucla.library.libservices.security.shibboleth.utility.PatronChecker;
import edu.ucla.library.libservices.security.shibboleth.utility.VoyagerLogin;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LoginHandlerServlet
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
    Cookie[] cookies;
    Logger log;
    String userID;
    CookieFinder finder;

    cookies = request.getCookies();
    finder = new CookieFinder();
    finder.setCookies( cookies );
    log = Logger.getLogger( LoginHandlerServlet.class );
    userID = setUserID( request );

    if //"db.source" ), 
      ( !PatronChecker.isGoodPatron( getServletContext().getInitParameter( getCookieValue( finder, 
                                                                                           "dbsource" ) ), 
                                     getServletContext().getInitParameter( "db.sql.select.patronid" ), 
                                     userID ) )
    {
      log.info( "sending to bad credential page because UID = " + userID + 
                " is not in patrons table" );
      response.sendRedirect( buildRedirectURL( finder, false, log ) );
    }
    //else send back to catalog
    else
    {
      handleData( cookies, userID );
      response.sendRedirect( buildRedirectURL( finder, true, log ) );
    }
  }

  private String setUserID( HttpServletRequest request )
  {
    return ( request.getHeader( "SHIBUCLAUNIVERSITYID" ).equalsIgnoreCase( getServletContext().getInitParameter( "app.catalog.badcredential" ) ) || 
             isEmpty( request.getHeader( "SHIBUCLAUNIVERSITYID" ) )? 
             handleLoginID( request.getHeader( "SHIBEDUPERSONPRINCIPALNAME" ) ) : 
             request.getHeader( "SHIBUCLAUNIVERSITYID" ) );
  }

  private String handleLoginID( String id )
  {
    if ( id.indexOf( "@" ) != -1 )
    {
      return id.substring( 0, id.indexOf( "@" ) );
    }
    else
    {
      return id;
    }
  }

  private String buildRedirectURL( CookieFinder finder, boolean goodLogin, Logger log )
  {
    StringBuffer redirect;
    String[] queryParams;
    boolean first;
    
    log.info("in LoginHandler, caller URL = " + getCookieValue( finder, "caller" ));
    redirect = new StringBuffer( getCookieValue( finder, "caller" ) );
    queryParams = getCookieValue( finder, "queryString" ).split( "[&=]" );
    first = true;

    redirect.append( getServletContext().getInitParameter( "requester.forward_url.tomcat" ) );

    for ( int i = 0; i < queryParams.length; i += 2 )
    {
      if ( !queryParams[ i ].replaceAll( "'", 
                                         "" ).equalsIgnoreCase( "submit" ) )
        redirect.append( ( first? "" : "&" ) + 
                         queryParams[ i ].replaceAll( "'", "" ) + "=" + 
                         queryParams[ i + 1 ] );
      first = false;
    }
    redirect.append( "&authenticate=" + ( goodLogin? "Y" : "N" ) );
    log.info("redirect URL = " + redirect);

    return redirect.toString();
  }

  private void handleData( Cookie[] cookies, String user )
  {
    CookieFinder finder;
    VoyagerLogin loginHandler;

    finder = new CookieFinder();
    finder.setCookies( cookies );
    loginHandler = new VoyagerLogin();

    loginHandler.setDbSource( getServletContext().getInitParameter( ( getCookieValue( finder, 
                                                                                      "dbsource" ) ) ) ); // "db.source" ) ) );
    loginHandler.setVoyPid( getCookieValue( finder, "userPID" ) );
    loginHandler.setUserKey( user );

    switch ( Integer.parseInt( getCookieValue( finder, "catalog" ) ) )
    {
      case 1:
        loginHandler.setDeleteSQL( getServletContext().getInitParameter( "db.sql.delete.ucladb" ) );
        loginHandler.setInsertSQL( getServletContext().getInitParameter( "db.sql.insert.ucladb" ) );
        break;
      case 2:
        loginHandler.setDeleteSQL( getServletContext().getInitParameter( "db.sql.delete.ethnodb" ) );
        loginHandler.setInsertSQL( getServletContext().getInitParameter( "db.sql.insert.ethnodb" ) );
        break;
      case 3:
        loginHandler.setDeleteSQL( getServletContext().getInitParameter( "db.sql.delete.filmntvdb" ) );
        loginHandler.setInsertSQL( getServletContext().getInitParameter( "db.sql.insert.filmntvdb" ) );
        break;
    }

    loginHandler.handleLogin();
  }

  private String getCookieValue( CookieFinder finder, String cookieName )
  {
    String value;

    value = "";
    finder.setCookieName( cookieName );
    if ( finder.findCookie() )
      value = finder.getCookieValue();

    return value;
  }

  private boolean isEmpty( String value )
  {
    return ( value == null || value.length() == 0 || 
             value.equalsIgnoreCase( "" ) );
  }
}
//if ( !PatronChecker.isGoodPatron( getServletContext().getInitParameter( "db.source" ), getServletContext().getInitParameter( "db.sql.select.patronid" ), userID ) )
