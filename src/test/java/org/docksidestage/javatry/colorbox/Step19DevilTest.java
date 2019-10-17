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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.space.DoorBoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Devil with color-box, (try if you woke up Devil in StringTest) <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author Tom Oliver
 */
public class Step19DevilTest extends PlainTestCase {
    List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
    List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
    List<Object> cleanContent = spaces.stream().map(x -> {

        if (x instanceof DoorBoxSpace) {
            ((DoorBoxSpace) x).openTheDoor();
        }
        return x.getContent();

    }).collect(Collectors.toList());
    // ===================================================================================
    //                                                                        Devil Parade
    //                                                                        ============

    public List<ColorBox> getDevils() {

        List<ColorBox> devils = colorBoxList.stream()
                .filter(x -> x.getSpaceList()
                        .stream()
                        .filter(y -> y.getContent() instanceof YourPrivateRoom.DevilBox)
                        .map(y -> (YourPrivateRoom.DevilBox) y.getContent())
                        .filter(y -> {
                            y.wakeUp();
                            y.allowMe();
                            y.open();

                            try {
                                y.getText();
                                return false;
                            } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {

                                return true;
                            }
                        })
                        .collect(Collectors.toList())
                        .size() > 0)
                .collect(Collectors.toList());
        return devils;
    }

    /**
     * What is the content in low space of color-box
     * of which lengths of the color is same as first place number of BigDecimal value first found in List in box spaces,
     * that the second decimal place is same as tens place of depth of the color-box
     * of which color name ends with third character of color-box that contains null as content? <br>
     * (nullを含んでいるカラーボックスの色の名前の3文字目の文字で色の名前が終わっているカラーボックスの深さの十の位の数字が小数点第二桁目になっている
     * スペースの中のリストの中で最初に見つかるBigDecimalの一の位の数字と同じ色の長さのカラーボックスの一番下のスペースに入っているものは？)
     */
    public void test_too_long() {

        List<BigDecimal> bds = colorBoxList.stream().map(x -> {
            return (List<BigDecimal>) x.getSpaceList()
                    .stream()
                    .filter(y -> y.getContent() instanceof List)
                    .filter(y -> ((List<Object>) y.getContent()).stream()
                            .filter(z -> z instanceof BigDecimal)
                            .collect(Collectors.toList())
                            .size() > 0)
                    .map(y -> (List) y.getContent())
                    .filter(y -> !y.isEmpty())
                    .flatMap(y -> y.stream())
                    .filter(y -> y instanceof BigDecimal)
                    .collect(Collectors.toList());
        }).flatMap(List::stream).collect(Collectors.toList());

        // Change to do devil boxes.
        List<ColorBox> nulls = colorBoxList.stream()
                .filter(x -> x.getSpaceList().stream().filter(y -> y.getContent() == null).collect(Collectors.toList()).size() > 0)
                .collect(Collectors.toList());

        nulls.addAll(getDevils());

        List<String> sanMojiMes = nulls.stream().map(x -> x.getColor().getColorName().substring(2, 3)).collect(Collectors.toList());

        log(sanMojiMes);

        List<ColorBox> ends = colorBoxList.stream().filter(x -> {
            return sanMojiMes.stream().filter(y -> x.getColor().getColorName().endsWith(y)).collect(Collectors.toList()).size() > 0;
        }).collect(Collectors.toList());

        List<BigDecimal> twoDp = bds.stream()
                .filter(x -> x.remainder(BigDecimal.ONE).doubleValue() > 0)
                .filter(x -> x.remainder(BigDecimal.ONE).toString().length() > 3)
                .collect(Collectors.toList());

        List<BigDecimal> common = twoDp.stream()
                .filter(x -> ends.stream()
                        .map(y -> (y.getSize().getDepth() + "").substring(0, 1))
                        .collect(Collectors.toList())
                        .contains(x.remainder(BigDecimal.ONE).toString().substring(3, 4)))
                .collect(Collectors.toList());

        log(colorBoxList.stream()
                .filter(x -> x.getColor().getColorName().length() == common.get(0).intValue())
                .map(x -> x.getSpaceList().get(x.getSpaceList().size() - 1).getContent()).collect(Collectors.toList()));
    }

    // ===================================================================================
    //                                                                      Java Destroyer
    //                                                                      ==============
    /**
     * What string of toString() is BoxSize of red color-box after changing height to 160 (forcedly in this method)? <br>
     * ((このテストメソッドの中だけで無理やり)赤いカラーボックスの高さを160に変更して、BoxSizeをtoString()すると？)
     */

    public void test_looks_like_easy() throws NoSuchFieldException, IllegalAccessException {
        ColorBox redBox = colorBoxList.stream().filter(x -> x.getColor().getColorName().equals("red")).collect(Collectors.toList()).get(0);
        Field field = redBox.getSize().getClass().getDeclaredField("height");
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.setInt(redBox.getSize(), 160);
        log(redBox);
    }

    // ===================================================================================
    //                                                                        Meta Journey
    //                                                                        ============
    /**
     * What value is returned from no-parameter functional method of interface that has FunctionalInterface annotation in color-boxes? <br> 
     * (カラーボックスに入っているFunctionalInterfaceアノテーションが付与されているインターフェースの引数なしのFunctionalメソッドの戻り値は？)
     */
    public void test_be_frameworker() {
        cleanContent.stream()
                .filter(x -> x instanceof YourPrivateRoom.FavoriteProvider)
                .forEach(x -> log(((YourPrivateRoom.FavoriteProvider) x).justHere()));
    }
}
