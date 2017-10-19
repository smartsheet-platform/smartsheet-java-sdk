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


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.util.Arrays;



/**
 * This class represents the format as applied to a cell, row or column.
 * 
 * @author kskeem
 *
 */
@JsonSerialize(using=Format.FormatSerializer.class)
public class Format {

    //The default format.
    private static final int[] DEFAULT_FORMAT = new int[]{0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    static final int UNSET = Integer.MIN_VALUE;
    int[] formatArray;

    /**
     * Constructs a {@link Format} object using the format string provided by the Smartsheet API.
     *
     *  The format of the string is a comma separated list. See
     *  <a href="http://www.smartsheet.com/developers/api-documentation#h.mf6r2e3k3ftb">http://www.smartsheet.com/developers/api-documentation#h.mf6r2e3k3ftb</a>
     *  for details
     * @param original the original
     */
    public Format(String original) {
        formatArray = new int[DEFAULT_FORMAT.length];
        FormatTokenizer tokenizer = new FormatTokenizer(original);
        for (int i = 0; tokenizer.next() && i < DEFAULT_FORMAT.length; i++) {
            if (tokenizer.isNextUnset()) {
                formatArray[i] = UNSET;
            } else {
                formatArray[i] = tokenizer.nextInt();
            }
        }
    }

    /**
     * Creates a {@link Format} object with default values.
     */
    public Format() {
        formatArray = Arrays.copyOf(DEFAULT_FORMAT, DEFAULT_FORMAT.length);
    }

    protected <T extends Enum<?>> T getFormatValue(FormatAttribute attribute, T[] values) {
        if (formatArray[attribute.ordinal()] >= values.length) {
            return values[DEFAULT_FORMAT[attribute.ordinal()]];
        } else if (formatArray[attribute.ordinal()] == UNSET) {
             return null;
        } else {
            return values[formatArray[attribute.ordinal()]];
        }
    }

    /**
     * @return the {@link FontFamily}.
     */
    public FontFamily getFontFamily () {
        return getFormatValue(FormatAttribute.FONT_FAMILY, FontFamily.values());
    }

    /**
     * @return the {@link FontSize}
     */
    public FontSize getFontSize () {
        return getFormatValue(FormatAttribute.FONT_SIZE, FontSize.values());
    }

    /**
     * @return the {@link Bold} format
     */
    public Bold getBold() {
        return getFormatValue(FormatAttribute.BOLD, Bold.values());
    }

    /**
     * @return the {@link Italic} format
     */
    public Italic getItalic() {
        return getFormatValue(FormatAttribute.ITALIC, Italic.values());
    }

    /**
     * @return the {@link Underline} status
     */
    public Underline getUnderline () {
        return getFormatValue(FormatAttribute.UNDERLINE, Underline.values());
    }

    /**
     * @return the {@link Strikethrough} status
     */
    public Strikethrough getStrikethrough () {
        return getFormatValue(FormatAttribute.STRIKETHROUGH, Strikethrough.values());
    }

    /**
     * @return the {@link HorizontalAlignment}
     */
    public HorizontalAlignment getHorizontalAlignment() {
        return getFormatValue(FormatAttribute.H_ALIGN, HorizontalAlignment.values());
    }

    /**
     * @return the {@link VerticalAlignment}
     */
    public VerticalAlignment getVerticalAlignment () {
        return getFormatValue(FormatAttribute.V_ALIGN, VerticalAlignment.values());
    }

    /**
     * @return the {@link Color} of the text.
     */
    public Color getTextColor() {
        return getFormatValue(FormatAttribute.TEXT_COLOR, Color.values());
    }

    /**
     * @return the {@link Color} of the background
     */
    public Color getBackgroundColor () {
        return getFormatValue(FormatAttribute.BACKGROUND_COLOR, Color.values());
    }

    /**
     * @return the {@link Color} of the task bar (gantt view)
     */
    public Color getTaskbarColor () {
        return getFormatValue(FormatAttribute.TASKBAR_COLOR, Color.values());
    }

    /**
     * @return the {@link Currency} format
     */
    public Currency getCurrency () {
        return getFormatValue(FormatAttribute.CURRENCY, Currency.values());
    }

    /**
     * @return the {@link DecimalCount}
     */
    public DecimalCount getDecimalCount () {
        return getFormatValue(FormatAttribute.DECIMAL_COUNT, DecimalCount.values());
    }

    /**
     * @return the {@link ThousandsSeparator}
     */
    public ThousandsSeparator getThousandsSeparator() {
        return getFormatValue(FormatAttribute.THOUSANDS_SEPARATOR, ThousandsSeparator.values());
    }

    /**
     * @return the {@link NumberFormat}
     */
    public NumberFormat getNumberFormat() {
        return getFormatValue(FormatAttribute.NUMBER_FORMAT, NumberFormat.values());
    }

    /**
     * @return the {@link TextWrap} status
     */
    public TextWrap getTextWrap () {
        return getFormatValue(FormatAttribute.TEXT_WRAP, TextWrap.values());
    }

    /**
     * @author kskeem
     * An enum whose "ordinal" property is used to identify the index into the format array.
     * Note that this means you !MUST NOT! change the order of these - even if you can't stand that they are not alphabetic
     */
    private enum FormatAttribute {
        FONT_FAMILY,
        FONT_SIZE,
        BOLD,
        ITALIC,
        UNDERLINE,
        STRIKETHROUGH,
        H_ALIGN,
        V_ALIGN,
        TEXT_COLOR,
        BACKGROUND_COLOR,
        TASKBAR_COLOR,
        CURRENCY,
        DECIMAL_COUNT,
        THOUSANDS_SEPARATOR,
        NUMBER_FORMAT,
        TEXT_WRAP,
        ;

    }


    /**
     * A utility class to help us parse the format string. Format strings are a comma separated list of integers.
     * Each position in the comma separated list maps to a specific format attribute. An attribute may not be set,
     * and in these situations, the default must be used.
     *
     * Valid strings (though actual format strings currently have 16 positions - these are for illustrative purposes only):
     * "0,1,0,2,0,1,"
     * ",,,,,,,,,,,"
     * ",,3,,,4,,1,,0"
     * "
     * This assumes positive integers only.
     *
     * @author kskeem
     *
     */
    class FormatTokenizer {
        char[] chars;
        int pos;
        final static char SEPARATOR = ',';

        /**
         * Construct the {@link FormatTokenizer}.
         * @param str
         */
        public FormatTokenizer (String str) {
            chars = str.toCharArray();
            pos = -1;
        }

        /**
         * Call this to check to see if there are any more ints available for consumption. This will avoid index out of bounds issues.
         * @return
         */
        public boolean next() {
            pos++;
            return pos < chars.length || (pos == chars.length && chars[pos -1] == SEPARATOR);
        }

        /**
         * Since we want to know the difference between set to "0" and unset, this  tells us if the next position is set or not. Accounts for
         * being at the end of the char array, where there is potentially one more position that needs to be accounted for.
         * @return whether the next position is set or not.
         */
        public boolean isNextUnset() {
            if (pos >= chars.length) {
                return true;
            } else {
                return chars[pos] == SEPARATOR;
            }
        }

        /**
         * Retrieves the next int. If an int is unset, 0 is returned. You cannot distinguish between unset and 0 using this method. see isNextUnset() instead.
         * @return the next integer value.
         */
        public int nextInt() {
            int value = 0;
            char currentChar;
            //Advance through the characters until you hit the separator
            while (pos < chars.length && (currentChar = chars[pos ++]) != SEPARATOR) {
                //Multiply the value by 10 to enable parsing multi-digit ints.
                //Use char math (subtracting '0' from the integer value) to cheaply convert the characters to ints.
                value = value * 10 + (currentChar - '0');
            }
            pos--;
            return value;
        }
    }


    /**
     * Builder class for a Format object
     */
    public static class FormatBuilder {
        int[] formatArray = new int[] {
            UNSET, UNSET, UNSET, UNSET,
            UNSET, UNSET, UNSET, UNSET,
            UNSET, UNSET, UNSET, UNSET,
            UNSET, UNSET, UNSET, UNSET
        };

        public Format build() {
            String delimiter = "";
            StringBuilder formatStringBuilder = new StringBuilder(30);
            for (Integer value : formatArray) {
                formatStringBuilder.append(delimiter);
                delimiter = ",";

                if (value != UNSET) {
                    formatStringBuilder.append(value);
                }
            }

            return new Format(formatStringBuilder.toString());
        }

        /**
         * Sets all properties based upon the supplied format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withFormat(Format value) {
            System.arraycopy(value.formatArray, 0, formatArray, 0, formatArray.length);
            return this;
        }

        /**
         * Sets the font family of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withFontFamily(FontFamily value) {
            formatArray[0] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the font size of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withFontSize(FontSize value) {
            formatArray[1] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the bold property of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withBold(Bold value) {
            formatArray[2] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the italics property of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withItalic(Italic value) {
            formatArray[3] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the underline property of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withUnderline(Underline value) {
            formatArray[4] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the strike through property of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withStrikethrough(Strikethrough value) {
            formatArray[5] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the horizontal alignment property of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withHorizontalAlignment(HorizontalAlignment value) {
            formatArray[6] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the vertical alignment property of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withVerticalAlignment(VerticalAlignment value) {
            formatArray[7] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the text color property of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withTextColor(Color value) {
            formatArray[8] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the background color property of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withBackgroundColor(Color value) {
            formatArray[9] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the taskbar color property of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withTaskbarColor(Color value) {
            formatArray[10] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the currency of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withCurrency(Currency value) {
            formatArray[11] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the decimal count of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withDecimalCount(DecimalCount value) {
            formatArray[12] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the thousands separator property of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withThousandsSeparator(ThousandsSeparator value) {
            formatArray[13] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the number format property of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withNumberFormat(NumberFormat value) {
            formatArray[14] = getOrdinal(value);
            return this;
        }

        /**
         * Sets the text wrap property of the format
         *
         * @param value the value
         * @return the format builder
         */
        public FormatBuilder withTextWrap(TextWrap value) {
            formatArray[15] = getOrdinal(value);
            return this;
        }

        private int getOrdinal(Enum<?> enumValue) {
            return (enumValue != null) ? enumValue.ordinal() : UNSET;
        }
    }

    public static class FormatSerializer extends JsonSerializer<Format> {
        @Override
        public void serialize(Format format, JsonGenerator generator, SerializerProvider provider) throws IOException {
            StringBuilder stringBuilder = new StringBuilder(30);
            String separator = "";
            for (int formatValue : format.formatArray) {
                stringBuilder.append(separator);
                separator = ",";

                if (formatValue != UNSET) {
                    stringBuilder.append(formatValue);
                }
            }

            generator.writeString(stringBuilder.toString());
        }
    }
}
