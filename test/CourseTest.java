/**
 * Created by Dominic Rossillo on 11/15/2016.
 */

import models.Course;
import models.CourseSection;
import models.Section;
import org.junit.Test;
import play.libs.Json;


/**
 * Created by Dominic Rossillo on 11/15/16.
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
    //Test for sections will be here to keep the course and section tests together for future
    //reference
    @Test
    public void testGetAllSections(){

        String resSection = this.get("http://localhost:9000/getAllSections");
        System.out.println("testing!!! " + resSection);
    }

    @Test
    public void testGetAllSectionsWithHistory(){

        String resSection = this.get("http://localhost:9000/getAllSectionsWithHistory");
        System.out.println("testing!!! " + resSection);
    }

    @Test
    public void testGetSectionByNumber() {
        int  sectionNumber = 1;
        String resSection = this.get("http://localhost:9000/getSectionByNumber/" + sectionNumber);
        System.out.println("testing!!! " + resSection);
    }

    @Test
    public void testGetSectionByCourseName() {
        String  courseName = "Intro to Programming";
        String resCourseSection = this.get("http://localhost:9000/getSectionByCourseName/" + courseName);
        System.out.println("testing!!! " + resCourseSection);
    }

    @Test
    public void testGetSectionById() {
        int  sectionId = 1;
        String resSection = this.get("http://localhost:9000/getSectionById/" + sectionId);
        System.out.println("testing!!! " + resSection);
    }

    @Test
    public void testGetSectionByNameAndNumber(){
        String  sectionName = "TestSection";
        int  sectionNumber= 1;
        String resCourse = this.get("http://localhost:9000/getSectionByNameAndNumber/" + sectionName+"/"+ sectionNumber);
        System.out.println("testing!!! " + resCourse);
    }
    @Test
    public void testSaveSection() {
        Section section = new Section();
        section.name = "New Test Section";
        section.sectionNumber = 202;
        System.out.println(Json.toJson(section).toString());
        this.post("http://localhost:9000/saveSection", Json.toJson(section).toString());
    }

    @Test
    public void testSaveSectionForCourse() {
        int courseId= 1;
        int sectionId= 2;
        CourseSection courseSection = new CourseSection();
        courseSection.courseId = courseId;
        courseSection.sectionId= sectionId;

        System.out.println(Json.toJson(courseSection).toString());
        this.post("http://localhost:9000/saveSectionForCourse/"+courseId+"/"+sectionId, Json.toJson(courseSection).toString());
    }



}
