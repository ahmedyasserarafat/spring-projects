package com.codenotfound.jms;

import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import org.springframework.stereotype.Component;

import oracle.jdbc.driver.OracleConnection;
import oracle.sql.CustomDatum;
import oracle.sql.CustomDatumFactory;
import oracle.sql.Datum;

@Component
public class EventMsgType implements  CustomDatumFactory
{
	public String name;
	public int current_status;
	public int next_status;
	public EventMsgType()
	{}
	public EventMsgType (String name, int current_status,int next_status)
	{
		this.name = name;
		this.current_status = current_status;
		this.next_status = next_status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCurrent_status() {
		return current_status;
	}
	public void setCurrent_status(int current_status) {
		this.current_status = current_status;
	}
	public int getNext_status() {
		return next_status;
	}
	public void setNext_status(int next_status) {
		this.next_status = next_status;
	}
	public CustomDatum create(Datum arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}  
	
	public static CustomDatumFactory getFactory() {
		return null;
	}
	
	
	
	
	
	


}
