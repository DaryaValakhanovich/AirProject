package services;

import dao.AccountDao;
import entities.Account;

public class AccountService {
    private AccountService() {
    }

    private static final class AccountServiceHolder {
        private static final AccountService INSTANCE = new AccountService();
    }
    public static AccountService getInstance() {
        return AccountService.AccountServiceHolder.INSTANCE;
    }

    public Account findByEmail(String email){
        return AccountDao.getInstance().findByEmail(email);
    }

    public Account create(Account account){
        return AccountDao.getInstance().create(account);
    }

    public void makeAdmin(long id){
        AccountDao.getInstance().makeAdmin(id);
    }
}
