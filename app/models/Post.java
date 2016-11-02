package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by Mitin on 10/19/2016.
 */
public class Post extends CommonState {

    public String title, content;

    public Post()
    {
        super();
    }
    public Post(String title, String content)
    {
        this.title = title;
        this.content = content;

    }
}
