package flustix.fluxifyed.modules.utility.commands;

import flustix.fluxifyed.Main;
import flustix.fluxifyed.components.SlashCommand;
import flustix.fluxifyed.utils.slash.SlashCommandUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

public class AboutSlashCommand extends SlashCommand {
    public AboutSlashCommand() {
        super("about", "Shows information about the bot");
    }

    @Override
    public void execute(SlashCommandInteraction interaction) {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("About Fluxifyed")
                .setDescription("A powerful and feature-rich Discord bot (soon) with a lot of customization.")
                .setThumbnail(Main.getBot().getSelfUser().getAvatarUrl())
                .setColor(Main.accentColor)
                .addField(":bust_in_silhouette: Author", "Flustix#5433", true)
                .addField(":scroll: Source Code", "[GitHub](https://github.com/Fluxifyed/Fluxifyed)", true)
                .addField(":link: Invite Link", "[Click here](" + Main.getBot().getInviteUrl(Permission.ADMINISTRATOR) + ")", true)
                .addField(":link: Support Server", "[Click here](https://discord.gg/GaKKeWg)", true)
                .addField(":link: Website", "[Click here](https://fluxifyed.foxes4life.net)", true)
                .addField(":link: Documentation", "[Click here](https://fluxifyed.foxes4life.net/docs)", true)
                .addField(":link: API", "[Click here](https://api.fluxifyed.foxes4life.net)", true)
                .addField(":link: API Documentation", "[Click here](https://fluxifyed.foxes4life.net/api/docs/)", true);

        SlashCommandUtils.reply(interaction, embed.build());
    }
}
