package com.zzBBc.pso.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.zzBBc.pso.entity.Route;

public class RouteUpdater {
	private boolean initialised;
	private int itineryLength;
	private Boolean[] availabilityMask;
	private Boolean[] selectedMask;
	private Integer[] destinationIndex;
	private int destinationIndexPointer;

	public boolean isInitialised() {
		return initialised;
	}

	public void setInitialised(boolean initialised) {
		this.initialised = initialised;
	}

	public void initialise(int cityCount) {
		itineryLength = cityCount;

		availabilityMask = createBooleanArray(itineryLength, true);

		selectedMask = createBooleanArray(itineryLength, false);

		destinationIndex = new Integer[itineryLength];
		destinationIndexPointer = 0;

		initialised = true;
	}

	private Boolean[] createBooleanArray(int size, boolean value) {
		List<Boolean> booleans = new ArrayList<>(Arrays.asList(new Boolean[size]));

		Collections.fill(booleans, value);

		return booleans.toArray(new Boolean[size]);
	}

	public int getItineryLength() {
		return itineryLength;
	}

	public void setItineryLength(int itineryLength) {
		this.itineryLength = itineryLength;
	}

	public Integer[] getDestinationIndex() {
		return destinationIndex;
	}

	public void setDestinationIndex(Integer[] destinationIndex) {
		this.destinationIndex = destinationIndex;
	}

	public int getDestinationIndexPointer() {
		return destinationIndexPointer;
	}

	public void setDestinationIndexPointer(int destinationIndexPointer) {
		this.destinationIndexPointer = destinationIndexPointer;
	}

	public void addSection(int pointer, Route section, boolean isReversed) {
		int startPos = pointer;

		if(isReversed)
			// reverse the order as it is equally valid in reverse
			Collections.reverse(Arrays.asList(section.getDestinationIndex()));

		setSelectedMask(startPos, section);
		this.setDestinationIndex(startPos, section);

	}

	private void setDestinationIndex(int startPosition, Route section) {
		int p = startPosition;
		for(int i = 0; i < section.getSegmentSize(); i++){
			int index = section.getDestinationIndex()[p];
			if(selectedMask[index]){
				destinationIndex[destinationIndexPointer] = index;
				destinationIndexPointer++;
			}
			p++;
			if(p == section.getDestinationIndex().length)
				p = 0;
		}
		// update the City AvailabilityMask
		// sets bits that represent cities that have been included to false
		availabilityMask = logicalXor(availabilityMask, selectedMask);
	}

	private Boolean[] logicalXor(Boolean[] current, Boolean[] compare) {
		Boolean[] result = new Boolean[current.length];

		for(int i = 0; i < current.length; i++)
			result[i] = Boolean.logicalXor(current[i], compare[i]);

		return result;
	}

	private void setSelectedMask(int pointer, Route section) {
		int p = pointer;
		selectedMask = createBooleanArray(selectedMask.length, false);
		// foreach city in the section set the appropriate bit
		// in the selected mask
		for(int i = 0; i < section.getSegmentSize(); i++){
			// set bit to signify that city is to be added if not already used
			selectedMask[section.getDestinationIndex()[p]] = true;

			p++;
			// p is a circular pointer in that it moves from the end of the route
			// to the start
			if(p == section.getDestinationIndex().length)
				p = 0;
		}
		// in the AvailabilityMask, true=available, false= already used
		// remove cities from the SelectedMask that have already been added
		selectedMask = logicalAnd(selectedMask, availabilityMask);
	}

	private Boolean[] logicalAnd(Boolean[] current, Boolean[] compare) {
		Boolean[] result = new Boolean[current.length];

		for(int i = 0; i < current.length; i++)
			result[i] = Boolean.logicalAnd(current[i], compare[i]);

		return result;
	}

	public Integer[] finalizeDestinationIndex(Route route) {
		selectedMask = createBooleanArray(selectedMask.length, true);
		selectedMask = logicalAnd(selectedMask, availabilityMask);

		for(Integer c: route.getDestinationIndex())
		 if(selectedMask[c]) {
				destinationIndex[destinationIndexPointer] = c;
				destinationIndexPointer++;
			}

		reset();
		return getDestinationIndex();
	}

	private void reset() {
		destinationIndexPointer = 0;
		availabilityMask = createBooleanArray(availabilityMask.length, true);

		selectedMask = createBooleanArray(selectedMask.length, false);
	}
}
