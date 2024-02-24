package iti.data_access_layer;

import iti.models.User;

public interface UserDAO {

    public boolean checkLogin(String userEmail, String userPassword);

    public User findByUserEmail(String userEmail);

}
