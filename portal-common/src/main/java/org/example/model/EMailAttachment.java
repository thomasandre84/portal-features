package org.example.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class EMailAttachment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Lob
    private byte[] attachment;

    @ManyToOne
    private EMail email;
}
