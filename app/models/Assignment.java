package models;

/**
 * Created by Mitin on 10/19/2016.
 */
public class Assignment extends Post {

    public int pointMax;
    public float score;

    public Assignment() { super(); }
    public Assignment(int pointMax, String inTitle, String inContent, long inSectionId) {
        super(inTitle, inContent, inSectionId);
        this.pointMax = pointMax;
        this.score = 0;
    }
}
