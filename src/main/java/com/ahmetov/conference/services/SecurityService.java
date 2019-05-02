package com.ahmetov.conference.services;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
