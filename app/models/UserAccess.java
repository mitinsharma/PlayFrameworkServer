package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by tsmanner on 10/19/2016.
 */
public class UserAccess extends CommonState {
    public UserAccess() { super(); }

    public UserAccess(long inUserId, AccessLevel inAccessLevel) {
        super();
        userId = inUserId;
        accessLevel = inAccessLevel;
    }

    public long userId;
    public AccessLevel accessLevel;
}
