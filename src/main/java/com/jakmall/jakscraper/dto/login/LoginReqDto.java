package com.jakmall.jakscraper.dto.login;

import javax.validation.constraints.NotBlank;

public class LoginReqDto {
	
	@NotBlank(message ="Email tidak boleh kosong")
	private String email;
	
	@NotBlank(message ="Password tidak boleh kosong")
	private String pass;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
