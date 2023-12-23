package haseid.discordbot;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;

class CommandListener extends ListenerAdapter {
    private HashMap<Long, GuildHandler> guilds;

    public CommandListener(HashMap<Long, GuildHandler> guilds) {
        this.guilds = guilds;

    }
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        User author = event.getAuthor();
        if (author.isBot()) {
            return; // ignore bots
        }

        Guild guild = event.getMessage().getGuild();
        GuildHandler guildHandler = guilds.get(guild.getIdLong());
        if (guildHandler == null) {// if its a new guild
            guildHandler = new GuildHandler();
            guilds.put(guild.getIdLong(), guildHandler);
        }
        guildHandler.handle(event);
    }
}
