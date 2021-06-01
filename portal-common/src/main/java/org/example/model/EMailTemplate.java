package org.example.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EMailTemplate.EMailTemplateId.class)
public class EMailTemplate {

    @Id
    private String name;

    @Id
    private Long version;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;

    @EqualsAndHashCode
    public static final class EMailTemplateId implements Serializable {
        Long version;
        String name;
    }
}
