package edu.ucla.library.libservices.security.shibboleth.utility;

import edu.ucla.library.libservices.security.shibboleth.utility.db.DataSourceFactory;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class PatronChecker
{
  public PatronChecker()
  {
  }

  public static boolean isGoodPatron( String dbSource, String selectSQL, 
                                      String userKey )
  {
    boolean isPatron;
    DataSource connection;

    isPatron = true;

    connection = DataSourceFactory.createDataSource( dbSource );

    if ( connection != null )
    {
      JdbcTemplate sql;

      sql = new JdbcTemplate( connection );

      if ( ( sql.queryForInt( selectSQL, new Object[]
          { userKey } ) ) != 1 )
        isPatron = false;
      connection = null;
    }
    else
    {
      isPatron = false;
    }

    return isPatron;
  }
}
