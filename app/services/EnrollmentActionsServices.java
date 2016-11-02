package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.EnrollmentActions;

import java.util.List;

/**
 * Created by Mitin on 10/26/2016.
 */
public class EnrollmentActionsServices extends FdfCommonServices {


    //having doubts - need AND condition

    public EnrollmentActions saveEAS(EnrollmentActions newEas)
    {
        save(EnrollmentActions.class, newEas);
        return newEas;
    }

    //Load all enrollments

    public List<EnrollmentActions> getAllEnrollments(){
        return this.getAllCurrent(EnrollmentActions.class);

    }

    public List<FdfEntity<EnrollmentActions>> getAllEnrollmentsWithHistory() {
        return this.getAll(EnrollmentActions.class);
    }

    //Get Enrollment by userId
    public EnrollmentActions getEnrollmentByuserId(String uid){
        List <FdfEntity<EnrollmentActions>> tarEnroll= getEntitiesByValueForPassedField(EnrollmentActions.class, "userId", uid);
        return tarEnroll.get(0).current;

    }

    //get user by id
    public EnrollmentActions getUserById(Long id){
        return getEntityCurrentById(EnrollmentActions.class,id);
    }


    public void deleteEnrollmentActions(Long id) { setDeleteFlag(EnrollmentActions.class,id,-1,-1); }
    public void undeleteEnrollmentActions(Long id){
        removeDeleteFlag(EnrollmentActions.class,id,-1,-1);
    }


    public void saveUserForSections()
    {

    }

}
