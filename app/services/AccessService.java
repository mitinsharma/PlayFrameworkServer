package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.AccessLevel;
import models.User;
import models.UserAccess;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tsmanner on 10/22/2016.
 */
@Singleton
public class AccessService extends FdfCommonServices {
    private static AccessService sInstance;

    public static AccessService getInstance() {
        if(sInstance == null) sInstance = new AccessService();
        return sInstance;
    }

    public UserAccess saveUserAccess(UserAccess userAccess) {
        List<FdfEntity<UserAccess>> savedUserAccesses = getUserAccessByIds(userAccess.userId, userAccess.elementId, userAccess.accessLevel);
        if(!savedUserAccesses.isEmpty() && savedUserAccesses.get(0).current != null &&
                savedUserAccesses.get(0).current.userId == userAccess.userId &&
                savedUserAccesses.get(0).current.elementId == userAccess.elementId &&
                savedUserAccesses.get(0).current.accessLevel == userAccess.accessLevel) {
            userAccess.id = savedUserAccesses.get(0).current.id;
        }
        save(UserAccess.class, userAccess);
        return userAccess;
    }

    public List<FdfEntity<UserAccess>> getUserAccessByIds(long userId, long elementId, AccessLevel accessLevel) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("userId", Long.toString(userId));
        fieldsAndValues.put("elementId", Long.toString(elementId));
        fieldsAndValues.put("accessLevel", accessLevel.name());
        return getEntitiesByValuesForPassedFields(UserAccess.class, fieldsAndValues);
    }

    public List<FdfEntity<UserAccess>> getUserAccessByUserAndElement(long userId, long elementId) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("userId", Long.toString(userId));
        fieldsAndValues.put("elementId", Long.toString(elementId));
        return getEntitiesByValuesForPassedFields(UserAccess.class, fieldsAndValues);
    }

    public boolean isAuthorized(long lmsElementId, long userId, EnumSet<AccessLevel> requiredAccessLevel) {
        List<FdfEntity<UserAccess>> entities = getUserAccessByUserAndElement(userId, lmsElementId);
        for(FdfEntity<UserAccess> entity : entities) {
            if(requiredAccessLevel.contains(entity.current.accessLevel)) {
                return true;
            }
        }
        return false;
    }
}
