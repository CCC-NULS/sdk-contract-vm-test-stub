package io.nuls.contract.service;

import io.nuls.contract.sdk.BlockHeader;

public interface ContractService {
    public BlockHeader getBlockHeader(long height);
    public BlockHeader getBestBlockHeader();
    public void callTxOffline(String contractAddress,String methodName,String methodDesc,Object[] args,String remark );
}
