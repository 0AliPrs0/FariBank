package ir.ac.kntu.style;

import ir.ac.kntu.*;
import ir.ac.kntu.util.Calendar;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class Tester {

    @Test
    public void testEncapsulationFields() throws NoSuchFieldException, NoSuchMethodException {
        Field[] userFields = User.class.getDeclaredFields();
        for (Field field : userFields) {
            assertTrue(java.lang.reflect.Modifier.isPrivate(field.getModifiers()));
        }

        Field[] userAccount = UserAccount.class.getDeclaredFields();
        for (Field field : userAccount) {
            assertTrue(java.lang.reflect.Modifier.isPrivate(field.getModifiers()));
        }

        Field[] support = Support.class.getDeclaredFields();
        for (Field field : support) {
            assertTrue(java.lang.reflect.Modifier.isPrivate(field.getModifiers()));
        }
    }

    @Test
    public void testEncapsulationMethods() throws NoSuchFieldException, NoSuchMethodException {
        Method[] userMethods = User.class.getDeclaredMethods();
        for (Method method : userMethods) {
            assertTrue(java.lang.reflect.Modifier.isPublic(method.getModifiers()));
        }

        Method[] userAccount = UserAccount.class.getDeclaredMethods();
        for (Method method : userAccount) {
            assertTrue(java.lang.reflect.Modifier.isPublic(method.getModifiers()));
        }

        Method[] support = Support.class.getDeclaredMethods();
        for (Method method : support) {
            assertTrue(java.lang.reflect.Modifier.isPublic(method.getModifiers()));
        }

    }

    @Test
    public void testUserAccountMethods() throws NoSuchFieldException, NoSuchMethodException {
        Bank bank = new Bank();
        UserAccount user = new UserAccount("ali", "prs", "09032948208", "1452006601", "#Ap84", 0, 12345678);

        assertFalse(bank.getUserAccounts().contains(user));
        bank.getUserAccounts().add(user);
        assertTrue(bank.getUserAccounts().contains(user));
    }

    @Test
    public void testTransfer() throws NoSuchFieldException, NoSuchMethodException {
        UserAccount user1 = new UserAccount("ali", "prs", "09032948208", "1452006601", "#Ap84", 0, 12345678);
        UserAccount user2 = new UserAccount("nima", "dan", "09964430265", "1465713522", "#Nd83", 0, 87654321);

        ChargeAccount charge = new ChargeAccount(10000, Calendar.getStringNow());
        user1.getChargeAccounts().add(charge);
        user1.setBalanceAccount(user1.getBalanceAccount() + 10000);
        assertEquals(10000, user1.getBalanceAccount());

        Transfer transfer = new Transfer(5000, 12345678, 87654321, "nima", Calendar.getStringNow());
        user1.addTransfer(transfer);
        user1.setBalanceAccount(user1.getBalanceAccount() - (5000 + 1000));
        user2.setBalanceAccount(user2.getBalanceAccount() + 5000 );

        assertEquals(4000, user1.getBalanceAccount());
        assertEquals(5000, user2.getBalanceAccount());
    }

    @Test
    public void testContact() throws NoSuchFieldException, NoSuchMethodException {
        UserAccount user = new UserAccount("ali", "prs", "09032948208", "1452006601", "#Ap84", 0, 12345678);
        Contact contact = new Contact("nima", "dan", "09964430265");

        assertFalse(user.getMyContacts().contains(contact));
        user.getMyContacts().add(contact);
        assertTrue(user.getMyContacts().contains(contact));
    }

    @Test
    public void testSearchSimilarityUser() throws NoSuchFieldException, NoSuchMethodException {
        UserAccount user = new UserAccount("ali", "prs", "09032948208", "1452006601", "#Ap84", 0, 12345678);

        ImplementUserInformation search = new ImplementUserInformation();
        assertTrue(search.similaritySearch(user.getFirstName(), "alo"));

    }
}