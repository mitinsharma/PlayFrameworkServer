package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by tsmanner on 11/1/2016.
 */
public class DepartmentCourse extends CommonState {
    public DepartmentCourse() { super(); }
    public DepartmentCourse(long inDepartmentId, long inCourseId) {
        super();
        departmentId = inDepartmentId;
        courseId = inCourseId;
    }
    public long departmentId;
    public long courseId;
}
