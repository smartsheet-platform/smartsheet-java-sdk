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
import com.smartsheet.api.models.format.Format;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;

import java.util.*;

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
			// the SDK does not have the ability to set a predecessor
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
			PagedResult<User> result = ss.userResources().listUsers();

			// assert
			Assert.assertEquals(1, (long)result.getPageNumber());
			Assert.assertEquals(100, (long)result.getPageSize());
			Assert.assertEquals(1, (long)result.getTotalPages());
			Assert.assertEquals(1, (long)result.getTotalCount());
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
			Row result = ss.sheetResources().rowResources().getRow(1, 2, null, null);

			// assert
			Assert.assertEquals("puppy.jpg", result.getCells().get(0).getValue());
			Assert.assertEquals(300, (long)result.getCells().get(0).getImage().getHeight());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeImageUrls() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Image Urls");
			ImageUrl imageUrl = new ImageUrl();
			imageUrl.setImageId("abc");
			imageUrl.setHeight(100L);
			imageUrl.setWidth(200L);
			List<ImageUrl> imageUrls = Arrays.asList(imageUrl);

			// act
			ImageUrlMap result = ss.imageUrlResources().getImageUrls(imageUrls);

			// assert
			Assert.assertEquals("abc", result.getImageUrls().get(0).getImageId());
			Assert.assertEquals(1800000, (long)result.getUrlExpiresInMillis());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeBulkFailure() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - BulkFailure");

			Cell cell1 = new Cell();
			cell1.setColumnId(2L);
			cell1.setValue("Some Value");

			Row row1 = new Row();
			row1.setToBottom(true);
			row1.setCells(Arrays.asList(cell1));

			Cell cell2 = new Cell();
			cell2.setColumnId(3L);
			cell2.setValue("Some Value");

			Row row2 = new Row();
			row2.setToBottom(true);
			row2.setCells(Arrays.asList(cell2));
			List<Row> rows = Arrays.asList(row1, row2);

			// act
			PartialRowUpdateResult result = ss.sheetResources().rowResources().addRowsAllowPartialSuccess(1, rows);

			// assert
			Assert.assertEquals("PARTIAL_SUCCESS", result.getMessage());
			Assert.assertEquals(13, (long)result.getResult().get(0).getRowNumber());
			Assert.assertEquals(1036, (long)result.getFailedItems().get(0).getError().getErrorCode());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeRows() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Rows");

			Format format = new Format(",,,,,,,,4,,,,,,,");
			Cell cell1 = new Cell();
			cell1.setColumnId(2L);
			cell1.setValue("url link");
			cell1.setStrict(false);

			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setUrl("https://google.com");
			cell1.setHyperlink(hyperlink1);

			Cell cell2 = new Cell();
			cell2.setColumnId(3L);
			cell2.setValue("sheet id link");
			cell2.setStrict(false);

			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setSheetId(4L);
			cell2.setHyperlink(hyperlink2);

			Cell cell3 = new Cell();
			cell3.setColumnId(5L);
			cell3.setValue("report id link");
			cell3.setStrict(false);

			Hyperlink hyperlink3 = new Hyperlink();
			hyperlink3.setReportId(6L);
			cell3.setHyperlink(hyperlink3);
			List<Cell> cells = Arrays.asList(cell1, cell2, cell3);

			Row row = new Row();
			row.setExpanded(true);
			row.setFormat(format);
			row.setCells(cells);
			row.setLocked(false);

			// act
			// SDK does not support adding a single row so this will fail
			List<Row> result = ss.sheetResources().rowResources().addRows(1, Arrays.asList(row));

			// assert
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeCellLink() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Cell Link");

			CellLink cellLink = new CellLink();
			cellLink.setSheetId(4L);
			cellLink.setRowId(5L);
			cellLink.setColumnId(6L);

			Cell cell = new Cell();
			cell.setColumnId(3L);
			cell.setValue(null);
			cell.setLinkInFromCell(cellLink);

			Row row = new Row();
			row.setId(2L);
			row.setCells(Arrays.asList(cell));

			// act
			// SDK cannot update a single row so will not work
			List<Row> result = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(row));

			// assert
			Assert.assertEquals("PARTIAL_SUCCESS", result.get(0));
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeFavorite() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Favorite");
			Favorite favorite = new Favorite();
			favorite.setType(FavoriteType.SHEET);
			favorite.setObjectId(1L);

			// act
			// SDK cannot add a single favorite so cannot match mock api
			List<Favorite> result = ss.favoriteResources().addFavorites(Arrays.asList(favorite));

			// assert
			Assert.assertEquals("PARTIAL_SUCCESS", result.get(0));
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeReport() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Report");

			// act
			Report result = ss.reportResources().getReport(1L, null, null, null);

			// assert
			Assert.assertEquals(AccessLevel.OWNER, result.getAccessLevel());
			Assert.assertEquals(false, result.getGanttEnabled());
			Assert.assertTrue(false); // isCellImageUploadEnabled doesn't exist, it should
