package com.job.payloads.response;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
	private String accessToken;
	private String tokenType = "Bearer";
	private String message;
	private Boolean status;

	public JwtAuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
	}

	public JwtAuthenticationResponse(Boolean status, String message,String accessToken) {
		this.accessToken = accessToken;
		this.message = message;
		this.status = status;
	}
}
