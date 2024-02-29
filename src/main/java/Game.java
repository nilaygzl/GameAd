import java.util.Scanner;

public class Game {

    private Scanner input = new Scanner(System.in);

    public void start(){
        System.out.println("Welcome to the Adventure Game! Enjoy...");
        System.out.print("Please enter your name : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Dear " + player.getName() + " Welcome to the dark side !");
        System.out.println("Everything that happens here is real and creepy.");
        System.out.println("Please choose a character !");
        System.out.println("#############################################################################");
        player.selectChar();

        Location location = null;

        while (true){
            player.printInfo();
            //selectChar metodunda yaptığım gibi lokasyonları sıralamak istiyorum
            System.out.println();
            System.out.println("#####Locations#####");
            System.out.println();
            System.out.println("1 - Safe Hause --> There is no enemy here");
            System.out.println("2 - Tool Store --> You can buy weapons and armor");
            System.out.println("3 - Cave --> Enter the Cave <Gift: Food>");
            System.out.println("4 - Forest --> Enter the Forest <Gift: Wood>");
            System.out.println("5 - River --> Enter the River <Gift: Water>");
            System.out.println("0 - Exit");
            System.out.print("Please select the location you want to go to : ");

          
            int selectLoc = input.nextInt();
            switch (selectLoc){
                case 0 :
                    location = null;
                    break;
                case 1:
                    location = new SafeHause(player); // burada direk player demek yerine this diyorum ve ürettiğim player nesnesini seçmiş almış oluyorum.(daha sonra class değişikliği aptığım için player diyebiliyorum))// burada aynı zamanda bir polimorfizm uygulandı. Locationı referans alıyor ama safe hause şeklinde davranıyor.
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                default:
                    System.out.println("Please enter a valid location ! ");
                    location = new SafeHause(player);
            }

            if (location == null){
                System.out.println("-END-");
                break;
            }
            if (!location.onLocation())
            {
                System.out.println("GAME OVER!");
                break;
            }

        }

    }
}
