package com.smartsheet.api.models;

/**
 * Represents the UpdateRequest object.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/504779-using-update-requests">Using Update Request Help</a>
 */
public class UpdateRequest extends IdentifiableModel<Long>{

    /**
     * Represents the ID of the update request.
     */
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
}
