package redis.example.model;

import java.io.Serializable;

/**
Created by: aganapathy@ebsco.com
Date: 4/1/18
*/
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sessionId;
	private String accessToken;
	private String idToken;
	private String refreshToken;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getIdToken() {
		return idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Session session = (Session) o;

		if (sessionId != session.sessionId)
			return false;
		if (accessToken != null ? !accessToken.equals(session.accessToken) : session.accessToken != null)
			return false;
		if (idToken != null ? !idToken.equals(session.idToken) : session.idToken != null)
			return false;
		return refreshToken != null ? refreshToken.equals(session.refreshToken) : session.refreshToken == null;
	}

	@Override public int hashCode() {
		int result = sessionId != null ? sessionId.hashCode() : 0;
		result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
		result = 31 * result + (idToken != null ? idToken.hashCode() : 0);
		result = 31 * result + (refreshToken != null ? refreshToken.hashCode() : 0);
		return result;
	}
}