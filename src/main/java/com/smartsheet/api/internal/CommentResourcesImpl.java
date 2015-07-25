package com.smartsheet.api.internal;

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

import com.smartsheet.api.AssociatedAttachmentResources;
import com.smartsheet.api.CommentResources;
import com.smartsheet.api.ResourceNotFoundException;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.Comment;

/**
 * This is the implementation of the CommentResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class CommentResourcesImpl extends AbstractResources implements CommentResources {
	/**
	 * Constructor.
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 *
	 * @param smartsheet the smartsheet
	 */
	public CommentResourcesImpl(SmartsheetImpl smartsheet) {
		super(smartsheet);
	}

	/**
	 * Return the AssociatedAttachmentResources object that provides access to attachment resources associated with
	 * Comment resources.
	 *
	 * @return the associated attachment resources
	 */
	public AssociatedAttachmentResources attachments() {
		throw new UnsupportedOperationException("Method moved to SheetCommentResources.");
	}

	public Comment getComment(long sheetId, long commentId) {
		throw new UnsupportedOperationException("Method moved to SheetCommentResources.");
	}

	public void deleteComment(long sheetId, long commentId) {
		throw new UnsupportedOperationException("Method moved to SheetCommentResources.");
	}
}
