public class PalindromeFinder {
    static Palindrome palindrome = new Palindrome();
    static In in = new In("../library-fa20/data/words.txt");

    public static void palindromesGreaterThanN(int minLength, CharacterComparator cc){
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                System.out.println(word);
            }
        }
    }



    public static void main(String[] args) {

        palindromesGreaterThanN(4, new OffByOne());


    }
}
