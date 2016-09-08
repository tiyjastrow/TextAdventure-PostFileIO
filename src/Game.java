import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by johnjastrow on 3/30/16.
 */
public class Game {
    public static Scanner scanner = new Scanner(System.in);
    public static Player player = new Player();
    private static final String INVENTORY = "/inv";
    private static final String SAVE = "/save";
    private static final String LOAD = "/load";
    private static final String EXIT = "/exit";

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome, traveller.");

        try {
            player = loadGame();
            System.out.println("Loaded saved game.");
        } catch (FileNotFoundException e) {
            System.out.println("Starting new game.");
        }

        if ( player.name == null) player.chooseName();
        if ( player.getWeapon() == null) player.chooseWeapon();
        if ( player.getLocation() == null ) player.chooseLocation();

        if (player.getItems().isEmpty()) {
            player.findItem("shield");
            player.findItem("boots");
            player.findItem("belt");
        }

        Enemy ogre = new Enemy("Ogre", 10, 10);
        player.battle(ogre);

        System.out.printf("enter command: [%s = view inventory," +
                " %s = save game, %s = reload, %s", INVENTORY, SAVE, LOAD, EXIT);
        nextLine();
    }

    public static String nextLine() {
        String line = scanner.nextLine();
        while (line.startsWith("/")) {
            switch (line) {
                case INVENTORY:
                    for (String item : player.getItems()) {
                        System.out.println(item);
                    }
                    break;
                case SAVE:
                    try {
                        saveGame();
                        System.out.println("Saved game.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case LOAD:
                    try {
                        loadGame();
                        System.out.println("ReLoaded saved game.");
                    } catch (FileNotFoundException e) {
                        System.out.println("No saved game found.");
                    }
                case EXIT:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Command not found!");
                    break;
            }
            line = scanner.nextLine();
        }
        return line;
    }

    public static void saveGame() throws IOException {
        JsonSerializer s = new JsonSerializer();
        String json = s.serialize(player);

        File f = new File("game.json");
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }

    public static Player loadGame() throws FileNotFoundException {
        File f = new File("game.json");
        Scanner s = new Scanner(f);
        s.useDelimiter("\\Z");
        String contents = s.next();
        s.close();

        JsonParser p = new JsonParser();
        return p.parse(contents, Player.class);
    }
}
