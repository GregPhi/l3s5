/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */


package adventure;

import java.util.ArrayList;
import java.util.Scanner;

import adventure.character.*;
import adventure.item.*;
import adventure.room.*;

public class AdventureGame {
	/**
	 * Instruction of game
	 */
	public static void instructions(){
		System.out.println("Here are the different instructions to play.\r\n" + 
				"There are four actions:\r\n");
		System.out.println("- look: when you enter a room for the first time this is the only action possible (it allows you to watch if you can go to another room, attack a monsters, retrieve an object).");
		System.out.println("- move: allows you to go from one room to another.");
		System.out.println("- attack: allows you to face different enemies in a room (you can not leave a room as long as monsters remain).");
		System.out.println("- use: allows you to retrieve ground objects, or buy items from the onearmedbandit.\n");
		System.out.println("You can chose the difficulty of dungeon :");
		System.out.println("- `easy' to create a easy dungeon.");
		System.out.println("- 'hard' to create a hard dungeon.");
		System.out.println("- or if have write nothing it's a normal dungeon.\n");
		System.out.println("Good game !");
		
	}

	/**
	 * Main of game
	 * @param args : (type-String[]) here we don't use this parameter
	 */
	public static void main(String[] args){
		instructions();
		System.out.print("Choose the difficulty : ");
		Scanner sc = new Scanner(System.in);
		String difficulty = sc.nextLine();
		Room entry = new Room("entry");
		if (difficulty.equals("easy")) {
			entry = dungeonGameEasy();
		}
		if (difficulty.equals("hard")) {
			entry = dungeonGameHard();
		}
		if(!difficulty.equals("easy") && !difficulty.equals("hard")) {
			entry = dungeonGame();
		}
		System.out.print("Choose you player's name : ");
		String name = sc.nextLine();
		System.out.println(name+" enters in the Naheulbeuk dungeon."+"\n");
		/*
		 * If you want create a player with random caracteristics (life, strength and gold)
		 * ArrayList<Integer> choseLife = new ArrayList<Integer>();
		 * choseLife.add(15);
		 * choseLife.add(30);
		 * ArrayList<Integer> choseStrength = new ArrayList<Integer>();
		 * choseStrength.add(10);
		 * choseStrength.add(15);
		 * ArrayList<Integer> choseGold = new ArrayList<Integer>();
		 * choseGold.add(0);
		 * choseGold.add(50);
		 * Player player = new Player(entry,choseLife,choseStrength,choseGold,name);
		 */
		Player player = new Player(entry,15,10,50,name);
		System.out.println(player.diplayCharacter());
		player.defaultAction();
		sc.close();
		
	}

	/**
	 * Create a medium dungeon
	 * @return : (type-Room) the entry of dungeon
	 */
	public static Room dungeonGame(){
		Room entry = new Room("Entry");
		Room choice = new Room("The choice");
		choice.addItem(new PurseGold(5));
		choice.addNeighbours(Direction.NORTH, entry);
		Room bandit = new Room("bandit");
		bandit.addItem(new OneArmedBandit(150));
		bandit.addNeighbours(Direction.NORTH, choice);
		Room darkkorridor = new Room("Dark Corridor");
		darkkorridor.addItem(new PurseGold(5));
		darkkorridor.addMonster(new Monster(darkkorridor,5,2,0,"Arakne"));
		darkkorridor.addNeighbours(Direction.EAST, choice);
		Room crypt = new Room("Crypt");
		crypt.addItem(new StrengthPotion(1));
		crypt.addMonster(new Monster(crypt,7,4,2,"small Zombie"));
		crypt.addMonster(new Monster(crypt,8,4,2,"Zombie"));
		crypt.addNeighbours(Direction.NORTH, darkkorridor);
		Room path = new Room("The path");
		path.addNeighbours(Direction.WEST, choice);
		Room walk = new Room("Walk");
		walk.addMonster(new Monster(walk,8,4,5,"Orc"));
		walk.addItem(new LifePotion(3));
		walk.addNeighbours(Direction.EAST, path);
		Room enig = new Room("End ?");
		Enigme enigm = new Enigme("Qu'est ce qui est petit et marron ?","un marron");
		enigm.addAnswer("un marron");
		enigm.addAnswer("une chataigne");
		enigm.addAnswer("un chocolat");
		enigm.addAnswer("une chaise");
		enig.addItem(enigm);		
		enig.addNeighbours(Direction.SOUTH,walk);
		Room dead = new Room("BOOS");
		dead.addMonster(new Monster(dead,100,7,50,"DRAGON"));
		dead.addNeighbours(Direction.SOUTH,enig);
		Room exit = new Room("This is the End");
		exit.becomesAnExit();
		exit.addNeighbours(Direction.SOUTH,dead);
		return entry;
	}
	
