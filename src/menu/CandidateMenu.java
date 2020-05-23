package menu;

import Valid.ValidInput;
import action.AccountAction;
import action.ResumeAction;
import action.UserAction;
import entity.Account;
import entity.Resume;
import entity.User;

public class CandidateMenu {
    private static final AccountAction accountAction = new AccountAction();
    private static final UserAction userAction = new UserAction();
    private static final ResumeAction resumeAction = new ResumeAction();
    private Resume resume;

    public void launchCandidateMenu(User user) {
        Account account = userAction.getAccount(user);
        int choice;
        do {
            System.out.println("Меню кандидата");
            System.out.print(
                    "1 - Открыть профиль" +
                            "\n2 - Редактировать профиль" +
                            "\n3 - Создать резюме" +
                            "\n4 - Редактировать резюме" +
                            "\n5 - Открыть резюме" +
                            "\n6 - Отправить резюме" +
                            "\n7 - Выход" +
                            "\nВыберите действие: "
            );
            choice = ValidInput.chooseAnAnswer(1, 7);
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
                    if(resume == null) resume = resumeAction.create();
                    else System.out.println("У вас уже есть резюме");
                    break;
                }
                case 4: {
                    if(resume == null) System.out.println("Вы еще не создали резюме");
                    else resumeAction.redact(resume);
                    break;
                }
                case 5: {
                    if(resume == null)System.out.println("У вас нет резюме");
                    else System.out.println(resumeAction.getInfo(resume));
                    break;
                }
                case 6: {
                    if(resume == null) System.out.println("Вы не создали резюме");
                    System.out.println(resumeAction.submit(resume) ?
                            "Резюме отправлено" : "Ваше резюме уже было отправлено");
                    break;
                }
                case 7:
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        } while (choice != 7);
    }

}
