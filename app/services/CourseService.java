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

    //save for Course model
    public Course saveCourse(Course course){
        if(getEntitiesByValueForPassedField(Course.class, "name", course.name).size()<1){
            save(Course.class,course);
        }
        else {
            course.id=getEntitiesByValueForPassedField(Course.class, "name", course.name).get(0).entityId;
            course.cf=true;
            save(Course.class, course);
        }
        return course;
    }

    //load all current for Course
    public List<Course> getAllCourses(){
        return this.getAllCurrent(Course.class);

    }

    public List<FdfEntity<Course>> getAllCoursesWithHistory() {
        return this.getAll(Course.class);
    }

    public Course getCourseByName(String name){
        List <FdfEntity<Course>> tarUser= getEntitiesByValueForPassedField(Course.class, "name", name);
        return tarUser.get(0).current;

    }

    //get course by id
    public Course getCourseByUserId(Long id){
        return getEntityCurrentById(Course.class,id);
    }

    public void deleteCourse(Long id) { setDeleteFlag(Course.class,id,-1,-1); }
    public void undeleteCourse(Long id){
        removeDeleteFlag(Course.class,id,-1,-1);
    }

    /**
     * CourseSection services
     */
    public void saveSectionsForCourse(long courseId, List<Section> sections) {
        for(Section section : sections) {
            CourseSection courseSection = new CourseSection(courseId, section.id);
            List<FdfEntity<CourseSection>> savedCourseSections;
            savedCourseSections = getCourseSectionByIds(courseId, section.id);
            if(!savedCourseSections.isEmpty() && savedCourseSections.get(0).current != null &&
                    savedCourseSections.get(0).current.courseId == courseId &&
                    savedCourseSections.get(0).current.sectionId == section.id) {
                courseSection.id = savedCourseSections.get(0).current.id;
            }
            save(CourseSection.class, courseSection);
        }
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
