package com.smartsheet.api.models.enums;

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

public enum WidgetType {

    /**
     * ChartWidgetContent object
     */
    CHART,
    /**
     * ImageWidgetContent object
     */
    IMAGE,
    /**
     * CellLinkWidgetContent object
     */
    METRIC,
    /**
     * ReportWidgetContent object
     */
    GRIDGANTT,
    /**
     * RichTextWidgetContent object
     */
    RICHTEXT,
    /**
     * ShortcutWidgetContent object
     */
    SHORTCUT,
     /**
     * same as RichTextWidgetContent object
     */
    TITLE,
    /**
     * WebContentWidgetContent object
     */
    WEBCONTENT,
    /**
     * NOTE: These are level=0 widget types that should be supported for now
     */
    /**
     * SHORTCUTLIST, SHORTCUTICON (both now SHORTCUT) decode to ShortcutWidgetContent object
     */
    SHORTCUTLIST,
    SHORTCUTICON,
    /**
     * SHEETSUMMARY (is now METRIC) decodes to CellLinkWidgetContent object
     */
    SHEETSUMMARY
}
