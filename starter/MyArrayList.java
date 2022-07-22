/**
 * Name: Brian Mendez
 * ID: A17211975
 * Email: b1mendez@ucsd.edu
 * Sources used: Tutor Andrew Tang for the hidden junit tests, 
 * Wk2ArrayListWorksheet<E> lecture slide, and Zybooks. 
 * 
 * This program depicts an ArrayList class using an interface
 * in MyList.java. Using a generics class, MyArrayList is been
 * developed to replicate an Arraylist without importing the 
 * function from java. Mulitple methods have also been implemented 
 * to display similar functions like append, expandCapacity, prepend,
 * getCapacity, Size, get, set, remove, and insert in order to replicate 
 * the methods using an arraylist.
 * 
 */

 /**
  * MyArrayList uses a generics that implements MyList using a generic as
  * function as well. MyArrayList uses two instant variables, one an object 
  * array and the other a size variable to keep track of the elements in the 
  * arraylist. 
  */
public class MyArrayList<E> implements MyList<E> {
    //instance variable: underlying data structure of the ArrayList
    Object[] data;
    //instance variable: setting equal to the valid elements in data
    int size;

    //constructor initializing the object array. 
    public MyArrayList() {
        this.data = new Object[5];
        this.size = 0; 
    }
    
    //constructor initializing the object array with initialCapacity 
    public MyArrayList(int initialCapacity){
        //if initialCapacity is less than 0 the program will throw an illegal argument
        if (initialCapacity > 0){
            this.data = new Object[initialCapacity]; 
        }
        else {
            throw new IllegalArgumentException(); 
        }
        this.size = 0;
    }

    //constructor initializing the instance variable with the input array
    public MyArrayList (E[] arr){
        this.data = arr;
        this.size = arr.length; 
    }

    /**
     * Increase the capacity of the underlying array
     */
    public void expandCapacity(int requiredCapacity){
        //throws illegal argument when data.length is greater than requiredCapacity
        if (size > requiredCapacity){
            throw new IllegalArgumentException(); 
        }

        //variables needed for algorithm
        int temp = data.length;
        //set the capacity to the default capacity if data.length equals 0
        if (temp == 0 || (temp < 5 && requiredCapacity > 5)){
            temp = 5;
        }
        //main algorithm to expand the Objects capacity
        else {
            while(temp < requiredCapacity){
                temp = temp * 2;
            }
        }
        data = new Object[temp]; 

    }

    /**
     * Get the amount of elements arraylist can hold 
     * @return Number of elements an arraylist can hold - length of the array
     */
    public int getCapacity(){
        return this.data.length; 
    }

    /**
     * Add an element at the specified index
     * @param index - position in the array to insert the element
     * @param element - the element to be inserted 
     */
    public void insert(int index, E element){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException(); 
        }

        if (data.length == size){
            expandCapacity(data.length + 1);
        }

        for (int i = size; i > index; i--){
            data[i] = data[i - 1];
        }
        data[index] = element; 
        size++;
    }

    /**
    * Add an element to the end of the list 
    * @param element - the element to be added    
    */
    public void append(E element){
        //expand the required capacity when the initial capacity is less than
        //the size. 
        if (data.length == size){
            expandCapacity(data.length + 1);
        }

        data[size] = element;
        size++;
    }

    /**
    * Add an element to the beginning of the list
    * @param element - the element to be added
    */
    public void prepend(E element){
        //expand the required capacity when the initial capacity is less than
        //the size. 
        if (data.length == size){
            expandCapacity(data.length + 1);
        } 
        
        for (int i = 0; i < size + 1; i ++){
            if (i == 0){
                data[0] = element; 
            }
            else {
                data[i] = data[i + 1];
            } 
        }
        size++; 
    }

    /**
    * Get the element at the given index 
    * @param index - position in the arraylist
    * @return element present in the given index
    */
    @SuppressWarnings("unchecked")
    public E get(int index){
        //Test if index is greater than or equal to the size
        //or if index is less than zero
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException(); 
        }

        return (E)data[index];
    }

    /**
    * Replaces an element at the specified index with a new element and return the original elements
    * @param index - position of the element to be replaced
    * @param element - new element replacing the old element
    * @return original element present in the index before replacement
    */
    @SuppressWarnings("unchecked")
    public E set(int index, E element){
        //Test if index is greater than or equal to the size
        //or if index is less than zero
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException(); 
        }
        
        E result = (E)data[index];
        for (int i = 0; i < size; i++){
            if (index == i) {
                data[i] = element;
            }
        }
        return result;
    }

    /**
    * Remove the element at the specified index and return the removed element
    * @param index - position of the element to be removed
    * @return element in that index
    */
    @SuppressWarnings("unchecked")
    public E remove(int index){
        //Test if index is greater than or equal to the size
        //or if index is less than zero
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException(); 
        }
        
        E result = (E)data[index]; 
        for (int i = 0; i < size - 1; i++){
            if (index == i) {
                break;
            }
            else {
                data[i] = data[i+1]; 
            }
        }
        size--; 
        return result;
    }

    /**
    * Get the number of elements in the list
    * @return number of elements present in the list
    */
    public int size(){
        return this.size;
    }

    public E replaceLast(E newElem) 
    {
        E item = (E) newElem;               // LINE A
        data[size] = newElem;  // LINE B
        return item;
    }
}