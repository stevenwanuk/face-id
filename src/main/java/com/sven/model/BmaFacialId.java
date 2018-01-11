package com.sven.model;

public class BmaFacialId extends FacialId
{

    private String bmaNumber;

    public BmaFacialId()
    {
        super();
    }

    public BmaFacialId(final String bmaNumber, final UserId userId, final String facialId)
    {
        super(userId, facialId);
        this.bmaNumber = bmaNumber;
    }

    public String getBmaNumber()
    {
        return bmaNumber;
    }

    public void setBmaNumber(final String bmaNumber)
    {
        this.bmaNumber = bmaNumber;
    }

}
