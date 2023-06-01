package org.jsp.Spring_boot_project.helper;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Login {

	String id;
	String password;
}
