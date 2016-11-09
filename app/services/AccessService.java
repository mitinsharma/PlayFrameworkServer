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
        save(UserAccess.class, userAccess);
        return userAccess;
    }

    public boolean isAuthorized(long lmsElementId, long userId, AccessLevel requiredAccessLevel) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("userId", Long.toString(userId));
        fieldsAndValues.put("elementId", Long.toString(lmsElementId));
        fieldsAndValues.put("accessLevel", requiredAccessLevel.name());
        List<FdfEntity<UserAccess>> entities = getEntitiesByValuesForPassedFields(UserAccess.class, fieldsAndValues);
        if(entities.size() > 0 && entities.get(0).current != null) return true;
        return false;
    }
}
