package jmri.jmrix.internal.configurexml;

import jmri.util.JUnitUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * InternalSensorManagerXmlTest.java
 *
 * Test for the InternalSensorManagerXml class
 *
 * @author   Paul Bender  Copyright (C) 2016
 */
public class InternalSensorManagerXmlTest {

    @Test
    public void testCtor(){
      Assert.assertNotNull("InternalSensorManagerXml constructor",new InternalSensorManagerXml());
    }

    @Before
    public void setUp() {
        JUnitUtil.setUp();
    }

    @After
    public void tearDown() {
        JUnitUtil.tearDown();
    }

}

