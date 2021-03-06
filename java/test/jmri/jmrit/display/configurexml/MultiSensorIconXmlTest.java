package jmri.jmrit.display.configurexml;

import jmri.util.JUnitUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * MultiSensorIconXmlTest.java
 *
 * Test for the MultiSensorIconXml class
 *
 * @author   Paul Bender  Copyright (C) 2016
 */
public class MultiSensorIconXmlTest {

    @Test
    public void testCtor(){
      Assert.assertNotNull("MultiSensorIconXml constructor",new MultiSensorIconXml());
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

