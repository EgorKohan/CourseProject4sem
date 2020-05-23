package holder;

import action.UserAction;
import entity.Account;
import entity.User;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserHolder extends Holder<Map<Account, User>> {

    public UserHolder() {
        collection = new HashMap<>();
    }

    @Override
    public Map<Account, User> getCollection() {
        return super.getCollection();
    }

    public User findUser(Account account) {
        return collection.get(account);
    }

    @Override
    public void readFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Map<Account, User> readFromFileMap = (Map<Account, User>) ois.readObject();
            collection.putAll(readFromFileMap);
        } catch (IOException e) {
            System.out.println("В файле отстуствуют " + fileName + " записи");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generifyReport() {
        UserAction userAction = new UserAction();
        String report = userAction.getFullReport();
        File file = new File("UsersReport.txt");
        try {
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            } else file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("UsersReport.txt"))){
            bw.write(report.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<User> getUserSet(){
        Set<User> set = new HashSet<>();
        for (Map.Entry<Account, User> entry : getCollection().entrySet()) {
            set.add(entry.getValue());
        }
        return set;
    }
}
