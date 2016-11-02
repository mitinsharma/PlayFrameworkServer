package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.Assignment;
import models.AssignmentPost;
import models.Post;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Mitin on 10/26/2016.
 */
public class AssignmentService extends FdfCommonServices{

    public Assignment saveAssignment(Assignment newAssignment)
    {
        save(Assignment.class,newAssignment);
        return newAssignment;
    }

    public void saveAssignmentForPost(long assignmentId, List<Post> posts) {
        for(Post post : posts) {
            AssignmentPost assignPost = new AssignmentPost(assignmentId, post.id);
            List<FdfEntity<AssignmentPost>> savedAssignmentPosts;
            savedAssignmentPosts = getAssignmentPostByIds(assignmentId, post.id);
            if(!savedAssignmentPosts.isEmpty() && savedAssignmentPosts.get(0).current != null &&
                    savedAssignmentPosts.get(0).current.assignmentId == assignmentId &&
                    savedAssignmentPosts.get(0).current.postId == post.id) {
                assignPost.id = savedAssignmentPosts.get(0).current.id;
            }
            save(AssignmentPost.class, assignPost);
        }
    }

    public List<FdfEntity<AssignmentPost>> getAssignmentPostByIds(long sectionId, long postId) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("assignmentId", Long.toString(sectionId));
        fieldsAndValues.put("postId", Long.toString(postId));
        return getEntitiesByValuesForPassedFields(AssignmentPost.class, fieldsAndValues);
    }

    public void deleteAssignmentPost(AssignmentPost assignPost) {
        setDeleteFlag(AssignmentPost.class, assignPost.id, -1, -1);
    }

    public void undeleteAssignmentPost(Assignment assignPost) {
        removeDeleteFlag(AssignmentPost.class, assignPost.id, -1, -1);
    }


}
