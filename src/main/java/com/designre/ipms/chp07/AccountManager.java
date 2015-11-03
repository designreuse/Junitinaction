package com.designre.ipms.chp07;

/**
 * Created by missaoui on 03/11/15.
 */
public interface AccountManager {

    Account findAccountForUser(String userId);
    void updateAccount(Account account);
}
