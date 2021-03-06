package mycontroller.strategy;

import mycontroller.MapRecorder;
import mycontroller.MyAIController;
import utilities.Coordinate;
import mycontroller.Pathway;
import mycontroller.pipeline.SimplifyPath;
import mycontroller.pipeline.Step;
import mycontroller.pipeline.astar.AStar;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Collections;

public class ExitStrategy implements IEscapeStrategy {
	
	public ExitStrategy() {
		
	}
	
	// initialise the pipeline
	Step<Coordinate[], Pathway> findRoute = Step.of(AStar::findShortestPath);
	Step<Coordinate[], Pathway> simpleRoute = findRoute.add(SimplifyPath::simplifyPath); 
	
	@Override
	public Pathway findDestination(MyAIController myAIController) {
		ArrayList<Coordinate> finishCoords = MapRecorder.finishLocations;
		return evaluateBest(finishCoords, myAIController, simpleRoute);
	}

	@Override
	public boolean isFinished(MyAIController myAIController) {
		return false;
	}
	
	@Override
	public boolean isTakeover(MyAIController myAIController) {
		return false;
	}

}
