public class AddOnlyList<T> {
    private T[] items;
    private int size;
    
    public AddOnlyList() {
        items = (T[]) new Object[8];
        size = 0;
    }
    private int indexOfI(int i, int M){
        if(i % 2 == 0){
            return i / 2;
        }else{
            return items.length - (i + 1)/2;
        }
    }
    public T get(int index) {
        return items[indexOfI(index, items.length)];
    }
    public void addLast(T item) {
        if(size == items.length){
            resize(items.length * 2);
        }
        int newItemsIndex = indexOfI(size, items.length);
        items[newItemsIndex] = item;
        size += 1;
    }
    private void resize(int newlen){
        T[] newItems = (T[]) new Object[newlen];
        int halfOfItems = items.length / 2;
        System.arraycopy(items, 0, newItems, 0, halfOfItems);
        System.arraycopy(items, halfOfItems, newItems, newlen - halfOfItems, halfOfItems);
        items = newItems;
    }
    
    public static void main(String[] args){
        AddOnlyList<Integer> aol = new AddOnlyList<>();
        for(int i = 0; i < 8; i++){
          aol.addLast(i);
        }
        
        for(int i = 8; i < 20; i++){
          aol.addLast(8);
        }
    }
}
