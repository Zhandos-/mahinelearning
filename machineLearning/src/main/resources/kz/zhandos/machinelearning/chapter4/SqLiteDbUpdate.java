package kz.zhandos.machinelearning.chapter4;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

public class SqLiteDbUpdate {
  private static final String DB_CHANGELOG =
      "kz/zhandos/machinelearning/liquibase/databasechangelog.xml";
  private final static Logger log = Logger.getLogger(SqLiteDbUpdate.class);

  public static void createDB(boolean databaseDelete) throws SQLException, LiquibaseException {
    Connection conn = null;
    Liquibase liquibase = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      String dbURL = "jdbc:sqlite:search.db";
      conn = DriverManager.getConnection(dbURL);

      if (conn != null) {
        System.out.println("Connected to the database");
        DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
        System.out.println("Driver name: " + dm.getDriverName());
        System.out.println("Driver version: " + dm.getDriverVersion());
        System.out.println("Product name: " + dm.getDatabaseProductName());
        System.out.println("Product version: " + dm.getDatabaseProductVersion());
        liquibase = new Liquibase(DB_CHANGELOG, new ClassLoaderResourceAccessor(),
            new JdbcConnection(conn));
        liquibase.update(new Contexts());
      }
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      if (stmt != null)
        stmt.close();
      if (conn != null)
        conn.close();
    }


  }

  public static void main(String args[]) {
    try {
      createDB(true);
    } catch (Exception e) {
      log.error("", e);
    }
  }

}
