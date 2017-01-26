package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.LMS;

import java.util.List;

/**
 * Created by tsmanner on 10/22/2016.
 */
public class LmsService extends FdfCommonServices {
    public LMS saveLms(LMS lms) {
        // First thing first, if this guy's already in there, just do an update instead of a new add.
        List<FdfEntity<LMS>> lmsList = getLmsByName(lms.name);
        if(!lmsList.isEmpty() &&
                lmsList.get(0).current != null &&
                lmsList.get(0).current.name.equals(lms.name)) {
            lms.id = lmsList.get(0).current.id;
        }
        save(LMS.class, lms);
        return lms;
    }

    public List<FdfEntity<LMS>> getLmsByName(String name) {
        return getEntitiesByValueForPassedField(LMS.class, "name", name);
    }
}
