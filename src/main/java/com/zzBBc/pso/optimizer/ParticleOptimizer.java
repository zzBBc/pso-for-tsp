package com.zzBBc.pso.optimizer;

import com.zzBBc.pso.entity.Route;

public interface ParticleOptimizer {
	Integer[] getOptimizedDestinationIndex(Route currentRoute, Route personBestRoute, Route localBestRoute);
}
