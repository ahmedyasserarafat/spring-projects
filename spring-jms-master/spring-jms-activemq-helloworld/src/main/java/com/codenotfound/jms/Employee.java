package com.codenotfound.jms;

import java.sql.SQLException;

import oracle.jdbc.driver.OracleConnection;
import oracle.jdbc.internal.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.CustomDatum;
import oracle.sql.CustomDatumFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;

public class Employee implements CustomDatum, CustomDatumFactory
{
  public static final String _SQL_NAME = "SA_ADMIN.employee";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  MutableStruct _struct;

  static int[] _sqlType =
  {
    12, 4
  };

  static CustomDatumFactory[] _factory = new CustomDatumFactory[2];

  static final Employee _EmployeeFactory = new Employee();
  public static CustomDatumFactory getFactory()
  {
    return _EmployeeFactory;
  }

  /* constructor */      
  public Employee()
  {
    _struct = new MutableStruct(new Object[2], _sqlType, _factory);
  }

  /* CustomDatum interface */ 
  public Datum toDatum(OracleConnection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }

  /* CustomDatumFactory interface */ 
  public CustomDatum create(Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null;
    Employee o = new Employee();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }

  /* accessor methods */
  public String getEmpname() throws SQLException
  { return (String) _struct.getAttribute(0); }

  public void setEmpname(String empname) throws SQLException
  { _struct.setAttribute(0, empname); }

  public Integer getEmpno() throws SQLException
  { return (Integer) _struct.getAttribute(1); }

  public void setEmpno(Integer empno) throws SQLException
  { _struct.setAttribute(1, empno); } 
}
