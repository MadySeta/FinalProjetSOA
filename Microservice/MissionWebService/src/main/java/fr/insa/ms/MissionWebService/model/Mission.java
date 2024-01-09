package fr.insa.ms.MissionWebService.model;

public class Mission {
	
	private int id;
	private int helpSeekerId;
	private String title;
	private String description;
	private int level; // 0 - no validation required; 1 - validation required
	private int status; // 0 - en attente de prise en charge, 1 - en attente de validation, 2 - attribuée, 2 - non validée, 3 - réalisée
	
	public Mission(int id, int helpSeekerId, String title, String description, int level, int status) {
		super();
		this.id = id;
		this.helpSeekerId = helpSeekerId;
		this.title = title;
		this.description = description;
		this.level = level;
		this.status = status;
	}

	public int getHelpSeekerId() {
		return helpSeekerId;
	}

	public void setHelpSeekerId(int helpSeekerId) {
		this.helpSeekerId = helpSeekerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
