package com.sven.services.faceplusplus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FaceSetCreateResponse
{

    @JsonProperty("faceset_token")
    private String facesetToken;

    public String getFacesetToken()
    {
        return facesetToken;
    }

    public void setFacesetToken(final String facesetToken)
    {
        this.facesetToken = facesetToken;
    }
}
