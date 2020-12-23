public class PalindromeFinder {
    static Palindrome palindrome = new Palindrome();

    public static void palindromesGreaterThanN(int minLength, CharacterComparator cc) {
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

        for (int i = 0; i < 7; i++) {
            System.out.print("Off by " + i + " ");
            palindromesGreaterThanN(minLength, new OffByN(i));
        }


    }
}
