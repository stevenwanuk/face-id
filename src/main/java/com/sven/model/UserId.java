package com.sven.model;

import com.sven.utils.JsonUtils;

public class UserId
{

    public UserId() {
        
    }
    public UserId(final String base64String)
    {
        super();
        UserId temp = JsonUtils.fromBase64(base64String, this.getClass());
        this.userName = temp.userName;
    }
    

    private String userName;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(final String userName)
    {
        this.userName = userName;
    }
    
    
    
    
}
