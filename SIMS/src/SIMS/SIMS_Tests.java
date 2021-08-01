package SIMS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SIMS_Tests {

    @Test
    void testVerifyUser() {
        char[] p = "P@ssw0rd".toCharArray();
        char[] tp = "password".toCharArray();
        Connector c = new Connector("guhlan", p, "sims-application-test-001.c17nei9nvbm9.us-east-2.rds.amazonaws.com", 
        		3306, "C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore", tp);
        assertTrue(c.verifyUser());
    }

    @Test
    void testVerifyUserFail() {
    	char[] p = "P@ssw0rd".toCharArray();
        char[] tp = "password".toCharArray();
        Connector c = new Connector("fail", p, "sims-application-test-001.c17nei9nvbm9.us-east-2.rds.amazonaws.com", 
        		3306, "C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore", tp);
        assertFalse(c.verifyUser());
    }

    @Test
    void testUserRoleZero() {
    	char[] p = "P@ssw0rd".toCharArray();
        char[] tp = "password".toCharArray();
        Connector c = new Connector("zyoung5", p, "sims-application-test-001.c17nei9nvbm9.us-east-2.rds.amazonaws.com", 
        		3306, "C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore", tp);
        c.getUserRole();
        assertTrue(c.role == 0);
    }

    @Test
    void testUserRoleOne() {
    	char[] p = "P@ssw0rd".toCharArray();
        char[] tp = "password".toCharArray();
        Connector c = new Connector("stetan", p, "sims-application-test-001.c17nei9nvbm9.us-east-2.rds.amazonaws.com", 
        		3306, "C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore", tp);
        c.getUserRole();
        assertTrue(c.role == 1);
    }

    @Test
    void testUserRoleTwo() {
    	char[] p = "P@ssw0rd".toCharArray();
        char[] tp = "password".toCharArray();
        Connector c = new Connector("bsutte", p, "sims-application-test-001.c17nei9nvbm9.us-east-2.rds.amazonaws.com", 
        		3306, "C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore", tp);
        c.getUserRole();
        assertTrue(c.role == 2);
    }

    @Test
    void testQuery() {
    	char[] p = "P@ssw0rd".toCharArray();
        char[] tp = "password".toCharArray();
        Connector c = new Connector("guhlan", p, "sims-application-test-001.c17nei9nvbm9.us-east-2.rds.amazonaws.com", 
        		3306, "C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore", tp);
        assertTrue(c.getResultsofQuery("inventory").size() == 4);
        assertEquals(c.getResultsofQuery("inventory").get(0).size(), 7);
    }

    @Test
    void testStringToPrice() {
        assertEquals(GeneralGuiFunctions.stringToPrice("3.5"), "$3.50");

    }
    
    @Test
    void testVerifyItemExists() {
    	char[] p = "P@ssw0rd".toCharArray();
        char[] tp = "password".toCharArray();
        Connector c = new Connector("guhlan", p, "sims-application-test-001.c17nei9nvbm9.us-east-2.rds.amazonaws.com", 
        		3306, "C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore", tp);
        assertTrue(c.verifyItemExists("Green Apples"));
    }
    
    @Test
    void testVerifyItemExistsFails() {
    	char[] p = "P@ssw0rd".toCharArray();
        char[] tp = "password".toCharArray();
        Connector c = new Connector("guhlan", p, "sims-application-test-001.c17nei9nvbm9.us-east-2.rds.amazonaws.com", 
        		3306, "C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore", tp);
        assertFalse(c.verifyItemExists("Cheese"));
    }
    
}
