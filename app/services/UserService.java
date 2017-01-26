package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

/**
 * Created by Mitin on 10/24/2016.
 */
public class UserService extends FdfCommonServices{

    public User saveUser(User newUser){
        if(getEntitiesByValueForPassedField(User.class, "userName", newUser.userName).size()<1){
            save(User.class,newUser);
        }
        else {
            newUser.id=getEntitiesByValueForPassedField(User.class, "userName", newUser.userName).get(0).entityId;
            newUser.cf=true;
            save(User.class, newUser);
        }
        return newUser;
    }


    public List<User> getAllUserss() { return this.getAllCurrent(User.class); }
    public List<FdfEntity<User>> getAllUsersWithHistory() {
        return this.getAll(User.class);
    }

    public List getUserByUserName(String userName){
        return  getEntitiesByValueForPassedField(User.class, "userName", userName);
    }

    public User getUserByUserId(Long id) { return getEntityCurrentById(User.class,id); }
    public void deleteUser(Long id) { setDeleteFlag(User.class,id,-1,-1); }
    public void undeleteUser(Long id) { removeDeleteFlag(User.class,id,-1,-1); }
    private String getPasswordHash(String password) { return DigestUtils.sha1Hex(password); }
}
