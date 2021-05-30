package org.example.model;

import lombok.Data;
import org.example.enums.EMailProtocol;

import javax.persistence.*;

@NamedQueries({
    @NamedQuery(name = EMailConfiguration.findAll, query = "select e from EMailConfiguration e"),
    @NamedQuery(name = EMailConfiguration.findById, query = "select e from EMailConfiguration e where e.id = :id")
})
@Data
@Entity
public class EMailConfiguration {
    public static final String findAll = "findAll";
    public static final String findById = "findbyId";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String host;
    private Integer port;
    private String username;
    private String password;
    private EMailProtocol protocol;

}
