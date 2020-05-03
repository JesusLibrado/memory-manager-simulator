public class WorstFit extends MemoryManager{

	public WorstFit(int Kb){
		this.memory = new int[Kb/2];
		this.availableMemory = memory.length;
		this.usedMemory = 0;
		super.fillMemory(-1);
		super.setMethodName("Worst Fit");
	}

	public boolean addProcess(Process p){
		int request = p.getMemory();
		int beginIndex = worstSpaceIndex(request);

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

	/****** Look for greatest space Beginning Index ******/
	private int worstSpaceIndex(int spaceSize){
		int maxSpaceCount = 0;
		int counter = 0;
		int index = 0;
		for(int i = 0; i<memory.length; i++){
			counter = 0;
			if(this.memory[i] == -1){	// if this space is empty
				int j = i;				// loop to find Max Sized space	
				while((j < memory.length) && (this.memory[j] == -1)){ // while memory is free
					counter++; 			// increase counter
					j++;				// increase loop index
				}	
				if(counter > maxSpaceCount){ // if this space is greater than Max sized space
					maxSpaceCount = counter; // change variables
					index = i;			// store beginning index
				}
				i = j;					// continue from other memory space
			}
		}
		if(maxSpaceCount > spaceSize)	// if this Process actually fits
			return index;				// return beginning Index
		else return -1;					// not suitable for current Process
	}
}