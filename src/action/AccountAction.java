package action;

import Valid.ValidInput;
import entity.Account;

import java.util.Formatter;

public class AccountAction implements IDataManagement<Account> {

    @Override
    public String getInfo(Account account) {
        String info = "Данные аккаунта\n";
        info += "Логин: " + account.getLogin() + '\n';
        info += "Пароль: " + account.getPassword() + '\n';
        return info;
    }

    @Override
    public void redact(Account account) { //must redact only password
        System.out.print("Введите новый пароль: ");
        String newPassword = ValidInput.inputString("\\w+");
        System.out.print("Повторите пароль: ");
        String repeatPassword = ValidInput.inputString("\\w+");
        if (newPassword.equals(repeatPassword)) {
            account.setPassword(newPassword);
        } else {
            System.out.println("Пароли не совпадают!");
        }
    }

    @Override
    public Account create() {
        System.out.print("Введите логин: ");
        String login = ValidInput.inputString("\\w+");
        System.out.print("Введите пароль: ");
        String password = ValidInput.inputString("\\w+");
        return new Account(login, password);
    }

    @Override
    public String getReportInfo(Account account) {
        Formatter formatter = new Formatter();
        formatter.format("|%-15s|%-15s",
                account.getLogin(),
                account.getPassword());
        return String.valueOf(formatter);
    }


    /**
     * @see{UserAction#getFullInfo()}
     */
    @Override
    public String getFullReport() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Account obj) {
        throw new UnsupportedOperationException();
    }

}
