package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.AccessLevel;
import models.Section;
import models.UserAccess;

import java.util.HashMap;
import java.util.List;

/**
 * Created by tsmanner on 10/22/2016.
 */
public class AccessService extends FdfCommonServices {
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

    public boolean isAuthorized(long lmsElementId, long userId, AccessLevel requiredAccessLevel) {
        List<FdfEntity<UserAccess>> entities = getUserAccessByIds(userId, lmsElementId, requiredAccessLevel);
        if(entities.size() > 0 && entities.get(0).current != null) return true;
        return false;
    }
}
