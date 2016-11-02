package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by tsmanner on 11/1/2016.
 */
public class SectionPost extends CommonState {
    public SectionPost() { super(); }
    public SectionPost(long inSectionId, long inPostId) {
        super();
        sectionId = inSectionId;
        postId = inPostId;
    }
    public long sectionId;
    public long postId;
}
