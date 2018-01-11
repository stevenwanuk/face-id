package com.sven.model;

import com.sven.utils.JsonUtils;

public class BmaUserId extends UserId
{

    public BmaUserId() {
        
    }
    public BmaUserId(final String base64String)
    {
        
        super(base64String);
        BmaUserId temp = JsonUtils.fromBase64(base64String, this.getClass());
        this.bmaNumber = temp.bmaNumber;
    }
    
    public BmaUserId(final String bmaNumber, final String userName)
    {
        super.setUserName(userName);
        this.bmaNumber = bmaNumber;
    }

    private String bmaNumber;

    public String getBmaNumber()
    {
        return bmaNumber;
    }

    public void setBmaNumber(final String bmaNumber)
    {
        this.bmaNumber = bmaNumber;
    }
    
}
