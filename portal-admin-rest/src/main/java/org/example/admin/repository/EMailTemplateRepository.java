package org.example.admin.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.example.model.EMailTemplate;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public final class EMailTemplateRepository implements PanacheRepositoryBase<EMailTemplate, EMailTemplate.EMailTemplateId> {
    public Long getMaxVersion(String name){
        return (Long) getEntityManager()
                .createNativeQuery("SELECT MAX(e.version) FROM EMailTemplate e WHERE e.name = :name", Long.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
