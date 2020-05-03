public abstract class MemoryManager{
	String name;
	int[] memory;
	int availableMemory;
	int usedMemory;

	public void setMethodName(String mehtodName){
		this.name = mehtodName;
	}

	public String getMethodName(){
		return this.name;
	}

	public void printMemoryStatus(){
		System.out.println("\n[Memory status]");
		System.out.printf("_used: %d Kb\n_available: %d Kb\n", this.usedMemory*2, this.availableMemory*2);
		for (int i = 0; i<memory.length; i++) {
			if(this.memory[i] == -1)
				System.out.print(" ");
			else
				System.out.printf("%d ", this.memory[i]);
		}
		System.out.println();
	}

	public void fillMemory(int value){
		for(int i = 0; i<memory.length; i++)
			this.memory[i] = value;
	}

	public abstract boolean addProcess(Process p);
	public abstract void removeProcess(Process p);
}