package org.seu.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Date;

@Getter
@Setter
@Builder
public class MailDto {

    private String to;

    private String content;

    private String cc;

    private String bcc;

    private String subject;

    private Date sendDate;

    private File[] files;

}
