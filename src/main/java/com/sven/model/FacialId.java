package com.sven.model;

public class FacialId
{
    private UserId userId;
    private String facialId;

    public FacialId()
    {
    }
    
    public FacialId(final UserId userId, final String facialId)
    {
        super();
        this.userId = userId;
        this.facialId = facialId;
    }
    public UserId getUserId()
    {
        return userId;
    }
    public void setUserId(final UserId userId)
    {
        this.userId = userId;
    }
    public String getFacialId()
    {
        return facialId;
    }
    public void setFacialId(final String facialId)
    {
        this.facialId = facialId;
    }

    
    
}
