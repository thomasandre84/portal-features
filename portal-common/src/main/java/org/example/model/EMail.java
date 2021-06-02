package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EMail {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String messageId;
    private String sender;

    @Column(columnDefinition = "TEXT")
    private String receivers;
    private String subject;
    private String body;

    @OneToMany(fetch = FetchType.LAZY)
    private List<EMailAttachment> attachments;
}
