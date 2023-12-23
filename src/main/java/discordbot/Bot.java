package haseid.discordbot;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.util.HashMap;

class Bot {
    private HashMap<Long, GuildHandler> guilds;
    private AutoSave autoSave;

    Bot() {
        try { // read data
            FileInputStream file = new FileInputStream("src/main/resources/data.txt");
            ObjectInputStream inStream = new ObjectInputStream(file);
            guilds = (HashMap<Long, GuildHandler>) inStream.readObject();
            inStream.close();
        } catch(FileNotFoundException e) { // first time we start or cant find data
            guilds = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        autoSave = new AutoSave();
    }

    void start() {
        try {
            new JDABuilder(AccountType.BOT)
                    .setToken("Your discord bot token here")
                    .addEventListeners(new CommandListener(guilds))
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        //autoSave.start();
    }

    private void saveData(HashMap<Long, GuildHandler> guilds) {
        try {
            FileOutputStream file = new FileOutputStream("src/main/resources/data.txt");
            ObjectOutputStream outStream = new ObjectOutputStream(file);
            outStream.writeObject(guilds);
            outStream.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private class AutoSave extends Thread {
        public void run() {
            try {
                while (true) {
                    Thread.sleep(600 * 1000);
                    saveData(guilds);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

