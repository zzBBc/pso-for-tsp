package com.zzBBc.pso.optimizer;

import java.util.Random;

import com.zzBBc.pso.Constants;
import com.zzBBc.pso.entity.Route;
import com.zzBBc.pso.manager.RouteManager;

public class TspParticleOptimizer implements ParticleOptimizer {
	private RouteManager routeManager = new RouteManager();

	@Override
	public Integer[] getOptimizedDestinationIndex(Route currentRoute, Route personBestRoute, Route localBestRoute) {
		double currentVelocity = routeManager.updateVelocity(currentRoute, Constants.W, 1);
		double personBestVelocity
				= routeManager.updateVelocity(personBestRoute, Constants.C1, new Random().nextDouble());
		double localBestVelocity = routeManager.updateVelocity(localBestRoute, Constants.C2, new Random().nextDouble());
		double totalVelocity = currentVelocity + personBestVelocity + localBestVelocity;

		// update the Segment size for each Route
		currentRoute.setSegmentSize(routeManager.getSectionSize(currentRoute, currentVelocity, totalVelocity));
		personBestRoute.setSegmentSize(routeManager.getSectionSize(personBestRoute, personBestVelocity, totalVelocity));
		localBestRoute.setSegmentSize(routeManager.getSectionSize(localBestRoute, localBestVelocity, totalVelocity));

		try{
			return routeManager.addSections(new Route[]{localBestRoute, personBestRoute, currentRoute});
		} catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
