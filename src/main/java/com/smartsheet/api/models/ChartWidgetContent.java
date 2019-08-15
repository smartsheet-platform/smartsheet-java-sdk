package com.smartsheet.api.models;
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

import com.smartsheet.api.models.enums.WidgetType;

import java.util.List;

public class ChartWidgetContent implements WidgetContent {

    /**
     *  Report Id denoting container source, if applicable
     */
    private Long reportId;

    /**
     * Sheet Id denoting container source, if applicable
     */
    private Long sheetId;

    /**
     * Array of Axes
     */
    private List<Object> axes;

    /**
     * The widget has when clicked attribute set to that hyperlink (if present and non-null)
     */
    private WidgetHyperlink hyperlink;

    /**
     * Array of columnIds if the range was selected through the UI
     */
    private List<Long> includedColumnIds;

    /**
     * The location in the widget where Smartsheet renders the legend, for example, RIGHT
     */
    private Object legend;

    /**
     * selection range if the source is a sheet
     */
    private List<SelectionRange> selectionRanges;

    /**
     * Array of Series objects
     */
    private List<Object> series;

    /**
     * Returns the type for this widget content object
     *
     * @return CHART
     */
    @Override
    public WidgetType getWidgetType() {
        return WidgetType.CHART;
    }

    /**
     * Gets the report ID denoting container source, if applicable
     *
     * @return the report ID
     */
    public Long getReportId() { return reportId; }

    /**
     * Sets the report ID denoting container source, if applicable
     *
     * @param reportId
     */
    public ChartWidgetContent setReportId(Long reportId) {
        this.reportId = reportId;
        return this;
    }

    /**
     * Gets the sheet ID denoting container source, if applicable
     *
     * @return the sheet ID
     */
    public Long getSheetId() { return sheetId; }

    /**
     * Sets the sheet ID denoting container source, if applicable
     *
     * @param sheetId
     */
    public ChartWidgetContent setSheetId(Long sheetId) {
        this.sheetId = sheetId;
        return this;
    }

    /**
     * Gets the array of axes
     *
     * @return the axes
     */
    public List<Object> getAxes() { return axes; }

    /**
     * Sets the array of axes
     *
     * @param axes
     */
    public ChartWidgetContent setAxes(List<Object> axes) {
        this.axes = axes;
        return this;
    }

    /**
     * Gets the hyperlink associated with this Widget
     *
     * @return the hyperlink
     */
    public WidgetHyperlink getHyperlink() { return hyperlink; }

    /**
     * Sets the hyperlink associated with this Widget
     *
     * @param hyperlink
     */
    public ChartWidgetContent setHyperlink(WidgetHyperlink hyperlink) {
        this.hyperlink = hyperlink;
        return this;
    }

    /**
     * Gets the array of column IDs if the range was selected through the UI
     *
     * @return the array of column IDs
     */
    public List<Long> getIncludedColumnIds() { return includedColumnIds; }

    /**
     * Sets the array of column IDs if the range was selected through the UI
     *
     * @param includedColumnIds
     */
    public ChartWidgetContent setIncludedColumnIds(List<Long> includedColumnIds) {
        this.includedColumnIds = includedColumnIds;
        return this;
    }

    /**
     * Gets the location in the widget where Smartsheet renders the legend
     *
     * @return the legend
     */
    public Object getLegend() { return legend; }

    /**
     * Sets the location in the widget where Smartsheet renders the legend
     *
     * @param legend
     */
    public ChartWidgetContent setLegend(Object legend) {
        this.legend = legend;
        return this;
    }

    /**
     * Gets the selection range if the source is a sheet
     *
     * @return the selection range
     */
    public List<SelectionRange> getSelectionRanges() { return selectionRanges; }

    /**
     * Sets the selection range if the source is a sheet
     *
     * @param selectionRanges
     */
    public ChartWidgetContent setSelectionRanges(List<SelectionRange> selectionRanges) {
        this.selectionRanges = selectionRanges;
        return this;
    }

    /**
     * Gets the array os series objects
     *
     * @return the array of series objects
     */
    public List<Object> getSeries() { return series; }

    /**
     * Sets the array of series objects
     *
     * @param series
     */
    public ChartWidgetContent setSeries(List<Object> series) {
        this.series = series;
        return this;
    }
}
