package com.zzBBc.pso.entity;

import com.zzBBc.pso.optimizer.ParticleOptimizer;
import com.zzBBc.pso.optimizer.TspParticleOptimizer;

public class TspParticle extends Particle {
	private ParticleOptimizer particleOptimizer = new TspParticleOptimizer();

	public TspParticle(Route route) {
		setCurrentRoute(route);
		setPersonalBestRoute(route);
	}

	@Override
	public double optimize(Route globalBestRoute) {
		Route personalBestRoute = getPersonalBestRoute();
		Route currentRoute = getCurrentRoute();

		Integer[] destinationIndex =
				particleOptimizer.getOptimizedDestinationIndex(currentRoute, personalBestRoute, globalBestRoute);
		currentRoute.setDestinationIndex(destinationIndex);
		// setCurrentRoute(currentRoute);

		double currentDistance = currentRoute.calculateTotalDistance();

		if(currentDistance < personalBestRoute.calculateTotalDistance()){
			personalBestRoute.setDestinationIndex(destinationIndex);
		}

		return currentDistance;
	}
}
