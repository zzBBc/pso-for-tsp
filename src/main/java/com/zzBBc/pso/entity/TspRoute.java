package com.zzBBc.pso.entity;

public class TspRoute extends Route {

	public TspRoute() {

	}

	public TspRoute(int[][] distanceLookup, Integer[] destinationIndex) {
		setDistanceLookup(distanceLookup);
		setDestinationIndex(destinationIndex);

		calculateTotalDistance();
		setSegmentSize(-1);
	}

	@Override
	public Route clone() {
		Route route = new TspRoute();

		route.setDestinationIndex(getDestinationIndex());
		route.setDistanceLookup(getDistanceLookup());
		route.setTourDistance(getTourDistance());

		return route;
	}

	@Override
	public double calculateTotalDistance() {
		Integer[] destinationIndex = getDestinationIndex();
		int[][] distanceLookup = getDistanceLookup();

		double total = 0;

		for(int i = 0; i < destinationIndex.length; i++){
			// Don't forget to add in the distance between the
			// last city in the array and the first city
			if(i == destinationIndex.length - 1){
				total += distanceLookup[destinationIndex[i]][destinationIndex[0]];
			} else{
				total += distanceLookup[destinationIndex[i]][destinationIndex[i + 1]];
			}
		}

		setTourDistance(total);

		return total;
	}

	@Override
	public void copyTo(Route targetItinery) {
		targetItinery.setDistanceLookup(getDistanceLookup());
		targetItinery.setDestinationIndex(getDestinationIndex());

		targetItinery.setTourDistance(getTourDistance());
	}
}
