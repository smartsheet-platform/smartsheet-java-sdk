package com.smartsheet.api.internal;

import com.smartsheet.api.AssociatedAttachmentResources;
import com.smartsheet.api.internal.*;
import com.smartsheet.api.models.*;

import java.io.File;
import java.lang.*;
import java.util.List;

/**
 * This is the implementation of the AssociatedAttachmentResources.
 * 
 * Thread Safety:
 * This class is thread safe because it is immutable and its base class is thread safe.
 */
public class AssociatedAttachmentResourcesImpl implements AssociatedAttachmentResources {
    /**
     * Constructor.
     * 
     * Parameters:
     * - smartsheet : the SmartsheetImpl
     * - masterResourceType : the master resource type (e.g. "sheet", "workspace").
     * 
     * Exceptions:
     * - IllegalArgumentException : if any argument is null or empty string
     * 
     * Implementation:
     * super(smartsheet, masterResourceType);
     * @param masterResourceType 
     * @param smartsheet 
     */
    public AssociatedAttachmentResourcesImpl(SmartsheetImpl smartsheet, String masterResourceType) {
    }

    /**
     * List attachments of a given object.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * GET /sheet/{id}/attachments
     * GET /row/{id}/attachments
     * GET /comment/{id}/attachments
     * 
     * Parameters:
     * - objectId : the ID of the object to which the attachments are associated
     * 
     * Returns:
     * the attachments (note that empty list will be returned if there is none)
     * 
     * Exceptions:
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * 
     * Implementation:
     * return this.listResources(getMasterResourceType() + "/" + objectId + "/attachments", Attachment.class);
     * @param objectId 
     * @return 
     */
    public List<Attachment> listAttachments(long objectId) {
        return null;
    }

    /**
     * Attach a file to the object.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * POST /sheet/{id}/attachments
     * POST /row/{id}/attachments
     * POST /comment/{idd}/attachments
     * 
     * Parameters:
     * - objectId : the ID of the object
     * - file : the file to attach
     * - contentType : the content type of the file
     * 
     * Returns:
     * the created attachment
     * 
     * Exceptions:
     * - IllegalArgumentException : if any argument is null or empty string
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * 
     * Implementation:
     * HttpRequest request = createHttpRequest(this.getSmartsheet().getBaseURI().resolve(getMasterResourceType() + "/" + objectId + "/attachments"), HttpMethod.POST);
     * request.getHeaders().put("Content-Disposition", "attachment; filename=" + file.getName());
     * HttpEntity entity = new HttpEntity();
     * entity.setContentType(contentType);
     * entity.setContent(new FileInputStream(file));
     * entity.setContentLength(file.length());
     * request.setEntity(entity);
     * 
     * HttpResponse response = this.getSmartsheet().getHttpClient().request(request);
     * 
     * switch (response.getStatusCode()) {
     * case 200:
     *     return this.getSmartsheet().getJsonSerializer().deserializeResult(Attachment.class, response.getEntity().getContent()).getResult();
     * default:
     *     handleError(response);
     * }
     * @param file 
     * @param objectId 
     * @param contentType 
     * @return 
     */
    public Attachment attachFile(long objectId, File file, String contentType) {
        return null;
    }

    /**
     * Attach a URL to the object.
     * 
     * The URL can be a normal URL (attachmentType "URL"), a Google Drive URL (attachmentType "GOOGLE_DRIVE") or a Box.com URL (attachmentType "BOX_COM").  
     * 
     * It mirrors to the following Smartsheet REST API method:
     * POST /sheet/{id}/attachments
     * POST /row/{id}/attachments
     * POST /comment/{idd}/attachments
     * 
     * Parameters:
     * - objectId : the ID of the object
     * - attachment : the attachment object limited to the following attributes:
     * * name
     * * description (applicable when attaching to sheet or row only)
     * * url
     * * attachmentType
     * * attachmentSubType
     * 
     * Returns:
     * the created attachment
     * 
     * Exceptions:
     * - IllegalArgumentException : if any argument is null
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * 
     * Implementation:
     * return this.createResource(getMasterResourceType() + "/" + objectId + "/attachments", Attachment.class, attachment);
     * @param objectId 
     * @param attachment 
     * @return 
     */
    public Attachment attachURL(long objectId, Attachment attachment) {
        return null;
    }
}

