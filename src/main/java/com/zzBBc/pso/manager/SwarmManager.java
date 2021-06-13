package com.zzBBc.pso.manager;

import com.zzBBc.pso.entity.Particle;
import com.zzBBc.pso.entity.TspData;
import com.zzBBc.pso.optimizer.SwarmOptimizer;

public class SwarmManager {
	private SwarmOptimizer swarmOptimizer = new SwarmOptimizer();

	public Particle[] buildSwarm(TspData tspData) {
		return swarmOptimizer.buildSwarm(tspData);
	}

	public double getBestPossibleDistance(TspData tspData) {
		return calculateBestDistance(tspData);
	}

	private double calculateBestDistance(TspData tspData) {
		Integer[] route = tspData.getBestRoute();

		double totalDistance = 0;
		for(int i = 0; i < route.length - 1; i++)
			totalDistance += tspData.getDistanceLookup()[route[i]][route[i + 1]];

		totalDistance += tspData.getDistanceLookup()[route[route.length - 1]][route[0]];

		return totalDistance;
	}

	public int optimize(Particle[] swarm) {
		return swarmOptimizer.optimize(swarm, true);
	}

	public SwarmOptimizer getSwarmOptimizer() {
		return swarmOptimizer;
	}

}
