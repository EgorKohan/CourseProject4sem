package menu;

import entity.User;

public class MainMenu {
    private AuthorizationMenu authorizationMenu = new AuthorizationMenu();
    private UserMenu userMenu = new UserMenu();

    public void launch() {
       try{
           while(true) {
               User user = authorizationMenu.launch();
               if (user != null) {
                   userMenu.launch(user);
               }
           }
        }catch (RuntimeException e){
           System.out.println("Good bue");
       }
    }
}
