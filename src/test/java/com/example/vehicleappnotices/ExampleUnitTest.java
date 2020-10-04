// IT19170176
// FERNANDO W.N.D
// CarMart Notices

package com.example.vehicleappnotices;
import android.database.CursorIndexOutOfBoundsException;

import androidx.test.filters.SmallTest;
//import android.support.test.filters.SmallTest;
//import android.test.suitebuilder.annotation.SmallTest;
import org.junit.Test;
import static org.junit.Assert.*;
@SmallTest
// IT19170176 - FERNANDO W.N.D -CarMart Notices
 public class ExampleUnitTest {
    //Correct Input test case
    @Test
    // IT19170176 - FERNANDO W.N.D -CarMart Notices -1
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name_@email.com"));
    }



    //Email with sub domain
    @Test
    // IT19170176 - FERNANDO W.N.D -CarMart Notices-2
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.co.uk"));
    }
    //without .com
    @Test
    // IT19170176 - FERNANDO W.N.D -CarMart Notices-3
    public void emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertTrue(EmailValidator.isValidEmail("name@email"));
    }
    //with extra characters
    @Test
    // IT19170176 - FERNANDO W.N.D -CarMart Notices-4
    public void emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertTrue(EmailValidator.isValidEmail("name@email..com"));
    }

    //Empty Input
    @Test
    // IT19170176 - FERNANDO W.N.D -CarMart Notices -5
    public void emailValidator_EmptyString_ReturnsFalse() {
        assertTrue(EmailValidator.isValidEmail(""));
    }
    //null value check
    @Test
    // IT19170176 - FERNANDO W.N.D -CarMart Notices -6
    public void emailValidator_NullEmail_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(null));
    }
   }
