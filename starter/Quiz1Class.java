public class Quiz1Class<T>
{
    private Object[] elements;

    public Quiz1Class(T[] arr) { 
        elements = arr;
        for (int i = 0; i < arr.length; i++) {
            elements[i] = arr[i];
        }
    }

    /** Replace the last element in the array with newElem
     * Return the value of the element that was replaced.
     *
     * For example, if elements is [10, 20, 30] and replaceLast(1)
     * is called, elements will become [10, 20, 1] and 
     * 30 will be returned.
     *
     * Assume elements is not null has length > 0.
     */
    public T replaceLast(T newElem) 
    {
        T item = (T) newElem;               // LINE A
        elements[elements.length] = newElem;  // LINE B
        return item;
    }

    public void printArray() {
        this.printArray(elements);
    }

    public void printArray(Object[] arr)
    {
        for (Object item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] myInts = {1, 2, 3, 4, 5};
        Quiz1Class<Integer> ref = new Quiz1Class<Integer>(myInts);
        System.out.println(ref.replaceLast(10));  // LINE 1
        ref.printArray();  
        ref.printArray(myInts);  // LINE 2
        
    }
}
