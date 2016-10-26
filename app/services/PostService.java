package services;

import com.fdflib.service.impl.FdfCommonServices;
import models.Post;

/**
 * Created by Mitin on 10/26/2016.
 */
public class PostService extends FdfCommonServices {

    public Post savePost(Post newPost)
    {
        save(Post.class,newPost);
        return newPost;
    }
}
