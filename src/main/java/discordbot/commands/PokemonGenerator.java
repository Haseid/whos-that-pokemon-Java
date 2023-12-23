package haseid.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PokemonGenerator {
    private ArrayList<Integer> generations = new ArrayList<>();
    private int index;

    PokemonGenerator(int[] generations) {
        int[] generationAmount = {151, 100, 135, 107, 156, 72, 88};
        int[][] generationNumbers = new int[7][];

        int lastGens = 1;
        for (int i = 0; i < 7; i++) {
            generationNumbers[i] = IntStream.range(lastGens, generationAmount[i] + 1).toArray();
            lastGens += generationAmount[i];
        }

        for (int gen : generations) {
            this.generations.addAll(Arrays.stream(generationNumbers[gen]).boxed().collect(Collectors.toList()));
        }

        Collections.shuffle(this.generations);
        index = 0;
    }

    public void next() {
        index++;
    }

    public MessageEmbed getPokemonHidden(int value) {
        EmbedBuilder newEmbed = new EmbedBuilder();
        newEmbed.setTitle("Commands");
        newEmbed.setAuthor("Professor Oak", null, "");

        newEmbed.setColor(new Color(0,175,135));
        newEmbed.addField("!stats", "Shows your stats.", false);
        newEmbed.addField("!gen", "Starts a round of given generations.\n Example: `!gen 1 2`", false);
        newEmbed.addField("!hint", "Gives a hint for the current pokémon.", false);
        return newEmbed.build();
    }

    public MessageEmbed getPokemonShown(int value, String user) {
        EmbedBuilder newEmbed = new EmbedBuilder();
        newEmbed.setTitle("Commands");
        newEmbed.setColor(new Color(0,175,135));
        newEmbed.addField("!stats", "Shows your stats.", false);
        newEmbed.addField("!gen", "Starts a round of given generations.\n Example: `!gen 1 2`", false);
        newEmbed.addField("!hint", "Gives a hint for the current pokémon.", false);
        return newEmbed.build();
    }

}
