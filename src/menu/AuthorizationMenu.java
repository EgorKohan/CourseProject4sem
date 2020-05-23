package menu;

import Valid.ValidInput;
import action.AccountAction;
import action.UserAction;
import entity.Account;
import entity.Storage;
import entity.User;

public class AuthorizationMenu {
    private static UserAction userAction = new UserAction();
    private static AccountAction accountAction = new AccountAction();

    private User login() {
        Account account = accountAction.create();
        return Storage.getUsersMap().get(account);
    }

    private boolean registration() {
        try {
            Account account = accountAction.create();
            if (Storage.getUsersMap().containsKey(account)){
                System.out.println("Данный логин занят");
                return false;
            }
            Storage.getUsersMap().put(account, userAction.create());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public User launch() {
        System.out.println("Меню авторизации");
        System.out.print("1 - Вход" +
                "\n2 - Регистрация" +
                "\n3 - Выход" +
                "\nВыберите действие: ");
        int choice = ValidInput.chooseAnAnswer(1, 3);
        switch (choice) {
            case 1: {
                User user = login();
                if (user == null) System.out.println("Данного пользователя не существует");
                return user;
            }
            case 2: {
                System.out.println(registration() ?
                        "Регистрация прошла успешно" : "Регистрация была отменена");
                break;
            }
            case 3:
                throw new RuntimeException("Exit");
            default:
                throw new UnsupportedOperationException();
        }
        return null;
    }

}
