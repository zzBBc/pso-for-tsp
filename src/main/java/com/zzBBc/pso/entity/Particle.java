package com.zzBBc.pso.entity;

import java.util.List;

public abstract class Particle {
	private Route currentRoute;
	private Route personalBestRoute;
	private Route localBestRoute;
	private List<Particle> informersList;

	public abstract double optimize() throws Exception;

	public Route getCurrentRoute() {
		return currentRoute;
	}

	public void setCurrentRoute(Route currentRoute) {
		this.currentRoute = currentRoute;
	}

	public Route getPersonalBestRoute() {
		return personalBestRoute;
	}

	public void setPersonalBestRoute(Route personalBestRoute) {
		this.personalBestRoute = personalBestRoute.clone();
	}

	public List<Particle> getInformersList() {
		return informersList;
	}

	public void setInformersList(List<Particle> informersList) {
		this.informersList = informersList;
	}

	public Route getLocalBestRoute() {
		return localBestRoute;
	}

	public void setLocalBestRoute(Route localBestRoute) {
		this.localBestRoute = localBestRoute;
	}

	@Override
	public String toString() {
		return "Particle [\r\ncurrentRoute=" + currentRoute
				+ ", \r\npersonalBestRoute="
				+ personalBestRoute
				+ ", \r\nlocalBestRoute="
				+ (localBestRoute != null? localBestRoute : "")
				+ "]";
	}

}
