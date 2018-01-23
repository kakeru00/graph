package gragh;

import java.io.Serializable;

public class Stack implements Serializable{  
    private long[] stackArray;  
    private int top;  
    private int maxSize;  
      
    public Stack(int maxSize){  
        this.maxSize = maxSize;  
        top = -1;  
        stackArray = new long[maxSize];  
    }  
    public void push(long value){  
        stackArray[++top] = value;  
        //maxSize++;  
    }  
    public long pop(){  
        return stackArray[top--];  
    }  
    public boolean isEmpty(){  
        return top == -1;  
    }  
    public boolean isFull(){  
        return maxSize == (top+1);  
    }  
    public long getTopValue(){  
       return stackArray[top];  
    }  
}  