	/**
	 * Create a easy dungeon
	 * the commentary when we have inizialise the neighbours of dungeon's room,
	 * were used to check the proper placement of the dungeon rooms 
	 * @return : (type-Room) the entry of dungeon
	 */
	public static Room dungeonGameEasy() {
		//initialize room
		//entry
		Room entry = new Room("Entry");
		//normal room
		Room normal1 = new Room("normal 1");
		Room normal2 = new Room("normal 2");
		Room normal3 = new Room("normal 3");
		Room normal4 = new Room("normal 4");
		Room normal5 = new Room("normal 5");
		//room with item
		Room normali1 = new Room("normal item 1");
		normali1.addItem(new LifePotion(2));
		Room normali2 = new Room("normal item 2");
		normali2.addItem(new PurseGold(10));
		Room normali3 = new Room("normal item 3");
		normali3.addItem(new StrengthPotion(5));
		Room normali4 = new Room("normal item 4");
		normali4.addItem(new PurseGold(5));
		Room bandit = new Room("bandit");
		bandit.addItem(new OneArmedBandit(50));
		//treasure
		Room treasure = new Room("treasure");
		treasure.addItem(new LifePotion(15));
		treasure.addItem(new StrengthPotion(7));
		treasure.addItem(new PurseGold(25));
		//room with monster
		Room monster1 = new Room("monster 1");
		monster1.addMonster(new Monster(monster1,5,5,5,"bat"));
		monster1.addMonster(new Monster(monster1,7,5,5,"spider"));
		Room monster2 = new Room("monster 2");
		monster2.addMonster(new Monster(monster2,15,7,10,"troll"));
		monster2.addMonster(new Monster(monster2,7,7,7,"skeleton"));
		Room monster3 = new Room("monster 3");
		monster3.addMonster(new Monster(monster3,5,5,5,"snake"));
		monster3.addMonster(new Monster(monster3,7,5,5,"spider"));
		//enigme
		Room enig = new Room("enigme");
		Enigme enigm = new Enigme("Qu'est ce qui est petit et marron ?","un marron");
		enigm.addAnswer("un marron");
		enigm.addAnswer("une chataigne");
		enigm.addAnswer("un chocolat");
		enigm.addAnswer("une chaise");
		enig.addItem(enigm);
		//boss
		Room boss = new Room("boss");
		boss.addMonster(new Monster(boss,50,10,100,"dragon"));
		//exit
		Room exit = new Room("Exit");
		exit.becomesAnExit();
		//trap
		Room trap = new Room("trap");
		trap.becomesAnTrap();
		
		//initalize neighbours
		entry.addNeighbours(Direction.NORTH, normal1);
		entry.addNeighbours(Direction.EAST, normal2);
		entry.addNeighbours(Direction.WEST, normali1);
		
		normal1.addNeighbours(Direction.NORTH, normali4);
		normal1.addNeighbours(Direction.EAST, enig);
		//normal1.addNeighbours(Direction.SOUTH, entry);
		
		normal2.addNeighbours(Direction.NORTH, enig);
		normal2.addNeighbours(Direction.SOUTH, normali2);
		normal2.addNeighbours(Direction.EAST, monster3);
		//normal2.addNeighbours(Direction.WEST,entry);
		
		normal3.addNeighbours(Direction.EAST, monster1);
		
		normal4.addNeighbours(Direction.SOUTH, monster1);
		normal4.addNeighbours(Direction.EAST, monster2);
		
		normal5.addNeighbours(Direction.WEST, bandit);
		normal5.addNeighbours(Direction.SOUTH, boss);
		
		normali1.addNeighbours(Direction.WEST, trap);
		//normali1.addNeighbours(Direction.EAST, entry);
		
		//normali2.addNeighbours(Direction.NORTH, normal2);
		
		normali3.addNeighbours(Direction.NORTH, boss);
		normali3.addNeighbours(Direction.SOUTH, monster3);
		normali3.addNeighbours(Direction.WEST, enig);
		
		normali4.addNeighbours(Direction.WEST, monster1);
		normali4.addNeighbours(Direction.NORTH, monster2);
		normali4.addNeighbours(Direction.EAST, treasure);
		//normali4.addNeighbours(Direction.SOUTH, normal1);
		
		bandit.addNeighbours(Direction.WEST, monster2);
		bandit.addNeighbours(Direction.SOUTH, treasure);
		//bandit.addNeighbours(Direction.EAST, normal5);
		
		//treasure.addNeighbours(Direction.WEST, normali4);
		//treasure.addNeighbours(Direction.NORTH, bandit);
		treasure.addNeighbours(Direction.EAST, boss);
		treasure.addNeighbours(Direction.SOUTH, enig);
		
		//monster1.addNeighbours(Direction.WEST, normal3);
		//monster1.addNeighbours(Direction.NORTH, normal4);
		//monster1.addNeighbours(Direction.EAST, normali4);
		
		//monster2.addNeighbours(Direction.EAST, bandit);
		//monster2.addNeighbours(Direction.SOUTH, normali4);
		//monster2.addNeighbours(Direction.WEST, normal4);
		
		//monster3.addNeighbours(Direction.NORTH, normali3);
		//monster3.addNeighbours(Direction.WEST, normal2);
		
		//enig.addNeighbours(Direction.NORTH, treasure);
		//enig.addNeighbours(Direction.WEST, normal1);
		//enig.addNeighbours(Direction.EAST, normali3);
		//enig.addNeighbours(Direction.SOUTH, normal2);
				
		//trap.addNeighbours(Direction.EAST, normali1);
		
		boss.addNeighbours(Direction.EAST, exit);
		//boss.addNeighbours(Direction.NORTH,normal5);
		//boss.addNeighbours(Direction.SOUTH, normali3);
		//boss.addNeighbours(Direction.WEST, treasure);
		
		//exit.addNeighbours(Direction.WEST, boss);
		return entry;
	}
	
