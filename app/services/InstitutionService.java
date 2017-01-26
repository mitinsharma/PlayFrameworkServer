package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.Department;
import models.Institution;
import models.InstitutionDepartment;

import java.util.HashMap;
import java.util.List;

/**
 * Created by tsmanner on 11/2/2016.
 */
public class InstitutionService extends FdfCommonServices {
    public Institution saveInstitution(Institution institution) {
        // First thing first, if this guy's already in there, just do an update instead of a new add.
        List<FdfEntity<Institution>> institutionList = getInstitutionByName(institution.name);
        if(!institutionList.isEmpty() &&
                institutionList.get(0).current != null &&
                institutionList.get(0).current.name.equals(institution.name)) {
            institution.id = institutionList.get(0).current.id;
        }
        save(Institution.class, institution);
        return institution;
    }

    public List<FdfEntity<Institution>> getInstitutionByName(String name) {
        return getEntitiesByValueForPassedField(Institution.class, "name", name);
    }

    public void saveDepartmentsForInstitution(long institutionId, List<Department> departments) {
        for(Department department : departments) {
            InstitutionDepartment institutionDepartment = new InstitutionDepartment(institutionId, department.id);
            List<FdfEntity<InstitutionDepartment>> savedInstitutionDepartments;
            savedInstitutionDepartments = getInstitutionDepartmentByIds(institutionId, department.id);
            if(!savedInstitutionDepartments.isEmpty() && savedInstitutionDepartments.get(0).current != null &&
                    savedInstitutionDepartments.get(0).current.institutionId == institutionId &&
                    savedInstitutionDepartments.get(0).current.departmentId == department.id) {
                institutionDepartment.id = savedInstitutionDepartments.get(0).current.id;
            }
            save(InstitutionDepartment.class, institutionDepartment);
        }
    }

    public List<FdfEntity<InstitutionDepartment>> getInstitutionDepartmentByIds(long institutionId, long departmentId) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("institutionId", Long.toString(institutionId));
        fieldsAndValues.put("departmentId", Long.toString(departmentId));
        return getEntitiesByValuesForPassedFields(InstitutionDepartment.class, fieldsAndValues);
    }

    public void deleteInstitutionDepartment(InstitutionDepartment institutionDepartment) {
        setDeleteFlag(InstitutionDepartment.class, institutionDepartment.id, -1, -1);
    }

    public void undeleteInstitutionDepartment(InstitutionDepartment institutionDepartment) {
        removeDeleteFlag(InstitutionDepartment.class, institutionDepartment.id, -1, -1);
    }
}
