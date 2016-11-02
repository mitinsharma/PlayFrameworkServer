package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.EnrollmentAction;

import java.util.List;

/**
 * Created by Mitin on 10/26/2016.
 */
public class EnrollmentActionService extends FdfCommonServices {


    //having doubts - need AND condition

    public EnrollmentAction saveEAS(EnrollmentAction newEas)
    {
        save(EnrollmentAction.class, newEas);
        return newEas;
    }

    //Load all enrollments

    public List<EnrollmentAction> getAllEnrollments(){
        return this.getAllCurrent(EnrollmentAction.class);

    }

    public List<FdfEntity<EnrollmentAction>> getAllEnrollmentsWithHistory() {
        return this.getAll(EnrollmentAction.class);
    }

    //Get Enrollment by userId
    public EnrollmentAction getEnrollmentByuserId(String uid){
        List <FdfEntity<EnrollmentAction>> tarEnroll= getEntitiesByValueForPassedField(EnrollmentAction.class, "userId", uid);
        return tarEnroll.get(0).current;

    }

    //get user by id
    public EnrollmentAction getUserById(Long id){
        return getEntityCurrentById(EnrollmentAction.class,id);
    }


    public void deleteEnrollmentActions(Long id) { setDeleteFlag(EnrollmentAction.class,id,-1,-1); }
    public void undeleteEnrollmentActions(Long id){
        removeDeleteFlag(EnrollmentAction.class,id,-1,-1);
    }


    public void saveUserForSections()
    {

    }

}
