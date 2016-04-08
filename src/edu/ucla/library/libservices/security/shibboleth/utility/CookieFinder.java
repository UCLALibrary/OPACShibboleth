package edu.ucla.library.libservices.security.shibboleth.utility;

import javax.servlet.http.Cookie;

public class CookieFinder
{
  private String cookieName;
  private String cookieValue;
  private Cookie[] cookies;

  public CookieFinder()
  {
  }

  public boolean findCookie()
  {
    boolean found = false;

    if ( ( cookies != null ) && ( cookies.length != 0 ) )
    {
      for ( int i = 0; i < cookies.length && !found; i++ )
      {
        if ( cookies[ i ].getName().equals( getCookieName() ) )
        {
          setCookieValue( cookies[ i ].getValue() );
          found = true;
        } //if cookie.name
      } //for cookies.length
    } //if cookies != null

    return found;
  }

  public void setCookieName( String cookieName )
  {
    this.cookieName = cookieName;
  }

  private String getCookieName()
  {
    return cookieName;
  }

  private void setCookieValue( String cookieValue )
  {
    this.cookieValue = cookieValue;
  }

  public String getCookieValue()
  {
    return cookieValue;
  }

  public void setCookies( Cookie[] cookies )
  {
    this.cookies = cookies;
  }
}
