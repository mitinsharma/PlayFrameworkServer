package services;

import com.fdflib.service.impl.FdfCommonServices;
import models.Assignment;

/**
 * Created by Mitin on 10/26/2016.
 */
public class AssignmentService extends FdfCommonServices{

    public Assignment saveAssignment(Assignment newAssignment)
    {
        save(Assignment.class,newAssignment);
        return newAssignment;
    }
}
