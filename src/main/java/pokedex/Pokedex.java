package haseid.pokedex;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Pokedex {
    private static Pokemon[] pokemons;

    static {
        pokemons = new Pokemon[809];
        JSONParser parser = new JSONParser();

        try { // reads pokemons from json file
            JSONArray array = (JSONArray) parser.parse(new FileReader("src/main/resources/pokedex.json"));

            for (Object obj : array) {
                JSONObject pokemon = (JSONObject) obj;

                int id = Math.toIntExact((long) pokemon.get("id"));

                String name = (String) ((JSONObject) pokemon.get("name")).get("english");
                if (name.contains("♂")){ // not everyone can type ♂ and ♀ into Discord
                    name = name.replace("♂","");
                } else if (name.contains("♀")) {
                    name = name.replace("♀","");
                }

                String[] types = new String[2];
                JSONArray typeArray = (JSONArray) pokemon.get("type");

                int i = 0;
                for (Object type : typeArray) {
                    types[i++] = (String) type;
                }

                pokemons[id-1] = new Pokemon(id, name, types); // adds the new pokemon to the array
            }

        } catch(IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    public static String getNameOf(int id) {
        return pokemons[id-1].name;

    }

    public static String getTypeOf(int id) {
        String[] type = pokemons[id-1].type;
        if(type[1]!=null) // if a pokemon has 2 types
            return type[0]+" and "+type[1];
        return type[0];
    }

    private static class Pokemon {
        int id;
        String name;
        String[] type;

        private Pokemon(int id, String name, String[] type) {
            this.id = id;
            this.name = name;
            this.type = type;
        }
    }
}
