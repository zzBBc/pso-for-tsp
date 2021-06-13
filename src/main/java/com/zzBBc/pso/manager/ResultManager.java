package com.zzBBc.pso.manager;

public class ResultManager {
	private int bestDistanceFound;
	private int highestDistanceFound;
	private int bestPossibleDistance;

	public void reset(int bestDistance) {
		bestDistanceFound = 0;
		highestDistanceFound = 0;
		bestPossibleDistance = bestDistance;
	}

	public int getBestDistanceFound() {
		return bestDistanceFound;
	}

	public int getBestPossibleDistance() {
		return bestPossibleDistance;
	}

	public void updateResults(int distance) {
		if(bestDistanceFound == 0 || bestDistanceFound > distance)
			bestDistanceFound = distance;

		// if(distance == bestPossibleDistance)
		// correctResultFound++;

		if(distance > highestDistanceFound)
			highestDistanceFound = distance;

		// int error = Math.abs(bestPossibleDistance - distance) /
		// bestPossibleDistance;
		//
		// totalError += error;
	}
}
