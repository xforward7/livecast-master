package com.aidingyun.ynlive;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getContentFromMessage() {
        ArrayList<String> contentFromMessage = getContentFromMessage("[详情|goto_feed_profile|feedid=85|]");
        for (String s : contentFromMessage) {
            System.out.println(s);
        }
    }

    private static Pattern p = Pattern.compile("\\[(.*?)\\]");

    public static ArrayList<String> getContentFromMessage(String targetStr) {
        Matcher m = p.matcher(targetStr);
        String group = null;
        while (m.find()) {
            group = m.group(1);
        }
        return group != null ? new ArrayList<>(Arrays.asList(group.split("\\|"))) : new ArrayList<>();
    }

}