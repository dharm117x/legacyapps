package com.example.config;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class AppSession extends WebSession {
	private static final long serialVersionUID = 1L;

	private String username;

    public AppSession(Request request) {
        super(request);
    }

    public boolean isSignedIn() {
        return username != null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
