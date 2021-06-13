package com.zzBBc.pso.entity;

public class TspData {
	private double[][] distanceLookup;
	private Integer[] bestRoute;
	private int cityCount;

	public TspData() {
	}

	public Integer[] getBestRoute() {
		return bestRoute;
	}

	public void setBestRoute(Integer[] bestRoute) {
		this.bestRoute = bestRoute;
	}

	public int getCityCount() {
		return cityCount;
	}

	public void setCityCount(int cityCount) {
		this.cityCount = cityCount;
	}

	public double[][] getDistanceLookup() {
		return distanceLookup;
	}

	public void setDistanceLookup(double[][] distanceLookup) {
		this.distanceLookup = distanceLookup;
		cityCount = distanceLookup.length;
	}

}
