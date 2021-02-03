package com.android.restaurantsenti;



import com.android.restaurantsenti.model.User;

import java.util.List;

public class Validation {

    public Validation() {
    }

    public boolean loginCheckUser(List<User> usersList, User currentUser){
        for(User user : usersList){
            if(currentUser.getEmail().compareTo(user.getEmail()) == 0 && currentUser.getPassword().compareTo(user.getPassword()) == 0){
                return true;
            }
        }
        return false;
    }
    public boolean signUpCheckUser(List<User> usersList, User currentUser) {
        for (User user : usersList) {
            if (currentUser.getEmail().compareTo(user.getEmail()) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean validateEmail(String email){
        String regexEmail = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}";
        return email.matches(regexEmail);
    }

    public boolean validatePasswordLength(User currentUser){
        return currentUser.getPassword().length() < 8;
    }

    public boolean loginCheckEmpty(User currentUser){
        return currentUser.getEmail().isEmpty() && currentUser.getPassword().isEmpty();
    }
    public boolean signUpCheckEmpty(User currentUser){
        return currentUser.getEmail().isEmpty() && currentUser.getPassword().isEmpty() && currentUser.getFullName().isEmpty() && currentUser.getUserName().isEmpty();
    }
}
