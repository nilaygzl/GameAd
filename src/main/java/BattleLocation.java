import java.util.Random;
import java.util.Scanner;

public abstract class BattleLocation extends Location{

    private Monster monster;
    private String award;
    private int maxMonster;
    public static Scanner input = new Scanner(System.in);
    public BattleLocation(Player player, String name, Monster monster, String award, int maxMonster) {
        super(player, name);
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;

    }

    @Override
    public boolean onLocation() {
        int mnstrNumber = this.randomMonsterNumber();
        System.out.println("Location: " + this.getName());
        System.out.println("Be careful there are " + mnstrNumber + " " + this.monster.getName() + " living here !");
        System.out.print("<F>ight or <R>un : ");
        String selectCase = input.nextLine();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("F") && combat(mnstrNumber)){
                System.out.println("You have defeated all the monsters in the place " + this.getName());
                return true;
        }
        if (this.getPlayer().getHealth() <= 0){
            System.out.println("You died");
            return false;
        }
        return true;
    }

    public boolean combat(int mnstrNumber){

        for (int i=1; i<=mnstrNumber; i++){
            this.getMonster().setHealth(this.getMonster().getDefaultHealth());
            playerStatus();
            monsterStatus(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth()> 0){
                System.out.print("<F>ight or <R>un : ");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("F")){
                    System.out.println("You are fighting the monster ! ");
                    this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getMonster().getHealth() > 0){
                        System.out.println("The monster is fighting you ! ");
                        int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (monsterDamage < 0){
                            monsterDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage );
                        afterHit();
                    }
                }else {
                    return false;
                }

            }

            if (this.getMonster().getHealth() < this.getPlayer().getHealth()){
                System.out.println("You defeated the enemy ! ");
                System.out.println("You won " + this.getMonster().getAward() + " money");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getAward());
                System.out.println("Your new money : " + this.getPlayer().getMoney());
                if(this.getMonster().getName().equals("Zombie")){
                    this.getPlayer().getInventory().setFood(true);
                }
                else if(this.getMonster().getName().equals("Bear")){
                    this.getPlayer().getInventory().setWater(true);
                }
                else if(this.getMonster().getName().equals("Vampire")){
                    this.getPlayer().getInventory().setFireWood(true);
                }

            }else {
                return false;
            }
        }
        return true;
    }

    public void afterHit(){
        System.out.println("Your Health : " + this.getPlayer().getHealth());
        System.out.println("Monster's Health : " + this.getMonster().getHealth());
        System.out.println("-----------------");
    }

    public void playerStatus(){
        System.out.println("Player's Status");
        System.out.println("-----------------");
        System.out.println("Health : " + this.getPlayer().getHealth());
        System.out.println("Waepon : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Armor : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Block : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Damage : " + this.getPlayer().getTotalDamage());
        System.out.println("Money : " + this.getPlayer().getMoney());
        System.out.println("-----------------");

    }

    public void monsterStatus(int i){
        System.out.println(i + " . Monster's Status");
        System.out.println("-----------------");
        System.out.println(this.getMonster().getName());
        System.out.println("Health : " + this.getMonster().getHealth());
        System.out.println("Damage : " + this.getMonster().getDamage());
        System.out.println("Award : " + this.getMonster().getAward());
        System.out.println("-----------------");

    }

    public int randomMonsterNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxMonster()) + 1;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }
}
