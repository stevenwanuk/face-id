package com.sven.services.faceplusplus;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.megvii.cloud.http.CommonOperate;
import com.megvii.cloud.http.FaceOperate;
import com.megvii.cloud.http.FaceSetOperate;
import com.megvii.cloud.http.Response;

@Service
public class FaceService
{

    @Value("${face.api.key}")
    private String apiKey;
    @Value("${face.api.secret}")
    private String apiSecret;
    @Value("${face.api.base}")
    private String baseUrl;

    private FaceSetOperate faceSetOperator;
    private FaceOperate faceOperator;
    private CommonOperate commonOperator;

    @PostConstruct
    public void init()
    {
        faceSetOperator = new FaceSetOperate(apiKey, apiSecret, true);
        faceOperator = new FaceOperate(apiKey, apiSecret, true);
        commonOperator = new CommonOperate(apiKey, apiSecret, true);
    }
    
    public Response detect(final byte[] fileByte) throws Exception {
        
        return commonOperator.detectByte(fileByte, 0, "none");
    }
    
    public Response faceSetUserId(final String faceToken, final String userId) throws Exception {
        
        return faceOperator.faceSetUserId(faceToken, userId);
    }
    
    public Response search(final byte[] fileByte, final String outerId) throws Exception {
        
        return commonOperator.searchByOuterId(null, null , fileByte, outerId, 1);
    }

    public Response createFaceSet(final String displayName,
            final String outerId,
            final String tags, final String FaceTokens, final String userData,
            final int ForceMerge) throws Exception
    {

        return faceSetOperator.createFaceSet(displayName, outerId, tags, FaceTokens,
                userData, ForceMerge);

    }

    public Response getFaceSet(final String tags) throws Exception
    {

        return faceSetOperator.getFaceSets(tags);

    }

    public Response delete(final String outerId, final int checkEmpty) throws Exception
    {

        return faceSetOperator.deleteFaceSetByOuterId(outerId, checkEmpty);

    }

    public Response addFaceByOuterId(final String FaceTokens, final String outerId)
            throws Exception
    {

        return faceSetOperator.addFaceByOuterId(FaceTokens, outerId);

    }
}
