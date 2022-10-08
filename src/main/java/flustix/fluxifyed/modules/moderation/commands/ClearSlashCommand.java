package flustix.fluxifyed.modules.moderation.commands;

import flustix.fluxifyed.Main;
import flustix.fluxifyed.components.SlashCommand;
import flustix.fluxifyed.utils.permissions.PermissionLevel;
import flustix.fluxifyed.utils.presets.EmbedPresets;
import flustix.fluxifyed.utils.slash.SlashCommandUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

import java.util.List;
import java.util.Objects;

public class ClearSlashCommand extends SlashCommand {
    public ClearSlashCommand() {
        super("clear", "Clears the chat.");
        setPermissionLevel(PermissionLevel.MODERATOR);
        addOption(OptionType.INTEGER, "amount", "The amount of messages to clear.", true, false);
    }

    public void execute(SlashCommandInteraction interaction) {
        int amount = Objects.requireNonNull(interaction.getOption("amount")).getAsInt();

        SlashCommandUtils.replyEphemeral(interaction, EmbedPresets.loading.build(), (hook) -> {
            try {
                List<Message> messages = interaction.getChannel().getHistory().retrievePast(amount).complete();
                interaction.getChannel().purgeMessages(messages);

                EmbedBuilder infoEmbed = new EmbedBuilder()
                        .setAuthor(interaction.getUser().getAsTag(), null, interaction.getUser().getAvatarUrl())
                        .setTitle("Cleared " + amount + " messages")
                        .setColor(Main.accentColor);

                EmbedBuilder successEmbed = new EmbedBuilder()
                        .setTitle("Done!")
                        .setColor(Main.accentColor);

                hook.editOriginalEmbeds(successEmbed.build()).complete();
                interaction.getChannel().sendMessageEmbeds(infoEmbed.build()).complete();
            } catch (Exception e) {
                EmbedBuilder errorEmbed = new EmbedBuilder()
                        .setTitle("Something went wrong!")
                        .setColor(Main.accentColor);

                hook.editOriginalEmbeds(errorEmbed.build()).complete();
                Main.LOGGER.error("Something went wrong while clearing messages!", e);
            }
        });
    }
}