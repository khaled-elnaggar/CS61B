public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        ArrayDeque<Character> result = new ArrayDeque<>();
        for(int i = 0; i < word.length(); i++){
            result.addLast(word.charAt(i));
        }
        return result;
    }

    public boolean isPalindrome(String word){
        return false;
    }

}
