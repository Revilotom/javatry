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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.space.DoorBoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Date with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author Tom Oliver
 */
public class Step14DateTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * What string is date in color-boxes formatted as slash-separated (e.g. 2019/04/24)? <br>
     * (カラーボックスに入っている日付をスラッシュ区切り (e.g. 2019/04/24) のフォーマットしたら？)
     */

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

    public void test_formatDate() {

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        cleanContent.stream().filter(x -> x instanceof LocalDate).map(x -> (LocalDate) x).forEach(x -> log(x.format(myFormatObj)));

        cleanContent.stream().filter(x -> x instanceof LocalDateTime).map(x -> (LocalDateTime) x).forEach(x -> log(x.format(myFormatObj)));

    }

    /**
     * What string of toString() is converted to LocalDate from slash-separated date string (e.g. 2019/04/24) in Set in yellow color-box? <br>
     * (yellowのカラーボックスに入っているSetの中のスラッシュ区切り (e.g. 2019/04/24) の日付文字列をLocalDateに変換してtoString()したら？)
     */
    public void test_parseDate() {
        List<ColorBox> yellows =
                colorBoxList.stream().filter(x -> x.getColor().getColorName().equals("yellow")).collect(Collectors.toList());
        List<BoxSpace> yellowSpaces = yellows.stream().map(x -> x.getSpaceList()).flatMap(List::stream).collect(Collectors.toList());
        List<Object> sets = yellowSpaces.stream().map(x -> x.getContent()).filter(x -> x instanceof Set).collect(Collectors.toList());
        Set<String> set = (Set<String>) sets.get(0);

        set.stream().filter(x -> x.split("/").length == 3).forEach(x -> {
            try {

                LocalDate date7 = LocalDate.parse(x, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                log(date7.toString());

            } catch (DateTimeParseException e) {

            }
        });

    }

    /**
     * What is total of month numbers of date in color-boxes? <br>
     * (カラーボックスに入っている日付の月を全て足したら？)
     */
    public void test_sumMonth() {
        int months = 0;
        months += cleanContent.stream()
                .filter(x -> x instanceof LocalDate)
                .map(x -> (LocalDate) x)
                .mapToInt(x -> (int) x.getMonth().getValue())
                .sum();
        months += cleanContent.stream()
                .filter(x -> x instanceof LocalDateTime)
                .map(x -> (LocalDateTime) x)
                .mapToInt(x -> (int) x.getMonth().getValue())
                .sum();
        log(months);
    }

    /**
     * What day of week is second-found date in color-boxes added to three days? <br>
     * (カラーボックスに入っている二番目に見つかる日付に3日進めると何曜日？)
     */
    public void test_plusDays_weekOfDay() {
        cleanContent.stream().filter(x -> x instanceof LocalDate).map(x -> (LocalDate) x).forEach(x -> {
            log(x.plusDays(3).getDayOfWeek());
        });
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * How many days (number of day) are between two dates in yellow color-boxes?   <br>
     * (yellowのカラーボックスに入っている二つの日付は何日離れている？)
     */
    public void test_diffDay() {

        int days1 =
                cleanContent.stream().filter(x -> x instanceof LocalDate).map(x -> (LocalDate) x).mapToInt(x -> (int) x.toEpochDay()).sum();
        int days2 = cleanContent.stream()
                .filter(x -> x instanceof LocalDateTime)
                .map(x -> (LocalDateTime) x)
                .mapToInt(x -> (int) x.toLocalDate().toEpochDay())
                .sum();
        log(days1 - days2);

    }

    /**
     * What date is LocalDate in yellow color-box
     * that is month-added with LocalDateTime's seconds in the same color-box,
     * and is day-added with Long value in red color-box,
     * and is day-added with the first decimal place of BigDecimal that has three (3) as integer in List in color-boxes? <br>
     * (yellowのカラーボックスに入っているLocalDateに、同じカラーボックスに入っているLocalDateTimeの秒数を月数として足して、
     * redのカラーボックスに入っているLong型を日数として足して、カラーボックスに入っているリストの中のBigDecimalの整数値が3の小数点第一位の数を日数として引いた日付は？)
     */
    public void test_birthdate() {

        LocalDate orig = (LocalDate) cleanContent.stream().filter(x -> x instanceof LocalDate).collect(Collectors.toList()).get(0);

        int months = cleanContent.stream()
                .filter(x -> x instanceof LocalDateTime)
                .map(x -> (LocalDateTime) x)
                .mapToInt(x -> (int) x.toEpochSecond(ZoneOffset.UTC))
                .sum();

        orig = orig.plusMonths(months);

        List<List> listOfLists =
                cleanContent.stream()
                        .filter(x -> x instanceof List)
                            .map(x -> (List)x).collect(Collectors.toList());

        List<BigDecimal> bigDs = (List<BigDecimal>) listOfLists.get(0)
                .stream()
                .filter(x -> x instanceof BigDecimal && x.toString().startsWith("3."))
                .collect(Collectors.toList());

        BigDecimal bD = bigDs.get(0);

        orig = orig.plusDays(Integer.parseInt(bD.toString().substring(2, 3)));

        LocalDate finalOrig = orig;
        colorBoxList.stream()
                .filter(x -> x.getColor().getColorName().equals("red"))
                .forEach(x -> x.getSpaceList().stream()
                        .filter(y -> y.getContent() instanceof Long).forEach(y -> finalOrig.plusDays((Long)y.getContent())));

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        log(finalOrig.format(myFormatObj));
    }

    /**
     * What second is LocalTime in color-boxes? <br>
     * (カラーボックスに入っているLocalTimeの秒は？)
     */
    public void test_beReader() {

        cleanContent.stream()
            .filter(x -> x instanceof LocalTime)
            .map(x -> (LocalTime) x)
            .forEach(x -> log(x.getSecond()));


    }
}
