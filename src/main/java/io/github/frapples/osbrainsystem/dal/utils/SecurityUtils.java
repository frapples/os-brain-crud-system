package io.github.frapples.osbrainsystem.dal.utils;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class SecurityUtils {
    public static String sha256(String s) {
        return Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();
    }

}
