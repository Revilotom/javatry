/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.colorbox;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.impl.StandardColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.space.DoorBoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, using Stream API you can. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author your_name_here
 */
public class Step12StringStreamTest extends PlainTestCase {

    List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

    List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());

    List<Object> cleanContent = spaces.stream()
            .map(x -> {

                if (x instanceof DoorBoxSpace){
                    ((DoorBoxSpace)x).openTheDoor();
                }
                return x.getContent();

            })
            .collect(Collectors.toList());
    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have?  5 <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {

        String answer = colorBoxList.stream()
                .findFirst()
                .map(colorBox -> colorBox.getColor().getColorName())
                .map(colorName -> colorName.length() + " (" + colorName + ")")
                .orElse("*not found");
        log(answer);
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<String> strings = cleanContent.stream()
                .filter(x -> x instanceof String)
                .map(x -> x.toString())
                .collect(Collectors.toList());

        int maxLength = strings.stream().max(Comparator.comparingInt(o -> o.length())).get().length();
        log(maxLength);
        Set<String> maxes = strings.stream().filter(x -> x.length() == maxLength).collect(Collectors.toSet());
        log(maxes);
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<String> strings =
                cleanContent.stream()
                .filter(x -> x instanceof String)
                .map(x -> x.toString())
                .collect(Collectors.toList());

        int maxLength = strings.stream().max(Comparator.comparingInt(o -> o.length())).get().length();
        int minLength = strings.stream().min(Comparator.comparingInt(o -> o.length())).get().length();
        log(maxLength - minLength);
    }

    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (without sort) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {
        List<String> strings =
                cleanContent.stream()
                        .filter(x -> x != null)
                .map(x -> x.toString())
                .collect(Collectors.toList());
        int maxLength = strings.stream().max(Comparator.comparingInt(o -> o.length())).get().length();
        List<String> leftOvers = strings.stream().filter(x -> x.length() < maxLength).collect(Collectors.toList());
        int secondMax = leftOvers.stream().max(Comparator.comparingInt(o -> o.length())).get().length();

        Set<String> maxes = strings.stream().filter(x -> x.length() == secondMax).collect(Collectors.toSet());

        log(maxes);
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<String> strings =
                cleanContent.stream()
                .filter(x -> x instanceof String)
                .map(x -> x.toString())
                .collect(Collectors.toList());
        int sum = strings.stream().map(x -> x.length()).mapToInt(Integer::intValue).sum();
        log(sum);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<String> colours = colorBoxList.stream().map(x -> x.getColor().getColorName()).collect(Collectors.toList());

        int maxLength = colours.stream().max(Comparator.comparingInt(o -> o.length())).get().length();
        log(maxLength);
        Set<String> maxes = colours.stream().filter(x -> x.length() == maxLength).collect(Collectors.toSet());
        log(maxes);
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<String> colours = colorBoxList.stream()
                .filter(x -> x.getSpaceList()
                        .stream()
                        .anyMatch(y -> y.getContent() != null && y.getContent().toString().startsWith("Water")))
                .map(x -> x.getColor().getColorName())
                .collect(Collectors.toList());
        log(colours);
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<String> colours = colorBoxList.stream()
                .filter(x -> x.getSpaceList().stream().anyMatch(y -> y.getContent() != null && y.getContent().toString().endsWith("front")))
                .map(x -> x.getColor().getColorName())
                .collect(Collectors.toList());
        log(colours);
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<String> strings =
                cleanContent.stream()
                .filter(x -> x instanceof String)
                .map(x -> x.toString())
                .collect(Collectors.toList());
        List<String> content = strings.stream().filter(x -> x.endsWith("front")).collect(Collectors.toList());
        log(content.get(0).indexOf("front") + 1);
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<String> strings =
                cleanContent.stream()
                .filter(x -> x instanceof String)
                .map(x -> x.toString())
                .collect(Collectors.toList());
        List<String> targets = strings.stream().filter(x -> x.split("ど").length == 3).collect(Collectors.toList());
        log(targets.stream().map(x -> x.lastIndexOf("ど") + 1).collect(Collectors.toList()));
    }
    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        List<String> strings =
                cleanContent.stream()
                .filter(x -> x instanceof String)
                .map(x -> x.toString())
                .collect(Collectors.toList());
        List<String> content = strings.stream().filter(x -> x.endsWith("front")).collect(Collectors.toList());
        log(content.get(0).substring(0, 1));
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<String> strings =
                cleanContent.stream()
                .filter(x -> x instanceof String)
                .map(x -> x.toString())
                .collect(Collectors.toList());
        List<String> content = strings.stream().filter(x -> x.startsWith("Water")).collect(Collectors.toList());
        log(content.get(0).substring(content.get(0).length() - 1));
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        List<String> strings =
                cleanContent.stream()
                .filter(x -> x instanceof String)
                .map(x -> x.toString())
                .collect(Collectors.toList());
        List<String> content = strings.stream().filter(x -> x.contains("o")).collect(Collectors.toList());
        log(content.stream().map(x -> x.replace("o", "").length()).collect(Collectors.toList()).get(0));
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<String> paths =
                cleanContent.stream()
                        .filter(x -> x instanceof File).map(x -> {
                    try {
                        return ((File) x).getCanonicalPath().replace("/", "\\");
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "";
                    }

                }).collect(Collectors.toList());

        log(paths);
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        List<YourPrivateRoom.DevilBox> devilBoxes =
                cleanContent.stream()
                .filter(x -> x instanceof YourPrivateRoom.DevilBox)
                .map(x -> (YourPrivateRoom.DevilBox) x)
                .collect(Collectors.toList());

        AtomicInteger total = new AtomicInteger();

        devilBoxes.forEach(x -> {
            x.wakeUp();
            x.allowMe();
            x.open();

            try {
                total.addAndGet(x.getText().length());
            } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {

            }
        });
        log(total.get());
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    // TODO tom 今は、MapのtoStringによって "{1-Day Passport=7400, Starlight Passport=5400,... }" というフォーマットで出力されているけど、
    //          これを "map:{ 1-Day Passport = 7400 ; Starlight Passport = 5400 ; ... }"というフォーマットで出力してみよう by もってぃ
    public void test_showMap_flat() {
        cleanContent.stream()
        .filter(x -> x instanceof LinkedHashMap)
        .map(x -> getPrettyMap((Map)x, false))
        .forEach(x -> log(x));
    }

    String getPrettyMap(Map m, boolean nested){
       return "map:{ " +  m.keySet().stream().map(k -> {
           if (m.get(k) instanceof Map && nested){
               return getPrettyMap((Map) m.get(k), true) + ";";
           }
          return k + " = " + m.get(k) + " ;";
       }).collect(Collectors.joining(" ")) + " }";
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    // TODO tom こちらも上と同じフォーマットで出力してみよう by もってぃ
    public void test_showMap_nested() {
        cleanContent.stream()
                .filter(x -> x instanceof LinkedHashMap)
                .map(x -> getPrettyMap((Map)x, true))
                .forEach(x -> log(x));
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    // TODO tom Stringの "map:{ dockside = over ; hangar = mystic ; broadway = bbb }" として出力するのではなく、
    //          Stringをparseして Map にしてみよう。(おそらく "{dockside=over,hangar=mystic,broadway=bbb}"と出力されるはず?) by もってぃ

    Map parse (String s){

        Map m = new HashMap();
        if (s.substring(0, 6).equals("map:{ ")){
            return parse(s.substring(6));
        }


        return new HashMap();
    }

    public void test_parseMap_flat() {
        List<StandardColorBox> standardColorBoxes = colorBoxList.stream()
                .filter(x -> x instanceof StandardColorBox)
                .map(x -> (StandardColorBox) x)
                .collect(Collectors.toList());
        YourPrivateRoom.SecretBox d = (YourPrivateRoom.SecretBox) (standardColorBoxes.stream()
                .filter(x -> x.getColor().getColorName().equals("white"))
                .map(x -> x.getUpperSpace())
                .collect(Collectors.toList())
                .get(0)
                .getContent());
        log(d.getText());
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    // TODO tom こちらもStringをparseしてMapにしてみよう by もってぃ
    public void test_parseMap_nested() {
        List<StandardColorBox> standardColorBoxes = colorBoxList.stream()
                .filter(x -> x instanceof StandardColorBox)
                .map(x -> (StandardColorBox) x)
                .collect(Collectors.toList());
        YourPrivateRoom.SecretBox d = (YourPrivateRoom.SecretBox) (standardColorBoxes.stream()
                .filter(x -> x.getColor().getColorName().equals("white"))
                .map(x -> x.getMiddleSpace())
                .collect(Collectors.toList())
                .get(0)
                .getContent());
        YourPrivateRoom.SecretBox d1 = (YourPrivateRoom.SecretBox) (standardColorBoxes.stream()
                .filter(x -> x.getColor().getColorName().equals("white"))
                .map(x -> x.getLowerSpace())
                .collect(Collectors.toList())
                .get(0)
                .getContent());
        log(d.getText());
        log(d1.getText());
    }
}
