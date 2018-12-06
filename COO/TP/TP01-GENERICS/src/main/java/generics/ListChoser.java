package generics;

import scanner.*;

import java.util.*;

public class ListChoser {

	// Definir la methode chose, qui prend en premier parametre
	// un message sous forme de chaene de caracteres et en second une liste.
	// Cette liste est typee mais sans restriction sur les types admis.
	// Cette methode propose de choisir un element de la liste en saisissant
	// sa position dans la liste.
	// L'element choisi est retourne par la methode, null si le choix 0 est
	// fait.
	//
	// ...methode chose...
	//
	public <T> T chose(String msg, List<T> liste) throws IndexOutOfBoundsException{
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
		// JEU DE TEST

		List<Carrot> lCarrots = new ArrayList<Carrot>();
		lCarrots.add(new Carrot(1));
		lCarrots.add(new Carrot(2));
		lCarrots.add(new Carrot(3));

		List<Apple> lApples = new ArrayList<Apple>();
		lApples.add(new Apple(1));
		lApples.add(new Apple(2));
		lApples.add(new Apple(3));

		ListChoser lc = new ListChoser();

		Carrot chosenCarrot = lc.chose("which carrot ? ", lCarrots);
		System.out.println("you have chosen : " + chosenCarrot);

		Apple chosenApple = lc.chose("which appel? ", lApples);
		System.out.println("you have chosen : " + chosenApple);
	}
}
