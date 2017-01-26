package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by tsmanner on 11/1/2016.
 */
public class UserPost extends CommonState {
    public UserPost() { super(); }
    public UserPost(long inUserId, long inPostId) {
        super();
        userId = inUserId;
        postId = inPostId;
    }
    public long userId;
    public long postId;
}
