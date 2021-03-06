package jmri.jmrix.loconet.usb_dcs52;

import jmri.jmrix.SystemConnectionMemoTestBase;
import jmri.jmrix.loconet.LocoNetInterfaceScaffold;
import jmri.util.JUnitUtil;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class UsbDcs52SystemConnectionMemoTest extends SystemConnectionMemoTestBase<UsbDcs52SystemConnectionMemo> {

    @Override
    @Before
    public void setUp() {
       JUnitUtil.setUp();
       scm = new UsbDcs52SystemConnectionMemo();
       LocoNetInterfaceScaffold lnis = new LocoNetInterfaceScaffold(scm);
       scm.setLnTrafficController(lnis);
       scm.configureCommandStation(jmri.jmrix.loconet.LnCommandStationType.COMMAND_STATION_DCS100,false,false,false);
       scm.configureManagers();
    }

    @Override
    @After
    public void tearDown() {
        scm.dispose();
        JUnitUtil.tearDown();
    }

    // private final static Logger log = LoggerFactory.getLogger(UsbDcs52SystemConnectionMemoTest.class);

}
