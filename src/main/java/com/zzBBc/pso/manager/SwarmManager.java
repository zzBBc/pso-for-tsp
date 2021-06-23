package com.zzBBc.pso.manager;

import com.zzBBc.pso.Constants;
import com.zzBBc.pso.entity.Particle;
import com.zzBBc.pso.entity.TspData;
import com.zzBBc.pso.optimizer.SwarmOptimizer;

public class SwarmManager {
	private SwarmOptimizer swarmOptimizer = new SwarmOptimizer();

	public Particle[] buildSwarm(TspData tspData) {
		return swarmOptimizer.buildSwarm(tspData);
	}

	public int optimize(Particle[] swarm) {
		return swarmOptimizer.optimize(swarm, Constants.PRINT_DISTANCE_IMPROVED);
	}

	public SwarmOptimizer getSwarmOptimizer() {
		return swarmOptimizer;
	}

}
