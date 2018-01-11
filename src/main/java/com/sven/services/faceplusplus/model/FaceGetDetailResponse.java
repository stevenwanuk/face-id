package com.sven.services.faceplusplus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sven.model.BmaUserId;

public class FaceGetDetailResponse extends FaceApiResponse
{

    @JsonProperty("face_token")
    private String faceToken;
    
    @JsonProperty("user_id")
    private BmaUserId userId;

    public String getFaceToken()
    {
        return faceToken;
    }

    public void setFaceToken(final String faceToken)
    {
        this.faceToken = faceToken;
    }

    public BmaUserId getUserId()
    {
        return userId;
    }

    public void setUserId(final BmaUserId userId)
    {
        this.userId = userId;
    }

}
