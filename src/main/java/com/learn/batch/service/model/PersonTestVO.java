package com.learn.batch.service.model;

import java.io.Serializable;
import java.util.Objects;


public class PersonTestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7966998975214300846L;
	
	private String firstName;
	
	private String lastName;
	
	public PersonTestVO() {}
	
	public PersonTestVO(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if( obj == null ) {
			return false;
			
		}
		
		if( obj == this ) {
			return true;
			
		}
		
		if( !(obj instanceof PersonTestVO) ) {
			return false;
			
		}
		
		PersonTestVO temp = (PersonTestVO) obj;
		
		return Objects.equals(this.firstName, temp.firstName) 
					&& Objects.equals(this.lastName, temp.lastName); 
		
	}
	
	@Override
	public String toString() {
		return "\"PersonTestVO\":{\"firstName\" : " + firstName + ", \"lastName\" : \" + lastName + \"}";
	}
	
}
