package other;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class MaximumProductOfWordLengths {
    public int maxProduct(String[] words) {
        final int length = words.length;
        int[] bitSets = toUniqueLettersBitSetsUsingInt(words);
        int max = 0;
        for(int i = 0; i < length; ++i) {
            for (int j = i + 1; j < length; ++j) {
                if((bitSets[i] & bitSets[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    private int[] toUniqueLettersBitSetsUsingInt(String[] words) {
        int[] bitSets = new int[words.length];
        for(int i = 0; i < words.length; ++i) {
            int bitSet = 0;
            for(int j = 0; j < words[i].length(); ++j) {
                //a << b: shift a by b bits ==> a * 2 ^ b;
                bitSet |= 1 << words[i].charAt(j);
            }
            bitSets[i] = bitSet;
        }
        return bitSets;
    }

    private BitSet[] toUniqueLetterBitSets(String[] words) {
        BitSet[] bitSets = new BitSet[words.length];
        for(int i = 0; i < words.length; ++i) {
            BitSet bitSet = new BitSet(26);
            for(int j = 0; j < words[i].length(); ++j) {
                bitSet.set(words[i].charAt(j));
            }
            bitSets[i] = bitSet;
        }
        return bitSets;
    }


    private boolean hasCommonLetters(String word1, String word2) {
        Set<Character> word1Chars = new HashSet<>();
        for(int i = 0; i < word1.length(); ++i) {
            word1Chars.add(word1.charAt(i));
        }

        Set<Character> word2Chars = new HashSet<>();
        for(int i = 0; i < word2.length(); ++i) {
            word2Chars.add(word2.charAt(i));
        }
        int totalSize = word1Chars.size()+ word2Chars.size();
        word1Chars.addAll(word2Chars);
        return totalSize != word1Chars.size();
    }
}
