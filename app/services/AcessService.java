package services;

import com.fdflib.service.impl.FdfCommonServices;
import models.UserAccess;

/**
 * Created by tsmanner on 10/22/2016.
 */
public class AcessService extends FdfCommonServices {
    public UserAccess saveUserAccess(UserAccess userAccess) {
        save(UserAccess.class, userAccess);
        return userAccess;
    }
}
