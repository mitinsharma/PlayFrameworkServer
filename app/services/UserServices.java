package services;

import com.fdflib.service.impl.FdfCommonServices;
import models.User;

/**
 * Created by Mitin on 10/24/2016.
 */
public class UserServices extends FdfCommonServices{
    public User saveUser(User newUser){

        save(User.class, newUser);
        return newUser;
    }


}
