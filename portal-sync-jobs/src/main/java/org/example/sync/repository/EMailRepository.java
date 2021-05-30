package org.example.sync.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.example.model.EMail;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EMailRepository implements PanacheRepositoryBase<EMail, Long> {
}
