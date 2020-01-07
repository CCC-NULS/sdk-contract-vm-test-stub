package io.nuls.contract.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import io.nuls.contract.sdk.util.ParameterUtils;
import io.nuls.core.core.annotation.Service;
import io.nuls.core.log.Log;
import io.nuls.core.model.StringUtils;

import java.net.URL;
import java.util.HashMap;

@Service
public class HttpClient {

    private static JsonRpcHttpClient rpcHttpClient;
    private static String serviceUrl;

    public static JsonRpcHttpClient getRpcHttpClient()  throws Exception{
        if(StringUtils.isBlank(ParameterUtils.SERVICE_URL)){
            throw new Exception("serviceUrl is null ,please set service url");
        }else{
            serviceUrl= ParameterUtils.SERVICE_URL;
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            rpcHttpClient=new JsonRpcHttpClient(objectMapper,new URL(serviceUrl),new HashMap());
        }catch (Exception e){
            Log.error(e);
        }
        return rpcHttpClient;
    }

    public static JsonRpcHttpClient getRpcHttpClient(String serviceUrl) throws Exception{
        if(StringUtils.isBlank(serviceUrl)){
            throw new Exception("serviceUrl is null ,please set service url");
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            rpcHttpClient=new JsonRpcHttpClient(objectMapper,new URL(serviceUrl),new HashMap());
        }catch (Exception e){
            Log.error(e);
        }
        return rpcHttpClient;
    }

}
