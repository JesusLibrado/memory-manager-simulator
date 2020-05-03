import java.util.*;

public class Process{

	private int pid;
	private int entranceTime;
	private int exitTime;
	private int memory;


	public Process(int pid, String line){
		this.pid = pid;
		this.decompose(line);
	}

	public int getPid(){
		return this.pid;
	}

	public int getEntranceTime(){
		return this.entranceTime;
	}

	public int getExitTime(){
		return this.exitTime;
	}

	public int getMemory(){
		return this.memory;
	}

	public void print(){
		System.out.println();
		System.out.printf("\t\tEntrance time: \t%d\n", this.entranceTime);
		System.out.printf("process %d\tExit time: \t%d\n", this.pid, this.exitTime);
		System.out.printf("\t\tMemory: \t%d\n", this.memory);
	}

	private void decompose(String line){
		String[] arr = line.split(" ");
		this.entranceTime = Integer.parseInt(arr[0]);
		this.exitTime = Integer.parseInt(arr[1]);
		this.memory = Integer.parseInt(arr[2]);
	}	
}