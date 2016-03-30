/**
 * Created by johnjastrow on 3/30/16.
 */
public class Player {
    String name;
    String weapon;
    String location;

    public void chooseName() {
        Game.print("What is your name? ");
        this.name = Game.scanner.nextLine();

        Game.print("Welcome, " + name);
    }

    public void chooseWeapon() throws Exception {
        Game.print("Choose your weapon [sword/mace]: ");
        this.weapon = Game.scanner.nextLine();

        if (weapon.equalsIgnoreCase("sword")) {
            Game.print("A sword is a fine choice!");
            Game.print("something happens here");
        }
        else if (weapon.equalsIgnoreCase("mace")) {
            Game.print("A mace is a really fine choice!");
        }
        else {
            throw new Exception("no weapon? What are you going to fight with your bare hands?");
        }
    }

    public void chooseLocation() throws Exception {
        Game.print("Choose your location [forest/tunnel]");
        this.location = Game.scanner.nextLine();

        if (location.equalsIgnoreCase("forest")) {
            Game.print("Now entering the dangerous forest.");
        }
        else if (location.equalsIgnoreCase("tunnel")) {
            Game.print("Entering dark tunnel. LOOK OUT!!");
        }
        else {
            throw new Exception("You can't go that way. Go back!");
        }
    }


}
