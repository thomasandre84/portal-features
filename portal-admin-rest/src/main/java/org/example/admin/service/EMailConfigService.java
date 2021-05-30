package org.example.admin.service;

import lombok.extern.slf4j.Slf4j;
import org.example.admin.repository.EMailConfigRepository;
import org.example.model.EMailConfiguration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.URI;
import java.util.List;

@Slf4j
@ApplicationScoped
public class EMailConfigService {

    @Inject
    EMailConfigRepository eMailConfigRepository;

    public URI addConfig(EMailConfiguration configuration) {
        return null;
    }

    public List<EMailConfiguration> getConfigs() {
        log.info("Requesting all EMail Configurations");
        return eMailConfigRepository.listAll();
    }
}
