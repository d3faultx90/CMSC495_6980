package SIMS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SIMS_Tests {

    @Test
    void testVerifyUser() {
        char[] p = "P@ssw0rd".toCharArray();
        Connector c = new Connector("guhlan", p);
        assertTrue(c.verifyUser());
    }

    @Test
    void testVerifyUserFail() {
        char[] p = "P@ssw0rd".toCharArray();
        Connector c = new Connector("fail", p);
        assertFalse(c.verifyUser());
    }

    @Test
    void testUserRoleZero() {
        char[] p = "P@ssw0rd".toCharArray();
        Connector c = new Connector("zyoung5", p);
        c.getUserRole();
        assertTrue(c.role == 0);
    }

    @Test
    void testUserRoleOne() {
        char[] p = "P@ssw0rd".toCharArray();
        Connector c = new Connector("stetan", p);
        c.getUserRole();
        assertTrue(c.role == 1);
    }

    @Test
    void testUserRoleTwo() {
        char[] p = "P@ssw0rd".toCharArray();
        Connector c = new Connector("bsutte", p);
        c.getUserRole();
        assertTrue(c.role == 2);
    }

    @Test
    void testQuery() {
        char[] p = "P@ssw0rd".toCharArray();
        Connector c = new Connector("guhlan", p);
        assertTrue(c.getResultsofQuery("inventory").size() == 4);
        assertEquals(c.getResultsofQuery("inventory").get(0).size(), 7);
    }

    @Test
    void testStringToPrice() {
        assertEquals(GeneralGuiFunctions.stringToPrice("3.5"), "$3.5");

    }

}
