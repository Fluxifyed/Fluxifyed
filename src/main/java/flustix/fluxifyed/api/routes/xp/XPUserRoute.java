package flustix.fluxifyed.api.routes.xp;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import flustix.fluxifyed.api.types.Route;
import flustix.fluxifyed.xp.XP;
import flustix.fluxifyed.xp.types.XPGuild;
import flustix.fluxifyed.xp.types.XPUser;

import java.util.HashMap;

public class XPUserRoute implements Route {
    @Override
    public JsonObject execute(HttpExchange exchange, HashMap<String, String> params) throws Exception {
        JsonObject json = new JsonObject();

        XPGuild guild = XP.getGuild(params.get("guild"));
        if (guild == null)
            return json; // return nothing if it doesnt exist :shrug:

        XPUser user = guild.getUser(params.get("user"));
        if (user == null)
            return json; // return nothing if it doesnt exist :shrug:

        json.addProperty("guild", guild.getId());
        json.addProperty("user", user.getID());
        json.addProperty("xp", user.getXP());

        return json;
    }
}