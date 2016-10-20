package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by tsmanner on 10/19/2016.
 */
public class UserAccess extends CommonState {
    public long userId;
    public AccessLevel accessLevel;
}
