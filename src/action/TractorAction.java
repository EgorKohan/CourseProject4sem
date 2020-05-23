package action;

import Valid.ValidInput;
import entity.Storage;
import entity.Tractor;

import java.util.Formatter;

import static entity.Tractor.*;

public class TractorAction implements IDataManagement<Tractor> {


    @Override
    public String getInfo(Tractor tractor) {
        String info = "";
        info += "Модель трактора: " + tractor.getName() + '\n';
        info += "Тип движителя: " + tractor.getPropType() + '\n';
        info += "Назначение: " + tractor.getDestType() + '\n';
        return info;
    }

    @Override
    public void redact(Tractor obj) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Tractor create() {
        System.out.print("Введите название трактора: ");
        String name = ValidInput.inputString("[\\w[А-я[-]]]+");
        System.out.print("Выберите тип движителя:" +
                "\n1 - Колесный" +
                "\n2 - Гусеничный" +
                "\nВыберите: ");
        int prop = ValidInput.chooseAnAnswer(1, 2);
        System.out.print("Выберите назначение трактора:" +
                "\n1 - Сельскохозяйственный" +
                "\n2 - Промышленный" +
                "\n3 - Армейский" +
                "\nВыберите: ");
        int dest = ValidInput.chooseAnAnswer(1, 3);
        PropulsionType propulsionType = (prop == 1) ? PropulsionType.WHEELED : PropulsionType.CRAWLER;
        DestinationType destinationType;
        if(dest == 1) destinationType = DestinationType.AGRICULTURAL;
        else if(dest == 2) destinationType = DestinationType.INDUSTRIAL;
        else destinationType = DestinationType.ARMY;
        return new Tractor(name, propulsionType, destinationType);
    }

    @Override
    public String getReportInfo(Tractor tractor) {
        Formatter formatter = new Formatter();
        formatter.format("|%-15s|%-15s|%-15s|%n",
                tractor.getName(),
                tractor.getPropType(),
                tractor.getDestType());
        return String.valueOf(formatter);
    }

    @Override
    public String getFullReport() {
        String title = String.format("|%-15s|%-15s|%-15s|%n",
                "Название",
                "Тип движителя",
                "Назначение");
        StringBuilder report = new StringBuilder(title);
        TractorAction tractorAction = new TractorAction();
        for (Tractor tractor : Storage.getTractors().getCollection()) {
            report.append(tractorAction.getReportInfo(tractor));
        }
        return String.valueOf(report);
    }

    @Override
    public void delete(Tractor tractor) {
        Storage.getTractorsList().remove(tractor);
    }
}
