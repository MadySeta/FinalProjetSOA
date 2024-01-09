package fr.insa.ms.OrchestratorWebService.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.ms.OrchestratorWebService.model.Mission;
import fr.insa.ms.OrchestratorWebService.model.Review;
import fr.insa.ms.OrchestratorWebService.model.User;
import fr.insa.ms.OrchestratorWebService.model.Validation;

@RestController
@RequestMapping("/orchestrator")
public class OrchestratorResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/getUser/{id}")
	public User getUser(@PathVariable int id) throws URISyntaxException {
		URI uri = new URI("http://userConfig/user/"+id);
		User user = restTemplate.getForObject(uri, User.class);
		return user;
	}
	
	/**
	 * Create a new user.
	 * 
	 * @param type  	int type of the user (0 - Demandeur d'aide, 1 - Valideur, 2 - Bénévole)
	 * @param username	String username of the user
	 * @param email		String email of the user
	 * @return			User instance of the created user profil
	 * @throws URISyntaxException
	 */
	@PostMapping("/createUser/{type}/{username}/{email}")
	public User createUser(@PathVariable int type,
			@PathVariable String username,
			@PathVariable String email) throws URISyntaxException {
		
		int id = 2; // à modifier
		
		URI uri = new URI("http://userConfig/user");
		User newUser = new User(id,username, email, type);
		
		User user = restTemplate.postForObject(uri, newUser, User.class);

		return user;
	}
	
	/**
	 * Création d'une nouvelle mission avec la validation si nécessaire.
	 * 
	 * @param level			int niveau de la mission (0 - no validation required; 1 - validation required)
	 * @param title			String titre de la mission
	 * @param description	String description de la mission
	 * @return 				Mission instance of the created mission
	 * @throws URISyntaxException
	 */
	@PostMapping("/createMission")
	public void createMission(@RequestBody Mission mission) throws URISyntaxException {
	
		int status = (mission.getLevel()==0)? 0 : 1;
			
		// Création de la mission et insertiion dans la base de données
		URI uri = new URI("http://missionConfig/mission");
		Mission newMission = new Mission(0, mission.getHelpSeekerId(),
				mission.getTitle(), mission.getDescription(), 
				mission.getLevel(),
				status);
		
		 restTemplate.postForObject(uri, newMission, Mission.class);
		 
		 // Création de la validation si mission complexe
		 if(mission.getLevel() == 1) {
			 ResponseEntity<Mission> createdMission = restTemplate.getForEntity(uri, Mission.class);
			 
			 URI validationUIR = new URI("http://validationConfig/validation/" + createdMission.getBody().getId());
			restTemplate.postForObject(validationUIR, null , null);
		 }
	}
	
	
	@PutMapping("/assignValidation/{missionId}/{validatorId}")
	public void assignValidation(@PathVariable int missionId,
			@PathVariable int validatorId) throws URISyntaxException {		
		URI uri = new URI("http://validationConfig/validation/"+ missionId + "/" + validatorId);
		
		restTemplate.put(uri, null);	
	}
	
	
	@PostMapping("/createReview")
	public void createReview(@RequestBody Review review) throws URISyntaxException {
		URI uri = new URI("http://reviewConfig/review");
		
		restTemplate.postForObject(uri, review, Review.class);
	}

}

