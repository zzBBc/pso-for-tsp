package com.zzBBc.branchbound;

import static java.lang.System.exit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

import com.zzBBc.pso.Constants;

public class Main {


    public static void main(String[] args) {
		int[][] graph =
				getGraphFromFile(args);

        Stack<Integer> bestPath;
        long start;
        long end;
        float elapsedTimeInSec;

        System.out.println("Branch and bound algorithm: ");
        // finding the time before the operation is executed
        start = System.currentTimeMillis();
        // Finds the shortest path cost with the Brunch and bound algorithm
        BranchBoundTSP branchBoundTSP = new BranchBoundTSP(graph);
        try {
            System.out.println("Path cost: " + branchBoundTSP.getShortestPathCost());
            bestPath = branchBoundTSP.getShortestPath();

            System.out.print("Path Taken: " + bestPath.pop());
            while (!bestPath.isEmpty()){
				System.out.print("-> " + bestPath.pop());
			}

            System.out.println();
            // finding the time after the operation is executed
            end = System.currentTimeMillis();
            // finding the time difference and converting it into seconds
            elapsedTimeInSec = (end - start) / 1000F;
            System.out.println("Time taken: " + elapsedTimeInSec + " seconds");
            System.out.println();
        } catch (OutOfMemoryError error) {
            branchBoundTSP = null;
            System.out.println("Error: Max heap size reached");
        }

    }

	private static int[][] getGraphFromFile(String[] args) {
		int[][] graph = null;
        try {
			graph = readGraphFromFile(Constants.FILE_PATH);
        } catch (Exception e) {
			// System.out.println("Error reading from " + args[0] + " file");
            System.out.println(e.getMessage());
            exit(1);
        }
		return graph;
	}

    // Reading the graph's adj matrix from the given .txt file
	private static int[][] readGraphFromFile(String fileName) throws FileNotFoundException {
		int[][] graph = null;
        // Used to get the size of the array
		// Scanner scanner = new Scanner(new File(fileName));
		// String firstLine = scanner.nextLine();
		// String[] data = firstLine.split(" "); // *First number on first line of the
		// file must not have a "space"*
		int n = Constants.CITY_COUNT;
		// scanner.close();

        // Copy the graph to the graph array
		Scanner scanner = new Scanner(new File(fileName));
        graph = new int[n][n];
		int i = 0;
		int j = 0;
		while(scanner.hasNext()){
			try{
				if(j == graph.length){
					break;
				}

				if(i == graph.length){
					j++;
					i = 0;
				}

				graph[i][j] = Integer.parseInt(scanner.next());
				i++;
			} catch(NumberFormatException e){
			}
		}

		scanner.close();
		return graph;
    }
}
