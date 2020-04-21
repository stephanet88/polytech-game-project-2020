package automaton;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import playerActions.*;
import playerCond.*;
import game.*;

import automata.ast.AST;
import automata.ast.Action;
import automata.ast.Behaviour;
import automata.ast.BinaryOp;
import automata.ast.Category;
import automata.ast.Condition;
import automata.ast.Direction;
import automata.ast.FunCall;
import automata.ast.IVisitor;
import automata.ast.Key;
import automata.ast.Mode;
import automata.ast.Transition;
import automata.ast.UnaryOp;
import automata.ast.Underscore;
import automata.ast.Value;

public class Interpretor implements IVisitor {

	List<String> statesNames;
	List<automaton.State> states;
	HashMap<State, automaton.Transition[]> mapTransitions;
	
	public Interpretor() {
	}

	@Override
	public Object visit(Category cat) {
		String name = cat.toString();
		switch(name) {
			case "A":
			case "C" :
			case "D" :
			case "G" :
			case "J" :
			case "M" :
			case "O" :
			case "P" :
			case "T" :
			case "V" :
			case "@" :
			case "_" :
				return new game.Category(name);
				
			default :
				return null;
		}
	}

	@Override
	public Object visit(Direction dir) {
		String name = dir.toString();
		switch(name) {
			case "N":
			case "E" :
			case "W" :
			case "S" :
			case "F" : // Front
			case "B" : // Back
			case "L" : // On my left
			case "R" : // On my right
				return new game.Direction(name);
				
			default :
				return null;
		}
	}

	@Override
	public Object visit(Key key) {
		String name = key.toString();
		switch (name) {
		
		// Flèches directionnelles
		case "FU":
			return 0x26;
		case "FD" :
			return 0x28;
		case "FL" :
			return 0x25;
		case "FR" : 
			return 0x27;
			
		// Touches Spéciales
		case "SPACE" :
			return 0x20;
		case "ENTER" :
			return (int)'\n';
			
		// Lettres et chiffres	
		default :
			return (int)name.charAt(0);
		}
	}

	@Override
	public Object visit(Value v) {
		return v.value;
	}

	@Override
	public Object visit(Underscore u) {
		System.out.println(u.toString());
		return null;
	}

	@Override
	public void enter(FunCall funcall) {
	}

	@Override
	public Object exit(FunCall funcall, List<Object> parameters) {
		String name = funcall.name;
		int percent = funcall.percent;
		int size = parameters.size();
		ListIterator<Object> parameter = parameters.listIterator();
		switch(name) {
		
		// ACTIONS
		
		case "Store" :
			return new ActionStore(percent);
		case "Explode":
			return new ActionExplode(percent);
		case "Get":
			return new ActionGet(percent);
		case "Hit":
			if (size == 1)
				return new ActionHit((game.Direction) parameter.next(), percent);
			else
				return new ActionHit(percent);
		case "Egg" :
			if (size == 1)
				return new ActionEgg((game.Direction) parameter.next(), percent);
			else
				return new ActionEgg(percent);
		case "Pop" :
			if (size == 1)
				return new ActionPop((game.Direction) parameter.next(), percent);
			else
				return new ActionPop(percent);
		case "Wizz" :
			if (size == 1)
				return new ActionWizz((game.Direction) parameter.next(), percent);
			else
				return new ActionWizz(percent);
		case "Jump" :
			if (size == 1)
				return new ActionJump((game.Direction) parameter.next(), percent);
			else
				return new ActionJump(percent);
		case "Move" :
			if (size == 1)
				return new ActionMove((game.Direction) parameter.next(), percent);
			else
				return new ActionMove(percent);
		case "Pick" :
			if (size == 1)
				return new ActionPick((game.Direction) parameter.next(), percent);
			else
				return new ActionPick(percent);
		case "Power" :
			return new ActionPower(percent);
		case "Turn" :
			if (size == 1)
				return new ActionTurn((game.Direction) parameter.next(), percent);
			else
				return new ActionTurn(percent);
		
		// CONDITIONS
			
		case "True":
			return new CondTrue();
		case "Cell" :
			return new CondCell((game.Direction) parameter.next(), (game.Category) parameter.next());
		case "Closest" :
			return new CondClosest((game.Category) parameter.next(), (game.Direction) parameter.next());
		case "GotPower" :
			return new CondGotPower();
		case "GotStuff" :
			return new CondGotStuff();
		case "Key" :
			return new CondKey((int) parameter.next());
		case "MyDir" :
			return new CondMyDir((game.Direction) parameter.next());
			
			
		default :
			return null;
		}
	}

