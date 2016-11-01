package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.Course;

import java.util.List;

/**
 * Created by Dominic Rossillo on 10/24/2016.
 */
public class CourseServices extends FdfCommonServices{

    //save for Course model
    public Course saveCourse(Course course){
        if(getEntitiesByValueForPassedField(Course.class, "username", course.name).size()<1){
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

    //get all courses with history
    public List<FdfEntity<Course>> getAllCoursesWithHistory() {
        return this.getAll(Course.class);
    }

    //get course by name
    public Course getCourseByName(String name){
        List <FdfEntity<Course>> tarUser= getEntitiesByValueForPassedField(Course.class, "name", name);
        return tarUser.get(0).current;

    }
    //get course by id
    public Course getUserByUserId(Long id){
        return getEntityCurrentById(Course.class,id);
    }


    //delete course
    public void deleteCourse(Long id){
        setDeleteFlag(Course.class,id,-1,-1);
    //undelete course
    }
    public void undeleteCourse(Long id){
        removeDeleteFlag(Course.class,id,-1,-1);
    }



}
