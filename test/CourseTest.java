/**
 * Created by Dominic Rossillo on 11/15/2016.
 */
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
      //  String resCourse = this.get("http://localhost:9000/getCourseByName/" + courseName);
        //System.out.println("testing!!! " + resCourse);
    }

//    @Test
//    public void testSaveUser() {
//        User user = new User();
//        user.email = "my@email.com";
//        user.firstName = "Bob";
//        user.lastName = "Johes";
//        user.username = "user";
//
//        System.out.println(Json.toJson(user).toString());
//
//        this.post("http://localhost:9000/saveUser", Json.toJson(user).toString());
//
//    }
//




}
