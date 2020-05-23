package action;

import Valid.ValidInput;
import entity.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AdminAction {

    public void hireEmployee(User user) {
        if (user == null) System.out.println("Не удалось нанять кандидата");
        else {
            user.setType(TypeOfUser.EMPLOYEE);
            System.out.println("Вы наняли " + user.getName() + ' ' + user.getSurname());
        }
    }

    public void dismissEmployee(User user) {
        if (user != null && user.getType() == TypeOfUser.EMPLOYEE) {
            user.setType(TypeOfUser.CANDIDATE);
            System.out.println("Вы уволили " + user.getName() + ' ' + user.getSurname());
        }
    }

    public void displayEmployeeList() {
        UserAction userAction = new UserAction();
        System.out.printf("|%-10s|%-15s|%-15s|%-9s|%-15s|%n",
                "Имя",
                "Фамилия",
                "Отчество",
                "Возраст",
                "Должность");
        for (User user : Storage.getUsers().getUserSet()) {
            if (user.getType() == TypeOfUser.EMPLOYEE) System.out.print(userAction.getReportInfo(user));
        }
    }

    public void generifyReports() {
        Storage.getUsers().generifyReport();
        Storage.getTractors().generifyReport();
        Storage.getResumes().generifyReport();
        System.out.println("Отчеты сформированы");
    }

    public void displayTractorList() {
        TractorAction tractorAction = new TractorAction();
        System.out.println(tractorAction.getFullReport());
    }

    public User startElection() {
        ResumeAction resumeAction = new ResumeAction();
        Set<Resume> set = Storage.getResumesSet();
        int count = set.size();
        if (count <= 2) {
            System.out.println("Не достаточно резюме для начало отбора");
            return null;
        } else System.out.println("Доступно " + count + " резюме");
        System.out.println("Введите кол-во экспертов: ");
        int countOfExperts = ValidInput.chooseAnAnswer(3, count);
        int[][] mas = new int[countOfExperts][countOfExperts];
        int i = 0;
        for (Resume resume : set) {
            System.out.println(resumeAction.getInfo(resume));
            for (int j = 0; j < countOfExperts; j++) {
                System.out.print("Оценка " + (j + 1) + " эксперта: ");
                mas[i][j] = ValidInput.chooseAnAnswer(0, 5);
            }
            i++;
            if(i == count) break;
        }
        User user = null;
        int max = 1;
        i = 0;
        Resume resume = null;
        Iterator<Resume> iter = set.iterator();
        for (i = 0; i < countOfExperts; i++) {
            max = 1;
            for (int j = 0; j < countOfExperts; j++) {
                if (mas[i][j] < mas[j][i] && i != j) {
                    max = 0;
                }
            }
            if (max == 1) {
                resume = iter.next();
                user = resumeAction.findUser(resume);
            } else iter.next();
        }
        resumeAction.delete(resume);
        System.out.println("Вы закончили отбор");
        return user;
    }

    public void sellTractor(Tractor tractor) {
        displayTractorList();
        Storage.getTractorsList().remove(tractor);
    }

    public Set<User> getEmployeeSet(Set<User> allUsers) {
        Set<User> employees = new HashSet<>();
        for (User user : allUsers) {
            if (user.getType() == TypeOfUser.EMPLOYEE) employees.add(user);
        }
        return employees;
    }

    public void startAssembly(Tractor tractor) {
        Set<User> employees = getEmployeeSet(Storage.getUsers().getUserSet());
        int count = employees.size();
        System.out.println("Сейчас доступно " + count + " рабочих");
        if (count == 0) return;
        System.out.print("Выберите количество рабочих для сборки: ");
        int choice = ValidInput.chooseAnAnswer(1, count);
        System.out.println("Сборка начата");
        try {
            Thread.sleep(10000 / choice);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Сборка завершена");
        Storage.getTractorsList().add(tractor);
    }

}
