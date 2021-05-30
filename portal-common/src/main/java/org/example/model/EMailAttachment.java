package org.example.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class EMailAttachment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] attachment;
}
