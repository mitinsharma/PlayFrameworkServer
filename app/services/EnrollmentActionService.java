package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.EnrollmentAction;

import java.util.List;

/**
 * Created by Mitin on 10/26/2016.
 */
public class EnrollmentActionService extends FdfCommonServices {
    public EnrollmentAction saveEnrollmentAction(EnrollmentAction enrollmentAction) {
        save(EnrollmentAction.class, enrollmentAction);
        return enrollmentAction;
    }

    public List<EnrollmentAction> getAllEnrollments(){
        return this.getAllCurrent(EnrollmentAction.class);
    }

    public List<FdfEntity<EnrollmentAction>> getAllEnrollmentsWithHistory() {
        return this.getAll(EnrollmentAction.class);
    }

    public EnrollmentAction getEnrollmentByUserId(long userId){
        List <FdfEntity<EnrollmentAction>> tarEnroll =
                getEntitiesByValueForPassedField(EnrollmentAction.class, "userId", Long.toString(userId));
        return tarEnroll.get(0).current;
    }

    public void deleteEnrollmentActions(Long id) { setDeleteFlag(EnrollmentAction.class, id, -1, -1); }
    public void undeleteEnrollmentActions(Long id) { removeDeleteFlag(EnrollmentAction.class, id, -1, -1); }

    public void saveEnrollmentActionsForSection(long sectionId, List<EnrollmentAction> enrollmentActions) {
    }

    public void saveEnrollmentActionsForUser(long userId, List<EnrollmentAction> enrollmentActions) {
    }
}
