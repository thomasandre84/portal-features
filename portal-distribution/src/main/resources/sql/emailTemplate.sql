--liquibase formatted sql
--changeset thomas:emailTemplate
CREATE TABLE EMailTemplate(
name VARCHAR(255) NOT NULL,
version BIGINT NOT NULL,
content MEDIUMTEXT NOT NULL,
PRIMARY KEY (name,version))
ENGINE=InnoDB;

CREATE TABLE ActiveEMailTemplate (
name VARCHAR(255) NOT NULL UNIQUE,
version BIGINT NOT NULL,
PRIMARY KEY (name, version))
ENGINE=InnoDB;

--rollback DROP TABLE IF EXISTS ActiveEMailTemplate ;
--rollback DROP TABLE IF EXISTS EMailTemplate;
