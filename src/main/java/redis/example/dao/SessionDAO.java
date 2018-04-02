package redis.example.dao;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import redis.example.model.Session;

import javax.annotation.Resource;
import java.util.Map;

/**
Created by: aganapathy@ebsco.com
Date: 4/1/18
*/
@Repository
@Transactional
public class SessionDAO {

	private static final String KEY = "session";

	@Resource(name="redisTemplate")
	private HashOperations<String, String, Session> hashOps;

	public void createSession(Session session) {
		hashOps.putIfAbsent(KEY, session.getSessionId(), session);
	}
	public void updateSession(Session session) {
		hashOps.put(KEY, session.getSessionId(), session);
	}
	public Session getSession(String id) {
		return hashOps.get(KEY, id);
	}
	public long getNumberOfSessions() {
		return hashOps.size(KEY);
	}
	public Map<String, Session> getAllSessions() {
		return hashOps.entries(KEY);
	}
	public long deleteSessions(String... ids) {
		return hashOps.delete(KEY, (Object)ids);
	}
	public long deleteSession(String sessionId) {
		return hashOps.delete(KEY, sessionId);
	}
}