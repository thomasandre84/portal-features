package org.example.admin.service;

import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.dto.rest.EMailTemplateDto;
import org.example.admin.repository.ActiveEMailTemplateRepository;
import org.example.admin.repository.EMailTemplateRepository;
import org.example.model.EMailTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Slf4j
@ApplicationScoped
public class EMailTemplateService {
    @Inject
    EMailTemplateRepository eMailTemplateRepository;

    @Inject
    ActiveEMailTemplateRepository activeEMailTemplateRepository;

    @Transactional
    public Uni<EMailTemplate> addEMailTemplate(EMailTemplateDto eMailTemplateDto){
        Long nextVersion = eMailTemplateRepository.getMaxVersion(eMailTemplateDto.getName()) + 1;
        return Uni.createFrom().item(eMailTemplateDto)
                .onItem().transform(dto -> EMailTemplate.builder()
                        .name(eMailTemplateDto.getName())
                        .version(nextVersion)
                        .content(eMailTemplateDto.getContent())
                        .build())
                .onItem().invoke(eMailTemplate -> eMailTemplateRepository.persist(eMailTemplate))
                .onItem().invoke(eMailTemplate -> log.info("persisted {}", eMailTemplate));
    }

}
