package com.smartsheet.api.sdk_test;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2018 Smartsheet
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

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class SerializationTest {
	@Test
	public void SerializeAttachment() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Attachment");
			Attachment attachment = new Attachment();
			attachment.setName("Search Engine");
			attachment.setDescription("A popular search engine");
			attachment.setAttachmentType(AttachmentType.LINK);
			attachment.setUrl("http://www.google.com");

			// act
			Attachment result = ss.sheetResources().attachmentResources().attachUrl(1, attachment);

			// assert
			Assert.assertEquals(AttachmentType.LINK, result.getAttachmentType());
			Assert.assertEquals(AttachmentParentType.SHEET, result.getParentType());
			Assert.assertEquals("Search Engine", result.getName());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeHome() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Home");

			// act
			Home result = ss.homeResources().getHome(null);

			// assert
			Assert.assertEquals(3, result.getSheets().size());
			Assert.assertEquals(AccessLevel.EDITOR_SHARE, result.getSheets().get(0).getAccessLevel());
			Assert.assertEquals(AccessLevel.OWNER, result.getReports().get(2).getAccessLevel());
			Assert.assertEquals("workspace folder folder sheet", result.getWorkspaces().get(0).getFolders().get(0)
					.getFolders().get(0).getSheets().get(0).getName());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeGroup() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Groups");

			GroupMember johnDoe = new GroupMember();
			johnDoe.setEmail("john.doe@smartsheet.com");
			GroupMember janeDoe = new GroupMember();
			janeDoe.setEmail("jane.doe@smartsheet.com");

			List<GroupMember> members = new ArrayList<GroupMember>();
			members.add(johnDoe);
			members.add(janeDoe);

			Group group = new Group();
			group.setName("mock api test group");
			group.setDescription("it's a group");
			group.setMembers(members);

			// act
			Group result = ss.groupResources().createGroup(group);

			// assert
			Assert.assertEquals(2, result.getMembers().size());
			Assert.assertEquals("John Doe", result.getMembers().get(0).getName());
			Assert.assertEquals("jane.doe@smartsheet.com", result.getMembers().get(1).getEmail());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeDiscussion() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Discussion");

			Comment comment = new Comment();
			comment.setText("This is a comment!");

			Discussion discussion = new Discussion();
			discussion.setComment(comment);

			// act
			Discussion result = ss.sheetResources().rowResources().discussionResources().createDiscussion(1, 2, discussion);

			// assert
			Assert.assertEquals(1, (long)result.getCommentCount());
			Assert.assertEquals("This is a comment!", result.getComments().get(0).getText());
			Assert.assertEquals("John Doe", result.getComments().get(0).getCreatedBy().getName());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeContact() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Contact");

			// act
			Contact result = ss.contactResources().getContact("ABC");

			// assert
			Assert.assertEquals("ABC", result.getId());
			Assert.assertEquals("John Doe", result.getName());
			Assert.assertEquals("john.doe@smartsheet.com", result.getEmail());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeFolder() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Folder");

			Folder folder = new Folder();
			folder.setName("folder");

			// act
			Folder result = ss.homeResources().folderResources().createFolder(folder);

			// assert
			Assert.assertEquals("folder", result.getName());
			Assert.assertEquals(1, (long)result.getId());
			Assert.assertEquals("https://app.smartsheet.com/b/home?lx=a", result.getPermalink());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeColumn() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Column");

			Column column = new Column();
			column.setTitle("A Brave New Column");
			column.setType(ColumnType.PICKLIST);
			column.setOptions(Arrays.asList("option1", "option2", "option3"));
			column.setIndex(2);
			column.setValidation(false);
			column.setWidth(42);
			column.setLocked(false);

			// act
			List<Column> result = ss.sheetResources().columnResources().addColumns(1, Arrays.asList(column));

			// assert
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeUserProfile() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - UserProfile");

			// act
			UserProfile result = ss.userResources().getCurrentUser();

			// assert
			Assert.assertEquals("en_US", result.getLocale());
			Assert.assertEquals("john.doe@smartsheet.com", result.getAccount().getName());
			Assert.assertEquals(66, (long)result.getSheetCount());
			Assert.assertEquals(0, result.getAlternateEmails().size());
			Assert.assertEquals(true, result.getLicensedSheetCreator());
			Assert.assertEquals(true, result.getAdmin());
			Assert.assertEquals("US/Pacific", result.getTimeZone());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeWorkspace() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Workspace");

			Workspace workspace = new Workspace();
			workspace.setName("A Whole New Workspace");

			// act
			Workspace result = ss.workspaceResources().createWorkspace(workspace);

			// assert
			Assert.assertEquals(AccessLevel.OWNER, result.getAccessLevel());
			Assert.assertEquals("https://app.smartsheet.com/b/home?lx=a", result.getPermalink());
			Assert.assertEquals("A Whole New Workspace", result.getName());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeUser() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - User");

			User user = new User();
			user.setEmail("john.doe@smartsheet.com");
			user.setAdmin(false);
			user.setLicensedSheetCreator(true);
			user.setFirstName("John");
			user.setLastName("Doe");
			user.setGroupAdmin(false);
			user.setResourceViewer(true);

			// act
			User result = ss.userResources().addUser(user);

			// assert
			Assert.assertEquals("John Doe", result.getName());
			Assert.assertEquals("abc", result.getProfileImage().getImageId());
			Assert.assertEquals(UserStatus.ACTIVE, result.getStatus());
			Assert.assertEquals("john.doe@smartsheet.com", result.getEmail());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeSheet() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Sheet");

			Column column1 = new Column();
			column1.setTitle("The First Column");
			column1.setPrimary(true);
			column1.setType(ColumnType.TEXT_NUMBER);

			AutoNumberFormat autoNumberFormat = new AutoNumberFormat();
			autoNumberFormat.setPrefix("{YYYY}-{MM}-{DD}-");
			autoNumberFormat.setSuffix("-SUFFIX");
			autoNumberFormat.setFill("000000");

			Column column2 = new Column();
			column2.setTitle("The Second Column");
			column2.setPrimary(false);
			column2.setType(ColumnType.TEXT_NUMBER);
			column2.setSystemColumnType(SystemColumnType.AUTO_NUMBER);
			column2.setAutoNumberFormat(autoNumberFormat);

			List<Column> columns = new ArrayList<Column>();
			columns.add(column1);
			columns.add(column2);

			Sheet sheet = new Sheet();
			sheet.setName("The First Sheet");
			sheet.setColumns(columns);

			// act
			Sheet result = ss.sheetResources().createSheet(sheet);

			// assert
			Assert.assertEquals("The First Sheet", result.getName());
			Assert.assertEquals(false, result.getColumnById(2).getValidation());
			Assert.assertEquals("-SUFFIX", result.getColumnById(3).getAutoNumberFormat().getSuffix());
			Assert.assertEquals(SystemColumnType.AUTO_NUMBER, result.getColumnById(3).getSystemColumnType());
			Assert.assertEquals(150, (long)result.getColumnById(2).getWidth());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeAlternateEmail() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - AlternateEmail");

			AlternateEmail alternateEmail = new AlternateEmail();
			alternateEmail.setEmail("not.not.john.doe@smartsheet.com");

			// act
			List<AlternateEmail> result = ss.userResources().addAlternateEmail(1, Arrays.asList(alternateEmail));

			// assert
			Assert.assertEquals("not.not.john.doe@smartsheet.com", result.get(0).getEmail());
			Assert.assertEquals(false, result.get(0).getConfirmed());
			Assert.assertEquals(1, (long)result.get(0).getId());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializePredecessor() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Predecessor");

			Duration lag = new Duration();
			lag.setNegative(false);
			lag.setElapsed(false);
			lag.setWeeks(1.5);
			lag.setDays(2.5);
			lag.setHours(3.5);
			lag.setMinutes(4.5);
			lag.setSeconds(5.5);
			lag.setMilliseconds(6d);

			Predecessor predecessor = new Predecessor();
			predecessor.setRowId(3l);
			predecessor.setType("FS");
			predecessor.setLag(lag);

			PredecessorList predecessorList = new PredecessorList();
			predecessorList.setPredecessors(Arrays.asList(predecessor));

			Cell cell = new Cell();
			cell.setColumnId(2l);
			cell.setObjectValue(predecessorList);

			Row row = new Row();
			row.setCells(Arrays.asList(cell));

			// act
			List<Row> result = ss.sheetResources().rowResources().addRows(1l, Arrays.asList(row));

			// assert
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeIndexResult() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - IndexResult");

			// act
			Contact result = ss.contactResources().getContact("ABC");

			// assert
			Assert.assertEquals("ABC", result.getId());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeImage() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Image");

			// act
			Contact result = ss.contactResources().getContact("ABC");

			// assert
			Assert.assertEquals("ABC", result.getId());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeImageUrls() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Image Urls");

			// act
			Contact result = ss.contactResources().getContact("ABC");

			// assert
			Assert.assertEquals("ABC", result.getId());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeBulkFailure() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Bulk Failure");

			// act
			Contact result = ss.contactResources().getContact("ABC");

			// assert
			Assert.assertEquals("ABC", result.getId());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeRows() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Rows");

			// act
			Contact result = ss.contactResources().getContact("ABC");

			// assert
			Assert.assertEquals("ABC", result.getId());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}
}
