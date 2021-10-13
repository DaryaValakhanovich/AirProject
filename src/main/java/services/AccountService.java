package services;

import dao.AccountDao;
import entities.Account;
import entities.Role;

import java.util.List;

public class AccountService {

    private static final class AccountServiceHolder {
        private static final AccountService INSTANCE = new AccountService();
    }
    public static AccountService getInstance() {
        return AccountService.AccountServiceHolder.INSTANCE;
    }

    /*
    public Account findByEmail(String email){
        return AccountDao.getInstance().findByEmail(email);
    }

    public Account create(Account account){
        return AccountDao.getInstance().create(account);
    }

    public void makeAdmin(long id){
        AccountDao.getInstance().makeAdmin(id);
    }

*/
    public Account findByEmail(String email){
        return accountDao.findByEmail(email);
    }
    public void makeAdmin(int id){
        Account account = findAccount(id);
        account.setRole(Role.ADMIN);
        updateAccount(account);
    }
    private AccountDao accountDao = new AccountDao();

    public AccountService() {
    }

    public Account findAccount(int id) {
        return accountDao.findById(id);
    }

    public void saveAccount(Account account) {
        accountDao.save(account);
    }

    public void deleteAccount(Account account) {
        accountDao.delete(account);
    }

    public void updateAccount(Account account) {
        accountDao.update(account);
    }

    public List<Account> findAllAccounts() {
        return accountDao.findAll();
    }
}