//			Assert.assertEquals(true, result.isCellImageUploadEnabled());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeShare() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Share");

			Share share = new Share();
			share.setEmail("john.doe@smartsheet.com");
			share.setAccessLevel(AccessLevel.VIEWER);
			share.setSubject("Check out this sheet");
			share.setMessage("Let me know what you think. Thanks!");
			share.setCcMe(true);

			// act
			List<Share> result = ss.sheetResources().shareResources().shareTo(1L, Arrays.asList(share), true);

			// assert
			Assert.assertEquals("PARTIAL_SUCCESS", result.get(0));
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeSendViaEmail() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Send via Email");

			RecipientEmail emailRecipient = new RecipientEmail();
			emailRecipient.setEmail("john.doe@smartsheet.com");

			RecipientGroup groupRecipient = new RecipientGroup();
			groupRecipient.setGroupId(2L);

			FormatDetails formatDetails = new FormatDetails();
			formatDetails.setPaperSize(PaperSize.LETTER);

			SheetEmail sheetEmail = new SheetEmail();
			sheetEmail.setSendTo(Arrays.asList(emailRecipient, groupRecipient));
			sheetEmail.setSubject("Some subject");
			sheetEmail.setMessage("Some message");
			sheetEmail.setCcMe(true);
			sheetEmail.setFormat(SheetEmailFormat.PDF);
			sheetEmail.setFormatDetails(formatDetails);

			// act
			// assert
			ss.sheetResources().sendSheet(1L, sheetEmail);
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeRowEmail() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Row Email");

			RecipientGroup groupRecipient = new RecipientGroup();
			groupRecipient.setGroupId(2L);

			MultiRowEmail rowEmail = new MultiRowEmail();
			rowEmail.setSendTo(Arrays.asList((Recipient)groupRecipient));
			rowEmail.setSubject("Some subject");
			rowEmail.setMessage("Some message");
			rowEmail.setColumnIds(Arrays.asList(3L));
			rowEmail.setIncludeAttachments(false);
			rowEmail.setIncludeDiscussions(true);
			rowEmail.setLayout("VERTICAL");
			rowEmail.setRowIds(Arrays.asList(4L));

			// act
			// assert
			ss.sheetResources().rowResources().sendRows(1L, rowEmail);
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeTemplate() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Template");

			// act
			PagedResult<Template> result = ss.templateResources().listPublicTemplates(null);

			// assert
			Assert.assertEquals(GlobalTemplate.BLANK_SHEET, result.getData().get(0).getGlobalTemplate());
			Assert.assertEquals("Featured Templates", result.getData().get(0).getCategories().get(0));
			Assert.assertEquals(true, result.getData().get(0).isBlank());
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeUpdateRequest() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Update Request");

			Calendar startAtCalendar = Calendar.getInstance();
			startAtCalendar.set(Calendar.YEAR, 2018);
			startAtCalendar.set(Calendar.MONTH, 3);
			startAtCalendar.set(Calendar.DAY_OF_MONTH, 1);
			startAtCalendar.set(Calendar.HOUR_OF_DAY, 19);


			Calendar endAtCalendar = Calendar.getInstance();
			endAtCalendar.set(Calendar.YEAR, 2018);
			endAtCalendar.set(Calendar.MONTH, 6);
			endAtCalendar.set(Calendar.DAY_OF_MONTH, 1);
			endAtCalendar.set(Calendar.HOUR_OF_DAY, 0);

			Schedule schedule = new Schedule();
			schedule.setType(ScheduleType.MONTHLY);
			schedule.setStartAt(startAtCalendar.getTime());// this is apparently the wrong way to set the date, not sure of the right way
			schedule.setEndAt(endAtCalendar.getTime());
			schedule.setDayOrdinal(DayOrdinal.FIRST);
			schedule.setDayDescriptors(Arrays.asList(DayDescriptor.FRIDAY));

			RecipientEmail recipient = new RecipientEmail();
			recipient.setEmail("john.doe@smartsheet.com");

			UpdateRequest updateRequest = new UpdateRequest();
			updateRequest.setSendTo(Arrays.asList((Recipient)recipient));
			updateRequest.setRowIds(Arrays.asList(2L));
			updateRequest.setColumnIds(Arrays.asList(3L));
			updateRequest.setIncludeAttachments(true);
			updateRequest.setIncludeDiscussions(false);
			updateRequest.setSubject("Some subject");
			updateRequest.setMessage("Some message");
			updateRequest.setCcMe(true);
			updateRequest.setSchedule(schedule);

			// act
			UpdateRequest result = ss.sheetResources().updateRequestResources().createUpdateRequest(1, updateRequest);

			// assert
//			Assert.assertEquals("PARTIAL_SUCCESS", result.get(0));
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeSentUpdateRequests() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Sent Update Requests");

			// act
			SentUpdateRequest result = ss.sheetResources().updateRequestResources().getSentUpdateRequest(1, 2);

			// assert
			Assert.assertEquals("Jane Doe", result.getSentBy().getName());
			Assert.assertEquals(UpdateRequestStatus.COMPLETE, result.getStatus());
			Assert.assertEquals("Jane Doe", result.getSentBy().getName());
			Assert.assertEquals(1, result.getSentAt().getMonth());
			Assert.assertEquals(2, result.getSentAt().getDate());
			Assert.assertEquals(4, (long)result.getRowIds().get(0));
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeSheetSettings() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Sheet Settings");

			// act
			List<Row> result = ss.sheetResources().rowResources().addRows(1, new ArrayList<Row>());

			// assert
			Assert.assertEquals("PARTIAL_SUCCESS", result.get(0));
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeContainerDestination() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Container Destination");

			// act
			List<Row> result = ss.sheetResources().rowResources().addRows(1, new ArrayList<Row>());

			// assert
			Assert.assertEquals("PARTIAL_SUCCESS", result.get(0));
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void SerializeCrossSheetReference() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Serialization - Cross Sheet Reference");

			// act
			List<Row> result = ss.sheetResources().rowResources().addRows(1, new ArrayList<Row>());

			// assert
			Assert.assertEquals("PARTIAL_SUCCESS", result.get(0));
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}
}
