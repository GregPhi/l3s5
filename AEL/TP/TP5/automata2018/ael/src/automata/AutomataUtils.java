package automata;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
/**
 *
 * @author Bruno.Bogaert (at) univ-lille.fr
 *
 */
public class AutomataUtils {

	/**
	 * Extends automaton a, so that it accepts also this word.
	 * Created states are prefixed by 'q_'
	 * @param word : word to be accepted
	 * @param a : target automaton
	 */
	public static void addSingleton(String word, AutomatonBuilder a) {
		addSingleton(word, a, "q");
	}

	/**
	 * Extends automaton a so that it accepts also this word.
	 * Created states are prefixed by namePrefix followed by '_'
	 * @param word : word to be accepted
	 * @param a : target automaton
	 * @param namePrefix : prefix to use for state names.
	 */
	public static void addSingleton(String word, AutomatonBuilder a, String namePrefix) {
		String init = namePrefix+"_epsilon";
		a.addNewState(init);
		a.setInitial(init);
		String begin;
		char l;
		for(int i = 0; i < word.length(); i++) {
			begin = word.substring(0,i+1);
			l = word.charAt(i);
			a.addNewState(namePrefix+"_"+begin);
			a.addTransition(i, l, i+1);
		}
		a.setAccepting(namePrefix+"_"+word);
	}

	/**
	 * Extends automaton a so that it accepts also this finite language
	 * created states are prefixed by namePrefix followed by '_'
	 * @param finiteLanguage : set of words to be accepted
	 * @param a : target automaton
	 */
	public static void addFiniteSet(Iterable<String> finiteLanguage, AutomatonBuilder a) {
		int i = 0;
		for(String s : finiteLanguage) {
			addSingleton(s,a,"q"+i);
			i++;
		}
	}

	/**
	 * Extends automaton a so that it accepts also language denoted by exp
	 * created states are prefixed by namePrefix followed by '_'
	 * @param exp : flat regular expression (only letters and *)
	 * @param a : target automaton
	 */
	public static void addFlatExp(String exp, AutomatonBuilder a) {
		addFlatExp(exp, a, "q");
	}

	/**
	 * Extends automaton a so that it accepts also language denoted by exp
	 * created states are prefixed by namePrefix followed by '_'
	 * @param exp : flat regular expression (only letters and *)
	 * @param a : target automaton
	 * @param namePrefix : prefix to use for state names.
	 */
	public static void addFlatExp(String exp, AutomatonBuilder a, String namePrefix) {
		String begin = namePrefix+"_epsilon";
		a.setInitial(a.addNewState(begin));
		String state;
		for(int i = 0; i < exp.length(); i++) {
			if((exp.length() - i > 1) && (exp.charAt(i+1)=='*')) {
				a.addTransition(begin, exp.charAt(i), begin);
				i++;
			}else {
				state = namePrefix+"_"+exp.substring(0,i+1);
				a.addNewState(state);
				a.addTransition(begin, exp.charAt(i), state);
				begin = state;
			}
		}
		a.setAccepting(begin);
	}

	/**
	 * Transpose automaton
	 * Note : mirror is cleared before the operation.
	 *
	 * @param original : automaton to be transposed
	 * @param mirror : receive the transposed automaton
	 */
	public static void transpose(Automaton original, AutomatonBuilder mirror) {
		Set<Character> charac = original.usedAlphabet();
		Set<State> states = original.getStates();
		Set<State> transition;
		mirror.clear();
		for(State s : states) {
			try{
				mirror.addNewState(s.getName());
			}catch(Exception e) {
				
			}
			if(original.isInitial(s.getName())) {
				mirror.setAccepting(s.getName());
			}
			if(original.isAccepting(s.getName())) {
				mirror.setInitial(s.getName());
			}
			for(Character c : charac) {
				transition = original.getTransitionSet(s, c);
				for(State st : transition) {
					try {
						mirror.addNewState(st.getName());
						mirror.addTransition(st.getName(), c, s.getName());
					}catch(Exception e) {
						
					}					
				}
			}
		}
	}

	/**
	 * Determinization of nfa automaton.
	 * Note : dfa is cleared before the operation.
	 * @param nfa : non deterministic automaton (to be determinize)
	 * @param dfa : receive determinization result
	 */
	@SuppressWarnings("unused")
	public static void determinize(Automaton nfa, AutomatonBuilder dfa) {
		// For each computed state set from nfa, a corresponding state has to be created in dfa
		// map represents relationship  between nfa state set (key) and created dfa state (value)
		Map<Set<State>, State> map = new HashMap<Set<State>, State>();

		// stack todo contains state sets whose transitions have not yet been computed
		Stack<Set<State>> todo = new Stack<Set<State>>();

		Set<State> transitionSet;
		State computedState;
		State emptySet = dfa.addNewState("emptyState");

		dfa.clear();

		Set<State> startSet = nfa.getInitialStates();

		// create matching state in DFA
		State start = dfa.addNewState(startSet.toString()); // state creation
		map.put(startSet, start);  // record relationship in map

		dfa.setAccepting(start); // start is the unique initial state of dfa

		todo.push(startSet); // put it in todo list.

		while (! todo.isEmpty()) {
			Set<State> fromSet = todo.pop(); // pick a state from todo list
			for(char l : nfa.usedAlphabet()) {
				transitionSet = nfa.getTransitionSet(fromSet, l);
				if(!map.containsKey(transitionSet)) {
					start = dfa.addNewState(transitionSet.toString());
					map.put(transitionSet, start);
					todo.push(startSet);
					dfa.addTransition(fromSet.toString(), l, startSet.toString());
				}
			}
		}
		for (Set<State> qSet : map.keySet()) {	// foreach computed state set
			for(State s : qSet) {
				if(nfa.isAccepting(s)) {
					dfa.setAccepting(map.get(qSet));
					break;
				}
			}
		}
	}

	public static void minimalise(Automaton a, AutomatonBuilder dest){
		//det(tr(det(tr(A))))
		dest.clear();
		NDAutomaton temp1 = new NDAutomaton();
		NDAutomaton temp2 = new NDAutomaton();
		NDAutomaton temp3 = new NDAutomaton();
		AutomataUtils.transpose(a,temp1);
		AutomataUtils.determinize(temp1,temp2);
		AutomataUtils.transpose(temp2,temp3);
		AutomataUtils.determinize(temp3,dest);
	}
}
