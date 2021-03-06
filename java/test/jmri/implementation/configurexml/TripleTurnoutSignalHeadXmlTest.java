package jmri.implementation.configurexml;

import jmri.util.JUnitUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TripleTurnoutSignalHeadXmlTest.java
 *
 * Test for the TripleTurnoutSignalHeadXml class
 *
 * @author   Paul Bender  Copyright (C) 2016
 */
public class TripleTurnoutSignalHeadXmlTest {

    @Test
    public void testCtor(){
      Assert.assertNotNull("TripleTurnoutSignalHeadXml constructor",new TripleTurnoutSignalHeadXml());
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

