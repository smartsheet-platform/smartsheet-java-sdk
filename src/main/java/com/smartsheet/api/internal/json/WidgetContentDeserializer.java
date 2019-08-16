package com.smartsheet.api.internal.json;
/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2019 Smartsheet
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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.WidgetType;
import com.smartsheet.api.models.format.Format;

import java.io.IOException;
import java.util.List;

public class WidgetContentDeserializer extends JsonDeserializer<WidgetContent> {

    @Override
    public WidgetContent deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        WidgetContent widgetContent = null;

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

        // Add the relevant custom deserializers
        SimpleModule module = new SimpleModule("FormatDeserializerModule", Version.unknownVersion());
        module.addDeserializer(Format.class, new FormatDeserializer());

        module = new SimpleModule("ObjectValueDeserializerModule", Version.unknownVersion());
        module.addDeserializer(ObjectValue.class, new ObjectValueDeserializer());
        mapper.registerModule(module);

        WidgetContentSuperset superset = mapper.readValue(jp, WidgetContentSuperset.class);

        WidgetType parsedType;
        try {
            parsedType = WidgetType.valueOf(superset.type);
        } catch (IllegalArgumentException e) {
            if(superset.type.equals("WidgetWebContent")) {
                parsedType = WidgetType.WEBCONTENT;
            }
            else {
                // If a new object type is introduced to the Smartsheet API that this version of the SDK
                // doesn't support, return null instead of throwing an exception.
                return null;
            }
        }

        switch (parsedType) {
            case CHART:
                ChartWidgetContent chartWidgetContent = new ChartWidgetContent();
                chartWidgetContent.setReportId(superset.reportId);
                chartWidgetContent.setSheetId(superset.sheetId);
                chartWidgetContent.setAxes(superset.axes);
                chartWidgetContent.setHyperlink(superset.hyperlink);
                chartWidgetContent.setIncludedColumnIds(superset.includedColumnIds);
                chartWidgetContent.setLegend(superset.legend);
                chartWidgetContent.setSelectionRanges(superset.selectionRanges);
                chartWidgetContent.setSeries(superset.series);
                widgetContent = chartWidgetContent;
                break;

            case IMAGE:
                ImageWidgetContent imageWidgetContent = new ImageWidgetContent();
                imageWidgetContent.setPrivateId(superset.privateId);
                imageWidgetContent.setFileName(superset.fileName);
                imageWidgetContent.setFormat(superset.format);
                imageWidgetContent.setHeight(superset.height);
                imageWidgetContent.setHyperlink(superset.hyperlink);
                imageWidgetContent.setWidth(superset.width);
                widgetContent = imageWidgetContent;
                break;

            case METRIC:
                CellLinkWidgetContent cellLinkWidgetContent = new CellLinkWidgetContent();
                cellLinkWidgetContent.setSheetId(superset.sheetId);
                cellLinkWidgetContent.setCellData(superset.cellData);
                cellLinkWidgetContent.setColumns(superset.columns);
                cellLinkWidgetContent.setHyperlink(superset.hyperlink);
                widgetContent = cellLinkWidgetContent;
                break;

            case GRIDGANTT:
                ReportWidgetContent reportWidgetContent = new ReportWidgetContent();
                reportWidgetContent.setReportId(superset.reportId);
                reportWidgetContent.setHtmlContent(superset.htmlContent);
                reportWidgetContent.setHyperlink(superset.hyperlink);
                widgetContent = reportWidgetContent;
                break;

            case RICHTEXT:            // Intentional fallthrough
            case TITLE:
                TitleRichTextWidgetContent titleRichTextWidgetContent = new TitleRichTextWidgetContent();
                titleRichTextWidgetContent.setBackgroundColor(superset.backgroundColor);
                titleRichTextWidgetContent.setHtmlContent(superset.htmlContent);
                widgetContent = titleRichTextWidgetContent;
                break;

            case SHORTCUT:
                ShortcutWidgetContent shortcutWidgetContent = new ShortcutWidgetContent();
                shortcutWidgetContent.setShortcutData(superset.shortcutData);
                widgetContent = shortcutWidgetContent;
                break;

            case WEBCONTENT:
                WebContentWidgetContent webContentWidgetContent = new WebContentWidgetContent();
                webContentWidgetContent.setUrl(superset.url);
                widgetContent = webContentWidgetContent;
                break;

            default:
                break;
        }
        return widgetContent;
    }

    private static class WidgetContentSuperset {

        // Common
        public String type;
        public Long sheetId;
        public Long reportId;
        public WidgetHyperlink hyperlink;
        public String htmlContent;

        // CellLinkWidgetContent
        public List<CellDataItem> cellData;
        public List<Column> columns;

        // ChartWidgetContent
        public List<Object> axes;
        public List<Long> includedColumnIds;
        public Object legend;
        public List<SelectionRange> selectionRanges;
        public List<Object> series;

        // ImageWidgetContent
        public String privateId;
        public String fileName;
        public Format format;
        public Integer height;
        public Integer width;

        // ReportWidgetContent

        // RichTextWidgetContent

        // ShortcutWidgetContent
        public List<ShortcutDataItem> shortcutData;

        // TitleWidgetContent
        public String backgroundColor;

        // WebContentWidgetContent
        public String url;
    }
}

