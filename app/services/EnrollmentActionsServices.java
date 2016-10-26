package services;

import com.fdflib.service.impl.FdfCommonServices;
import models.EnrollmentActions;

/**
 * Created by Mitin on 10/26/2016.
 */
public class EnrollmentActionsServices extends FdfCommonServices {

    public EnrollmentActions saveEAS(EnrollmentActions newEas)
    {
        save(EnrollmentActions.class, newEas);
        return newEas;
    }
}
