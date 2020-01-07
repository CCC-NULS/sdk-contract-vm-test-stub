package io.nuls.contract.util;

import io.nuls.contract.sdk.util.ParameterUtils;

import java.util.HashMap;
import java.util.Map;

public class MockEnvUtil {
    public static void initMockEnv(int chainId,int assetId,int assetChainId,boolean isOffline,String apiUrl) throws Exception{
        ParameterUtils.CHAIN_ID=chainId;
        ParameterUtils.ASSET_ID=assetId;
        ParameterUtils.ASSET_CHAIN_ID=assetChainId;
        ParameterUtils.ISOFFLINE=isOffline;
        if(!isOffline){
            if(apiUrl==null){
                throw new Exception("apiUrl is not null");
            }
            if(!apiUrl.startsWith("http://")){
                throw new Exception("apiUrl format is error");
            }
        }
        ParameterUtils.API_URL=apiUrl;
    }
    public static void initMockEnv(boolean isOffline,String apiUrl)throws Exception{
        ParameterUtils.ISOFFLINE=isOffline;
        if(!isOffline){
            if(apiUrl==null){
                throw new Exception("apiUrl is not null");
            }
            if(!apiUrl.startsWith("http://")){
                throw new Exception("apiUrl format is error");
            }
        }
        ParameterUtils.API_URL=apiUrl;
    }
    public static void putMockCallReturnValue(String address,String methodName,Object value){
        Map<String,Object> valueMap= ParameterUtils.MOCK_CALL_RETRUN_VALUE.get(address);
        if(valueMap==null){
            valueMap= new HashMap<String,Object>();
            ParameterUtils.MOCK_CALL_RETRUN_VALUE.put(address,valueMap);
        }
        valueMap.put(methodName,value);
    }

    public static void setLogLevel(String logLevel){
        System.setProperty("log.level", logLevel);
    }

}
