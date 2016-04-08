package edu.ucla.library.libservices.security.shibboleth.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ShibRouterServlet
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
    Logger log;

    log = Logger.getLogger( ShibRouterServlet.class );

    log.info( "Shib login request from " + 
              ( request.getHeader( "CLIENTIP" ) != null? 
                request.getHeader( "CLIENTIP" ) : 
                request.getRemoteAddr() ) );

    storeRequestInfo( request, response );
    response.sendRedirect( "https://webservices.library.ucla.edu/Shibboleth.sso/Login?target=https%3A%2F%2Fwebservices.library.ucla.edu%2Fcatalog%2Floginhandler" );
    //response.sendRedirect( "https://webservices-test.library.ucla.edu/Shibboleth.sso/Login?target=https%3A%2F%2Fwebservices-test.library.ucla.edu%2Fcatalog%2Floginhandler" );
  }

  private void storeRequestInfo( HttpServletRequest request, 
                                 HttpServletResponse response )
  {
    String url;
    url = request.getHeader( "REFERER" );

    if ( url == null || url.equals( "" ) || url.length() == 0 )
      url = request.getRequestURL().toString();

    response.addCookie( new Cookie( "queryString", 
                                    request.getQueryString() ) );
    response.addCookie( new Cookie( "userPID", 
                                    findPID( request.getQueryString() ) ) );
/*
 * extend substring to include /cgi-bin [+8] or /vwebv {+6]
 * */
    if ( url.contains( "/cgi" ) )
      response.addCookie( new Cookie( "caller", 
                                      url.substring( 0, url.indexOf( "/cgi" ) + 8 ) ) );
    else if ( url.contains( "/vwebv" ) )
      response.addCookie( new Cookie( "caller", 
                                      url.substring( 0, url.indexOf( "/vwebv" ) + 6 ) ) );
    else
      response.addCookie( new Cookie( "caller", null ) );

    response.addCookie( new Cookie( "catalog", determineCatalog( url ) ) );
    /*if ( url.contains( "/cgi" ) )
      response.addCookie( new Cookie( "dbsource", "db.source.prod" ) );
    else if ( url.contains( "/vwebv" ) )
      response.addCookie( new Cookie( "dbsource", "db.source.test" ) );*/
    if ( url.contains("test") )
      response.addCookie( new Cookie( "dbsource", "db.source.test" ) );
    else
      response.addCookie( new Cookie( "dbsource", "db.source.prod" ) );
  }

  private String findPID( String query )
  {
    String[] queryParams;
    String pid;
    boolean found;

    found = false;
    pid = "";
    queryParams = query.split( "[&=]" );

    for ( int i = 0; i < queryParams.length && !found; i += 2 )
    {
      if ( queryParams[ i ].indexOf( "PID" ) != -1 )
      {
        pid = queryParams[ i + 1 ];
        found = true;
      }
    }

    return pid;
  }

  private String determineCatalog( String url )
  {
    if ( url.indexOf( getServletContext().getInitParameter( "requester.catalog.name.ethno" ) ) != 
         -1 )
      return getServletContext().getInitParameter( "requester.catalog.type.ethno" );
    else if ( url.indexOf( getServletContext().getInitParameter( "requester.catalog.name.filmntv" ) ) != 
              -1 )
      return getServletContext().getInitParameter( "requester.catalog.type.filmntv" );
    else
      return getServletContext().getInitParameter( "requester.catalog.type.ucla" );
  }
}
    /*if ( url.indexOf( getServletContext().getInitParameter( "requester.catalog.test" ) ) != 
         -1 ) 
         //|| url.indexOf( getServletContext().getInitParameter( "requester.catalog.tomcat" ) ) !=  -1 )
    {
      response.addCookie( new Cookie( "dbsource", "db.source.test" ) );
    }
    else if ( url.indexOf( getServletContext().getInitParameter( "requester.catalog.exp" ) ) != 
              -1 )
    {
      response.addCookie( new Cookie( "dbsource", "db.source.exp" ) );
    }
    else
    {
      response.addCookie( new Cookie( "dbsource", "db.source.prod" ) );
    }*/
