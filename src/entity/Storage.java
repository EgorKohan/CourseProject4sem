package entity;

import holder.ResumeHolder;
import holder.TractorHolder;
import holder.UserHolder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Storage implements Serializable {
    private static UserHolder users = new UserHolder();
    private static ResumeHolder resumes = new ResumeHolder();
    private static TractorHolder tractors = new TractorHolder();

    private static final String usersFileName = "Users.txt";
    private static final String tractorsFileName = "Tractors.txt";
    private static final String resumesFileName = "Resumes.txt";

    public void readAllFromFile() {
        users.readFromFile(usersFileName);
        tractors.readFromFile(tractorsFileName);
        resumes.readFromFile(resumesFileName);
    }

    public void writeAllToFile() {
        users.writeToFile(usersFileName);
        tractors.writeToFile(tractorsFileName);
        resumes.writeToFile(resumesFileName);
    }

    public static UserHolder getUsers() {
        return users;
    }

    public static ResumeHolder getResumes() {
        return resumes;
    }

    public static TractorHolder getTractors() {
        return tractors;
    }



    public static Map<Account, User> getUsersMap() {
        return users.getCollection();
    }

    public static List<Tractor> getTractorsList() {
        return tractors.getCollection();
    }

    public static Set<Resume> getResumesSet() {
        return resumes.getCollection();
    }


    public static void setUsers(UserHolder users) {
        Storage.users = users;
    }

    public static void setResumes(ResumeHolder resumes) {
        Storage.resumes = resumes;
    }

    public static void setTractors(TractorHolder tractors) {
        Storage.tractors = tractors;
    }
}

