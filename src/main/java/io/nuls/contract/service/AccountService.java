package io.nuls.contract.service;

import io.nuls.contract.service.dto.BalanceInfo;
import io.nuls.core.exception.NulsException;

public interface AccountService {
    public BalanceInfo getAccountBalance(String address) throws NulsException;
}
