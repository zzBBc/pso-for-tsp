package com.zzBBc.pso.optimizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zzBBc.pso.Constants;
import com.zzBBc.pso.entity.Particle;
import com.zzBBc.pso.entity.Route;
import com.zzBBc.pso.entity.TspData;
import com.zzBBc.pso.entity.TspParticle;
import com.zzBBc.pso.entity.TspRoute;

public class SwarmOptimizer {
	private Route globalBestRoute;

	public Particle[] buildSwarm(TspData tspData) {
		Particle[] swarm = new Particle[Constants.SWARM_SIZE];

		for(int i = 0; i < Constants.SWARM_SIZE; i++){
			Route route = getNewRoute(tspData);

			swarm[i] = new TspParticle(route);
		}

		// Integer[] particleIndex = initArray(Constants.SWARM_SIZE);
		//
		// updateInformers(swarm, particleIndex);

		return swarm;
	}

	private Route getNewRoute(TspData tspData) {
		int numberOfDestinations = tspData.getCityCount();

		Integer[] destinationIndex = initArray(numberOfDestinations);

		Route route = new TspRoute(tspData.getDistanceLookup(), destinationIndex);

		return route;
	}

	public Integer[] initArray(int cityCount) {
		List<Integer> array = new ArrayList<>();

		for(int i = 0; i < cityCount; i++){
			array.add(i);
		}

		Collections.shuffle(array);

		return array.toArray(new Integer[array.size()]);
	}

	public int optimize(Particle[] swarm, boolean isPrintBestDistance) {
		globalBestRoute = swarm[0].getPersonalBestRoute().clone();

		// Integer[] particleIndex = initArray(Constants.SWARM_SIZE);

		int epoch = 0;

		while(epoch < Constants.MAX_EPOCHS){
			// boolean isDistanceImproved = false;

			for(Particle particle: swarm){
				double distance = 0;

				try{
					distance = particle.optimize(globalBestRoute, epoch);
				} catch(Exception e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(distance < globalBestRoute.getTourDistance()){
					globalBestRoute = particle.getPersonalBestRoute().clone();

					// isDistanceImproved = true;
					if(isPrintBestDistance){
						System.out.println(globalBestRoute);
						System.out.println(distance);
						System.out.println();
					}
				}
			}

			// if(!isDistanceImproved){
			// staticEpochs++;
			//
			// if(staticEpochs == Constants.MAX_STATIC_EPOCHS){
//					updateInformers(swarm, particleIndex);
			//// staticEpochs = 0;
			// break;
			// }
			// }

			epoch++;
		}

		return (int) globalBestRoute.getTourDistance();
	}

	public Route getGlobalBestRoute() {
		return globalBestRoute;
	}

	public void setGlobalBestRoute(Route globalBestRoute) {
		this.globalBestRoute = globalBestRoute;
	}

}
