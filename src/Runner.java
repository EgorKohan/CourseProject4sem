import entity.*;
import holder.UserHolder;

public class Runner {
    public static void main(String[] args) {
        User user = new User("Админ", "Админ", "Админович", 18);
        user.setType(TypeOfUser.ADMIN);
        Storage.getUsersMap().put(new Account("admin", "admin"), user);
        UserHolder userHolder = Storage.getUsers();
        userHolder.writeToFile("Users.txt");
    }
}