	@Override
	public Object visit(BinaryOp operator, Object left, Object right) {
		return new CondBinaryOp(operator.operator, (ICondition) left, (ICondition) right);
	}

	@Override
	public Object visit(UnaryOp operator, Object expression) {
		return new CondUnaryOp(operator.operator, (ICondition) expression);
	}

	@Override
	public Object visit(automata.ast.State state) {
		// Si un état avec ce nom a déjà été créé, on le renvoie
		if (statesNames.contains(state.name)) {
			int index = statesNames.indexOf(state.name);
			return states.get(index);
		}
		statesNames.add(state.name);
		automaton.State newState = new automaton.State(state.name);
		states.add(newState);
		return newState;
	}

	@Override
	public void enter(Mode mode) {
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public Object exit(Mode mode, Object source_state, Object behaviour) {
		ListIterator<automaton.Transition> transitionIterator = ((LinkedList<automaton.Transition>)behaviour).listIterator();
		automaton.Transition tr;
		int size = ((LinkedList<automaton.Transition>)behaviour).size();
		automaton.Transition[] transitions = new automaton.Transition[size];
		int i = 0;
		while (transitionIterator.hasNext()) {
			tr = transitionIterator.next();
			transitions[i++] = tr;
		}
		mapTransitions.put((automaton.State)source_state, transitions);
		return null;
	}

	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		return transitions;
	}

	@Override
	public void enter(Condition condition) {
	}

	@Override
	public Object exit(Condition condition, Object expression) {
		return expression;
	}
		

	@Override
	public void enter(Action action) {
	}
	
	@Override
	public Object exit(Action action, List<Object> funcalls) {
		ListIterator<Object> actionIterator = funcalls.listIterator();
		int size = funcalls.size();
		int index = 0;
		float sumPercentage = 0;
		LinkedList<Integer> indexNoPercentage = new LinkedList<Integer>();
		playerActions.Action actions[] = new playerActions.Action[size];
		playerActions.Action act;
		while (actionIterator.hasNext()) {
			act = (playerActions.Action) actionIterator.next();
			if (act.percentage != -1) {
				sumPercentage += act.percentage;
			} else {
				indexNoPercentage.add(index);
			}
			actions[index] = act;
			index++;
		}
		
		// Dans le cas où il existe des actions sans pourcentage (= -1), on déduit le pourcentage réel
		if (indexNoPercentage.size() != 0) {
			ListIterator<Integer> indexIterator = indexNoPercentage.listIterator();
			float newPercentage = (100 - sumPercentage) / (float) indexNoPercentage.size();
			while (indexIterator.hasNext()) {
				actions[indexIterator.next()].percentage = newPercentage;
			}
		}
		return actions;
	}

	@Override
	public Object visit(Transition transition, Object condition, Object action, Object target_state) {
		return new automaton.Transition((ICondition) condition, (automaton.State) target_state, (IAction[]) action);
	}

//	@Override
	public void enter(Automaton automaton) {
		statesNames = new LinkedList<String>();
		states = new LinkedList<automaton.State>();
		mapTransitions = new HashMap<automaton.State, automaton.Transition[]>(); 
	}

//	@Override
	public Object exit(Automaton automaton, Object initial_state, List<Object> modes) {
		return new automaton.Automaton((automaton.State)initial_state, mapTransitions);
	}

	@Override
	public Object visit(AST bot, List<Object> automata) {
		return automata;
	}

	@Override
	public void enter(automata.ast.Automaton automaton) {
		statesNames = new LinkedList<String>();
		states = new LinkedList<automaton.State>();
		mapTransitions = new HashMap<automaton.State, automaton.Transition[]>();
	}

	@Override
	public Object exit(automata.ast.Automaton automaton, Object initial_state, List<Object> modes) {
		return new automaton.Automaton((automaton.State)initial_state, mapTransitions);
	}

}
