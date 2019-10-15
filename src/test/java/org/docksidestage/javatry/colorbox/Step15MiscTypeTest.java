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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.impl.StandardColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.space.DoorBoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of various type with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author your_name_here
 */
public class Step15MiscTypeTest extends PlainTestCase {



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
    //                                                                           Exception
    //                                                                           =========
    /**
     * What class name is throw-able object in color-boxes? <br>
     * (カラーボックスに入っているthrowできるオブジェクトのクラス名は？)
     */
    public void test_throwable() {
        cleanContent.stream().filter(x -> x instanceof Throwable).forEach(x -> log(x.getClass()));
    }

    /**
     * What message is for exception that is nested by exception in color-boxes? <br>
     * (カラーボックスに入っている例外オブジェクトのネストした例外インスタンスのメッセージは？)
     */
    public void test_nestedException() {
        cleanContent.stream().filter(x -> x instanceof Throwable).forEach(x -> log(((Throwable) x).getCause().getMessage()));
    }

    // ===================================================================================
    //                                                                           Interface
    //                                                                           =========
    /**
     * What value is returned by justHere() of FavoriteProvider in yellow color-box? <br>
     * (カラーボックスに入っているFavoriteProviderインターフェースのjustHere()メソッドの戻り値は？)
     */
    public void test_interfaceCall() {
        cleanContent.stream().filter(x -> x instanceof YourPrivateRoom.FavoriteProvider)
                .forEach(x -> log(((YourPrivateRoom.FavoriteProvider) x).justHere()));
    }

    // ===================================================================================
    //                                                                            Optional
    //                                                                            ========
    /**
     * What keyword is in BoxedStage of BoxedResort in List in beige color-box? (show "none" if no value) <br>
     * (beigeのカラーボックスに入っているListの中のBoxedResortのBoxedStageのkeywordは？(値がなければ固定の"none"という値を))
     */
    public void test_optionalMapping() {
        List<ColorBox> beige = colorBoxList.stream().filter(x -> x.getColor().getColorName().equals("beige")).collect(Collectors.toList());

        List<Object> objects = beige.stream()
                .map(x -> x.getSpaceList())
                .flatMap(List::stream)
                .map(x -> x.getContent())
                .collect(Collectors.toList());

        List<YourPrivateRoom.BoxedResort> boxedResorts =
                (List<YourPrivateRoom.BoxedResort>) objects.stream().filter(x -> x instanceof List).flatMap(x -> ((List) x).stream()).collect(Collectors.toList());

        List<String> stages = boxedResorts.stream()
                .map(x -> x.getPark().flatMap(y -> y.getStage()).map(y -> y.getKeyword()).orElse("none"))
                .collect(Collectors.toList());
        log(stages);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What line number is makeEighthColorBox() call in getColorBoxList()? <br>
     * (getColorBoxList()メソッドの中のmakeEighthColorBox()メソッドを呼び出している箇所の行数は？)
     */
    public void test_lineNumber() {

        // Line 51
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
    }
}