	/**
	 * Generate a hard dungeon
	 * @return : (type-Room) the entry of dungeon
	 */
	public static Room dungeonGameHard() {
		//initialize room
		//entry
		Room entry = new Room("Entry");
		//normal room
		Room normal1 = new Room("normal 1");
		Room normal2 = new Room("normal 2");
		Room normal3 = new Room("normal 3");
		Room normal4 = new Room("normal 4");
		Room normal5 = new Room("normal 5");
		Room normal6 = new Room("normal 6");
		Room normal7 = new Room("normal 7");
		Room normal8 = new Room("normal 8");
		// room with item
		Room normali1 = new Room("normal item 1");
		normali1.addItem(new LifePotion(2));
		Room normali2 = new Room("normal item 2");
		normali2.addItem(new PurseGold(10));
		Room normali3 = new Room("normal item 3");
		normali3.addItem(new StrengthPotion(5));
		Room normali4 = new Room("normal item 4");
		normali4.addItem(new PurseGold(5));
		Room normali5 = new Room("normal item 5");
		normali5.addItem(new LifePotion(2));
		Room normali6 = new Room("normal item 6");
		normali6.addItem(new PurseGold(10));
		Room normali7 = new Room("normal item 7");
		normali7.addItem(new StrengthPotion(5));
		// room with bandit
		Room bandit1 = new Room("bandit 1");
		bandit1.addItem(new OneArmedBandit(50));
		Room bandit2 = new Room("bandit 2");
		bandit2.addItem(new OneArmedBandit(150));
		//treasure
		Room treasure = new Room("treasure");
		ArrayList<Integer> choseLife = new ArrayList<Integer>();
		choseLife.add(25);
		choseLife.add(50);
		treasure.addItem(new LifePotion(choseLife));
		ArrayList<Integer> choseStrength = new ArrayList<Integer>();
		choseStrength.add(10);
		choseStrength.add(20);
		treasure.addItem(new StrengthPotion(choseStrength));
		ArrayList<Integer> choseGold = new ArrayList<Integer>();
		choseGold.add(50);
		choseGold.add(250);
		treasure.addItem(new PurseGold(choseGold));
		//room with monster
		Room monster1 = new Room("monster 1");
		monster1.addMonster(new Monster(monster1,5,5,5,"bat"));
		Room monster2 = new Room("monster 2");
		monster2.addMonster(new Monster(monster2,7,5,5,"spider"));
		Room monster3 = new Room("monster 3");
		monster3.addMonster(new Monster(monster3,5,7,5,"snake"));
		monster3.addMonster(new Monster(monster3,7,5,5,"spider"));
		Room monster4 = new Room("monster 4");
		monster4.addMonster(new Monster(monster4,10,15,10,"troll"));
		monster4.addMonster(new Monster(monster4,15,10,10,"skeleton"));
		Room monster5 = new Room("monster 5");
		monster5.addMonster(new Monster(monster5,15,20,25,"demon"));
		monster5.addMonster(new Monster(monster5,7,5,1,"imp1"));
		monster5.addMonster(new Monster(monster5,7,5,1,"imp2"));
		Room monster6 = new Room("monster 6");
		monster6.addMonster(new Monster(monster6,30,15,25,"angel"));
		monster6.addMonster(new Monster(monster6,7,5,1,"cherub1"));
		monster6.addMonster(new Monster(monster6,7,5,1,"cherub2"));
		//enigme
		//easy enigme
		Room easy = new Room("enigme");
		Enigme enigm1 = new Enigme("Qu'est ce qui est petit et marron ?","un marron");
		enigm1.addAnswer("un marron easy");
		enigm1.addAnswer("une chataigne");
		enigm1.addAnswer("un chocolat");
		enigm1.addAnswer("une chaise");
		easy.addItem(enigm1);
		//medium enigme
		Room medium = new Room("enigme medium");
		String ennon1 = "Plus j'ai de gardien, moins je suis gardé."+"\n";
		ennon1 += "Moins j'ai de gardien, plus je suis gardé."+"\n";
		ennon1 += "Qui suis-je ?";
		Enigme enigm2 = new Enigme(ennon1,"un secret");
		enigm2.addAnswer("un sentiment");
		enigm2.addAnswer("une maladie");
		enigm2.addAnswer("un secret");
		enigm2.addAnswer("un test");
		medium.addItem(enigm2);
		//hard enigme
		Room hard = new Room("enigme hard");
		String ennon2 = "Deux gardiens sont devant 2 portes. L’une mène au Paradis, et l’autre en Enfer. "+"\n"
				+ "L’un des gardiens est un menteur (il dit toujours le contraire de la vérité), et l’autre, au contraire, ne dit que la vérité. "+"\n"
				+ "On ne sait pas quel gardien est devant quelle porte. On veut bien sur savoir où est le Paradis. "+"\n"
				+ "Pour cela, on peut poser 1 question. Attention, on n’a qu’une seule question à poser à 1 seul gardien."+"\n"
				+ "Alors quelle est cette question pour ne pas moisir en Enfer ?";
		Enigme enigm3 = new Enigme(ennon2,"Est-ce que l’autre gardien me dirait que je suis devant la porte du paradis ?");
		enigm3.addAnswer("Est-ce que je suis devant la porte du paradis ?");
		enigm3.addAnswer("Est-ce que l’autre gardien me dirait que je suis devant la porte du paradis ?");
		enigm3.addAnswer("Je ne sais pas.");
		enigm3.addAnswer("Est-ce que je suis devant la porte de l'enfer ?");
		hard.addItem(enigm3);
		//lien vers l'enigme 3 ==> http://enigmesetdevinettes.com/enigme/enigme-paradis-enfer/
		//trap
		Room trap1 = new Room("trap 1");
		trap1.becomesAnTrap();
		Room trap2 = new Room("trap 2");
		trap2.becomesAnTrap();
		Room trap3 = new Room("trap 3");
		trap3.becomesAnTrap();
		//boss
		Room boss = new Room("boss");
		boss.addMonster(new Monster(boss,150,20,250,"Zangdar"));
		//exit
		Room exit = new Room("exit");
		exit.becomesAnExit();
		
		//initalize neighbours
		entry.addNeighbours(Direction.WEST, normal4);
		normal4.addNeighbours(Direction.SOUTH, trap1);
		normal4.addNeighbours(Direction.NORTH, monster3);
		normal1.addNeighbours(Direction.WEST, monster3);
		monster3.addNeighbours(Direction.WEST, normal3);
		normal4.addNeighbours(Direction.WEST, monster4);
		normal3.addNeighbours(Direction.SOUTH, monster4);
		monster4.addNeighbours(Direction.SOUTH, bandit1);
		monster4.addNeighbours(Direction.WEST, normal5);
		normal5.addNeighbours(Direction.WEST, treasure);
		normal5.addNeighbours(Direction.SOUTH, normali3);
		normali3.addNeighbours(Direction.WEST, monster5);
		monster5.addNeighbours(Direction.NORTH, treasure);
		monster5.addNeighbours(Direction.WEST, normali4);
		entry.addNeighbours(Direction.NORTH, normal1);
		normal1.addNeighbours(Direction.EAST, monster1);
		monster1.addNeighbours(Direction.EAST, normal2);
		monster1.addNeighbours(Direction.NORTH, trap3);
		normal1.addNeighbours(Direction.NORTH, normali1);
		normali1.addNeighbours(Direction.NORTH, monster2);
		normali1.addNeighbours(Direction.EAST, trap1);
		monster2.addNeighbours(Direction.WEST, easy);
		easy.addNeighbours(Direction.NORTH, normali2);
		easy.addNeighbours(Direction.WEST, normal8);
		normal8.addNeighbours(Direction.WEST, bandit2);
		bandit2.addNeighbours(Direction.NORTH, trap2);
		bandit2.addNeighbours(Direction.WEST, normal7);
		bandit2.addNeighbours(Direction.SOUTH, medium);
		normal7.addNeighbours(Direction.WEST, normali6);
		normal7.addNeighbours(Direction.SOUTH, monster6);
		monster6.addNeighbours(Direction.EAST, medium);
		monster6.addNeighbours(Direction.SOUTH, normal6);
		normal6.addNeighbours(Direction.SOUTH, treasure);
		normal6.addNeighbours(Direction.WEST, normali5);
		normali5.addNeighbours(Direction.SOUTH, hard);
		hard.addNeighbours(Direction.EAST, treasure);
		hard.addNeighbours(Direction.SOUTH, normali4);
		hard.addNeighbours(Direction.WEST, boss);
		boss.addNeighbours(Direction.WEST, exit);
		
		return entry;
	}
}
