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
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.impl.StandardColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author your_name_here
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have?  5 <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            ColorBox colorBox = colorBoxList.get(0);
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            int answer = colorName.length();
            log(answer + " (" + colorName + ")"); // also show name for visual check
        } else {
            log("*not found");
        }
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<String> strings = spaces.stream()
                .filter(x -> x.getContent() != null)
                .map(x -> x.getContent())
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<String> strings = spaces.stream()
                .filter(x -> x.getContent() != null)
                .map(x -> x.getContent())
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<String> strings = spaces.stream()
                .filter(x -> x.getContent() != null)
                .map(x -> x.getContent())
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<String> strings = spaces.stream()
                .filter(x -> x.getContent() != null)
                .map(x -> x.getContent())
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<String> strings = spaces.stream()
                .filter(x -> x.getContent() != null)
                .map(x -> x.getContent())
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<String> strings = spaces.stream()
                .filter(x -> x.getContent() != null)
                .map(x -> x.getContent())
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<String> strings = spaces.stream()
                .filter(x -> x.getContent() != null)
                .map(x -> x.getContent())
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<String> strings = spaces.stream()
                .filter(x -> x.getContent() != null)
                .map(x -> x.getContent())
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<String> strings = spaces.stream()
                .filter(x -> x.getContent() != null)
                .map(x -> x.getContent())
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<String> paths =
                spaces.stream().filter(x -> x.getContent() != null).map(x -> x.getContent()).filter(x -> x instanceof File).map(x -> {
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<YourPrivateRoom.DevilBox> devilBoxes = spaces.stream()
                .filter(x -> x.getContent() != null)
                .map(x -> x.getContent())
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
    public void test_showMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<String> toStrings = spaces.stream()
                .filter(x -> x.getContent() != null && x.getContent() instanceof LinkedHashMap)
                .map(x -> x.getContent())
                .map(x -> x.toString())
                .collect(Collectors.toList());
        log(toStrings.stream().filter(x -> x.contains("{") && x.split("}").length == 1).collect(Collectors.toList()));
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<String> toStrings = spaces.stream()
                .filter(x -> x.getContent() != null && x.getContent() instanceof LinkedHashMap)
                .map(x -> x.getContent())
                .map(x -> x.toString())
                .collect(Collectors.toList());
        log(toStrings.stream().filter(x -> x.contains("{") && x.split("}").length > 1).collect(Collectors.toList()));
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
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
    public void test_parseMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
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
