package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by tsmanner on 11/2/2016.
 */
public class DepartmentService extends FdfCommonServices {
    public Department saveDepartment(Department department) {
        // First thing first, if this guy's already in there, just do an update instead of a new add.
        List<FdfEntity<Department>> departmentList = getDepartmentByName(department.name);
        if(!departmentList.isEmpty() &&
                departmentList.get(0).current != null &&
                departmentList.get(0).current.name.equals(department.name)) {
            department.id = departmentList.get(0).current.id;
        }
        save(Department.class, department);
        return department;
    }

    public List<FdfEntity<Department>> getDepartmentByName(String name) {
        return getEntitiesByValueForPassedField(Department.class, "name", name);
    }

    public void saveCoursesForDepartment(long departmentId, List<Course> courses) {
        for(Course course : courses) {
            DepartmentCourse departmentCourse = new DepartmentCourse(departmentId, course.id);
            List<FdfEntity<DepartmentCourse>> savedDepartmentCourses;
            savedDepartmentCourses = getDepartmentCourseByIds(departmentId, course.id);
            if(!savedDepartmentCourses.isEmpty() && savedDepartmentCourses.get(0).current != null &&
                    savedDepartmentCourses.get(0).current.departmentId == departmentId &&
                    savedDepartmentCourses.get(0).current.courseId == course.id) {
                departmentCourse.id = savedDepartmentCourses.get(0).current.id;
            }
            save(DepartmentCourse.class, departmentCourse);
        }
    }

    public List<FdfEntity<DepartmentCourse>> getDepartmentCourseByIds(long departmentId, long courseId) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("departmentId", Long.toString(departmentId));
        fieldsAndValues.put("courseId", Long.toString(courseId));
        return getEntitiesByValuesForPassedFields(DepartmentCourse.class, fieldsAndValues);
    }

    public void deleteInstitutionDepartment(DepartmentCourse departmentCourse) {
        setDeleteFlag(DepartmentCourse.class, departmentCourse.id, -1, -1);
    }

    public void undeleteInstitutionDepartment(DepartmentCourse departmentCourse) {
        removeDeleteFlag(DepartmentCourse.class, departmentCourse.id, -1, -1);
    }
}
