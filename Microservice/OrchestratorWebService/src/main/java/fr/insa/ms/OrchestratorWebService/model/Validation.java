package fr.insa.ms.OrchestratorWebService.model;

public class Validation {
	private int missionId;
	private int validatorId;
	private String comment;
	
	public Validation() { }
	
	public Validation(int missionId) {
		super();
		this.missionId = missionId;
	}

	public int getMissionId() {
		return missionId;
	}

	public void setMissionId(int missionId) {
		this.missionId = missionId;
	}

	public int getValidatorId() {
		return validatorId;
	}

	public void setValidatorId(int validatorId) {
		this.validatorId = validatorId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
