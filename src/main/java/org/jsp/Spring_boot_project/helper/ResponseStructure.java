package org.jsp.Spring_boot_project.helper;

import lombok.Data;

@Data
public class ResponseStructure<T> {
 
	String message;
	int statuscode;
	T data;
}
