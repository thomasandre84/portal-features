package org.example.distribution.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.example.model.ActiveEMailTemplate;
import org.example.model.EMailTemplate;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActiveEMailTemplateRepository implements PanacheRepositoryBase<ActiveEMailTemplate, EMailTemplate.EMailTemplateId> {
}
