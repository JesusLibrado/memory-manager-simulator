public class FirstFit extends MemoryManager{

	public FirstFit(int Kb){
		this.memory = new int[Kb/2];
		this.availableMemory = memory.length;
		this.usedMemory = 0;
		super.fillMemory(-1);
		super.setMethodName("First Fit");
	}

	public boolean addProcess(Process p){
		int request = p.getMemory();
		for(int i = 0; i<memory.length; i++){
			if(this.requestSpaceAvailable(i, request)){
				this.usedMemory += request;
				this.availableMemory -= request;
				for(int j = i; j < (i+request); j++)
					this.memory[j] = p.getPid();
				return true;
			}
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

	private boolean requestSpaceAvailable(int initialPosition, int spaceSize){
		if((initialPosition+spaceSize)<memory.length){
			for(int i = initialPosition; i<(initialPosition+spaceSize); i++)
				if(this.memory[i] != -1) return false;
			return true;
		}
		else
			return false;
	}
}