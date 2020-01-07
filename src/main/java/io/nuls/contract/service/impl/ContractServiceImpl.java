package io.nuls.contract.service.impl;

import io.nuls.contract.sdk.Address;
import io.nuls.contract.sdk.BlockHeader;
import io.nuls.contract.sdk.util.ParameterUtils;
import io.nuls.contract.service.ContractService;
import io.nuls.core.basic.Result;
import io.nuls.core.core.annotation.Service;
import io.nuls.v2.NulsSDKBootStrap;
import io.nuls.v2.model.dto.BlockHeaderDto;
import io.nuls.v2.util.NulsSDKTool;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ContractServiceImpl implements ContractService {
    @Override
    public BlockHeader getBlockHeader(long height) {
        BlockHeader blockHeader =new BlockHeader();
        NulsSDKBootStrap.init(ParameterUtils.CHAIN_ID,ParameterUtils.API_URL);
        Result blockHeaderR= NulsSDKTool.getBlockHeader(height);
        BlockHeaderDto blockHeaderDto = (BlockHeaderDto) blockHeaderR.getData();
        if(blockHeaderDto==null){
            System.out.println("getBlockHeader error!");
            return blockHeader;
        }
        blockHeader.setHash(blockHeaderDto.getHash());
        blockHeader.setHeight(blockHeaderDto.getHeight());
        blockHeader.setPackingAddress(new Address(blockHeaderDto.getPackingAddress()));
        blockHeader.setTxCount(blockHeaderDto.getTxCount());
        blockHeader.setStateRoot(blockHeaderDto.getStateRoot());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(blockHeaderDto.getTime(), pos);
        blockHeader.setTime(strtodate.getTime());
        return blockHeader;
    }

    @Override
    public BlockHeader getBestBlockHeader() {
        BlockHeader blockHeader =new BlockHeader();
        NulsSDKBootStrap.init(ParameterUtils.CHAIN_ID,ParameterUtils.API_URL);
        Result blockHeaderR= NulsSDKTool.getBestBlockHeader();
        BlockHeaderDto blockHeaderDto = (BlockHeaderDto) blockHeaderR.getData();
        if(blockHeaderDto==null){
            System.out.println("getBlockHeader error!");
            return blockHeader;
        }
        blockHeader.setHash(blockHeaderDto.getHash());
        blockHeader.setHeight(blockHeaderDto.getHeight());
        blockHeader.setPackingAddress(new Address(blockHeaderDto.getPackingAddress()));
        blockHeader.setTxCount(blockHeaderDto.getTxCount());
        blockHeader.setStateRoot(blockHeaderDto.getStateRoot());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(blockHeaderDto.getTime(), pos);
        blockHeader.setTime(strtodate.getTime());
        return blockHeader;
    }

    @Override
    public void callTxOffline(String contractAddress,String methodName,String methodDesc,Object[] args,String remark ) {
    }
}
