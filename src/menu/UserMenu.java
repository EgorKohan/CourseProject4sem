package menu;

import entity.User;

public class UserMenu {

    public void launch(User user) {
        switch (user.getType()) {
            case ADMIN:{
                AdminMenu adminMenu = new AdminMenu();
                adminMenu.launchAdminMenu(user);
                break;
            }
            case EMPLOYEE:{
                System.out.println("Меню рабочего не поддерживается");
                break;
            }
            case CANDIDATE:{
                CandidateMenu candidateMenu = new CandidateMenu();
                candidateMenu.launchCandidateMenu(user);
                break;
            }
            default:
                throw new UnsupportedOperationException();
        }
    }

}
