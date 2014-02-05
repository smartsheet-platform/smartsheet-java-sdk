package com.smartsheet.api.internal;

import java.io.File;
import java.util.List;

import com.smartsheet.api.AssociatedAttachmentResources;
import com.smartsheet.api.models.Attachment;

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
 * This is the implementation of the AssociatedAttachmentResources for comments.
 * 
 * It extends AssociatedAttachmentResourcesImpl and overrides listAttachments method by throwing
 * UnsupportedOperationException (since it's not supported for comments).
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class CommentAttachmentResources extends AssociatedAttachmentResourcesImpl implements AssociatedAttachmentResources  {
	/**
	 * Constructor.
	 * 
	 * Parameters: - smartsheet : the SmartsheetImpl
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null or empty string
	 * 
	 * Implementation: super(smartsheet, "comment");
	 * 
	 * @param smartsheet
	 */
	public CommentAttachmentResources(SmartsheetImpl smartsheet) {
		super(smartsheet, "comment");
	}

	@Override
	public List<Attachment> listAttachments(long objectId) {
		throw new UnsupportedOperationException();
	}
}
