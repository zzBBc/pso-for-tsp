package com.zzBBc.pso.entity;

import java.util.Arrays;

public abstract class Route {
	private double tourDistance;
	private Integer[] destinationIndex;
	private double[][] distanceLookup;
	private int segmentSize;

	@Override
	public abstract Route clone();

	public abstract void copyTo(Route bestGlobalItinery);

	protected abstract double calculateTotalDistance();

	public double getTourDistance() {
		return tourDistance;
	}

	public void setTourDistance(double tourDistance) {
		this.tourDistance = tourDistance;
	}

	public Integer[] getDestinationIndex() {
		return destinationIndex;
	}

	public void setDestinationIndex(Integer[] destinationIndex) {
		this.destinationIndex = destinationIndex.clone();
	}

	public double[][] getDistanceLookup() {
		return distanceLookup;
	}

	public void setDistanceLookup(double[][] distanceLookup) {
		this.distanceLookup = distanceLookup;
	}

	public int getSegmentSize() {
		return segmentSize;
	}

	public void setSegmentSize(int segmentSize) {
		this.segmentSize = segmentSize;
	}

	@Override
	public String toString() {
		return "\r\nRoute [destinationIndex=" + Arrays.toString(destinationIndex)
				+ ", \r\ntourDistance="
				+ tourDistance
				+ "]";
	}


}
