package io.nuls.contract.util;

import io.nuls.base.data.Address;
import io.nuls.contract.rockdb.service.RocksDBService;
import io.nuls.contract.sdk.Msg;
import io.nuls.contract.sdk.annotation.Payable;
import io.nuls.contract.sdk.util.ParameterUtils;
import io.nuls.v2.util.AccountTool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;

public class ContractOperUtil<T> {

    public static <T> String createContractForLocal(T obj,String senderAddress,long gasprice){
        RocksDBService.init(ParameterUtils.getDataPath());
        try {
            RocksDBService.createTable(ParameterUtils.CONTRACT_TALBE);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Address address =AccountTool.createContractAddress(ParameterUtils.CHAIN_ID);
        Msg.setValue(BigInteger.ZERO);
        Msg.setAddress(new io.nuls.contract.sdk.Address(address.toString()));
        Msg.setGasprice(gasprice);
        Msg.setSender(new io.nuls.contract.sdk.Address(senderAddress));
        ContractDBUtil.putModel(ParameterUtils.CONTRACT_TALBE,address.getAddressBytes(),obj);
        return address.toString();
    }

    public static <T> Object callContractFormLocal(Class<T> contractClass,String contractAddress,String methodName,Object[] args,String senderAddress, BigInteger value,long gasprice){
        RocksDBService.init(ParameterUtils.getDataPath());
        Msg.setValue(value);
        Msg.setAddress(new io.nuls.contract.sdk.Address(contractAddress));
        Msg.setGasprice(gasprice);
        Msg.setSender(new io.nuls.contract.sdk.Address(senderAddress));
        Object contract= ContractDBUtil.getModel(ParameterUtils.CONTRACT_TALBE,new Address(contractAddress).getAddressBytes(),contractClass);
        Class[] parameterTypes=null;
        if(args!=null&&args.length>0){
            parameterTypes=new Class[args.length];
            for(int i=0;i<args.length;i++){
               Class convertClass= ObjectBeanUtil.BASIC_CLASS.get(args[i].getClass().getName());
                if(convertClass!=null){
                    parameterTypes[i]=convertClass;
                }else{
                    parameterTypes[i]=args[i].getClass();
                }
            }
        }
        try {
           Method method= contractClass.getMethod(methodName,parameterTypes);
            Payable[] payables=method.getAnnotationsByType(Payable.class);
            if(payables!=null && payables.length>0){
                if(value!=null && value.compareTo(BigInteger.ZERO)<=0){
                    System.out.println("该方法有Payable注解，参数value 不能为空或者小于零");
                    System.exit(0);
                }
            }
           Object result= method.invoke(contract,args);
            ContractDBUtil.putModel(ParameterUtils.CONTRACT_TALBE,new Address(contractAddress).getAddressBytes(),contract);
           if(result!=null){
               return result;
           }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            System.out.println("传入参数与方法的参数不匹配");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T getContractFormLocal(String address, Class<T> clazz){
        RocksDBService.init(ParameterUtils.getDataPath());
        T contract= ContractDBUtil.getModel(ParameterUtils.CONTRACT_TALBE,new Address(address).getAddressBytes(),clazz);
        return contract;
    }

    public static <T> boolean saveContractForLocal(String address,T obj){
        RocksDBService.init(ParameterUtils.getDataPath());
        ContractDBUtil.putModel(ParameterUtils.CONTRACT_TALBE,new Address(address).getAddressBytes(),obj);
        return true;
    }
}
