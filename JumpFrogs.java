import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class JumpFrogs {
	
	public static char[] FrogsJumps(char[] arr, int ind1, int ind2) {
		char temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] =  temp;
		return arr;
	}
	
	public static int IndexFree (char[] arr) {
		int index=0;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]=='_') { index = i;}
		}
		return index;
	}
	
	public static boolean IsVisited(char[] arr, ArrayList<char[]> arrayList) {
		if(arrayList.isEmpty()) {return false;}
		for(char[] node : arrayList) {
			if(Arrays.equals(arr, (char[]) node)) { return true;}
		}
		return false;
	}
	
	public static void StartFinalFrogs(char[] frogStart, char[] frogFinal, int frogs) {
		for(int i=0; i < (2*frogs + 1);i++) {
	    	if(i < frogs) {
	    		frogStart[i] = '>';
	    		frogFinal[i] = '<';
	    	}
	    	else{
	    		if (i == frogs) {
	    			frogStart[i] = frogFinal[i] = '_';
	    		}
	    		else {
	    			frogStart[i] = '<';
	    			frogFinal[i] = '>';
	    		}	
	    	}
	    }
	}
	
	public static char[] MakeCopy(char[] arr) {
		char[] copy = new char[arr.length];
		for(int i=0; i < arr.length; i++) {
			copy[i] = arr[i];
		}
		return copy;
	}
	
	public static void PrintPath(Stack<char[]> stack) {
		ArrayList<char[]> pathToFinal = new ArrayList<char[]>();  
	    while(!stack.empty()){
	        pathToFinal.add(stack.pop());
	    }
		for(int i=pathToFinal.size()-1; i>=0; i--) {
			System.out.println(pathToFinal.get(i));
			//System.out.println(Arrays.toString(pathToFinal.get(i)));
		}
	}

	
	public static void main(String[] args) {
		
		System.out.print("Number of frogs on each side: ");
	    Scanner input = new Scanner(System.in);
	    int frogs = input.nextInt();
	    input.close();
	    System.out.println(frogs);
	    char[] frogStart = new char[2*frogs + 1];
	    char[] frogFinal = new char[2*frogs + 1];
	    
	    StartFinalFrogs(frogStart,frogFinal,frogs);
	   // System.out.println(Arrays.toString(frogStart) + " " + Arrays.toString(frogFinal));
	    
	    Stack<char[]> stack = new Stack<char[]>();
	    ArrayList<char[]> visitedNodes = new ArrayList<char[]>();

	    stack.push(MakeCopy(frogStart));

	    
	    while (!Arrays.equals(frogStart,frogFinal)) {
		   int freeIndex = IndexFree(frogStart);
	  
		   if( (freeIndex >=1) &&(frogStart[freeIndex - 1] == '>') && (!IsVisited(FrogsJumps(MakeCopy(frogStart),freeIndex-1,freeIndex), visitedNodes)) ) {
			   FrogsJumps(frogStart,freeIndex-1,freeIndex);
			   stack.push(MakeCopy(frogStart));
	           visitedNodes.add(MakeCopy(frogStart));    
	           continue;
		   }
		   if((freeIndex >=2) && (frogStart[freeIndex - 2] == '>') &&(!IsVisited(FrogsJumps(MakeCopy(frogStart),freeIndex-2,freeIndex), visitedNodes))) {
			   FrogsJumps(frogStart,freeIndex-2,freeIndex);
			   stack.push(MakeCopy(frogStart));
	           visitedNodes.add(MakeCopy(frogStart));    
	           continue;
		   }
		   if((freeIndex < frogStart.length-1) && (frogStart[freeIndex+1] == '<') && (!IsVisited( FrogsJumps(MakeCopy(frogStart),freeIndex+1,freeIndex), visitedNodes))) {
			   FrogsJumps(frogStart,freeIndex+1,freeIndex);
			   stack.push(MakeCopy(frogStart));
	           visitedNodes.add(MakeCopy(frogStart));    
	           continue;
		   }
		   if((freeIndex < frogStart.length-2) && (frogStart[freeIndex+2] == '<') && (!IsVisited( FrogsJumps(MakeCopy(frogStart),freeIndex+2,freeIndex), visitedNodes))) {
			   FrogsJumps(frogStart,freeIndex+2,freeIndex);
			   stack.push(MakeCopy(frogStart));
	           visitedNodes.add(MakeCopy(frogStart));    
	           continue;
		   }
		   stack.pop();
		   frogStart=(char[]) stack.peek();
		   frogStart = MakeCopy(frogStart);
	   }
	   PrintPath(stack);  
	}
}
