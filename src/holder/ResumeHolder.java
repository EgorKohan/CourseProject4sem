package holder;

import action.ResumeAction;
import entity.Resume;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ResumeHolder extends Holder<Set<Resume>> {

    public ResumeHolder() {
        collection = new HashSet<>();
    }

    @Override
    public Set<Resume> getCollection() {
        return super.getCollection();
    }

    @Override
    public void readFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Set<Resume> readFromFileSet = (Set<Resume>) ois.readObject();
            collection.addAll(readFromFileSet);
        } catch (IOException e) {
            System.out.println("В файле отстуствуют " + fileName + " записи");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generifyReport() {
        ResumeAction resumeAction = new ResumeAction();
        String report = resumeAction.getFullReport();
        File file = new File("ResumesReport.txt");
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ResumesReport.txt"))) {
            bw.write(report.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
