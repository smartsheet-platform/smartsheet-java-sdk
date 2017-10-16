package com.smartsheet.api.models.format;

/*
 * #[license]
 * Smartsheet Java SDK
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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FormatTest {

    @Mock
    JsonGenerator generator;
    @Mock
    SerializerProvider provider;

    @Before
    public void setup() {

    }
    private final static int U = Format.UNSET;
    enum ParserTests {
        VALID_A     (",,1,1,1,,,,20,29,,,,,,",      new int[]{U,U,1,1,1,U,U,U,20,29,U,U,U,U,U,U}),
        VALID_B     ("1,11,,,,,,,,,,13,0,1,2,",     new int[]{1,11,U,U,U,U,U,U,U,U,U,13,0,1,2,U}),
        VALID_C     (",1,,1,1,,1,1,,27,,,,,,1",     new int[]{U,1,U,1,1,U,1,1,U,27,U,U,U,U,U,1}),
        VALID_D     (",,,,,,2,2,,,,,,,,",           new int[]{U,U,U,U,U,U,2,2,U,U,U,U,U,U,U,U}),
        VALID_E     ("3,7,1,,,,,,,,,,,,,1",         new int[]{3,7,1,U,U,U,U,U,U,U,U,U,U,U,U,1}),
        TOO_MANY    ("3,7,1,,,,,,,,,,,,,1,1,2,3,1,",new int[]{3,7,1,U,U,U,U,U,U,U,U,U,U,U,U,1}),
        TOO_MANY_2  ("3,7,1,,,,,,,,,,,,,1,1,2,3,1", new int[]{3,7,1,U,U,U,U,U,U,U,U,U,U,U,U,1}),
        ;
        final static int EXPECTED_COUNT = 16;
        final String format;
        final int[] output;

        ParserTests(String format, int[] output) {
            this.format = format;
            this.output = output;
        }
    }

    @Test
    public void testParser() {
        for (ParserTests t : ParserTests.values()) {
            Format f = new Format(t.format);
            int i = 0;
            for (; i< t.output.length; i++) {
                assertEquals("index " + i +" failed in " + t, t.output[i], f.formatArray[i]);
            }
            assertTrue("Did not parse the correct amount: " + i, i >= ParserTests.EXPECTED_COUNT);
        }
    }

    @Test
    public void testFormatBuilderAllDefaults() throws IOException {
        Format actual = new Format.FormatBuilder()
                .build();

        verifySerializedFormat(",,,,,,,,,,,,,,,", actual);
    }

    @Test
    public void testFormatBuilderVariousSettings() throws IOException {
        Format actual = new Format.FormatBuilder()
                .withBackgroundColor(Color.ORANGE_4)
                .withCurrency(Currency.BRAZIL_REAIS)
                .withHorizontalAlignment(HorizontalAlignment.CENTER)
                .withNumberFormat(NumberFormat.PERCENT)
                .build();

        verifySerializedFormat(",,,,,,"+ HorizontalAlignment.CENTER.ordinal() +",,,"+ Color.ORANGE_4.ordinal() +",,"+ Currency.BRAZIL_REAIS.ordinal() +",,,"+ NumberFormat.PERCENT.ordinal() +",",
                actual);
    }

    @Test
    public void testAllFormats() throws IOException {
        runTestCases(FontFamilyTest.values());
        runTestCases(FontSizeTest.values());
        runTestCases(BoldTest.values());
        runTestCases(ItalicTest.values());
        runTestCases(UnderlineTest.values());
        runTestCases(StrikethroughTest.values());
        runTestCases(HAlignTest.values());
        runTestCases(VAlignTest.values());
        runTestCases(TextColorTest.values());
        runTestCases(BackgroundColorTest.values());
        runTestCases(TaskbarColorTest.values());
        runTestCases(ThousandsSeparatorTest.values());
        runTestCases(NumberFormatTest.values());
        runTestCases(TextWrapTest.values());

    }
    public void runTestCases(FormatTestCase<?>[] testCases) throws IOException {
        testFormatStringParsing(testCases);
        testFormatSerialization(testCases);
        testFormatBuilderSetFunction(testCases);
        testFormatBuilderCloneFormat(testCases);
    }

    public void testFormatStringParsing(FormatTestCase<?>[] testCases) throws IOException {
        for (FormatTestCase test : testCases) {
            Format format = new Format(test.getFormat());
            assertEquals ("Test case " + test, test.getExpected(), test.getResult(format));
        }
    }

    public void testFormatSerialization(FormatTestCase<?>[] testCases) throws IOException {
        for (FormatTestCase test : testCases) {
            Format format = new Format(test.getFormat());

            // Serialize the format out and make sure it matches the original value
            verifySerializedFormat(test.getFormat(), format);
        }
    }

    public void testFormatBuilderSetFunction(FormatTestCase<?>[] testCases) throws IOException {
        int testCaseIndex = 0;
        for (FormatTestCase test : testCases) {
            // Use the format builder to build the format and then test to see if it was built properly
            if (testCaseIndex == test.getExpected().ordinal()) {
                Format.FormatBuilder formatBuilder = new Format.FormatBuilder();
                test.applyToFormatBuilder(test.getExpected(), formatBuilder);
                Format builtFormat = formatBuilder.build();

                verifySerializedFormat(test.getFormat(), builtFormat);
            }

            testCaseIndex++;
        }
    }

    public void testFormatBuilderCloneFormat(FormatTestCase<?>[] testCases) throws IOException {
        for (FormatTestCase test : testCases) {
            Format format = new Format(test.getFormat());

            Format clonedFormat = new Format.FormatBuilder()
                    .withFormat(format)
                    .build();
            verifySerializedFormat(test.getFormat(), clonedFormat);
        }
    }

    /**
     * Serializes out a format and compares it to the expected value. Fails the test if it does not match.
     */
    private void verifySerializedFormat(String expected, Format format) throws IOException {
        Format.FormatSerializer formatSerializer = new Format.FormatSerializer();
        formatSerializer.serialize(format, generator, provider);
        Mockito.verify(generator).writeString(expected);
        Mockito.verifyZeroInteractions(provider);
        Mockito.reset(generator, provider);
    }

    interface FormatTestCase <T extends Enum> {
        String getFormat();
        T getExpected();
        T getResult(Format result);
        void applyToFormatBuilder(T value, Format.FormatBuilder formatBuilder);
    }

    enum FontFamilyTest implements FormatTestCase<FontFamily> {
        ARIAL           ("0,,,,,,,,,,,,,,,", FontFamily.ARIAL),
        TAHOMA          ("1,,,,,,,,,,,,,,,", FontFamily.TAHOMA),
        TIMES_NEW_ROMAN ("2,,,,,,,,,,,,,,,", FontFamily.TIMES_NEW_ROMAN),
        VERDANA         ("3,,,,,,,,,,,,,,,", FontFamily.VERDANA),
        SOMETHING_NEW   ("4,,,,,,,,,,,,,,,", FontFamily.ARIAL),
        ;
        final String format;
        FontFamily expected;

        FontFamilyTest (String format, FontFamily font) {
            this.expected = font;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public FontFamily getExpected() {
            return expected;
        }
        public FontFamily getResult(Format result) {
            return result.getFontFamily();
        }

        public void applyToFormatBuilder(FontFamily value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withFontFamily(value);
        }
    }

    enum FontSizeTest implements FormatTestCase <FontSize> {
        PT_8            (",0,,,,,,,,,,,,,,", FontSize.PT_8),
        PT_9            (",1,,,,,,,,,,,,,,", FontSize.PT_9),
        PT_10           (",2,,,,,,,,,,,,,,", FontSize.PT_10),
        PT_12           (",3,,,,,,,,,,,,,,", FontSize.PT_12),
        PT_14           (",4,,,,,,,,,,,,,,", FontSize.PT_14),
        PT_16           (",5,,,,,,,,,,,,,,", FontSize.PT_16),
        PT_18           (",6,,,,,,,,,,,,,,", FontSize.PT_18),
        PT_20           (",7,,,,,,,,,,,,,,", FontSize.PT_20),
        PT_24           (",8,,,,,,,,,,,,,,", FontSize.PT_24),
        PT_28           (",9,,,,,,,,,,,,,,", FontSize.PT_28),
        PT_32           (",10,,,,,,,,,,,,,,", FontSize.PT_32),
        PT_36           (",11,,,,,,,,,,,,,,", FontSize.PT_36),
        SOMETHING_NEW   (",12,,,,,,,,,,,,,,", FontSize.PT_10),
        ;
        final String format;
        FontSize expected;

        FontSizeTest (String format, FontSize fontSize) {
            this.expected = fontSize;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public FontSize getExpected() {
            return expected;
        }
        public FontSize getResult(Format result) {
            return result.getFontSize();
        }
        public void applyToFormatBuilder(FontSize value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withFontSize(value);
        }
    }

    enum BoldTest implements FormatTestCase<Bold>  {
        OFF             (",,0,,,,,,,,,,,,,", Bold.NONE),
        ON              (",,1,,,,,,,,,,,,,", Bold.ON),
        SOMETHING_NEW   (",,2,,,,,,,,,,,,,", Bold.NONE),
        ;
        final String format;
        Bold expected;
        BoldTest (String format, Bold bold) {
            this.expected = bold;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public Bold getExpected() {
            return expected;
        }
        public Bold getResult(Format result) {
            return result.getBold();
        }
        public void applyToFormatBuilder(Bold value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withBold(value);
        }
    }

    enum ItalicTest implements FormatTestCase<Italic>  {
        OFF             (",,,0,,,,,,,,,,,,", Italic.NONE),
        ON              (",,,1,,,,,,,,,,,,", Italic.ON),
        SOMETHING_NEW   (",,,2,,,,,,,,,,,,", Italic.NONE),
        ;
        final String format;
        Italic expected;
        ItalicTest (String format, Italic expected) {
            this.expected = expected;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public Italic getExpected() {
            return expected;
        }
        public Italic getResult(Format result) {
            return result.getItalic();
        }
        public void applyToFormatBuilder(Italic value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withItalic(value);
        }
    }

    enum UnderlineTest implements FormatTestCase<Underline>  {
        OFF             (",,,,0,,,,,,,,,,,", Underline.NONE),
        ON              (",,,,1,,,,,,,,,,,", Underline.ON),
        SOMETHING_NEW   (",,,,2,,,,,,,,,,,", Underline.NONE),
        ;
        final String format;
        Underline expected;
        UnderlineTest (String format, Underline expected) {
            this.expected = expected;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public Underline getExpected() {
            return expected;
        }
        public Underline getResult(Format result) {
            return result.getUnderline();
        }
        public void applyToFormatBuilder(Underline value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withUnderline(value);
        }
    }

    enum StrikethroughTest implements FormatTestCase<Strikethrough>  {
        OFF             (",,,,,0,,,,,,,,,,", Strikethrough.NONE),
        ON              (",,,,,1,,,,,,,,,,", Strikethrough.ON),
        SOMETHING_NEW   (",,,,,2,,,,,,,,,,", Strikethrough.NONE),
        ;
        final String format;
        Strikethrough expected;
        StrikethroughTest (String format, Strikethrough expected) {
            this.expected = expected;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public Strikethrough getExpected() {
            return expected;
        }
        public Strikethrough getResult(Format result) {
            return result.getStrikethrough();
        }
        public void applyToFormatBuilder(Strikethrough value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withStrikethrough(value);
        }
    }

    enum HAlignTest implements FormatTestCase<HorizontalAlignment>  {
        DEFAULT         (",,,,,,0,,,,,,,,,", HorizontalAlignment.DEFAULT_ALIGNMENT),
        LEFT            (",,,,,,1,,,,,,,,,", HorizontalAlignment.LEFT),
        CENTER          (",,,,,,2,,,,,,,,,", HorizontalAlignment.CENTER),
        RIGHT           (",,,,,,3,,,,,,,,,", HorizontalAlignment.RIGHT),
        SOMETHING_NEW   (",,,,,,4,,,,,,,,,", HorizontalAlignment.DEFAULT_ALIGNMENT),
        ;
        final String format;
        HorizontalAlignment expected;
        HAlignTest (String format, HorizontalAlignment expected) {
            this.expected = expected;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public HorizontalAlignment getExpected() {
            return expected;
        }
        public HorizontalAlignment getResult(Format result) {
            return result.getHorizontalAlignment();
        }
        public void applyToFormatBuilder(HorizontalAlignment value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withHorizontalAlignment(value);
        }
    }

    enum VAlignTest implements FormatTestCase<VerticalAlignment>  {
        DEFAULT         (",,,,,,,0,,,,,,,,", VerticalAlignment.DEFAULT_ALIGNMENT),
        LEFT            (",,,,,,,1,,,,,,,,", VerticalAlignment.TOP),
        CENTER          (",,,,,,,2,,,,,,,,", VerticalAlignment.MIDDLE),
        RIGHT           (",,,,,,,3,,,,,,,,", VerticalAlignment.BOTTOM),
        SOMETHING_NEW   (",,,,,,,4,,,,,,,,", VerticalAlignment.DEFAULT_ALIGNMENT),
        ;
        final String format;
        VerticalAlignment expected;
        VAlignTest (String format, VerticalAlignment expected) {
            this.expected = expected;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public VerticalAlignment getExpected() {
            return expected;
        }
        public VerticalAlignment getResult(Format result) {
            return result.getVerticalAlignment();
        }
        public void applyToFormatBuilder(VerticalAlignment value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withVerticalAlignment(value);
        }
    }

    enum TextColorTest implements FormatTestCase<Color>  {
        NONE            (",,,,,,,,0,,,,,,,", Color.NONE),
        BLACK           (",,,,,,,,1,,,,,,,", Color.BLACK),
        WHITE           (",,,,,,,,2,,,,,,,", Color.WHITE),
        TRANSPARENT     (",,,,,,,,3,,,,,,,", Color.TRANSPARENT),

        RED_0           (",,,,,,,,4,,,,,,,", Color.RED_0),
        ORANGE_0        (",,,,,,,,5,,,,,,,", Color.ORANGE_0),
        YELLOW_0        (",,,,,,,,6,,,,,,,", Color.YELLOW_0),
        GREEN_0         (",,,,,,,,7,,,,,,,", Color.GREEN_0),
        BLUE_0          (",,,,,,,,8,,,,,,,", Color.BLUE_0),
        PURPLE_0        (",,,,,,,,9,,,,,,,", Color.PURPLE_0),
        BROWN_0         (",,,,,,,,10,,,,,,,", Color.BROWN_0),

        RED_1           (",,,,,,,,11,,,,,,,", Color.RED_1),
        ORANGE_1        (",,,,,,,,12,,,,,,,", Color.ORANGE_1),
        YELLOW_1        (",,,,,,,,13,,,,,,,", Color.YELLOW_1),
        GREEN_1         (",,,,,,,,14,,,,,,,", Color.GREEN_1),
        BLUE_1          (",,,,,,,,15,,,,,,,", Color.BLUE_1),
        PURPLE_1        (",,,,,,,,16,,,,,,,", Color.PURPLE_1),
        BROWN_1         (",,,,,,,,17,,,,,,,", Color.BROWN_1),
        GREY_1          (",,,,,,,,18,,,,,,,", Color.GREY_1),

        RED_2           (",,,,,,,,19,,,,,,,", Color.RED_2),
        ORANGE_2        (",,,,,,,,20,,,,,,,", Color.ORANGE_2),
        YELLOW_2        (",,,,,,,,21,,,,,,,", Color.YELLOW_2),
        GREEN_2         (",,,,,,,,22,,,,,,,", Color.GREEN_2),
        BLUE_2          (",,,,,,,,23,,,,,,,", Color.BLUE_2),
        PURPLE_2        (",,,,,,,,24,,,,,,,", Color.PURPLE_2),
        BROWN_2         (",,,,,,,,25,,,,,,,", Color.BROWN_2),
        GREY_2          (",,,,,,,,26,,,,,,,", Color.GREY_2),

        RED_3           (",,,,,,,,27,,,,,,,", Color.RED_3),
        ORANGE_3        (",,,,,,,,28,,,,,,,", Color.ORANGE_3),
        YELLOW_3        (",,,,,,,,29,,,,,,,", Color.YELLOW_3),
        GREEN_3         (",,,,,,,,30,,,,,,,", Color.GREEN_3),
        BLUE_3          (",,,,,,,,31,,,,,,,", Color.BLUE_3),
        PURPLE_3        (",,,,,,,,32,,,,,,,", Color.PURPLE_3),
        BROWN_3         (",,,,,,,,33,,,,,,,", Color.BROWN_3),
        GREY_3          (",,,,,,,,34,,,,,,,", Color.GREY_3),

        RED_4           (",,,,,,,,35,,,,,,,", Color.RED_4),
        ORANGE_4        (",,,,,,,,36,,,,,,,", Color.ORANGE_4),
        YELLOW_4        (",,,,,,,,37,,,,,,,", Color.YELLOW_4),
        GREEN_4         (",,,,,,,,38,,,,,,,", Color.GREEN_4),
        BLUE_4          (",,,,,,,,39,,,,,,,", Color.BLUE_4),
        PURPLE_4        (",,,,,,,,40,,,,,,,", Color.PURPLE_4),
        BROWN_4         (",,,,,,,,41,,,,,,,", Color.BROWN_4),
        SOMETHING_NEW   (",,,,,,,,42,,,,,,,", Color.NONE),

        ;
        final String format;
        Color expected;
        TextColorTest (String format, Color expected) {
            this.expected = expected;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public Color getExpected() {
            return expected;
        }
        public Color getResult(Format result) {
            return result.getTextColor();
        }
        public void applyToFormatBuilder(Color value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withTextColor(value);
        }
    }


    enum BackgroundColorTest implements FormatTestCase<Color>  {
        NONE            (",,,,,,,,,0,,,,,,", Color.NONE),
        BLACK           (",,,,,,,,,1,,,,,,", Color.BLACK),
        WHITE           (",,,,,,,,,2,,,,,,", Color.WHITE),
        TRANSPARENT     (",,,,,,,,,3,,,,,,", Color.TRANSPARENT),

        RED_0           (",,,,,,,,,4,,,,,,", Color.RED_0),
        ORANGE_0        (",,,,,,,,,5,,,,,,", Color.ORANGE_0),
        YELLOW_0        (",,,,,,,,,6,,,,,,", Color.YELLOW_0),
        GREEN_0         (",,,,,,,,,7,,,,,,", Color.GREEN_0),
        BLUE_0          (",,,,,,,,,8,,,,,,", Color.BLUE_0),
        PURPLE_0        (",,,,,,,,,9,,,,,,", Color.PURPLE_0),
        BROWN_0         (",,,,,,,,,10,,,,,,", Color.BROWN_0),

        RED_1           (",,,,,,,,,11,,,,,,", Color.RED_1),
        ORANGE_1        (",,,,,,,,,12,,,,,,", Color.ORANGE_1),
        YELLOW_1        (",,,,,,,,,13,,,,,,", Color.YELLOW_1),
        GREEN_1         (",,,,,,,,,14,,,,,,", Color.GREEN_1),
        BLUE_1          (",,,,,,,,,15,,,,,,", Color.BLUE_1),
        PURPLE_1        (",,,,,,,,,16,,,,,,", Color.PURPLE_1),
        BROWN_1         (",,,,,,,,,17,,,,,,", Color.BROWN_1),
        GREY_1          (",,,,,,,,,18,,,,,,", Color.GREY_1),

        RED_2           (",,,,,,,,,19,,,,,,", Color.RED_2),
        ORANGE_2        (",,,,,,,,,20,,,,,,", Color.ORANGE_2),
        YELLOW_2        (",,,,,,,,,21,,,,,,", Color.YELLOW_2),
        GREEN_2         (",,,,,,,,,22,,,,,,", Color.GREEN_2),
        BLUE_2          (",,,,,,,,,23,,,,,,", Color.BLUE_2),
        PURPLE_2        (",,,,,,,,,24,,,,,,", Color.PURPLE_2),
        BROWN_2         (",,,,,,,,,25,,,,,,", Color.BROWN_2),
        GREY_2          (",,,,,,,,,26,,,,,,", Color.GREY_2),

        RED_3           (",,,,,,,,,27,,,,,,", Color.RED_3),
        ORANGE_3        (",,,,,,,,,28,,,,,,", Color.ORANGE_3),
        YELLOW_3        (",,,,,,,,,29,,,,,,", Color.YELLOW_3),
        GREEN_3         (",,,,,,,,,30,,,,,,", Color.GREEN_3),
        BLUE_3          (",,,,,,,,,31,,,,,,", Color.BLUE_3),
        PURPLE_3        (",,,,,,,,,32,,,,,,", Color.PURPLE_3),
        BROWN_3         (",,,,,,,,,33,,,,,,", Color.BROWN_3),
        GREY_3          (",,,,,,,,,34,,,,,,", Color.GREY_3),

        RED_4           (",,,,,,,,,35,,,,,,", Color.RED_4),
        ORANGE_4        (",,,,,,,,,36,,,,,,", Color.ORANGE_4),
        YELLOW_4        (",,,,,,,,,37,,,,,,", Color.YELLOW_4),
        GREEN_4         (",,,,,,,,,38,,,,,,", Color.GREEN_4),
        BLUE_4          (",,,,,,,,,39,,,,,,", Color.BLUE_4),
        PURPLE_4        (",,,,,,,,,40,,,,,,", Color.PURPLE_4),
        BROWN_4         (",,,,,,,,,41,,,,,,", Color.BROWN_4),
        SOMETHING_NEW   (",,,,,,,,,42,,,,,,", Color.NONE),

        ;
        final String format;
        Color expected;
        BackgroundColorTest (String format, Color expected) {
            this.expected = expected;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public Color getExpected() {
            return expected;
        }
        public Color getResult(Format result) {
            return result.getBackgroundColor();
        }
        public void applyToFormatBuilder(Color value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withBackgroundColor(value);
        }
    }

    enum TaskbarColorTest implements FormatTestCase<Color>  {
        NONE            (",,,,,,,,,,0,,,,,", Color.NONE),
        BLACK           (",,,,,,,,,,1,,,,,", Color.BLACK),
        WHITE           (",,,,,,,,,,2,,,,,", Color.WHITE),
        TRANSPARENT     (",,,,,,,,,,3,,,,,", Color.TRANSPARENT),

        RED_0           (",,,,,,,,,,4,,,,,", Color.RED_0),
        ORANGE_0        (",,,,,,,,,,5,,,,,", Color.ORANGE_0),
        YELLOW_0        (",,,,,,,,,,6,,,,,", Color.YELLOW_0),
        GREEN_0         (",,,,,,,,,,7,,,,,", Color.GREEN_0),
        BLUE_0          (",,,,,,,,,,8,,,,,", Color.BLUE_0),
        PURPLE_0        (",,,,,,,,,,9,,,,,", Color.PURPLE_0),
        BROWN_0         (",,,,,,,,,,10,,,,,", Color.BROWN_0),

        RED_1           (",,,,,,,,,,11,,,,,", Color.RED_1),
        ORANGE_1        (",,,,,,,,,,12,,,,,", Color.ORANGE_1),
        YELLOW_1        (",,,,,,,,,,13,,,,,", Color.YELLOW_1),
        GREEN_1         (",,,,,,,,,,14,,,,,", Color.GREEN_1),
        BLUE_1          (",,,,,,,,,,15,,,,,", Color.BLUE_1),
        PURPLE_1        (",,,,,,,,,,16,,,,,", Color.PURPLE_1),
        BROWN_1         (",,,,,,,,,,17,,,,,", Color.BROWN_1),
        GREY_1          (",,,,,,,,,,18,,,,,", Color.GREY_1),

        RED_2           (",,,,,,,,,,19,,,,,", Color.RED_2),
        ORANGE_2        (",,,,,,,,,,20,,,,,", Color.ORANGE_2),
        YELLOW_2        (",,,,,,,,,,21,,,,,", Color.YELLOW_2),
        GREEN_2         (",,,,,,,,,,22,,,,,", Color.GREEN_2),
        BLUE_2          (",,,,,,,,,,23,,,,,", Color.BLUE_2),
        PURPLE_2        (",,,,,,,,,,24,,,,,", Color.PURPLE_2),
        BROWN_2         (",,,,,,,,,,25,,,,,", Color.BROWN_2),
        GREY_2          (",,,,,,,,,,26,,,,,", Color.GREY_2),

        RED_3           (",,,,,,,,,,27,,,,,", Color.RED_3),
        ORANGE_3        (",,,,,,,,,,28,,,,,", Color.ORANGE_3),
        YELLOW_3        (",,,,,,,,,,29,,,,,", Color.YELLOW_3),
        GREEN_3         (",,,,,,,,,,30,,,,,", Color.GREEN_3),
        BLUE_3          (",,,,,,,,,,31,,,,,", Color.BLUE_3),
        PURPLE_3        (",,,,,,,,,,32,,,,,", Color.PURPLE_3),
        BROWN_3         (",,,,,,,,,,33,,,,,", Color.BROWN_3),
        GREY_3          (",,,,,,,,,,34,,,,,", Color.GREY_3),

        RED_4           (",,,,,,,,,,35,,,,,", Color.RED_4),
        ORANGE_4        (",,,,,,,,,,36,,,,,", Color.ORANGE_4),
        YELLOW_4        (",,,,,,,,,,37,,,,,", Color.YELLOW_4),
        GREEN_4         (",,,,,,,,,,38,,,,,", Color.GREEN_4),
        BLUE_4          (",,,,,,,,,,39,,,,,", Color.BLUE_4),
        PURPLE_4        (",,,,,,,,,,40,,,,,", Color.PURPLE_4),
        BROWN_4         (",,,,,,,,,,41,,,,,", Color.BROWN_4),
        SOMETHING_NEW   (",,,,,,,,,,42,,,,,", Color.NONE),

        ;
        final String format;
        Color expected;
        TaskbarColorTest (String format, Color expected) {
            this.expected = expected;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public Color getExpected() {
            return expected;
        }
        public Color getResult(Format result) {
            return result.getTaskbarColor();
        }
        public void applyToFormatBuilder(Color value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withTaskbarColor(value);
        }
    }
    enum CurrencyTest implements FormatTestCase<Currency>  {

        NONE                (",,,,,,,,,,,0,,,,", Currency.NONE),
        ARGENTINE_PESO      (",,,,,,,,,,,1,,,,", Currency.ARGENTINE_PESO),
        AUSTRALIAN_DOLLAR   (",,,,,,,,,,,2,,,,", Currency.AUSTRALIAN_DOLLAR),
        BRAZIL_REAIS        (",,,,,,,,,,,3,,,,", Currency.BRAZIL_REAIS),
        CANADIAN_DOLLAR     (",,,,,,,,,,,4,,,,", Currency.CANADIAN_DOLLAR),
        CHILEAN_PESOS       (",,,,,,,,,,,5,,,,", Currency.CHILEAN_PESOS),
        EURO                (",,,,,,,,,,,6,,,,", Currency.EURO),
        BRITISH_POUND       (",,,,,,,,,,,7,,,,", Currency.BRITISH_POUND),
        ISRAEL_SHEKEL       (",,,,,,,,,,,8,,,,", Currency.ISRAEL_SHEKEL),
        INDIA_RUPEES        (",,,,,,,,,,,9,,,,", Currency.INDIA_RUPEES),
        JAPAN_YEN           (",,,,,,,,,,,10,,,,", Currency.JAPAN_YEN),
        MEXICAN_PESOS       (",,,,,,,,,,,11,,,,", Currency.MEXICAN_PESOS),
        RUSSIAN_RUBLES      (",,,,,,,,,,,12,,,,", Currency.RUSSIAN_RUBLES),
        USD                 (",,,,,,,,,,,13,,,,", Currency.USD),
        SOUTHAFRICA_RAND    (",,,,,,,,,,,14,,,,", Currency.SOUTHAFRICA_RAND),
        SWISS_FRANC         (",,,,,,,,,,,15,,,,", Currency.SWISS_FRANC),
        CHINA_YUAN          (",,,,,,,,,,,16,,,,", Currency.CHINA_YUAN),
        DENMARK_KRONER      (",,,,,,,,,,,17,,,,", Currency.DENMARK_KRONER),
        HONKKONG_DOLLAR     (",,,,,,,,,,,18,,,,", Currency.HONKKONG_DOLLAR),
        SOUTHKOREAN_WON     (",,,,,,,,,,,19,,,,", Currency.SOUTHKOREAN_WON),
        NORWAY_KRONER       (",,,,,,,,,,,20,,,,", Currency.NORWAY_KRONER),
        NEWZEALAND_DOLLAR   (",,,,,,,,,,,21,,,,", Currency.NEWZEALAND_DOLLAR),
        SWEDEN_KRONOR       (",,,,,,,,,,,22,,,,", Currency.SWEDEN_KRONOR),
        SINGAPORE_DOLLAR    (",,,,,,,,,,,23,,,,", Currency.SINGAPORE_DOLLAR),
        SOMETHING_NEW       (",,,,,,,,,,,24,,,,", Currency.NONE),

        ;
        final String format;
        Currency expected;
        CurrencyTest (String format, Currency expected) {
            this.expected = expected;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public Currency getExpected() {
            return expected;
        }
        public Currency getResult(Format result) {
            return result.getCurrency();
        }
        public void applyToFormatBuilder(Currency value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withCurrency(value);
        }
    }

    enum DecimalCountTest implements FormatTestCase<DecimalCount>  {

        COUNT_0     (",,,,,,,,,,,,0,,,", DecimalCount.COUNT_0),
        COUNT_1     (",,,,,,,,,,,,0,,,", DecimalCount.COUNT_1),
        COUNT_2     (",,,,,,,,,,,,1,,,", DecimalCount.COUNT_2),
        COUNT_3     (",,,,,,,,,,,,2,,,", DecimalCount.COUNT_3),
        COUNT_4     (",,,,,,,,,,,,3,,,", DecimalCount.COUNT_4),
        COUNT_5     (",,,,,,,,,,,,4,,,", DecimalCount.COUNT_5),
        COUNT_6     (",,,,,,,,,,,,5,,,", DecimalCount.COUNT_0),

        ;
        final String format;
        DecimalCount expected;
        DecimalCountTest (String format, DecimalCount expected) {
            this.expected = expected;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public DecimalCount getExpected() {
            return expected;
        }
        public DecimalCount getResult(Format result) {
            return result.getDecimalCount();
        }
        public void applyToFormatBuilder(DecimalCount value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withDecimalCount(value);
        }
    }

    enum ThousandsSeparatorTest implements FormatTestCase<ThousandsSeparator>  {
        OFF     (",,,,,,,,,,,,,0,,", ThousandsSeparator.NONE),
        ON      (",,,,,,,,,,,,,1,,", ThousandsSeparator.ON),
        OTHER   (",,,,,,,,,,,,,2,,", ThousandsSeparator.NONE),

        ;
        final String format;
        ThousandsSeparator expected;
        private ThousandsSeparatorTest (String format, ThousandsSeparator expected) {
            this.expected = expected;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public ThousandsSeparator getExpected() {
            return expected;
        }
        public ThousandsSeparator getResult(Format result) {
            return result.getThousandsSeparator();
        }
        public void applyToFormatBuilder(ThousandsSeparator value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withThousandsSeparator(value);
        }
    }
    enum NumberFormatTest implements FormatTestCase<NumberFormat>  {

        NONE        (",,,,,,,,,,,,,,0,", NumberFormat.NONE),
        NUMBER      (",,,,,,,,,,,,,,1,", NumberFormat.NUMBER),
        CURRENCY    (",,,,,,,,,,,,,,2,", NumberFormat.CURRENCY),
        PERCENT     (",,,,,,,,,,,,,,3,", NumberFormat.PERCENT),
        OTHER       (",,,,,,,,,,,,,,4,", NumberFormat.NONE),

        ;
        final String format;
        NumberFormat expected;
        private NumberFormatTest (String format, NumberFormat expected) {
            this.expected = expected;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public NumberFormat getExpected() {
            return expected;
        }
        public NumberFormat getResult(Format result) {
            return result.getNumberFormat();
        }
        public void applyToFormatBuilder(NumberFormat value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withNumberFormat(value);
        }
    }
    enum TextWrapTest implements FormatTestCase<TextWrap>  {

        NONE        (",,,,,,,,,,,,,,,0", TextWrap.NONE),
        NUMBER      (",,,,,,,,,,,,,,,1", TextWrap.ON),
        OTHER       (",,,,,,,,,,,,,,,2", TextWrap.NONE),

        ;
        final String format;
        TextWrap expected;
        private TextWrapTest (String format, TextWrap expected) {
            this.expected = expected;
            this.format = format;
        }
        public String getFormat() {
            return format;
        }
        public TextWrap getExpected() {
            return expected;
        }
        public TextWrap getResult(Format result) {
            return result.getTextWrap();
        }
        public void applyToFormatBuilder(TextWrap value, Format.FormatBuilder formatBuilder) {
            formatBuilder.withTextWrap(value);
        }
    }
}
