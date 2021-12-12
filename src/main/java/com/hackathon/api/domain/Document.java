package com.hackathon.api.domain;

import lombok.Data;

@Data
public class Document {

    private String title;
    private Integer price;
    private SignedStatus signedStatus;

}
