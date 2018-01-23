package gragh;

import java.io.Serializable;

//����ʵ�ֶ���
class queue implements Serializable{
    private long[] array;
    private int front;
    private int rear;
    private int nElements;
    private int maxSize;
    
    public queue(int s){
        maxSize = s;
        array = new long[maxSize];
        front = 0;
        rear = -1;
        nElements = 0;
    }
    public void insert(long value){
        if(rear == maxSize-1){
            rear = -1;
        }
        array[++rear] = value;
        nElements++;
    }
    public long remove(){
        if(front+1 == maxSize){
            front = 0;
        }
        nElements--;
        return array[front++];
    }
    public long getFrontElement(){
        return array[front];
    }
    public boolean isEmpty(){
        return (nElements == 0);
    }
    public boolean isFull(){
        return (maxSize == nElements);
    }
    public int getNElements(){
        return nElements;
    }
}
