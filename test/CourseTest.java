/**
 * Created by Dominic Rossillo on 11/15/2016.
 */

import models.Course;
import play.libs.Json;
import org.junit.Test;


/**
 * Created by brian.gormanly on 11/9/16.
 */


public class CourseTest extends GenericTest {
    @Test
    public void testGetAllCourses () {
        String resCourse = this.get("http://localhost:9000/getAllCourses");
        System.out.println(resCourse);
    }

    @Test
    public void testGetCourseByCourseName() {
        String  courseName = "Intro to Programming";
        String resCourse = this.get("http://localhost:9000/getCourseByName/" + courseName);
        System.out.println("testing!!! " + resCourse);
    }

    @Test
    public void testSaveCourse() {
        Course course = new Course();
        course.name = "Software Development 1";
        course.code = 202;
        System.out.println(Json.toJson(course).toString());
        this.post("http://localhost:9000/saveCourse", Json.toJson(course).toString());
    }

    @Test
    public void testGetCourseSectionByIds(){
        int  courseId = 1;
        int  sectionId= 1;
        String resCourse = this.get("http://localhost:9000/getCourseSectionByIds/" + courseId+"/"+ sectionId);
        System.out.println("testing!!! " + resCourse);
    }
}
