package fr.insa.ms.OrchestratorWebService.model;

public class User {
	private int id;
	private String username;
	private String email;
	private int type; // 0 - Demandeur d'aide, 1 - Valideur, 2 - Bénévole
	
	public User() { }
	
	public User(int id, String username, String email, int type) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
