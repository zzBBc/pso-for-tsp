package com.zzBBc.pso.entity;

import java.util.ArrayList;

import com.zzBBc.pso.optimizer.ParticleOptimizer;
import com.zzBBc.pso.optimizer.TspParticleOptimizer;

public class TspParticle extends Particle {
	private ParticleOptimizer particleOptimizer = new TspParticleOptimizer();

	public TspParticle(Route route) {
		setCurrentRoute(route);
		setPersonalBestRoute(route);
		setInformersList(new ArrayList<>());
	}

	@Override
	public double optimize() throws Exception {
		Route localBestRoute = getLocalBestRoute();
		setLocalBestRoute(localBestRoute);

		Route personalBestRoute = getPersonalBestRoute();
		Route currentRoute = getCurrentRoute();

		Integer[] destinationIndex
				= particleOptimizer.getOptimizedDestinationIndex(currentRoute, personalBestRoute, localBestRoute);
		currentRoute.setDestinationIndex(destinationIndex);
		setCurrentRoute(currentRoute);

		double currentDistance = currentRoute.calculateTotalDistance();

		if(currentDistance < personalBestRoute.calculateTotalDistance())
			personalBestRoute.setDestinationIndex(currentRoute.getDestinationIndex());

		return currentDistance;
	}

	@Override
	public Route getLocalBestRoute() {
		Route localBestRoute = getPersonalBestRoute();

		for(Particle particle: getInformersList())
			if(localBestRoute.getTourDistance() > particle.getPersonalBestRoute().getTourDistance())
				localBestRoute = particle.getPersonalBestRoute();

		return localBestRoute;
	}
}
