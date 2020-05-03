import java.util.*;

public class Main{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int i = -1;

		int n = in.nextInt();							// read number of processes
		in.nextLine();									// read until break of line

		Process[] p = new Process[n];					// processes
	
		while(++i < n){ 								// read process information
			String processLine = in.nextLine();			// read line
			p[i] = new Process(i+1, processLine);		// create Object
		}

		simulation(new FirstFit(256), p, n);			// First Fit
		simulation(new NextFit(256), p, n);				// Next Fit
		simulation(new WorstFit(256), p, n);			// Worst Fit
		simulation(new BestFit(256), p, n);				// Best Fit
	}

	public static void simulation(MemoryManager memory, Process[] p, int processNumber){
		System.out.printf("\t\t------------------------\n \t\t\t%s \n\t\t------------------------\n", memory.getMethodName());

		LinkedList<Process> buffer = new LinkedList<Process>(); // Queue of Process [Buffer]

		int finishedProcessCount = 0; 					// counter for each finished Process
		int time = 0;									// time counter

		while(finishedProcessCount < processNumber){ 	// while not all processes are finished

			for(int i = 0; i<p.length; i++){ 			// loop Array of processes
				if(p[i].getEntranceTime() == time) 		// check for entrance time of Process
					buffer.add(p[i]);					// if Entrance time matches, add to Buffer
				if(p[i].getExitTime() == time){			// if Exit time matches, remove Process
					memory.removeProcess(p[i]);			// remove Process from Memory
					finishedProcessCount++;				// increase counter
				}
			}

			if(!buffer.isEmpty()){						// if there is any Process waiting
				Process temp = buffer.remove(); 		// get the first in Buffer
				if(!(memory.addProcess(temp))){			// if addition to Memory was NOT successful 
					System.out.println("Process: "+temp.getPid()+" added to buffer"); // Print that this Process did NOT fin in Memory
					buffer.add(temp);					// put that Process at the end of Buffer
				}
			}

			memory.printMemoryStatus();					// print Memory status
			
			time++;										// increase time unit
		}
		System.out.println("Finished at time: "+(time-1)); // print time at which everything ended
		System.out.println();
	}
}