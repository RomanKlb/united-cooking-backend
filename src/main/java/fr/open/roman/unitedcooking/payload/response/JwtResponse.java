package fr.open.roman.unitedcooking.payload.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

	private String token;

	private String type = "Bearer";

	private Long id;

	private String pseudo;

	private String email;

	private List<String> roles;


	public JwtResponse(String accessToken, Long id, String pseudo, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.pseudo = pseudo;
		this.email = email;
		this.roles = roles;
	}
}
