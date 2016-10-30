package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by tsmanner on 10/19/2016.
 */
public class UserAccess extends CommonState {
    public UserAccess() { super(); }

    public UserAccess(long inUserId, long inElementId, AccessLevel inAccessLevel) {
        super();
        userId = inUserId;
        elementId = inElementId;
        accessLevel = inAccessLevel;
    }

    public long userId;
    public long elementId;
    public AccessLevel accessLevel;
}
