package haseid.discordbot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.HashMap;

class GuildHandler implements java.io.Serializable{
    long lastCorrect;
    private HashMap<Long, Integer> scoreboard;

    GuildHandler() {
        scoreboard = new HashMap<>();
    }

    void handle(GuildMessageReceivedEvent event) {
        String content = event.getMessage().getContentRaw();
        TextChannel channel = event.getChannel();

        switch (content) {
            case "!help": // !help - shows list of commands
                channel.sendMessage(help()).queue();
                return;
            case "!stats": //  !stats - shows stats of that user
                channel.sendMessage(stats()).queue();
                return;
            case "!hint": // !hint - gives a hint of current pokemon
                return;
            case "!gen ": // !gen ... - starts the given generations
                return;
            default:
                return;
        }
    }

    private MessageEmbed help() {
        EmbedBuilder newEmbed = new EmbedBuilder();
        newEmbed.setTitle("Commands");
        newEmbed.setColor(new Color(0,175,135));
        newEmbed.addField("!stats", "Shows your stats.", false);
        newEmbed.addField("!gen", "Starts a round of given generations.\n Example: `!gen 1 2`", false);
        newEmbed.addField("!hint", "Gives a hint for the current pokémon.", false);
        return newEmbed.build();
    }

    private MessageEmbed stats() {
        EmbedBuilder newEmbed = new EmbedBuilder();
        newEmbed.setTitle("Commands");
        newEmbed.setColor(new Color(0,175,135));
        newEmbed.addField("!stats", "Shows your stats.", false);
        newEmbed.addField("!gen", "Starts a round of given generations.\n Example: `!gen 1 2`", false);
        newEmbed.addField("!hint", "Gives a hint for the current pokémon.", false);
        return newEmbed.build();
    }
}
