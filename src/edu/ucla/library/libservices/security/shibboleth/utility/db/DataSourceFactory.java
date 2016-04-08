package edu.ucla.library.libservices.security.shibboleth.utility.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

public class DataSourceFactory
{
  public DataSourceFactory()
  {
  }

  public static DataSource createDataSource( String name )
  {
    Context envContext;
    InitialContext context;
    DataSource connection;

    try
    {
      context = new InitialContext();
      envContext = (Context)context.lookup("java:/comp/env");
      connection = ( DataSource ) envContext.lookup( name ); //( DataSource ) context.lookup( name );
    }
    catch ( NamingException e )
    {
      e.printStackTrace();
      connection = null;
    }

    return connection;
  }
}
