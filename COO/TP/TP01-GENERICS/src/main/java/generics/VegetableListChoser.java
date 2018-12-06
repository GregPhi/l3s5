package generics;

import scanner.*;
import java.util.*;

public class VegetableListChoser {

    // methode chose
    // elle est similaire a celle de ListChoser simplement elle ne fonctionne
    // qu'avec des listes d'objets de type Legume (cf. interface Legume)
    // quel changement apporter a la methode de ListChoser  ?
	public <T extends Vegetable> T chose(String msg, List<T> liste) throws IndexOutOfBoundsException{
		System.out.println("Enter an integer from 0 to "+liste.size()+" : ");
		int j = ScannerInt.readInt(liste.size()+1);
		System.out.println("you typed : "+j);
		if(j<=liste.size() && j>0) {
			return liste.get(j-1);
		}if(j==0) {
			return null;
		}else {
			throw new IndexOutOfBoundsException("Wrong index "+j);
		}
	}

    public static void main(String[] args) {
		List<Carrot> lCarrots = new ArrayList<Carrot>();
		lCarrots.add(new Carrot(1));
		lCarrots.add(new Carrot(2));
		lCarrots.add(new Carrot(3));

		List<Apple> lApples = new ArrayList<Apple>();
		lApples.add(new Apple(1));
		lApples.add(new Apple(2));
		lApples.add(new Apple(3));

		VegetableListChoser lcl = new VegetableListChoser();

		Carrot chosenCarrot = lcl.chose("which carrot ? ", lCarrots);
		System.out.println("You have chosen : " + chosenCarrot);

		// NE COMPILE PAS
		// Apple chosenApple = lcl.chose("which apple ? ",lApples);


    }
}
