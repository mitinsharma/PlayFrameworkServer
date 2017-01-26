package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.AccessLevel;
import models.EnrollmentAction;
import models.EnrollmentType;
import org.joda.time.DateTime;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mitin on 10/26/2016.
 */
public class EnrollmentActionService extends FdfCommonServices {
    public EnrollmentAction saveEnrollmentAction(EnrollmentAction enrollmentAction) {
        save(EnrollmentAction.class, enrollmentAction);
        return enrollmentAction;
    }

    public boolean enrollSelf(long userId, long sectionId) {
        EnrollmentAction enrollmentAction = new EnrollmentAction(userId, sectionId, new DateTime(), EnrollmentType.ADD);
        List<FdfEntity<EnrollmentAction>> enrollments = getAllEnrollmentsByUserAndSectionId(userId, sectionId);
        if (!enrollments.isEmpty()) {
            Collections.sort(enrollments, (a, b) -> a.current.dateTime.compareTo(b.current.dateTime));
            Collections.reverse(enrollments);
            // If the last action that we took was to ADD this class, do nothing
            if (enrollments.get(0).current.type.equals(EnrollmentType.ADD)) {
                return false;
            }
        }
        saveEnrollmentAction(enrollmentAction);
        return true;
    }

    public boolean enrollOther(long myId, long userId, long sectionId) {
        if (myId == userId) return enrollSelf(userId, sectionId);
        return AccessService.getInstance().isAuthorized(sectionId, myId, EnumSet.of(AccessLevel.ADMINISTRATOR)) &&
                enrollSelf(userId, sectionId);
    }

    public boolean disenrollSelf(long userId, long sectionId) {
        EnrollmentAction enrollmentAction = new EnrollmentAction(userId, sectionId, new DateTime(), EnrollmentType.DROP);
        List<FdfEntity<EnrollmentAction>> enrollments = getAllEnrollmentsByUserAndSectionId(userId, sectionId);
        if (!enrollments.isEmpty()) {
            Collections.sort(enrollments, (a, b) -> a.current.dateTime.compareTo(b.current.dateTime));
            Collections.reverse(enrollments);
            // If the last action that we took was to DROP this class, do nothing
            if (enrollments.get(0).current.type.equals(EnrollmentType.DROP)) {
                return false;
            }
        }
        saveEnrollmentAction(enrollmentAction);
        return true;
    }

    public boolean disenrollOther(long myId, long userId, long sectionId) {
        if(myId == userId) return disenrollSelf(userId, sectionId);
        if(AccessService.getInstance().isAuthorized(sectionId, myId, EnumSet.of(AccessLevel.ADMINISTRATOR))) {
            return disenrollSelf(userId, sectionId);
        }
        return false;
    }

    public List<EnrollmentAction> getAllEnrollments() { return getAllCurrent(EnrollmentAction.class); }

    public List<FdfEntity<EnrollmentAction>> getAllEnrollmentsWithHistory() { return getAll(EnrollmentAction.class); }

    public List<FdfEntity<EnrollmentAction>> getAllEnrollmentsByUserId(long userId){
        return getEntitiesByValueForPassedField(EnrollmentAction.class, "userId", Long.toString(userId));
    }

    public List<FdfEntity<EnrollmentAction>> getAllEnrollmentsBySectionId(long sectionId){
        return getEntitiesByValueForPassedField(EnrollmentAction.class, "sectionId", Long.toString(sectionId));
    }

    public List<FdfEntity<EnrollmentAction>> getAllEnrollmentsByUserAndSectionId(long userId, long sectionId){
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("userId", Long.toString(userId));
        fieldsAndValues.put("sectionId", Long.toString(sectionId));
        return getEntitiesByValuesForPassedFields(EnrollmentAction.class, fieldsAndValues);
    }

    public void deleteEnrollmentActions(Long id) { setDeleteFlag(EnrollmentAction.class, id, -1, -1); }
    public void undeleteEnrollmentActions(Long id) { removeDeleteFlag(EnrollmentAction.class, id, -1, -1); }
}
