import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int orjHealth;
    private int money;
    private String name;  //dışarıdan sadece bunu alacağım. O yüzden constructor içine yalnız bunu yazabilirim.
    private String charName;

    private Scanner input = new Scanner(System.in);
    private Inventory inventory;

    public Player(String name){
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar(){
        //Samurai samurai = new Samurai();
        //Knight knight = new Knight();
        //Archer archer = new Archer();

        GameCharacter[] charactersList = {new Samurai(), new Archer(), new Knight()}; //Polymorphism
        System.out.println("#############################################################################");
        for(GameCharacter gameCharacter: charactersList){
            System.out.println("ID: "+ gameCharacter.getId() +
                    "\t\t Karakter: "+ gameCharacter.getName() +
                    "\t\t Hasar: "+ gameCharacter.getDamage() +
                    "\t\t Sağlık: "+ gameCharacter.getHealth() +
                    "\t\t Para: " + gameCharacter.getMoney());
        }
        System.out.println("#############################################################################");
        System.out.print("Please Enter Character ! ");
        int selectChar = input.nextInt();
        switch (selectChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
        System.out.println("Karakter: " +this.getCharName() +
                ", Hasar: " + this.getDamage() +
                ", Sağlık: " + this.getHealth() +
                ", Para: " + this.getMoney());


    }



    public void initPlayer(GameCharacter gameCharacter){
        this.setDamage(gameCharacter.getDamage());
        this.setHealth(gameCharacter.getHealth());
        this.setOrjHealth(gameCharacter.getHealth());
        this.setMoney(gameCharacter.getMoney());
        this.setCharName(gameCharacter.getName());
    }

    public void printInfo(){
        System.out.println("Silah: " +this.getInventory().getWeapon().getName() +
                ", Zırh: " + this.getInventory().getArmor().getName() +
                ", Bloklama: " + this.getInventory().getArmor().getBlock()+
                ", Hasar: " + this.getTotalDamage() +
                ", Sağlık: " + this.getHealth() +
                ", Para: " + this.getMoney()+
                ", SU: " + this.getInventory().isWater() +
                ", YEMEK: " + this.getInventory().isFood() +
                ", ODUN: " + this.getInventory().isFireWood());

    }

    public int getTotalDamage(){
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0){
            health = 0;
        }
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOrjHealth() {
        return orjHealth;
    }

    public void setOrjHealth(int orjHealth) {
        this.orjHealth = orjHealth;
    }
}
