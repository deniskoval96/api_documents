package com.hackathon.api.domain.diadok;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiadokUserCreds {

    private String login;
    private String password;

}
