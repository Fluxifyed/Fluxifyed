package flustix.fluxifyed.database.api.authentification;

import flustix.fluxifyed.Main;
import flustix.fluxifyed.database.Database;
import flustix.fluxifyed.utils.permissions.PermissionLevel;
import flustix.fluxifyed.utils.permissions.PermissionUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AuthUtils {
    public static String getUserId(String token) {
        ResultSet rs = Database.executeQuery("SELECT userid FROM tokens WHERE token = '" + token + "'");

        try {
            if (rs.next())
                return rs.getString("userid");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static List<Guild> getServers(String userid) {
        List<Guild> ids = new ArrayList<>();

        Main.getBot().getGuilds().forEach(guild -> {
            try {
                Member member = guild.retrieveMemberById(userid).complete();
                if (member == null) member = guild.retrieveMemberById(userid).complete();

                if (PermissionUtils.checkLevel(member, PermissionLevel.ADMIN))
                    ids.add(guild);
            } catch (Exception ignored) {}
        });

        return ids;
    }
}
