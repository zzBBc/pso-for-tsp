package com.zzBBc.pso.entity;

public abstract class Particle {
	private Route currentRoute;
	private Route personalBestRoute;

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
		this.personalBestRoute = personalBestRoute;
	}

	@Override
	public String toString() {
		return "Particle [\r\ncurrentRoute=" + currentRoute
				+ ", \r\npersonalBestRoute="
				+ personalBestRoute
				+ ", \r\n"
				+ "]";
	}

	public abstract double optimize(Route globalBestRoute);

}
