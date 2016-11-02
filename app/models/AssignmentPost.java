package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by Mitin on 11/2/2016.
 */
public class AssignmentPost  extends CommonState{

    public AssignmentPost() { super(); }
    public AssignmentPost(long inAssignmentId, long inPostId) {
        super();
        assignmentId = inAssignmentId;
        postId = inPostId;
    }
    public long assignmentId;
    public long postId;
}
