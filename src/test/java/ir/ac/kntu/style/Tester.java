package ir.ac.kntu.style;

import ir.ac.kntu.main.baseclass.InterestRatesAndFees;
import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.manager.implement.ImplementAutoTransaction;
import ir.ac.kntu.manager.implement.ImplementUserManagement;
import ir.ac.kntu.manager.info.Manager;
import ir.ac.kntu.support.implement.ImplementUserInformation;
import ir.ac.kntu.support.info.Support;
import ir.ac.kntu.user.implement.ImplementCapitalFund;
import ir.ac.kntu.user.implement.ImplementSIMCardCharge;
import ir.ac.kntu.user.implement.ImplementTransfer;
import ir.ac.kntu.user.info.*;
import ir.ac.kntu.util.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Tester {
    private UserAccount user1;
    private UserAccount user2;
    private Bank bank;
    private Manager manager;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        user1 = new UserAccount("ali", "prs", "09032948208", "1452006601", "#Ap84", 0, 12345678, 0);
        bank.getUserAccounts().add(user1);
        user2 = new UserAccount("nima", "dan", "09964430265", "1465713522", "#Nd83", 0, 87654321, 0);
        bank.getUserAccounts().add(user2);
        manager = new Manager("AliPrs", "@Ap84", new ArrayList<>());
        bank.addManager(manager);
    }

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
    public void testTransfer() throws NoSuchFieldException, NoSuchMethodException {
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
        Contact contact = new Contact("nima", "dan", "09964430265");

        assertFalse(user1.getMyContacts().contains(contact));
        user1.getMyContacts().add(contact);
        assertTrue(user1.getMyContacts().contains(contact));
    }

    @Test
    public void testSearchSimilarityUser() throws NoSuchFieldException, NoSuchMethodException {
        ImplementUserInformation search = new ImplementUserInformation();
        assertTrue(search.similaritySearch(user1.getFirstName(), "alo"));
    }

    @Test
    public void testChargeSIMCard() throws NoSuchFieldException, NoSuchMethodException {
        user1.setBalanceAccount(user1.getBalanceAccount() + 4000);
        ImplementSIMCardCharge charge = new ImplementSIMCardCharge();
        charge.setCharge(bank, user1, "09964430265", 1000);
        assertEquals(3000, user1.getBalanceAccount());
        assertEquals(1000, user2.getSimCardValidity());
    }

    @Test
    public void testFunds() throws NoSuchFieldException, NoSuchMethodException {
        user1.setBalanceAccount(user1.getBalanceAccount() + 10000);
        user1.addProfitFund(new ProfitFund("food"));
        ImplementCapitalFund capitalFund = new ImplementCapitalFund();
        capitalFund.transfer(user1, 2, 3000);
        assertEquals(7000, user1.getBalanceAccount());
        assertEquals(3000, user1.getProfitFunds().get(0).getFundBalance());
    }

    @Test
    public void testAutoTransaction() throws NoSuchFieldException, NoSuchMethodException {
        bank.setInterests(new InterestRatesAndFees("2%", "300", "2%", "2000", "0"));

        user1.setBalanceAccount(user1.getBalanceAccount() + 10000);
        Transfer transfer = new Transfer(4000, 12345678, 87654321, "nima", Calendar.getStringNow());
        bank.addTransfer(transfer);

        ImplementAutoTransaction autoTransaction = new ImplementAutoTransaction();
        autoTransaction.handleTransfers(bank);
        assertEquals(5000, user1.getBalanceAccount());
        assertEquals(920, user1.getRemainingFund().getFundBalance());
    }

    @Test
    public void testComputingRemainingMoney() throws NoSuchFieldException, NoSuchMethodException {
        ImplementTransfer transfer = new ImplementTransfer();
        int money = transfer.computingRemainingMoney(12321250);
        assertEquals(money, 678750);
    }
}