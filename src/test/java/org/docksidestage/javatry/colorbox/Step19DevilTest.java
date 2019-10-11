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
import java.lang.reflect.Method;
import java.util.Arrays;
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
 * @author your_name_here
 */
public class Step19DevilTest extends PlainTestCase {
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
    //                                                                        Devil Parade
    //                                                                        ============
    /**
     * What is the content in low space of color-box
     * of which lengths of the color is same as first place number of BigDecimal value first found in List in box spaces,
     * that the second decimal place is same as tens place of depth of the color-box
     * of which color name ends with third character of color-box that contains null as content? <br>
     * (nullを含んでいるカラーボックスの色の名前の3文字目の文字で色の名前が終わっているカラーボックスの深さの十の位の数字が小数点第二桁目になっている
     * スペースの中のリストの中で最初に見つかるBigDecimalの一の位の数字と同じ色の長さのカラーボックスの一番下のスペースに入っているものは？)
     */
    public void test_too_long() {

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
        Field field  = redBox.getSize().getClass().getDeclaredField("height");
        field.setAccessible(true);
        field.set(redBox, 160);
        log(redBox);

//        Object o = (Object)redBox;

//        List<Method> methods =  Arrays.asList(YourPrivateRoom.class.getMethods());
//        System.out.println(methods.stream()
//                .filter(method -> method.getName().contains("makeSecondColorbox"))
//                .collect(Collectors.toList()));




    }

    // ===================================================================================
    //                                                                        Meta Journey
    //                                                                        ============
    /**
     * What value is returned from functional method of interface that has FunctionalInterface annotation in color-boxes? <br> 
     * (カラーボックスに入っているFunctionalInterfaceアノテーションが付与されているインターフェースのFunctionalメソッドの戻り値は？)
     */
    public void test_be_frameworker() {
        YourPrivateRoom.FavoriteProvider f = new YourPrivateRoom.FavoriteProvider() {
            @Override
            public String justHere() {
                return null;
            }

        };

        log(f.justHere()); // In this example null
    }
}
