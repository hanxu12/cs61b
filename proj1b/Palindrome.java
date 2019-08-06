public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> newdeque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            newdeque.addLast(word.charAt(i));
        }
        return newdeque;
    }


    public boolean isPalindrome(String word) {
        return isPalindromehelper(wordToDeque(word));
    }

    private boolean isPalindromehelper(Deque<Character> deque) {
        if (deque.size() <= 1) {
            return true;
        } else {
            char first = deque.removeFirst();
            char last = deque.removeLast();
            if (first == last) {
                isPalindromehelper(deque);
            } else {
                return false;
            }
            return true;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeCChelper(wordToDeque(word), cc);
    }

    private boolean isPalindromeCChelper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() < 2) {
            return true;
        } else {
            char first = deque.removeFirst();
            char last = deque.removeLast();
            if (cc.equalChars(first, last)) {
                isPalindromeCChelper(deque, cc);
            } else {
                return false;
            }
            return true;
        }
    }
}
