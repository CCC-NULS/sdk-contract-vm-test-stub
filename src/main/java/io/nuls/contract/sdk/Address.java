package io.nuls.contract.sdk;

import io.nuls.base.basic.AddressTool;
import io.nuls.contract.sdk.util.ParameterUtils;
import io.nuls.core.basic.Result;
import io.nuls.v2.NulsSDKBootStrap;
import io.nuls.v2.model.dto.ContractViewCallForm;
import io.nuls.v2.util.NulsSDKTool;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Address {
    private final String address;

    public Address(String address) {
        valid(address);
        this.address = address;
    }

    /**
     * native
     * 获取该地址的可用余额
     *
     * @return BigInteger
     */
    public  BigInteger balance(){
        if(ParameterUtils.ISOFFLINE){
            Object object= this.getMockData(address,"balance");
            String result= String.valueOf(object);
            if (result.equals(ParameterUtils.callWithReturnDefaultValue)){
                return ParameterUtils.balance;
            }else{
                return (BigInteger)object;
            }
        }else{
            try {
                System.setProperty("log.level", ParameterUtils.LEVEL_LOG);
                NulsSDKBootStrap.init(ParameterUtils.CHAIN_ID,ParameterUtils.API_URL);
                Result  result=NulsSDKTool.getAccountBalance(address,ParameterUtils.CHAIN_ID,ParameterUtils.ASSET_ID);
                Map dto = (HashMap) result.getData();
                return new BigInteger(dto.get("available").toString());
                // BalanceInfo balanceInfo= accountService.getAccountBalance(address);
                // return balanceInfo.getBalance();
            } catch (Exception e) {
                e.printStackTrace();
                return BigInteger.ZERO;
            }
        }
    }

    /**
     * native
     * 获取该地址的总余额
     *
     * @return BigInteger
     */
    public BigInteger totalBalance(){
        if(ParameterUtils.ISOFFLINE){
            Object object= this.getMockData(address,"totalBalance");
            String result= String.valueOf(object);
            if (result.equals(ParameterUtils.callWithReturnDefaultValue)){
                return ParameterUtils.totalBalance;
            }else{
                return (BigInteger)object;
            }
        }else{
            try {
                System.setProperty("log.level", ParameterUtils.LEVEL_LOG);
                NulsSDKBootStrap.init(ParameterUtils.CHAIN_ID,ParameterUtils.API_URL);
                Result  result=NulsSDKTool.getAccountBalance(address,ParameterUtils.CHAIN_ID,ParameterUtils.ASSET_ID);
                Map dto = (HashMap) result.getData();
                return new BigInteger(dto.get("total").toString());
                //     return new BigInteger(dto.getTotal());
                // BalanceInfo balanceInfo= accountService.getAccountBalance(address);
                // return balanceInfo.getTotalBalance();
            } catch (Exception e) {
                e.printStackTrace();
                return BigInteger.ZERO;
            }
        }

    }

    /**
     * native
     * 合约向该地址转账
     *
     * @param value 转账金额（多少Na）
     */
    public void transfer(BigInteger value){
        System.out.println("contract transfer"+value.toString() +" to "+address);
    }

    /**
     * native
     * 调用该地址的合约方法
     *
     * @param methodName 方法名
     * @param methodDesc 方法签名
     * @param args       参数
     * @param value      附带的货币量（多少Na）
     */
    public void call(String methodName, String methodDesc, String[][] args, BigInteger value){
        System.out.println("call the method["+methodName+"] of contract["+address+"]");
    }

    /**
     * native
     * 调用该地址的合约方法并带有返回值(String)
     *
     * @param methodName 方法名
     * @param methodDesc 方法签名
     * @param args       参数
     * @param value      附带的货币量（多少Na）
     * @return 调用合约后的返回值
     */
    public  String callWithReturnValue(String methodName, String methodDesc, String[][] args, BigInteger value){
        if(ParameterUtils.ISOFFLINE){
            Object object= this.getMockData(address,methodName);
            return String.valueOf(object);
        }else{
            System.setProperty("log.level", ParameterUtils.LEVEL_LOG);
            //只能调用view方法
            NulsSDKBootStrap.init(ParameterUtils.CHAIN_ID,ParameterUtils.API_URL);
            ContractViewCallForm form = new ContractViewCallForm();
            try{
                form.setContractAddress(this.address);
                form.setMethodName(methodName);
                form.setMethodDesc(methodDesc);
                form.setArgs(args);
                Result  result=NulsSDKTool.invokeView(form);
                Map resultMap = (Map) result.getData();
                return resultMap==null?"":(String) resultMap.get("result");
            }catch (Exception e){
                System.out.println(e.getMessage());
                return ParameterUtils.callWithReturnDefaultValue;
            }
        }
    }

    private Object getMockData(String address,String methodName){
        if(ParameterUtils.OUT_PUT_TIPS){
            System.out.println("提示：调用方法返回的结果为模拟值，可以通过MockEnvUtil#putMockCallReturnValue方法设定模拟值");
            ParameterUtils.OUT_PUT_TIPS=false;
        }
        Map<String,Object> returnValue=ParameterUtils.MOCK_CALL_RETRUN_VALUE.get(address);
        if(returnValue!=null){
            Object object=returnValue.get(methodName);
            if(object!=null){
                return object;
            }
        }
        return ParameterUtils.callWithReturnDefaultValue;
    }

    /**
     * native
     * 验证地址
     *
     * @param address 地址
     */
    private void valid(String address){
        AddressTool.validAddress(ParameterUtils.CHAIN_ID,address);
    }

    /**
     * native
     * 验证地址是否是合约地址
     *
     */
    public boolean isContract(){
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address1 = (Address) o;
        return address != null ? address.equals(address1.address) : address1.address == null;
    }

    @Override
    public int hashCode() {
        return address != null ? address.hashCode() : 0;
    }

    @Override
    public String toString() {
        return address;
    }

}
