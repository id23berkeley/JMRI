package jmri.server.json.throttle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Paul Bender Copyright (C) 2017	
 */
public class JsonThrottleTest {

    @Test
    @Ignore("still need to actually create the throttle (private method) or use getThrottle")
    public void testCTor() throws java.io.IOException, jmri.server.json.JsonException {
        java.io.DataOutputStream output = new java.io.DataOutputStream(
                new java.io.OutputStream() {
                    // null output string drops characters
                    // could be replaced by one that checks for specific outputs
                    @Override
                    public void write(int b) throws java.io.IOException {
                    }
                });
        jmri.server.json.JsonMockConnection mc = new jmri.server.json.JsonMockConnection(output);
        ObjectMapper m = new ObjectMapper();
        JsonNode jn = m.readTree("");
        
        JsonThrottleSocketService ts = new JsonThrottleSocketService(mc);
        JsonThrottle t = JsonThrottle.getThrottle("42",jn,ts);
        Assert.assertNotNull("exists",t);
    }

    // The minimal setup for log4J
    @Before
    public void setUp() {
        apps.tests.Log4JFixture.setUp();
        jmri.util.JUnitUtil.resetInstanceManager();
    }

    @After
    public void tearDown() {
        jmri.util.JUnitUtil.resetInstanceManager();
        apps.tests.Log4JFixture.tearDown();
    }

    private final static Logger log = LoggerFactory.getLogger(JsonThrottleTest.class.getName());

}
