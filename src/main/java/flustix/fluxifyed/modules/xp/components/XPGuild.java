package flustix.fluxifyed.modules.xp.components;

import flustix.fluxifyed.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XPGuild {
    private final String id;
    private final HashMap<String, XPUser> users = new HashMap<>();
    private final List<XPRole> roles = new ArrayList<>();

    public XPGuild(String id) {
        this.id = id;
    }

    public XPUser getUser(String id) {
        XPUser user = users.get(id);

        if (user == null) {
            user = new XPUser(this.id, id);
            users.put(id, user);
        }

        return user;
    }

    public void addUser(XPUser user) {
        users.put(user.getID(), user);
    }

    public void addRole(XPRole role) {
        Main.LOGGER.info("Adding role " + role.getID() + " to guild " + id + " with level " + role.getLevel());
        roles.add(role);
    }

    public List<XPUser> getTop() {
        List<XPUser> top = new ArrayList<>(users.values().stream().toList());
        top.sort((a, b) -> b.getXP() - a.getXP());
        return top;
    }

    public String getID() {
        return id;
    }

    public List<XPRole> getRoles() {
        return roles;
    }
}
