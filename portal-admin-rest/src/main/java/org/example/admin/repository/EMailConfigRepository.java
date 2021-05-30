package org.example.admin.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.example.model.EMailConfiguration;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EMailConfigRepository implements PanacheRepositoryBase<EMailConfiguration, Long> {
}
