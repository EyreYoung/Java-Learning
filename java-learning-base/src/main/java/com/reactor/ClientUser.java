package com.reactor;

public class ClientUser {

    String url;

    String type;

    @Override
    public String toString() {
        return "ClientUser{" +
                "url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public ClientUser(String url, String type) {
        this.url = url;
        this.type = type;
    }
}
