public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        ArrayDeque<Character> result = new ArrayDeque<>();
        for(int i = 0; i < word.length(); i++){
            result.addLast(word.charAt(i));
        }
        return result;
    }

    public boolean isPalindrome(String word){
        if(word == null){
            return false;
        }
        Deque<Character> wordD = wordToDeque(word);

        while(wordD.size() > 1){
            if(wordD.removeFirst() != wordD.removeLast()){
                return false;
            }
        }
        return true;
    }
}
