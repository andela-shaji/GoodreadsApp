package com.goodreadsapp.login;

public class LoginPresenter<T extends LoginScreen> {

    private T screen;

    public LoginPresenter(T screen) {
        this.screen = screen;
    }
}
