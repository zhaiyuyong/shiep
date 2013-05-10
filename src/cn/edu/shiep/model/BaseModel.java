package cn.edu.shiep.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	public String toString() {
	      return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	 }
}
