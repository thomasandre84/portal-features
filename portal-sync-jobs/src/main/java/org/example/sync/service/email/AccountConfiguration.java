package org.example.sync.service.email;

public record AccountConfiguration(String name, String host, Integer port, String username, String password,
                                   Protocol protocol) {
}
