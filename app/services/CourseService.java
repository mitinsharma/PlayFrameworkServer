package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.Course;
import models.CourseSection;
import models.Section;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Dominic Rossillo on 10/24/2016.
 */
public class CourseService extends FdfCommonServices{

    public Course saveCourse(Course course) {
        FdfEntity<Course> existingCourse = getCourseByNameAndNumber(course.name, course.code);
        if(existingCourse != null){
            course.id = existingCourse.current.id;
        }
        save(Course.class, course);
        return course;
    }

    public List<Course> getAllCourses() { return getAllCurrent(Course.class); }

    public List<FdfEntity<Course>> getAllCoursesWithHistory() { return getAll(Course.class); }

    public Course getCourseByName(String name) {
        List <FdfEntity<Course>> tarUser = getEntitiesByValueForPassedField(Course.class, "name", name);
        return tarUser.get(0).current;
    }

    public FdfEntity<Course> getCourseByNameAndNumber(String name, int courseCode) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("name", name);
        fieldsAndValues.put("code", Integer.toString(courseCode));
        List<FdfEntity<Course>> course = getEntitiesByValuesForPassedFields(Course.class, fieldsAndValues);
        if(course.isEmpty()) return null;
        return course.get(0);
    }

    public Course getCourseById(Long id) { return getEntityCurrentById(Course.class, id); }

    public void deleteCourse(Long id) { setDeleteFlag(Course.class,id,-1,-1); }
    public void undeleteCourse(Long id) { removeDeleteFlag(Course.class,id,-1,-1); }

    /**
     * CourseSection services
     */
    public void saveSectionsForCourse(long courseId, List<Section> sections) {
        for(Section section : sections) {
            CourseSection courseSection = new CourseSection(courseId, section.id);
            List<FdfEntity<CourseSection>> savedCourseSections = getCourseSectionByIds(courseId, section.id);
            if(!savedCourseSections.isEmpty() && savedCourseSections.get(0).current != null &&
                    savedCourseSections.get(0).current.courseId == courseId &&
                    savedCourseSections.get(0).current.sectionId == section.id) {
                courseSection.id = savedCourseSections.get(0).current.id;
            }
            save(CourseSection.class, courseSection);
        }
    }

    public void saveCourseSection(long courseId, long sectionId) {
        CourseSection courseSection = new CourseSection(courseId, sectionId);
        List<FdfEntity<CourseSection>> savedCourseSections = getCourseSectionByIds(courseSection.courseId, courseSection.sectionId);
        if(!savedCourseSections.isEmpty() && savedCourseSections.get(0).current != null &&
                savedCourseSections.get(0).current.courseId == courseSection.courseId &&
                savedCourseSections.get(0).current.sectionId == courseSection.sectionId) {
            courseSection.id = savedCourseSections.get(0).current.id;
        }
        save(CourseSection.class, courseSection);
    }

    public List<FdfEntity<CourseSection>> getCourseSectionByIds(long courseId, long sectionId) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("courseId", Long.toString(courseId));
        fieldsAndValues.put("sectionId", Long.toString(sectionId));
        return getEntitiesByValuesForPassedFields(CourseSection.class, fieldsAndValues);
    }

    public void deleteCourseSection(CourseSection courseSection) {
        setDeleteFlag(CourseSection.class, courseSection.id, -1, -1);
    }

    public void undeleteCourseSection(CourseSection courseSection) {
        removeDeleteFlag(CourseSection.class, courseSection.id, -1, -1);
    }
}
