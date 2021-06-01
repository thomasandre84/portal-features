package org.example.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
/*@Table(uniqueConstraints= {
        @UniqueConstraint(columnNames = {"name"})
})*/
public class ActiveEMailTemplate implements Serializable {

    @Id
    @JoinColumn(name = "version", referencedColumnName = "version")
    @JoinColumn(name = "name", referencedColumnName = "name")
    @OneToOne
    private EMailTemplate eMailTemplate;
}
