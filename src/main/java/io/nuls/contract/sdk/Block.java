package io.nuls.contract.sdk;

import io.nuls.contract.sdk.util.ParameterUtils;
import io.nuls.contract.service.ContractService;
import io.nuls.contract.service.impl.ContractServiceImpl;
import io.nuls.core.basic.Result;
import io.nuls.core.core.annotation.Autowired;
import io.nuls.v2.NulsSDKBootStrap;
import io.nuls.v2.model.dto.BlockHeaderDto;
import io.nuls.v2.util.NulsSDKTool;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Block {

/*    @Autowired
    private static ContractService contractService= new ContractServiceImpl();
    */
    /**
     * native
     * 给定块的区块头
     *
     * @param blockNumber 区块高度
     * @return 给定块的区块头
     */
    public static BlockHeader getBlockHeader(long blockNumber){
        if(ParameterUtils.ISOFFLINE){
            BlockHeader blockHeader=new BlockHeader();
            blockHeader.setPackingAddress(new Address(ParameterUtils.MOCK_PACKING_ADDRESS));
            long now=System.currentTimeMillis()/1000;
            blockHeader.setHeight(blockNumber);
            blockHeader.setTime(blockNumber*10+ParameterUtils.INIT_TIME);
            blockHeader.setTxCount(1);
            blockHeader.setHash("mockData");
            blockHeader.setStateRoot("mockData");
            return blockHeader;
        }else{
            System.setProperty("log.level", ParameterUtils.LEVEL_LOG);
            NulsSDKBootStrap.init(ParameterUtils.CHAIN_ID,ParameterUtils.API_URL);
            Result result =NulsSDKTool.getBlockHeader(blockNumber);
            BlockHeaderDto dto= (BlockHeaderDto) result.getData();
            BlockHeader blockHeader=new BlockHeader(dto);
            return blockHeader;
        }

      //  return contractService.getBlockHeader(blockNumber);
    }

    /**
     * native
     * 当前块的区块头
     *
     * @return 当前块的区块头
     */
    public static BlockHeader currentBlockHeader(){
        if(ParameterUtils.ISOFFLINE){
            BlockHeader blockHeader=new BlockHeader();
            blockHeader.setPackingAddress(new Address(ParameterUtils.MOCK_PACKING_ADDRESS));
            long now=System.currentTimeMillis()/1000;
            blockHeader.setHeight((now-ParameterUtils.INIT_TIME)/10);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strsystime = formatter.format(System.currentTimeMillis());
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strsystime, pos);
            blockHeader.setTime(strtodate.getTime());
            blockHeader.setTxCount(1);
            blockHeader.setHash("mockData");
            blockHeader.setStateRoot("mockData");
            return blockHeader;
        }else{
            System.setProperty("log.level", ParameterUtils.LEVEL_LOG);
            NulsSDKBootStrap.init(ParameterUtils.CHAIN_ID,ParameterUtils.API_URL);
            Result result =NulsSDKTool.getBestBlockHeader();
            BlockHeaderDto dto= (BlockHeaderDto) result.getData();
            BlockHeader blockHeader=new BlockHeader(dto);
            return blockHeader;
        }
    }
    /**
     * native
     * 最新块的区块头
     *
     * @return 最新块的区块头
     */
    public static BlockHeader newestBlockHeader(){
        if(ParameterUtils.ISOFFLINE){
            BlockHeader blockHeader=new BlockHeader();
            blockHeader.setPackingAddress(new Address(ParameterUtils.MOCK_PACKING_ADDRESS));
            long now=System.currentTimeMillis()/1000;
            blockHeader.setHeight((now-ParameterUtils.INIT_TIME)/10);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strsystime = formatter.format(System.currentTimeMillis());
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strsystime, pos);
            blockHeader.setTime(strtodate.getTime());
            blockHeader.setTxCount(1);
            blockHeader.setHash("mockData");
            blockHeader.setStateRoot("mockData");
            return blockHeader;
        }else{
            System.setProperty("log.level", ParameterUtils.LEVEL_LOG);
            NulsSDKBootStrap.init(ParameterUtils.CHAIN_ID,ParameterUtils.API_URL);
            Result result =NulsSDKTool.getBestBlockHeader();
            BlockHeaderDto dto= (BlockHeaderDto) result.getData();
            BlockHeader blockHeader=new BlockHeader(dto);
            return blockHeader;
            //return contractService.getBestBlockHeader();
        }
    }

    /**
     * 给定块的哈希值
     * hash of the given block
     *
     * @param blockNumber
     * @return 给定块的哈希值
     */
    public static String blockhash(long blockNumber) {
        return getBlockHeader(blockNumber).getHash();
    }

    /**
     * 当前块矿工地址
     * current block miner’s address
     *
     * @return 地址
     */
    public static Address coinbase() {
        return currentBlockHeader().getPackingAddress();
    }

    /**
     * 当前块编号
     * current block number
     *
     * @return number
     */
    public static long number() {
        return currentBlockHeader().getHeight();
    }

    /**
     * 当前块时间戳
     * current block timestamp
     *
     * @return timestamp
     */
    public static long timestamp() {
        return currentBlockHeader().getTime();
    }
}
