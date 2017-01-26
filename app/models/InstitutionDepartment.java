package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by tsmanner on 11/1/2016.
 */
public class InstitutionDepartment extends CommonState {
    public InstitutionDepartment() { super(); }
    public InstitutionDepartment(long inInstitutionId, long inDepartmentId) {
        super();
        institutionId = inInstitutionId;
        departmentId = inDepartmentId;
    }
    public long institutionId;
    public long departmentId;
}
