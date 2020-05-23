package action;

import Valid.ValidInput;
import entity.Account;
import entity.Resume;
import entity.Storage;
import entity.User;

import java.util.Formatter;
import java.util.Map;
import java.util.Set;

public class ResumeAction implements IDataManagement<Resume> {

    @Override
    public String getInfo(Resume resume) {
        String info = "";
        info += "Кандидат: " + resume.getName() + ' ' + resume.getSurname() + '\n';
        info += "Место рождения: " + resume.getBpl() + '\n';
        info += "Образование: " + resume.getEducation() + '\n';
        info += "Предыдущее место работы: " + resume.getPreviousEmployment() + '\n';
        info += "Опыт работы: " + resume.getExperience() + '\n';
        return info;
    }

    private void addBpl(Resume resume) {
        System.out.print("Введите место рождения: ");
        String bpl = ValidInput.inputString("[А-я]+");
        resume.setBpl(bpl);
    }

    private void addEducation(Resume resume) {
        System.out.print("Какое ваше образование:\n1 - Высшее\n2 - Среднее\nВыберите: ");
        int education = ValidInput.chooseAnAnswer(1, 2);
        resume.setEducation((education == 1) ? Resume.Education.HIGH : Resume.Education.MIDDLE);
    }

    private void addPreviousEmployment(Resume resume) {
        System.out.print("Введите место предыдущей работы: ");
        String previousEmployments = ValidInput.inputString("[\\w[А-я]]+");
        resume.setPreviousEmployment(previousEmployments);
    }

    private void addExperience(Resume resume) {
        System.out.print("Введите рабочий стаж: ");
        int experience = ValidInput.chooseAnAnswer(0, 50);
        resume.setExperience(experience);
    }

    @Override
    public void redact(Resume resume) {
        System.out.print(
                "1 - Изменить место рождения" +
                        "\n2 - Изменить образование" +
                        "\n3 - Изменить предыдущее место работы" +
                        "\n4 - Изменить рабочий стаж" +
                        "\n5 - Выход" +
                        "\nВыберите действие: ");
        int choice = ValidInput.chooseAnAnswer(1, 5);
        switch (choice) {
            case 1: {
                addBpl(resume);
                System.out.println("Место рождения изменено");
                break;
            }
            case 2: {
                addEducation(resume);
                System.out.println("Уровень образования изменен");
                break;
            }
            case 3: {
                addPreviousEmployment(resume);
                System.out.println("Предыдущее место работы изменено");
                break;
            }
            case 4: {
                addExperience(resume);
                System.out.println("Опыт работы изменен");
                break;
            }
            case 5: {
                break;
            }
            default: {
                throw new RuntimeException();
            }
        }
    }

    @Override
    public Resume create() {
        System.out.print("Введите имя: ");
        String name = ValidInput.inputString("[А-я]+");
        System.out.print("Введите фамилию: ");
        String surname = ValidInput.inputString("[А-я]+");
        Resume resume = new Resume(name, surname);
        addBpl(resume);
        addEducation(resume);
        addPreviousEmployment(resume);
        addExperience(resume);
        return resume;
    }

    @Override
    public String getReportInfo(Resume resume) {
        Formatter formatter = new Formatter();
        formatter.format("|%-10s|%-15s|%-15s|%-12s|%-15s|%-5d|%n",
                resume.getName(),
                resume.getSurname(),
                resume.getBpl(),
                resume.getEducation(),
                resume.getPreviousEmployment(),
                resume.getExperience());
        return String.valueOf(formatter);
    }

    public boolean submit(Resume resume) {
        if(resume == null) return false;
        if (Storage.getResumesSet().contains(resume)) {
            return false;
        }
        Storage.getResumesSet().add(resume);
        return true;
    }

    @Override
    public String getFullReport() {
        String title = String.format("|%-10s|%-15s|%-15s|%-12s|%-15s|%-5s|%n",
                "Имя",
                "Фамилия",
                "Город",
                "Образование",
                "Работа",
                "Стаж");
        StringBuilder report = new StringBuilder(title);
        ResumeAction resumeAction = new ResumeAction();
        for (Resume resume : Storage.getResumes().getCollection()) {
            report.append(resumeAction.getReportInfo(resume));
        }
        return String.valueOf(report);
    }

    @Override
    public void delete(Resume resume) {
        Storage.getResumesSet().remove(resume);
    }

    public User findUser(Resume resume){
        Set<Map.Entry<Account, User>> set =  Storage.getUsersMap().entrySet();
        for (Map.Entry<Account, User> entry: set) {
            User user = entry.getValue();
            if(user.getName().equals(resume.getName()) &&
            user.getSurname().equals(resume.getSurname())){
                return  user;
            }
        }
        return null;
    }
}
