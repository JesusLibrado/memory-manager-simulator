public class BestFit extends MemoryManager{

	public BestFit(int Kb){
		this.memory = new int[Kb/2];
		this.availableMemory = memory.length;
		this.usedMemory = 0;
		super.fillMemory(-1);
		super.setMethodName("Best Fit");
	}

	public boolean addProcess(Process p){
		int request = p.getMemory();
		int beginIndex = bestSpaceIndex(request);

		if(beginIndex != -1){
			for(int i = beginIndex; i<(beginIndex+request); i++)
				this.memory[i] = p.getPid();
			this.usedMemory += request;
			this.availableMemory -= request;
			return true;
		}
		return false;
	}

	public void removeProcess(Process p){
		this.usedMemory -= p.getMemory();
		this.availableMemory += p.getMemory();
		for(int i = 0; i<memory.length; i++)
			if(this.memory[i] == p.getPid())
				this.memory[i] = -1;
	}

	private int bestSpaceIndex(int spaceSize){
		int minSpaceCount = 127;
		int maxSpaceCounter = 0;
		int counter = 0;
		int minIndex = 0;
		int maxIndex = 0;
		for(int i = 0; i<memory.length; i++){
			counter = 0;
			if(this.memory[i] == -1){	// if this space is empty
				int j = i;				// loop to find Max Sized space	
				while((j < memory.length) && (this.memory[j] == -1)){ // while memory is free
					counter++;			// increase counter
					j++;				// increase loop index
				}
				if(counter < minSpaceCount){ // if this space is greater than Max sized space
					minSpaceCount = counter; // change variables
					minIndex = i;		// store beginning index
				}
				if(counter > maxSpaceCounter){ // if this space is smaller than Minimum sized space
					maxSpaceCounter = counter; // change variables
					maxIndex = i;		// store beginning index
				}
				i = j;					// continue from other memory space
			}
		}
		if(minSpaceCount > spaceSize) // if smallest space is suitable for Process
			return minIndex;		// return index of this 
		if(maxSpaceCounter > spaceSize)	// else, return index of maximum space (otherwise it will keep adding Process to buffer)
			return maxIndex;		// return index of this
		else return -1;				// not suitable space
	}
}