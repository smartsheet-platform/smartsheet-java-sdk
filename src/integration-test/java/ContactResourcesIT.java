import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContactResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testGetContact() throws SmartsheetException, IOException {
//        Contact contact = smartsheet.contactResources().getContact("Aditi Nioding");
//        assertEquals(contact.getName(), "Aditi Nioding");
    }

    @Test
    public void testListContacts() throws SmartsheetException, IOException {
//        PagedResult<Contact> contacts = smartsheet.contactResources().listContacts(new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build());
//        assertEquals(contacts.getData().get(0).getName(), "David Davidson");
//        assertTrue(contacts.getTotalCount() == 2);
    }
}
