package fr.insa.rest.RestWebService;

import javax.json.bind.annotation.JsonbProperty;

public class User {
	private String username;
    private String email;
    private int type;
    public User() {}
	public User(String username, String email, int type) {
		super();
		this.username = username;
		this.email = email;
		this.type = type;
	}
	
	@JsonbProperty("username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@JsonbProperty("email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonbProperty("type")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
