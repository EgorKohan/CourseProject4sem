package entity;

import java.io.Serializable;
import java.util.Objects;

public class Resume implements Serializable {
    private final String name;
    private final String surname;
    private String bpl;
    private int experience;
    private String previousEmployment;
    private Education education;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public  enum Education{
        HIGH, MIDDLE
    }

    public Resume(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getBpl() {
        return bpl;
    }

    public void setBpl(String bpl) {
        this.bpl = bpl;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getPreviousEmployment() {
        return previousEmployment;
    }

    public void setPreviousEmployment(String previousEmployment) {
        this.previousEmployment = previousEmployment;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", bpl='" + bpl + '\'' +
                ", experience=" + experience +
                ", previousEmployment='" + previousEmployment + '\'' +
                ", education=" + education +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(name, resume.name) &&
                Objects.equals(surname, resume.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
