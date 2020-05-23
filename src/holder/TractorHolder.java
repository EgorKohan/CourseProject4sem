package holder;

import action.TractorAction;
import entity.Tractor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TractorHolder extends Holder<List<Tractor>> {

    public TractorHolder() {
        collection = new ArrayList<>();
    }

    @Override
    public List<Tractor> getCollection() {
        return super.getCollection();
    }

    @Override
    public void readFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            collection = (List<Tractor>) ois.readObject();
        } catch (IOException e) {
            System.out.println("В файле отстуствуют " + fileName + " записи");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generifyReport() {
        TractorAction tractorAction = new TractorAction();
        String report = tractorAction.getFullReport();
        File file = new File("TractorsReport.txt");
        try {
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            } else file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("TractorsReport.txt"))) {
            bw.write(report.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
