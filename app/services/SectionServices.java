package services;

import com.fdflib.service.impl.FdfCommonServices;
import models.Section;

/**
 * Created by Dominic Rossillo on 10/24/2016.
 */
public class SectionServices extends FdfCommonServices {

        public Section enroll(User addedStudent){
        //need miten to do his part to continue
            save(EntrollmentAction.class, addedStudent);
            return newSection;
        }
    public Section drop(User dropStudent){
        //need miten to do his part to continue
        save(EntrollmentAction.class, addedStudent);
        return newSection;
    }
    public Section submitPost(Post post){
        //need miten to do his part to continue
        save(Post.class, post);
        return ;
    }
    public Section getGrade(User user){
        //need miten to do his part to continue

    }


//+ enroll(student:User)
//+ drop(student:User)
//+ submitPost(post:Post)
//+ getGrade(student:User): float

}
