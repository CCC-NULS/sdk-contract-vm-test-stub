package io.nuls.contract.sdk;

import java.math.BigInteger;

public class Msg {
    private  static Address sender;
    private static BigInteger value;
    private static long gasprice;
    //合约地址
    private static Address address;

    /**
     *  native
     * 剩余Gas
     * remaining gas
     *
     * @return 剩余gas
     */
    public static long gasleft(){
        return 3;
    }

    /**
     * native
     * 合约发送者地址
     * sender of the contract
     *
     * @return 消息发送者地址
     */
    public static Address sender(){
        return sender;
    }

    public static void setSender(Address sender) {
        Msg.sender = sender;
    }

    /**
     * native
     * 合约发送者地址公钥
     * sender public key of the contract
     *
     * @return 消息发送者地址公钥
     */
    public static String senderPublicKey(){
        return "";
    }

    /**
     * native
     * 合约发送者转入合约地址的Nuls数量，单位是Na，1Nuls=1亿Na
     * The number of Nuls transferred by the contract sender to the contract address, the unit is Na, 1Nuls = 1 billion Na
     *
     * @return
     */
    public static BigInteger value(){
        return Msg.value ;
    }

    public static void setValue(BigInteger value) {
        Msg.value = value;
    }

    /**
     * native
     * Gas价格
     * gas price
     *
     * @return Gas价格
     */
    public static long gasprice(){
        return Msg.gasprice;
    }

    public static void setGasprice(long gasprice) {
        Msg.gasprice = gasprice;
    }

    /**
     * native
     * 合约地址
     * contract address
     *
     * @return 合约地址
     */
    public static Address address(){
        return Msg.address;
    }
    public static void setAddress(Address address) {
        Msg.address = address;
    }
}
