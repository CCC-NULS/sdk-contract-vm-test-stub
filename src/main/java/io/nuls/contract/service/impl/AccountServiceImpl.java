package io.nuls.contract.service.impl;

import io.nuls.contract.sdk.util.ParameterUtils;
import io.nuls.contract.service.AccountService;
import io.nuls.contract.service.HttpClient;
import io.nuls.contract.service.dto.BalanceInfo;
import io.nuls.core.core.annotation.Autowired;
import io.nuls.core.core.annotation.Service;
import io.nuls.core.exception.NulsException;
import io.nuls.core.log.Log;

@Service
public class AccountServiceImpl implements AccountService {

        @Autowired
        private HttpClient httpClient;

    @Override
    public BalanceInfo getAccountBalance(String address) throws NulsException {
        BalanceInfo balanceInfo=null;
        try {
            balanceInfo= httpClient.getRpcHttpClient(ParameterUtils.SERVICE_URL).invoke("getAccountBalance",new Object[]{ParameterUtils.CHAIN_ID,ParameterUtils.ASSET_CHAIN_ID,ParameterUtils.ASSET_ID,address},BalanceInfo.class);
        } catch (Throwable e) {
            Log.error(e.getMessage());
        }
        return balanceInfo;
    }
}
