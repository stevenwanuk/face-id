package com.sven.services.faceplusplus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FaceSetCreateResponse
{

    @JsonProperty("request_id")
    private String requestId;
    
    @JsonProperty("faceset_token")
    private String facesetToken;

    @JsonProperty("outer_id")
    private String outerId;
    
    @JsonProperty("face_added")
    private int faceAdded;
    
    @JsonProperty("face_count")
    private int faceCount;
    
    @JsonProperty("failure_detail")
    private String[] failureDetail;
    
    @JsonProperty("time_used")
    private int timeUsed;
    
    @JsonProperty("error_message")
    private int errorMessage;
    
    public String getRequestId()
    {
        return requestId;
    }

    public void setRequestId(final String requestId)
    {
        this.requestId = requestId;
    }

    public String getOuterId()
    {
        return outerId;
    }

    public void setOuterId(final String outerId)
    {
        this.outerId = outerId;
    }

    public int getFaceAdded()
    {
        return faceAdded;
    }

    public void setFaceAdded(final int faceAdded)
    {
        this.faceAdded = faceAdded;
    }

    public int getFaceCount()
    {
        return faceCount;
    }

    public void setFaceCount(final int faceCount)
    {
        this.faceCount = faceCount;
    }

    public String[] getFailureDetail()
    {
        return failureDetail;
    }

    public void setFailureDetail(final String[] failureDetail)
    {
        this.failureDetail = failureDetail;
    }

    public int getTimeUsed()
    {
        return timeUsed;
    }

    public void setTimeUsed(final int timeUsed)
    {
        this.timeUsed = timeUsed;
    }

    public int getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(final int errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getFacesetToken()
    {
        return facesetToken;
    }

    public void setFacesetToken(final String facesetToken)
    {
        this.facesetToken = facesetToken;
    }
}
