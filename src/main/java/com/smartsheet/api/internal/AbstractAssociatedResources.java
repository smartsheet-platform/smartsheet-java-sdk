package com.smartsheet.api.internal;

import com.smartsheet.api.internal.*;
import java.lang.*;

/**
 * This is the base class of the Smartsheet REST API resources that are associated to other resources.
 * 
 * Thread Safety:
 * This class is thread safe because it is immutable and its base class is thread safe.
 */
public abstract class AbstractAssociatedResources extends AbstractResources {
    /**
     * Represents the master resource type (e.g. "sheet", "workspace").
     * 
     * It will be initialized in constructor and will not change afterwards.
     */
    private String masterResourceType;

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
     * super(smartsheet);
     * this.masterResourceType = masterResourceType;
     * @param masterResourceType 
     * @param smartsheet 
     */
    public AbstractAssociatedResources(SmartsheetImpl smartsheet, String masterResourceType) {
    	super(smartsheet);
    }

    /**
     * Getter of corresponding field.
     * 
     * Returns:
     * corresponding field.
     * 
     * Implementation:
     * Simply return corresponding field.
     * @return 
     */
    protected String getMasterResourceType() {
        return null;
    }
}

