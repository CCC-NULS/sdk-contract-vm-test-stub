package io.nuls.test;

import ch.qos.logback.classic.Level;
import io.nuls.contract.rockdb.service.RocksDBService;
import io.nuls.contract.sdk.Address;
import io.nuls.contract.sdk.Block;
import io.nuls.contract.sdk.BlockHeader;
import io.nuls.contract.sdk.util.ParameterUtils;
import io.nuls.contract.service.AccountService;
import io.nuls.contract.service.dto.BalanceInfo;
import io.nuls.contract.service.impl.AccountServiceImpl;
import io.nuls.contract.util.ContractDBUtil;
import io.nuls.contract.util.MockEnvUtil;
import io.nuls.core.basic.Result;
import io.nuls.core.parse.JSONUtils;
import io.nuls.v2.NulsSDKBootStrap;
import io.nuls.v2.model.dto.BlockHeaderDto;
import io.nuls.v2.util.NulsSDKTool;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class AccountServiceTest {

    @Test
    public void testMockEnv(){
        MockEnvUtil.putMockCallReturnValue("tNULSeBaN2SH39sV44u8MjNehTmrkG8ZMQfyFV","name","testPocm");
        MockEnvUtil.putMockCallReturnValue("tNULSeBaN2SH39sV44u8MjNehTmrkG8ZMQfyFV","decimals","3");
        MockEnvUtil.putMockCallReturnValue("tNULSeBaN2SH39sV44u8MjNehTmrkG8ZMQfyFV","symbol","TEST");
    }

    @Test
    public void testAddress(){
        try {
            MockEnvUtil.initMockEnv(false,"http://127.0.0.1:18004");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Address address=new Address("tNULSeBaMu38g1vnJsSZUCwTDU9GsE5TVNUtpD");
        System.out.println("balance:"+address.balance());
        System.out.println("totalbalance:"+address.totalBalance());
        BlockHeader blockHeader=Block.getBlockHeader(13411);
        System.out.println(blockHeader.toString());
        System.out.println("current block header:"+Block.currentBlockHeader().toString());
    }

    @Test
    public void getAccountBalance(){
        try {
            MockEnvUtil.initMockEnv(true,"http://127.0.0.1:18004");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Address address=new Address("tNULSeBaMyk4L3mvZkaNuea7LwctcJM2TZdMg9");
        String result=address.callWithReturnValue("decimals","",null,BigInteger.ZERO);

        AccountService accountService= new AccountServiceImpl();
        try {
            BalanceInfo info= accountService.getAccountBalance("tNULSeBaMvEtDfvZuukDf2mVyfGo3DdiN8KLRG");
            if(info!=null){
                System.out.println("balance:"+info.getBalance());
                System.out.println("totalBalance:"+info.getTotalBalance());
            }

            NulsSDKBootStrap.init(ParameterUtils.CHAIN_ID,"http://127.0.0.1:18004");
            Result accountBalanceR =NulsSDKTool.getAccountBalance("tNULSeBaMvEtDfvZuukDf2mVyfGo3DdiN8KLRG",ParameterUtils.CHAIN_ID,ParameterUtils.ASSET_ID);
            Assert.assertTrue(JSONUtils.obj2PrettyJson(accountBalanceR), accountBalanceR.isSuccess());
            Map balance = (Map) accountBalanceR.getData();
            BigInteger senderBalance = new BigInteger(balance.get("available").toString());
            String nonce = balance.get("nonce").toString();

            System.out.println("senderBalance:"+senderBalance);
            System.out.println("nonce:"+nonce);

            Result blockHeaderR= NulsSDKTool.getBlockHeader(4177);
            BlockHeaderDto blockHeaderDto = (BlockHeaderDto) blockHeaderR.getData();
            BlockHeader blockHeader =new BlockHeader();
            blockHeader.setHash(blockHeaderDto.getHash());
            blockHeader.setHeight(blockHeaderDto.getHeight());
            blockHeader.setPackingAddress(new Address(blockHeaderDto.getPackingAddress()));
            blockHeader.setTxCount(blockHeaderDto.getTxCount());
            blockHeader.setStateRoot(blockHeaderDto.getStateRoot());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(blockHeaderDto.getTime(), pos);
            blockHeader.setTime(strtodate.getTime());
            System.out.println(blockHeader.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDB(){
        RocksDBService.init(ParameterUtils.getDataPath());
        String table = "TEST_TABLE";
        String value="TEST";
        try {
            RocksDBService.createTable("TEST_TABLE");
        } catch (Exception e) {
            e.printStackTrace();
        }
        BlockHeader  header=new BlockHeader();
        header.setTime(1232131);
        header.setHeight(12312);
        ContractDBUtil.putModel(table,value.getBytes(),header);
        BlockHeader pocmtest2= ContractDBUtil.getModel(table,value.getBytes(),BlockHeader.class);
        System.out.println(pocmtest2.getTime());
    }

}
