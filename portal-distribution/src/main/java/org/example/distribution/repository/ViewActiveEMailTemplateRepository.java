package org.example.distribution.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.example.model.ViewActiveEMailTemplate;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public final class ViewActiveEMailTemplateRepository implements PanacheRepository<ViewActiveEMailTemplate> {
}
