package io.github.frapples.osbrainsystem.biz.service;

public class RecordCheckingService {

    public int levenshteinDistance(String a, String b) {
        return levenshteinDistanceImp(a, b, a.length(), b.length(), 0, 0);
    }

    private int levenshteinDistanceImp(String s1, String s2,
        int len1, int len2, int pos1, int pos2)
    {
        if(pos1 >= len1) {
            return (pos2 >= len2) ? 0 : len2 - pos2;
        } else if(pos2 >= len2) {
            return len1 - pos1;
        } else if (s1.codePointAt(pos1) == s2.codePointAt(pos2)) {
            return levenshteinDistanceImp(s1, s2, len1, len2, pos1 + 1, pos2 + 1);
        } else {
            int d1 = levenshteinDistanceImp(s1, s2, len1, len2, pos1 + 1, pos2);
            int d2 = levenshteinDistanceImp(s1, s2, len1, len2, pos1, pos2 + 1);
            int d3 = levenshteinDistanceImp(s1, s2, len1, len2, pos1 +1, pos2 + 1);
            return Integer.min(d1, Integer.min(d2,d3)) + 1;
        }
    }

}
