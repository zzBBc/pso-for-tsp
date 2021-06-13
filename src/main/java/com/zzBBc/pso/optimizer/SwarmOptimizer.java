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
	private Route bestGlobalItinery;

	public Particle[] buildSwarm(TspData tspData) {
		Particle[] swarm = new Particle[Constants.SWARM_SIZE];

		for(int i = 0; i < Constants.SWARM_SIZE; i++){
			Route route = getNewRoute(tspData);

			swarm[i] = new TspParticle(route);
		}

		Integer[] particleIndex = initArray(Constants.SWARM_SIZE);

		updateInformers(swarm, particleIndex);

		return swarm;
	}

	private void updateInformers(Particle[] swarm, Integer[] particleIndexes) {
		List<Particle> informers = new ArrayList<>();
		int informerCount = Constants.MAX_INFORMERS + 1;

		for(int i = 1; i < particleIndexes.length + 1; i++){
			Integer particleIndex = particleIndexes[i - 1];
			informers.add(swarm[particleIndex]);

			if(i % informerCount == 0){
				addInformersToParticle(informers);

				informers.clear();
			}
		}

		// the number of informers added here
		// will be less than the informer count
		addInformersToParticle(informers);
	}

	private void addInformersToParticle(List<Particle> informers) {
		for(Particle tspParticle: informers){
			tspParticle.getInformersList().clear();
			tspParticle.getInformersList().addAll(informers);
			tspParticle.getInformersList().remove(tspParticle);
		}
	}

	private Route getNewRoute(TspData tspData) {
		int numberOfDestinations = tspData.getCityCount();

		Integer[] destinationIndex = initArray(numberOfDestinations);

		Route route = new TspRoute(tspData.getDistanceLookup(), destinationIndex);

		return route;
	}

	public Integer[] initArray(int cityCount) {
		List<Integer> array = new ArrayList<>();

		for(int i = 0; i < cityCount; i++)
			array.add(i);

		Collections.shuffle(array);

		return array.toArray(new Integer[array.size()]);
	}

	public int optimize(Particle[] swarm, boolean isPrintBestDistance) {
		bestGlobalItinery = swarm[0].getCurrentRoute().clone();

		Integer[] particleIndex = initArray(Constants.SWARM_SIZE);

		int epoch = 0;
		int staticEpochs = 0;

		while(epoch < Constants.MAX_EPOCHS){
			boolean isDistanceImproved = false;

			for(Particle particle: swarm){
				double distance = 0;

				try{
					distance = particle.optimize();
				} catch(Exception e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(distance < bestGlobalItinery.getTourDistance()){
					particle.getCurrentRoute().copyTo(bestGlobalItinery);

					isDistanceImproved = true;
					if(isPrintBestDistance){
						System.out.println(particle);
						System.out.println(distance);
						System.out.println();
					}
				}
			}

			if(!isDistanceImproved){
				staticEpochs++;

				if(staticEpochs == Constants.MAX_STATIC_EPOCHS){
					updateInformers(swarm, particleIndex);
					staticEpochs = 0;
				}
			}

			epoch++;
		}

		return (int) bestGlobalItinery.getTourDistance();
	}

	public Route getBestGlobalItinery() {
		return bestGlobalItinery;
	}

	public void setBestGlobalItinery(Route bestGlobalItinery) {
		this.bestGlobalItinery = bestGlobalItinery;
	}
}
