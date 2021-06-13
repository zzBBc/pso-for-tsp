package com.zzBBc.pso;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.zzBBc.pso.entity.Particle;
import com.zzBBc.pso.entity.TspData;
import com.zzBBc.pso.manager.ResultManager;
import com.zzBBc.pso.manager.SwarmManager;

public class PsoApplication {
	private static SwarmManager swarmManager = new SwarmManager();
	private static ResultManager resultManager = new ResultManager();

	public static void main(String[] args) {
		TspData tspData = null;

		try{
			tspData = getTspData();
		} catch(FileNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Particle[] swarm = swarmManager.buildSwarm(tspData);

		System.out.println("Starting Optimizer. Listing shortest trips found...");

		double bestPossibleDistance = swarmManager.getBestPossibleDistance(tspData);

		resultManager.reset((int) bestPossibleDistance);

		int iterations = 1;
		long start = System.currentTimeMillis();

		for(int i = 0; i < iterations; i++){
			int distance = swarmManager.optimize(swarm);

			resultManager.updateResults(distance);
		}

		long end = System.currentTimeMillis();
		long elapsedTime = end - start;

		System.out.println("\r\n  Optimizer completed in " + elapsedTime + " miliseconds");
		System.out.println(resultManager.getBestDistanceFound());
		System.out.println(resultManager.getBestPossibleDistance());
		System.out.println(swarmManager.getSwarmOptimizer().getBestGlobalItinery());
	}

	private static TspData getTspData() throws FileNotFoundException {
		TspData tspData = new TspData();

		Scanner scan = new Scanner(new File("text.txt"));

		int cityCount = 76;
		double[][] distanceLookup = new double[cityCount][cityCount];
		Integer[] bestRoutes = new Integer[cityCount];

		int i = 0;
		int j = 0;
		while(scan.hasNext()){
			if(i == bestRoutes.length){
				i = 0;

				break;
			}

			try{
				bestRoutes[i] = Integer.parseInt(scan.next());
				i++;
			} catch(NumberFormatException e){
			}

		}

		tspData.setBestRoute(bestRoutes);

		while(scan.hasNext())
			try{
				if(j == distanceLookup.length)
					break;

				if(i == distanceLookup.length){
					j++;
					i = 0;
				}

				distanceLookup[i][j] = Double.parseDouble(scan.next());
				i++;
			} catch(NumberFormatException e){
			}

		tspData.setDistanceLookup(distanceLookup);

		scan.close();

		return tspData;
	}
}
