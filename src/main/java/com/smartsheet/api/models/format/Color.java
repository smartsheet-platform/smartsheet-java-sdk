package com.smartsheet.api.models.format;
/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2014 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */


/**
 * @author kskeem
 * An enumeration representing all of the colors available for formatting within Smartsheet.
 * 
 */
public enum Color {
    NONE            (null),
    BLACK           ("000000"),
    WHITE           ("FFFFFF"),
    TRANSPARENT     ("transparent"),

    RED_0           ("FEEEF0"),
    ORANGE_0        ("FFF4E6"),
    YELLOW_0        ("FCFAD3"),
    GREEN_0         ("ECFEEF"),
    BLUE_0          ("E6F5FE"),
    PURPLE_0        ("F3E5FA"),
    BROWN_0         ("F2E8DE"),

    RED_1           ("FDDADB"),
    ORANGE_1        ("FEE6C9"),
    YELLOW_1        ("FCF99B"),
    GREEN_1         ("D9F9DD"),
    BLUE_1          ("D3E8F6"),
    PURPLE_1        ("E9C9FA"),
    BROWN_1         ("EEDCCA"),
    GREY_1          ("E5E5E5"),

    RED_2           ("FCABAE"),
    ORANGE_2        ("FDD09C"),
    YELLOW_2        ("FFFF00"),
    GREEN_2         ("A9EEAE"),
    BLUE_2          ("95B9F0"),
    PURPLE_2        ("D39EF0"),
    BROWN_2         ("D7B99C"),
    GREY_2          ("CDCCCC"),

    RED_3           ("FF0000"),
    ORANGE_3        ("FF8100"),
    YELLOW_3        ("FDE900"),
    GREEN_3         ("00CA0E"),
    BLUE_3          ("165DEF"),
    PURPLE_3        ("A601D6"),
    BROWN_3         ("974C02"),
    GREY_3          ("808080"),

    RED_4           ("770E12"),
    ORANGE_4        ("D05800"),
    YELLOW_4        ("FFC800"),
    GREEN_4         ("00750D"),
    BLUE_4          ("00395E"),
    PURPLE_4        ("6C018B"),
    BROWN_4         ("5A2D01"),
    ;

    private final String hex;

    /**
     * The default setting when the {@link Format} for {@link Color} is null;
     */
    public static final Color DEFAULT = NONE;

    Color (String hex) {
        this.hex = hex;
    }

    /**
     * @return the hex
     */
    public String getHex() {
        return hex;
    }
}