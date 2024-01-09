package fr.insa.ms.ReviewWebService.model;

public class Review {
	private String review;
	private int star;
	private int userId;

	public Review(String review, int star, int userId) {
		super();
		this.review = review;
		this.star = star;
		this.userId = userId;
	}
	

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}
