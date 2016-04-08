package edu.ucla.library.libservices.security.shibboleth.utility;

import edu.ucla.library.libservices.security.shibboleth.utility.db.DataSourceFactory;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class VoyagerLogin
{
  private DataSource connection;
  private String userKey;
  private String voyPid;
  private String insertSQL;
  private String deleteSQL;
  private String dbSource;

  public VoyagerLogin()
  {
  }

  public void handleLogin()
  {
    connection = DataSourceFactory.createDataSource( dbSource );

    if ( connection != null )
    {
      clearOldData();
      if ( voyPid != null && userKey != null )
      {
        writeData();
      }
      connection = null;
    }
  }

  private void writeData()
  {
    JdbcTemplate sql;

    sql = new JdbcTemplate( connection );

    sql.update( insertSQL, new Object[]
        { voyPid, userKey } );
  }

  private void clearOldData()
  {
    JdbcTemplate sql;

    sql = new JdbcTemplate( connection );

    sql.update( deleteSQL, new Object[]
        { voyPid, userKey } );
  }

  public void setUserKey( String userKey )
  {
    this.userKey = userKey;
  }

  public void setInsertSQL( String insertSQL )
  {
    this.insertSQL = insertSQL;
  }

  public void setDeleteSQL( String deleteSQL )
  {
    this.deleteSQL = deleteSQL;
  }

  public void setDbSource( String dbSource )
  {
    this.dbSource = dbSource;
  }

  public void setVoyPid( String voyPid )
  {
    this.voyPid = voyPid;
  }
}
