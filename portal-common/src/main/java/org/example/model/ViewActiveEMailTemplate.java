package org.example.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
public class ViewActiveEMailTemplate {

    private String name;

    private Long version;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;

}
