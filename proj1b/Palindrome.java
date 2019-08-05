public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> newdeque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++){
            newdeque.addLast(word.charAt(i));
        }
        return newdeque;
    }

    public boolean isPalindrome(String word){
        return false;
    }
}
