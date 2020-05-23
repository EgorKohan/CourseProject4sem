package action;

import Valid.ValidInput;
import entity.Account;
import entity.User;
import entity.Storage;

import java.util.*;

public class UserAction implements IDataManagement<User> {

    @Override
    public String getInfo(User user) {
        String info = "Данные пользователя: \n";
        info += "Имя " + user.getName() + '\n';
        info += "Фамилия " + user.getSurname() + '\n';
        info += "Отчество " + user.getPatronymic() + '\n';
        info += "Возраст " + user.getAge() + '\n';
        info += "Должность " + user.getType() + '\n';
        return info;
    }

    private void addName(User user) {
        System.out.print("Введите имя: ");
        String name = ValidInput.inputString("[А-я]+");
        user.setName(name);
    }

    private void addSurname(User user) {
        System.out.print("Введите фамилию: ");
        String surname = ValidInput.inputString("[А-я]+");
        user.setSurname(surname);
    }

    private void addPatronymic(User user) {
        System.out.print("Введите отчество: ");
        String patronymic = ValidInput.inputString("[А-я]+");
        user.setPatronymic(patronymic);
    }

    private void addAge(User user) {
        System.out.print("Введите возраст(18 - 63): ");
        int age = ValidInput.chooseAnAnswer(18, 63);
        user.setAge(age);
    }

    @Override
    public void redact(User user) {
        System.out.println("Меню редактирования пользователя");
        System.out.print("1 - Изменить имя" +
                "\n2 - Изменить фамилию" +
                "\n3 - Изменить отчество" +
                "\n4 - Изменить возраст" +
                "\n5 - Выход" +
                "\nВыберите действие: ");
        int choice = ValidInput.chooseAnAnswer(1, 5);
        switch (choice) {
            case 1:
                addName(user);
                break;
            case 2:
                addSurname(user);
                break;
            case 3:
                addPatronymic(user);
                break;
            case 4:
                addAge(user);
                break;
            case 5:
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public User create() {
        User newUser = new User("", "", "", 0);
        addName(newUser);
        addSurname(newUser);
        addPatronymic(newUser);
        addAge(newUser);
        return newUser;
    }

    public Account getAccount(User user) throws IllegalArgumentException{ //iterator works
        Set<Map.Entry<Account, User>> set = Storage.getUsersMap().entrySet();
        Iterator<Map.Entry<Account, User>> iterator = set.iterator();
        Map.Entry<Account, User> entry;
        while (iterator.hasNext()) {
            entry = iterator.next();
            if (user.equals(entry.getValue())) return entry.getKey();
        }
        throw new IllegalArgumentException(); //user must have an account
    }

    public String getReportInfo(User user){
        Formatter formatter = new Formatter();
        formatter.format("|%-10s|%-15s|%-15s|%-9d|%-15s|%n",
                user.getName(),
                user.getSurname(),
                user.getPatronymic(),
                user.getAge(),
                user.getType());
        return String.valueOf(formatter);
    }

    public String getFullReport(){
        String title = String.format("|%-15s|%-15s|%-11s|%-15s|%-15s|%-9s|%-15s|%n",
                "Логин",
                "Пароль",
                "Имя",
                "Фамилия",
                "Отчество",
                "Возраст",
                "Должность");
        StringBuilder report = new StringBuilder(title);
        AccountAction accountAction = new AccountAction();
        Set<Map.Entry<Account, User>> set = Storage.getUsers().getCollection().entrySet();
        Iterator<Map.Entry<Account, User>> iterator = set.iterator();
        Map.Entry<Account, User> entry = null;
        while (iterator.hasNext()) { //iterator works
            entry = iterator.next();
            report.append(accountAction.getReportInfo(entry.getKey()));
            report.append(getReportInfo(entry.getValue()));
        }
        return String.valueOf(report);
    }

    @Override
    public void delete(User obj) {
        throw new UnsupportedOperationException();
    }

}
