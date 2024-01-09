package fr.insa.ms.ValidationWebService.model;

public class Validation {
	private boolean validity;
	private int missionId;
	private int validatorId;
	private String comment;
	
	public Validation(int missionId) {
		super();
		this.missionId = missionId;
	}
	
	public Validation(int missionId, boolean validity, int validatorId, String comment) {
		super();
		this.validity = validity;
		this.missionId = missionId;
		this.validatorId = validatorId;
		this.comment = comment;
	}
	
	public boolean isValidity() {
		return validity;
	}

	public void setValidity(boolean validity) {
		this.validity = validity;
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
