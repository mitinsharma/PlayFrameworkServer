package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by tsmanner on 11/1/2016.
 */
public class CourseSection extends CommonState {
    public CourseSection() { super(); }
    public CourseSection(long inCourseId, long inSectionId) {
        super();
        courseId = inCourseId;
        sectionId = inSectionId;
    }
    public long courseId;
    public long sectionId;
}
