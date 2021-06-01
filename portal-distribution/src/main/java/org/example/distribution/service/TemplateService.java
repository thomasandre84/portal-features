package org.example.distribution.service;

import io.quarkus.qute.Engine;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import lombok.extern.slf4j.Slf4j;
import org.example.distribution.repository.ActiveEMailTemplateRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Get Qute EMail Templates from DB.
 */
@Slf4j
@ApplicationScoped
public class TemplateService {
    @Inject
    Engine engine;

    @Inject
    ActiveEMailTemplateRepository activeEMailTemplateRepository;

    public TemplateInstance getTemplateInstance(String name) {
        return null;
    }

    public Template getTemplate(String name) {
        String content = getTemplateContent(name);
        return engine.parse(content);
    }

    private String getTemplateContent(String name) {
        return null;
    }
}
