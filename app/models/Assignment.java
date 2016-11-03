package models;

/**
 * Created by Mitin on 10/19/2016.
 */
public class Assignment extends Post {

    public int pointMax;
    public float score;
    String content;

    public Assignment()
    {
        super();
    }
    public Assignment(int pointMax, float score, String content)
    {
        this.pointMax = pointMax;
        this.score = score;
        this.content = content;
    }
}
