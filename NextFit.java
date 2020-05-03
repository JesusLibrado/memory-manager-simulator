public class NextFit extends MemoryManager{
	
	private int lastIndexAdded;					// Index of the end of last stored memory

	public NextFit(int Kb){
		this.memory = new int[Kb/2];			// array of Kb/2
		this.availableMemory = memory.length;	// all memory is available
		this.usedMemory = 0;					// all spaces empty
		this.lastIndexAdded = 0;				// beginning in 0
		super.fillMemory(-1);					// fill Memory with empty spaces
		super.setMethodName("Next Fit");
	}

	/****** Process addition ******/
	public boolean addProcess(Process p){
		int request = p.getMemory();			// get space that the Process requires

		if((lastIndexAdded+request)>=memory.length)	// if Process ocuppies more than the lenght of Memory, return to index 0
			this.lastIndexAdded = 0;

		for(int i = lastIndexAdded; i<memory.length; i++){ // loop from Last index
			if(this.requestSpaceAvailable(i, request)){ // check if from this Index, there is enough space
				this.usedMemory += request;		// Process uses some space in Memory
				this.availableMemory -= request; // take the Process space from available space
				for(int j = i; j < (i+request); j++){ // loop until Process ocupies all neede space from Memory
					this.memory[j] = p.getPid(); // store Process' Pid in Memory
					this.lastIndexAdded = j; // change Last index to where this Process was stored
				}
				this.lastIndexAdded++; 			// increase one space 
				return true; 					// addition was successful
			}
		}
		return false; 							// addition was unsuccessful (generally because there is no enough memory available)
	}

	/****** Process removal ******/
	public void removeProcess(Process p){ 
		this.usedMemory -= p.getMemory();		// remove Process from used memory
		this.availableMemory += p.getMemory();	// add available Memory
		for(int i = 0; i<memory.length; i++)	// loop Memory
			if(this.memory[i] == p.getPid())	// look for Process in Memory
				this.memory[i] = -1;			// empty 
	}

	/****** Check if there is enough memory for some Process ******/
	private boolean requestSpaceAvailable(int initialPosition, int spaceSize){
		if((initialPosition+spaceSize)<memory.length){ // if Initial position + requested space is not larger than Memory size
			for(int i = initialPosition; i<(initialPosition+spaceSize); i++) // then, validate if there is enough space for this Process
				if(this.memory[i] != -1) return false; // if there is something stored here, exit
			return true; 						// else, return true (enough space)
		}
		return false; 							// not enough space
	}
}