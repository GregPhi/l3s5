package automata;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Implémentation d'un automate non déterministe.
 * 
 * @author Bruno.Bogaert (at) univ-lille1.fr
 *
 */
public class NDAutomaton extends AbstractNDAutomaton implements Recognizer, AutomatonBuilder {

	/**
	 * Fake implementation : always return false.
	 */
	public boolean accept(String word) {
		Set<State> transitionSet = new HashSet<State>();
		transitionSet = this.getInitialStates();
		for(int i = 0; i < word.length(); i++) {
			transitionSet = this.getTransitionSet(transitionSet, word.charAt(i));
			if(transitionSet.isEmpty()) {
				return false;
			}
		}
		for(State s : transitionSet) {
			if(this.isAccepting(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	* Calcul de l'ensemble des états pouvant être obtenus depuis un ensemble d'états
	* @param fromSet : ensemble d'états
	* @param letter : lettre de l'alphabet
	* @return ensemble d'états pouvant être obtenus en lisant letter,
	* en partant de n'importe lequel des états de l'ensemble fromSet
	*/
	public Set<State> getTransitionSet(Set<State> fromSet, char letter) {
		Set<State> allTransition = new HashSet<State>();
		for(State s : fromSet) {
			allTransition.addAll(this.getTransitionSet(s,letter));
		}
		return allTransition;
	}



}
