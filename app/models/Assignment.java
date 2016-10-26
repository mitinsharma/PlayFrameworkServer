package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by Mitin on 10/19/2016.
 */
public class Assignment extends CommonState {

    public int postId, pointMax;
    public float score;
    String content;

    public Assignment()
    {
        super();
    }
    public Assignment(int postId, int pointMax, float score, String content)
    {
        this.postId = postId;
        this.pointMax = pointMax;
        this.score = score;
        this.content = content;
    }
}
