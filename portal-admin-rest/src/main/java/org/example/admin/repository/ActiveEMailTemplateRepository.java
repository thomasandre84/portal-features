package org.example.admin.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.example.model.ActiveEMailTemplate;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActiveEMailTemplateRepository implements PanacheRepositoryBase<ActiveEMailTemplate, String> {
}
