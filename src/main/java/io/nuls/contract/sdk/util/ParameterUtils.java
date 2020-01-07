package io.nuls.contract.sdk.util;

import java.io.File;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class ParameterUtils {
    public static boolean OUT_PUT_TIPS=true;
    public static String SERVICE_URL="http://127.0.0.1:18003";
    public static String API_URL="http://127.0.0.1:18004";
    public static String LEVEL_LOG="ERROR";
    public static  int CHAIN_ID=2;
    public static int ASSET_CHAIN_ID=2;
    public static int ASSET_ID=1;

    public static String callWithReturnDefaultValue="0";

    public static BigInteger totalBalance=new BigInteger("90000000000000");
    public static BigInteger balance=new BigInteger("90000000000000");
    //是否离线调试,不接入测试链
    public static boolean ISOFFLINE=true;

    //模拟调用合约方法后的返回值 key1-<key2-value> key1：地址，key2:合约方法名；value:方法返回值
    public static Map<String, Map<String,Object>> MOCK_CALL_RETRUN_VALUE=new HashMap<String,Map<String,Object>>();

    public static String MOCK_PACKING_ADDRESS="tNULSeBaN2SH39sV44u8MjNehTmrkG8ZMQfyFV";

    public static long INIT_TIME=1568286730;

    private static String DATA_FILE_NAME="DEBUG";

    public  static String CONTRACT_TALBE="CONTRACT_TALBE";

    public static String getDataPath() {
        String  dataPath= System.getProperty("user.home")+ File.separator+ DATA_FILE_NAME+ File.separator+"data";
        return dataPath;
    }

}
