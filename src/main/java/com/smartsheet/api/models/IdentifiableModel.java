package com.smartsheet.api.models;

/**
 * Represents an object with ID.
 */
public abstract class IdentifiableModel {
    /**
     * Represents the ID.
     */
    private Long id;

    /**
     * Check if the given object equals to this object.
     * 
     * Parameters:
     * - object : the object to compare
     * 
     * Returns:
     * true if given object equals to this object, false otherwise
     * 
     * Implementation:
     * return object != null && (object == this || (object.getClass() == this.getClass() && object.getId() == this.getId()));
     * @param object 
     * @return 
     */
    public boolean equals(Object object) {
        return false;
    }

    /**
     * Return the hash code of this object.
     * 
     * Parameters:
     * None
     * 
     * Returns:
     * the hash code
     * 
     * Implementation:
     * int result = 17;
     * result = 31 * result + (int) (this.id.longValue() ^ (this.id.longValue() >>> 32));
     * 
     * return result;
     * @return 
     */
    public int hashCode() {
        return 0;
    }
}

