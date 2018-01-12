package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.AbstractState.MOVE;
import model.State;

public class CallumAnderson2048 extends AbstractPlayer {
	
	private final Random random = new Random();

	@Override
	public MOVE getMove(State game) {

		pause();
		
		List<MOVE> bestMoves = new ArrayList<MOVE>();
		
		double bestScore = Double.NEGATIVE_INFINITY;
		double average = 0;
		
		for(MOVE move : game.getMoves()) {
			
			game.move(move);
			for(int i = 0; i < 200; i++){
				average += game.rollout();
			}
			average = average/200;
			game.undo();
			
			double score = average;
			if(score > bestScore) {
				bestMoves.clear();
				bestMoves.add(move);
				bestScore = score;
			} else if (score == bestScore) {
				bestMoves.add(move);
			}
			
		}
		
		return bestMoves.get(random.nextInt(bestMoves.size()));
	}

	@Override
	public int studentID() {
		return 201181111;
	}

	@Override
	public String studentName() {
		return "Phil Rodgers";
	}

}
