public class ToolStore extends NormalLocation{
    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Welcome to the Tool Store!");
        //mağazaya geldiğinde ne satın almak istiyor? silah mı zırh mı yoksa mağazadan çıkmak mı istiyor?
        boolean showMenu = true;
        while (showMenu){
            System.out.println("1 - Weapons");
            System.out.println("2 - Armor");
            System.out.println("3 - Exit");
            System.out.print("Your Choice : ");
            int selectCase = Location.input.nextInt();
            while (selectCase < 1 || selectCase > 3){
                System.out.print("Please try again : ");
                selectCase = Location.input.nextInt();
            }
            switch (selectCase){
                case 1 :
                    printWeapon();
                    buyWeapon();
                    break;
                case 2 :
                    printArmor();
                    buyArmor();
                    break;
                case 3 :
                    System.out.println("We wait again!");
                    showMenu = false;
                    return true;

            }
        }
        return true;
    }

    public void printWeapon(){
        System.out.println("Weapons");
        //Burada el ile gireceğm ama obje üretip yap!!
        //
        for (Weapon w : Weapon.weapons()){
            System.out.println(w.getId() + "-" + w.getName() + " |Para : " + w.getPrice() + " , Hasar : " + w.getDamage());
        }
        System.out.println("0-Çıkış Yap");

    }

    public void buyWeapon(){

        System.out.print("Select Weapon : ");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length){
            System.out.print("Please try again : ");
            selectWeaponID = Location.input.nextInt();
        }

        if (selectWeaponID != 0){
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);

            if (selectedWeapon != null){
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("You don't have enough money ! ");
                }else {
                    // Satın almanın gerçekleştiği alan
                    System.out.println("You bought " + selectedWeapon.getName() );
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Money : " + this.getPlayer().getMoney());
                    System.out.println("Your previous weapon : " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Your new weapon : " + this.getPlayer().getInventory().getWeapon().getName());
                }
            }
        }



    }
    public void printArmor(){
        System.out.println("Armors");

       for (Armor a : Armor.armors()){
           System.out.println(a.getId() + " - " + a.getName() + " |Para : " + a.getPrice() + " , Zırh : " + a.getBlock());
       }
        System.out.println("0 - Çıkış Yap");
    }

    public void buyArmor(){
        System.out.print("Select Armor : ");
        int selectArmorID = input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().size()){
            System.out.print("Please try again : ");
            selectArmorID = Location.input.nextInt();
        }

        if (selectArmorID != 0){
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);

            if (selectedArmor != null){
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("You don't have enough money ! ");
                }else {
                    // Satın almanın gerçekleştiği alan
                    System.out.println("You bought " + selectedArmor.getName() );
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Money : " + this.getPlayer().getMoney());
                    System.out.println("Your previous armor : " + this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Your new weapon : " + this.getPlayer().getInventory().getArmor().getName());
                }
            }
        }



    }
}
