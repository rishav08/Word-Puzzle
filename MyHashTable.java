// MyHashTable Hash table class implementing LinearProbing
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// bool insert( x )       --> Insert x
// bool remove( x )       --> Remove x
// bool contains( x )     --> Return true if x is present
// void makeEmpty( )      --> Remove all items


public class MyHashTable<AnyType> {
	
	public MyHashTable(){
		this(DEFAULT_TABLE_SIZE);
		doClear();
	}
	
	public MyHashTable(int size) {
		array = new HashEntry[size];
		doClear();
	}
	
	public void printMyHash() {
		for(HashEntry<AnyType> entry : array) {
			if(entry != null) {
				System.out.println(entry.element + " " + entry.isWord);
			}			
		}
	}

	public boolean insert(AnyType x, boolean j) {
		int currentPos = findPos(x);
		if(isActive(currentPos)) {
			return false;
		}
		if(array[currentPos] == null) {
			++occupied;
		}
		array[currentPos] = new HashEntry<>(x, true, j);
		theSize++;
		
		if( occupied > array.length / 2 ) {
			rehash( );
		}
           
		return true;
	}
	private int findPos(AnyType x) {
		int currentPos = myhash(x);
		while(array[currentPos]!=null && !array[currentPos].element.equals(x) ) {
			currentPos++;
			if(currentPos >= array.length) {
				currentPos -= array.length;
			}
		}		
		return currentPos;
	}
	public boolean remove(AnyType x) {
		int currentPos = findPos(x);
		if(isActive(currentPos)) {
			array[currentPos].isActive = false;
			theSize--;
			return true;
		}else {
			return false;
		}
	}
	public int contains(AnyType x) {
		int currentPos = findPos(x);
		if(isActive(currentPos)) {
			if(array[currentPos].isWord) {
				return 1;
			}else {
				return 2;
			}
		}else {
			return 0;
		}
	}
	private void rehash() {
		HashEntry<AnyType> [] oldArray = array;
		array = new HashEntry[2*oldArray.length];
		occupied = 0;
		theSize = 0;
		for(HashEntry<AnyType> entry : oldArray) {
			if(entry != null && entry.isActive) {
				insert(entry.element, entry.isWord);
			}
		}
	}
	private int myhash(AnyType x) {
		int hashVal = x.hashCode();
		hashVal %= array.length;
		if(hashVal <0) {
			hashVal += array.length;
		}
		return hashVal;
	}
	private boolean isActive(int currentPos) {
		return array[currentPos] != null && array[currentPos].isActive;
	}
	
	public void makeEmpty() {
		doClear();
	}
	private void doClear() {
		occupied = 0;
		for(int i = 0; i < array.length; i++) {
			array[i] = null;
		}
	}
	private static class HashEntry<AnyType>{
		public AnyType element;
		public boolean isActive; //false if marked deleted
		public boolean isWord; //false if not word
		
		public HashEntry (AnyType e) {
			this(e, true, true);
		}
		public HashEntry(AnyType e, boolean i, boolean j) {
			element = e;
			isActive = i;
			isWord = j;
		}
	}
	private static final int DEFAULT_TABLE_SIZE = 100;
	private HashEntry<AnyType> [ ] array;
	private int occupied;
	private int theSize;
}
