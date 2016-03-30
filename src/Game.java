import java.util.Scanner;

/**
 * Created by johnjastrow on 3/30/16.
 */
public class Game {

    public static Scanner scanner = new Scanner(System.in);
    public static Player player = new Player();

    public static void main(String[] args) throws Exception {

        Game.print("Welcome, traveller.");

        player.chooseName();
        player.chooseWeapon();
        player.chooseLocation();

    }

    public static void print(String anyString) {
        System.out.println(anyString);
    }
}
