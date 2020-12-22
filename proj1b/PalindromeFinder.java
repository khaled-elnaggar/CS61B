public class PalindromeFinder {
    static Palindrome palindrome = new Palindrome();

    public static void palindromesGreaterThanN(int minLength, CharacterComparator cc){
        In in = new In("../library-fa20/data/words.txt");

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                System.out.print(word + " ");
            }
        }
        System.out.println();
    }



    public static void main(String[] args) {

        int minLength = 4;

        palindromesGreaterThanN(minLength, null);
        palindromesGreaterThanN(minLength, new OffByOne());
        palindromesGreaterThanN(minLength, new OffByN(2));
        palindromesGreaterThanN(minLength, new OffByN(3));
        palindromesGreaterThanN(minLength, new OffByN(4));
        palindromesGreaterThanN(minLength, new OffByN(5));


    }
}
