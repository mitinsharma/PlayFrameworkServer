package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.*;

import java.util.HashMap;
import java.util.List;

import static models.AccessLevel.FACULTY;
import static models.AccessLevel.TA;

/**
 * Created by Mitin on 10/26/2016.
 */
public class PostService extends FdfCommonServices {
    /**
     * Posts
     */

    public Post savePost(Post post) {
        save(Post.class, post);
        return post;
    }

    public List<Post> getAllPosts() { return this.getAllCurrent(Post.class); }
    public List<FdfEntity<Post>> getAllPostsWithHistory() { return this.getAll(Post.class); }
    public Post getPostById(Long id) { return getEntityCurrentById(Post.class, id); }
    public void deletePost(Post post) { setDeleteFlag(Post.class, post.id, -1, -1); }
    public void undeletePost(Post post) { removeDeleteFlag(Post.class, post.id, -1, -1); }

    /**
     * Assignments
     */

    public Assignment saveAssignment(Assignment assignment) {
        save(Assignment.class, assignment);
        return assignment;
    }

    public List<Assignment> getAllAssignments() { return this.getAllCurrent(Assignment.class); }
    public List<FdfEntity<Assignment>> getAllAssignmentsWithHistory() { return this.getAll(Assignment.class); }
    public Assignment getAssignmentById(Long id) { return getEntityCurrentById(Assignment.class, id); }
    public void deleteAssignment(Assignment assignment) { setDeleteFlag(Assignment.class, assignment.id, -1, -1); }
    public void undeleteAssignment(Assignment assignment) { removeDeleteFlag(Assignment.class, assignment.id, -1, -1); }

    public boolean gradeAssignment(long userId, long assignmentId, float grade) {
        AccessService accessService = new AccessService();
        SectionService sectionService = new SectionService();
        Assignment assignment = getAssignmentById(assignmentId);
        Section section = sectionService.getSectionById(assignment.sectionId);
        if(section != null){
            if(accessService.isAuthorized(section.id, userId, FACULTY) ||
                    accessService.isAuthorized(section.id, userId, TA)) {
                assignment.score = grade;
                saveAssignment(assignment);
                return true;
            }
        }
        return false;
    }

    /**
     *  SectionPost services
     */
    public void savePostsForSection(long sectionId, List<Post> posts) {
        for(Post post : posts) {
            SectionPost sectionPost = new SectionPost(sectionId, post.id);
            List<FdfEntity<SectionPost>> savedSectionPosts;
            savedSectionPosts = getSectionPostByIds(sectionId, post.id);
            if(!savedSectionPosts.isEmpty() && savedSectionPosts.get(0).current != null &&
                    savedSectionPosts.get(0).current.sectionId == sectionId &&
                    savedSectionPosts.get(0).current.postId == post.id) {
                sectionPost.id = savedSectionPosts.get(0).current.id;
            }
            save(SectionPost.class, sectionPost);
        }
    }

    public void saveAssignmentsForSection(long sectionId, List<Assignment> assignments) {
        for(Post post : assignments) {
            SectionPost sectionPost = new SectionPost(sectionId, post.id);
            List<FdfEntity<SectionPost>> savedSectionPosts;
            savedSectionPosts = getSectionPostByIds(sectionId, post.id);
            if(!savedSectionPosts.isEmpty() && savedSectionPosts.get(0).current != null &&
                    savedSectionPosts.get(0).current.sectionId == sectionId &&
                    savedSectionPosts.get(0).current.postId == post.id) {
                sectionPost.id = savedSectionPosts.get(0).current.id;
            }
            save(SectionPost.class, sectionPost);
        }
    }

    public List<FdfEntity<SectionPost>> getSectionPostByIds(long sectionId, long postId) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("sectionId", Long.toString(sectionId));
        fieldsAndValues.put("postId", Long.toString(postId));
        return getEntitiesByValuesForPassedFields(SectionPost.class, fieldsAndValues);
    }

    public void deleteSectionPost(SectionPost sectionPost) {
        setDeleteFlag(SectionPost.class, sectionPost.id, -1, -1);
    }

    public void undeleteSectionPost(SectionPost sectionPost) {
        removeDeleteFlag(SectionPost.class, sectionPost.id, -1, -1);
    }

    /**
     *  UserPost services
     */
    public void savePostsForUser(long userId, List<Post> posts) {
        for(Post post : posts) {
            UserPost userPost = new UserPost(userId, post.id);
            List<FdfEntity<UserPost>> savedUserPosts;
            savedUserPosts = getUserPostByIds(userId, post.id);
            if(!savedUserPosts.isEmpty() && savedUserPosts.get(0).current != null &&
                    savedUserPosts.get(0).current.userId == userId &&
                    savedUserPosts.get(0).current.postId == post.id) {
                userPost.id = savedUserPosts.get(0).current.id;
            }
            save(UserPost.class, userPost);
        }
    }

    public void saveAssignmentsForUser(long userId, List<Assignment> assignments) {
        for(Post post : assignments) {
            UserPost userPost = new UserPost(userId, post.id);
            List<FdfEntity<UserPost>> savedUserPosts;
            savedUserPosts = getUserPostByIds(userId, post.id);
            if(!savedUserPosts.isEmpty() && savedUserPosts.get(0).current != null &&
                    savedUserPosts.get(0).current.userId == userId &&
                    savedUserPosts.get(0).current.postId == post.id) {
                userPost.id = savedUserPosts.get(0).current.id;
            }
            save(UserPost.class, userPost);
        }
    }

    public List<FdfEntity<UserPost>> getUserPostByIds(long userId, long postId) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("userId", Long.toString(userId));
        fieldsAndValues.put("postId", Long.toString(postId));
        return getEntitiesByValuesForPassedFields(UserPost.class, fieldsAndValues);
    }

    public void deleteUserPost(UserPost userPost) {
        setDeleteFlag(UserPost.class, userPost.id, -1, -1);
    }

    public void undeleteUserPost(UserPost userPost) {
        removeDeleteFlag(UserPost.class, userPost.id, -1, -1);
    }

}
