package com.zzBBc.pso.manager;

import java.util.Random;

import com.zzBBc.pso.entity.Route;
import com.zzBBc.pso.service.RouteUpdater;

public class RouteManager {
	private RouteUpdater routeUpdater = new RouteUpdater();

	public double updateVelocity(Route currentRoute, double weight, double randomDouble) {
		return 1 / currentRoute.getTourDistance() * randomDouble * weight;
	}

	public int getSectionSize(Route currentRoute, double segmentVelocity, double totalVelocity) {
		int length = currentRoute.getDestinationIndex().length;

		return (int) (segmentVelocity / totalVelocity * length);
	}

	public Integer[] addSections(Route[] sections) throws Exception {
		if(sections == null || sections.length == 0)
			throw new Exception();
		if(!routeUpdater.isInitialised())
			routeUpdater.initialise(sections[0].getDestinationIndex().length);
		for(Route routeSection: sections)
			routeUpdater.addSection(new Random().nextInt(routeSection.getDestinationIndex().length),
					routeSection,
					new Random().nextBoolean());
		return routeUpdater.finalizeDestinationIndex(sections[0]);
	}

}
