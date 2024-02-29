public class SafeHause extends NormalLocation{
    public SafeHause(Player player) {
        super(player, "GÜVENLİ EV");
    }

    @Override
    public boolean onLocation() {
        if(this.getPlayer().getInventory().isWater() &&
                this.getPlayer().getInventory().isFireWood() &&
                this.getPlayer().getInventory().isFood()){
            System.out.println("You won the game!");
            return false;
        }

        System.out.println("Safe Place!");
        System.out.println("Your life is renewed!"); // can yenilene işlemi yapılacak
        this.getPlayer().setHealth(this.getPlayer().getOrjHealth());
        return true;
    }
}
