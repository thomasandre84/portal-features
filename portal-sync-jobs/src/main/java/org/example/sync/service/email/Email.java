package org.example.sync.service.email;

import java.util.List;

public record Email(String id, String accountName, String from, String to, String subject, String body, List<String> tags) {
}
