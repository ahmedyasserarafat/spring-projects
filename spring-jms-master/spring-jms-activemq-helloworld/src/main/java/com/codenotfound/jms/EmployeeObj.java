package com.codenotfound.jms;

import java.sql.*;
import oracle.jdbc.*;

public class EmployeeObj implements SQLData
{
  private String sql_type;

  public String name;
  public int current_status;

  public EmployeeObj()
  {
  }

  public EmployeeObj (String sql_type, String name, int current_status)
  {
    this.sql_type = sql_type;
    this.name = name;
    this.current_status = current_status;
  }  

  ////// implements SQLData //////
 
  public String getSQLTypeName() throws SQLException
  { 
    return sql_type; 
  } 
 
  public void readSQL(SQLInput stream, String typeName)
    throws SQLException
  {
    sql_type = typeName;
 
    name = stream.readString();
    current_status = stream.readInt();
  }
 
  public void writeSQL(SQLOutput stream)
    throws SQLException
  { 
    stream.writeString(name);
    stream.writeInt(current_status);
  }
}