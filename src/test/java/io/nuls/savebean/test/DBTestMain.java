package io.nuls.savebean.test;

import io.nuls.contract.rockdb.service.RocksDBService;
import io.nuls.contract.sdk.util.ParameterUtils;
import io.nuls.contract.util.ContractDBUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DBTestMain {

    @Test
    public void TestMain2(){
        Father father=new Father("1231");
        father.setName("12312");
        RocksDBService.init(ParameterUtils.getDataPath());
        try {
            RocksDBService.createTable(ParameterUtils.CONTRACT_TALBE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String key="11111";
        ContractDBUtil.putModel(ParameterUtils.CONTRACT_TALBE,key.getBytes(),father);
        Father tmp=ContractDBUtil.getModel(ParameterUtils.CONTRACT_TALBE,key.getBytes(),Father.class);
        System.out.println(tmp.getName());
    }
}
