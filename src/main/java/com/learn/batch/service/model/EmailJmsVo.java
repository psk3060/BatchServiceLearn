package com.learn.batch.service.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO Object 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailJmsVo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8378943452772786754L;
	
	private String from;
	private String to;
	private String body;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return String.format("{\"from\":\"%s\",\"to\":\"%s\",\"body\":\"%s\"}", getFrom(), getTo(), getBody());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(from, to, body);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if( obj == null ) {
			return false;
			
		}
		
		if( !(obj instanceof EmailJmsVo) ) {
			return false;
		}
		
		if( this == obj ) {
			return true;
		}
				
		
		EmailJmsVo temp = (EmailJmsVo) obj;
		
		return 
				Objects.equals(this.getFrom(), temp.getFrom())
				&& Objects.equals(this.getTo(), temp.getTo())
				&& Objects.equals(this.getBody(), temp.getBody())
		;
		
	}
	
}
