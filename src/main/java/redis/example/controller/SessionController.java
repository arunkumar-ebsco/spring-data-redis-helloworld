package redis.example.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.example.dao.SessionDAO;
import redis.example.model.Session;

import java.util.Map;
import java.util.UUID;

/**
Created by: aganapathy@ebsco.com
Date: 4/1/18
*/
@RestController
@RequestMapping("/session")
public class SessionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionController.class);

	@Autowired
	SessionDAO sessionDAO;

	@RequestMapping(value = "/create" ,method = RequestMethod.GET)
	public void createSession(){

		Session session = new Session();
		session.setSessionId(UUID.randomUUID().toString());
		boolean useLetters = true;
		boolean useNumbers = false;
		session.setAccessToken(RandomStringUtils.random(10, useLetters,useNumbers));
		session.setIdToken(RandomStringUtils.random(10,useLetters,useNumbers));
		session.setRefreshToken(RandomStringUtils.random(10,useLetters,useNumbers));
		sessionDAO.createSession(session);
		LOGGER.info("Session created successfully");

	}

	@RequestMapping(method = RequestMethod.GET)
	public Map<String,Session> getAllSessions(){
		return sessionDAO.getAllSessions();

	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public long getNoOfSessions(){
		return sessionDAO.getNumberOfSessions();

	}

	@RequestMapping(value = "/delete/{sessionId}", method = RequestMethod.GET)
	public void getNoOfSessions(@PathVariable String sessionId){
		if(sessionDAO.getSession(sessionId) != null){
			sessionDAO.deleteSession(sessionId);
			LOGGER.info("Session with id : "+sessionId+" deleted successfully");
		}


	}
}