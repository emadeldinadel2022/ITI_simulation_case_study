package iti.iti_simulation;

import iti.models.User;

public class DataHolder {
    private static User currentUser;

    public static synchronized void setCurrentUser(User currentUser){
        DataHolder.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return DataHolder.currentUser;
    }
}
