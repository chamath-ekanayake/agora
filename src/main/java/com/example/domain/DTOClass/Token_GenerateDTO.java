package com.example.domain.DTOClass;

import io.swagger.annotations.ApiModelProperty;

public class Token_GenerateDTO {

    @ApiModelProperty(notes = "No of token")
    private String agora_token;

    public String getAgora_token() {
        return agora_token;
    }

    public void setAgora_token(String agora_token) {
        this.agora_token = agora_token;
    }
}

