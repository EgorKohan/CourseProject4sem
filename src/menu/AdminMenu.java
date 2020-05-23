package menu;

import Valid.ValidInput;
import action.AccountAction;
import action.AdminAction;
import action.TractorAction;
import action.UserAction;
import entity.Account;
import entity.Storage;
import entity.Tractor;
import entity.User;

public class AdminMenu {

    private AdminAction adminAction = new AdminAction();
    private UserAction userAction = new UserAction();
    private AccountAction accountAction = new AccountAction();

    public void dismissEmployeeCase() {
        adminAction.displayEmployeeList();
        System.out.print("Введите имя: ");
        String name = ValidInput.inputString("[А-я]+");
        System.out.print("Введите фамилию: ");
        String surname = ValidInput.inputString("[А-я]+");
        User user = new User(name, surname, "", 0);
        for (User setUser : Storage.getUsers().getUserSet()) {
            if (setUser.equals(user)) adminAction.dismissEmployee(setUser);
        }
    }

    public void sellTractorCase() {
        adminAction.displayTractorList();
        System.out.print("Введите название трактора: ");
        String name = ValidInput.inputString("[\\w[А-я[-]]]+");
        Storage.getTractorsList().remove(new Tractor(name, null, null));
    }

    public void launchAdminMenu(User user) {
        Account account = userAction.getAccount(user);
        int choice;
        do {
            System.out.println("Меню администратора");
            System.out.print(
                    "1 - Открыть профиль" +
                            "\n2 - Редактировать профиль" +
                            "\n3 - Открыть список рабочих" +
                            "\n4 - Открыть список собранной техники" +
                            "\n5 - Начать отбор кандидатов" +
                            "\n6 - Уволить рабочего" +
                            "\n7 - Начать новый проект" +
                            "\n8 - Продать трактора" +
                            "\n9 - Сформировать отчеты" +
                            "\n10 - Выход" +
                            "\nВыберите действие: "
            );
            choice = ValidInput.chooseAnAnswer(1, 10);
            switch (choice) {
                case 1: {
                    System.out.print(accountAction.getInfo(account) + userAction.getInfo(user));
                    break;
                }
                case 2: {
                    System.out.println("1 - Изменить пароль" +
                            "\n2 - Изменить профиль");
                    int choiceForRedact = ValidInput.chooseAnAnswer(1, 2);
                    if (choiceForRedact == 1) accountAction.redact(account);
                    else userAction.redact(user);
                    break;
                }
                case 3: {
                    adminAction.displayEmployeeList();
                    break;
                }
                case 4: {
                    adminAction.displayTractorList();
                    break;
                }
                case 5: {
                    adminAction.hireEmployee(adminAction.startElection());
                    break;
                }
                case 6: {
                    dismissEmployeeCase();
                    break;
                }
                case 7: {
                    TractorAction tractorAction = new TractorAction();
                    adminAction.startAssembly(tractorAction.create());
                    break;
                }
                case 8: {
                    sellTractorCase();
                    break;
                }
                case 9: {
                    adminAction.generifyReports();
                    break;
                }
                case 10:
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        } while (choice != 10);
    }

}
