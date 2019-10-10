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

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Number with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author Tom Oliver
 */
public class Step13NumberTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * How many integer-type values in color-boxes are between 0 and 54? <br>
     * (カラーボックの中に入っているInteger型で、0から54までの値は何個ある？)
     */

    List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
    List<BoxSpace> spaces = colorBoxList.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
    List<Object> cleanContent = spaces.stream().filter(x -> x.getContent() != null).map(x -> x.getContent()).collect(Collectors.toList());

    public void test_countZeroToFiftyFour_IntegerOnly() {
        List<Integer> ans = cleanContent.stream()
                .filter(x -> x instanceof Integer)
                .map(x -> (Integer) x)
                .filter(x -> x >= 0 && x <= 54)
                .collect(Collectors.toList());
        System.out.println(ans);
    }

    /**
     * How many number values in color-boxes are between 0 and 54? <br>
     * (カラーボックの中に入っている数値で、0から54までの値は何個ある？)
     */
    public void test_countZeroToFiftyFour_Number() {
        List<Number> ans = cleanContent.stream()
                .filter(x -> x instanceof Number)
                .map(x -> (Number) x)
                .filter(x -> x.doubleValue() >= 0 && x.doubleValue() <= 54)
                .collect(Collectors.toList());
        System.out.println(ans);
    }

    /**
     * What color name is used by color-box that has integer-type content and the biggest width in them? <br>
     * (カラーボックスの中で、Integer型の Content を持っていてBoxSizeの幅が一番大きいカラーボックスの色は？)
     */
    public void test_findColorBigWidthHasInteger() {
        Optional<ColorBox> max = colorBoxList.stream()
                .filter(x -> x.getSpaceList().stream().anyMatch(y -> y.getContent() instanceof Integer))
                .max(Comparator.comparingInt(o -> o.getSize().getWidth()));
        System.out.println(max);
    }

    /**
     * What is total of BigDecimal values in List in color-boxes? <br>
     * (カラーボックスの中に入ってる List の中の BigDecimal を全て足し合わせると？)
     */
    public void test_sumBigDecimalInList() {
        List<List> listOfLists = cleanContent.stream().filter(x -> x instanceof List).map(x -> (List) x).collect(Collectors.toList());

        double sum = listOfLists.stream().map(x -> {
            List<BigDecimal> bdList = (List<BigDecimal>) x.stream().filter(y -> y instanceof BigDecimal).collect(Collectors.toList());

            BigDecimal result = bdList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

            return result.doubleValue();

        }).mapToDouble(Double::doubleValue).sum();

        log(sum);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What key is related to value that is max number in Map that has only number in color-boxes? <br>
     * (カラーボックスに入ってる、valueが数値のみの Map の中で一番大きいvalueのkeyは？)
     */
    public void test_findMaxMapNumberValue() {
        List<Map> maps = cleanContent.stream().filter(x -> x instanceof Map).map(x -> (Map) x).collect(Collectors.toList());
        List<Map> mapsWithNumbersOnly =
                maps.stream().filter(x -> x.values().stream().allMatch(y -> y instanceof Number)).collect(Collectors.toList());
        List<Comparable> maxes = mapsWithNumbersOnly.stream().map(x -> Collections.max(x.values())).collect(Collectors.toList());
        log(Collections.max(maxes));
    }

    /**
     * What is total of number or number-character values in Map in purple color-box? <br> 
     * (purpleのカラーボックスに入ってる Map の中のvalueの数値・数字の合計は？)
     */
    public void test_sumMapNumberValue() {
        List<ColorBox> purples =
                colorBoxList.stream().filter(x -> x.getColor().getColorName().equals("purple")).collect(Collectors.toList());
        List<BoxSpace> purpleSpaces = purples.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<Map> maps = purpleSpaces.stream()
                .filter(x -> x.getContent() instanceof Map)
                .map(x -> (Map) x.getContent())
                .collect(Collectors.toList());
        double total = 0.0;

        for (Map m : maps) {
            for (Object n : m.values()) {
                Number num = 0;

                try{
                    if (n instanceof String) {
                    num = Integer.parseInt((String) n);
                } else {
                        num = (Number) n;
                    }
                }
                catch (ClassCastException | NumberFormatException e){

                }
                total += num.doubleValue();
            }
        }
        log(total);
    }

}
