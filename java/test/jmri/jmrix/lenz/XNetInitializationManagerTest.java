package jmri.jmrix.lenz;

import jmri.implementation.NmraConsistManager;
import jmri.jmrix.roco.RocoXNetThrottleManager;
import jmri.util.JUnitUtil;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * XNetInitializationManagerTest.java
 *
 * Test for the jmri.jmrix.lenz.XNetInitializationManager class
 *
 * @author Paul Bender
 */
public class XNetInitializationManagerTest {

    private XNetTrafficController tc;
    private XNetSystemConnectionMemo memo;
    private LenzCommandStation cs;

    @Test
    public void testNoCSVersionResponse() {
        Mockito.when(cs.getCommandStationSoftwareVersion()).thenReturn(-1.0f);
        Mockito.when(cs.getCommandStationSoftwareVersionBCD()).thenReturn(-1.0f);
        Mockito.when(cs.getCommandStationType()).thenReturn(-1);
        XNetInitializationManager m = new XNetInitializationManager(memo) {
            @Override
            protected int getInitTimeout() {
                return 50;   // shorten, because this will fail & delay test
            }
        };
        assertThat(m).withFailMessage("exists").isNotNull();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(memo.getCommandStation()).isEqualTo(cs);
        softly.assertThat(memo.getPowerManager()).isExactlyInstanceOf((XNetPowerManager.class));
        softly.assertThat(memo.getThrottleManager()).isExactlyInstanceOf(XNetThrottleManager.class);
        softly.assertThat(memo.getProgrammerManager()).isExactlyInstanceOf(XNetProgrammerManager.class);
        softly.assertThat(memo.getProgrammerManager().getGlobalProgrammer()).isExactlyInstanceOf(XNetProgrammer.class);
        softly.assertThat(memo.getProgrammerManager().getAddressedProgrammer(false,42)).isExactlyInstanceOf(XNetOpsModeProgrammer.class);
        softly.assertThat(memo.getTurnoutManager()).isExactlyInstanceOf(XNetTurnoutManager.class);
        softly.assertThat(memo.getSensorManager()).isExactlyInstanceOf(XNetSensorManager.class);
        softly.assertThat(memo.getLightManager()).isExactlyInstanceOf(XNetLightManager.class);
        softly.assertThat(memo.getConsistManager()).isExactlyInstanceOf(XNetConsistManager.class);
        softly.assertAll();
        jmri.util.JUnitAppender.assertWarnMessage("Command Station disconnected, or powered down assuming LZ100/LZV100 V3.x");
    }

    @Test
    public void testUnsupportedSoftwareVersionResponse() {
        Mockito.when(cs.getCommandStationSoftwareVersion()).thenReturn(2.0f);
        Mockito.when(cs.getCommandStationSoftwareVersionBCD()).thenReturn(2.0f);
        Mockito.when(cs.getCommandStationType()).thenReturn(0x00);
        XNetInitializationManager m = new XNetInitializationManager(memo) {
            @Override
            protected int getInitTimeout() {
                return 50;   // shorten, because this will fail & delay test
            }
        };
        assertThat(m).withFailMessage("exists").isNotNull();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(memo.getCommandStation()).isNull();
        softly.assertThat(memo.getPowerManager()).isExactlyInstanceOf((XNetPowerManager.class));
        softly.assertThat(memo.getThrottleManager()).isExactlyInstanceOf(XNetThrottleManager.class);
        softly.assertThat(memo.getProgrammerManager()).isNull();
        softly.assertThat(memo.getTurnoutManager()).isNull();
        softly.assertThat(memo.getSensorManager()).isNull();
        softly.assertThat(memo.getLightManager()).isNull();
        softly.assertThat(memo.getConsistManager()).isNull();
        softly.assertAll();
        jmri.util.JUnitAppender.assertWarnMessage("Command Station does not support XpressNet Version 3 Command Set");
    }

    @Test
    public void testVersion35LZ100Response() {
        Mockito.when(cs.getCommandStationSoftwareVersion()).thenReturn(3.5f);
        Mockito.when(cs.getCommandStationSoftwareVersionBCD()).thenReturn(3.5f);
        Mockito.when(cs.getCommandStationType()).thenReturn(0x00);
        XNetInitializationManager m = new XNetInitializationManager(memo) {
            @Override
            protected int getInitTimeout() {
                return 50;   // shorten, because this will fail & delay test
            }
        };
        assertThat(m).withFailMessage("exists").isNotNull();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(memo.getCommandStation()).isEqualTo(cs);
        softly.assertThat(memo.getPowerManager()).isExactlyInstanceOf((XNetPowerManager.class));
        softly.assertThat(memo.getThrottleManager()).isExactlyInstanceOf(XNetThrottleManager.class);
        softly.assertThat(memo.getProgrammerManager()).isExactlyInstanceOf(XNetProgrammerManager.class);
        softly.assertThat(memo.getProgrammerManager().getGlobalProgrammer()).isExactlyInstanceOf(XNetProgrammer.class);
        softly.assertThat(memo.getProgrammerManager().getAddressedProgrammer(false,42)).isExactlyInstanceOf(XNetOpsModeProgrammer.class);
        softly.assertThat(memo.getTurnoutManager()).isExactlyInstanceOf(XNetTurnoutManager.class);
        softly.assertThat(memo.getSensorManager()).isExactlyInstanceOf(XNetSensorManager.class);
        softly.assertThat(memo.getLightManager()).isExactlyInstanceOf(XNetLightManager.class);
        softly.assertThat(memo.getConsistManager()).isExactlyInstanceOf(XNetConsistManager.class);
        softly.assertAll();
    }

    @Test
    public void testVersion3LH200Response() {
        Mockito.when(cs.getCommandStationSoftwareVersion()).thenReturn(3.0f);
        Mockito.when(cs.getCommandStationSoftwareVersionBCD()).thenReturn(3.0f);
        Mockito.when(cs.getCommandStationType()).thenReturn(0x01);
        XNetInitializationManager m = new XNetInitializationManager(memo) {
            @Override
            protected int getInitTimeout() {
                return 50;   // shorten, because this will fail & delay test
            }
        };
        assertThat(m).withFailMessage("exists").isNotNull();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(memo.getCommandStation()).isNull();
        softly.assertThat(memo.getPowerManager()).isExactlyInstanceOf((XNetPowerManager.class));
        softly.assertThat(memo.getThrottleManager()).isExactlyInstanceOf(XNetThrottleManager.class);
        softly.assertThat(memo.getProgrammerManager()).isNull();
        softly.assertThat(memo.getTurnoutManager()).isNull();
        softly.assertThat(memo.getSensorManager()).isNull();
        softly.assertThat(memo.getLightManager()).isNull();
        softly.assertThat(memo.getConsistManager()).isNull();
        softly.assertAll();
    }

    @Test
    public void testVersion32CompactResponse() {
        Mockito.when(cs.getCommandStationSoftwareVersion()).thenReturn(3.2f);
        Mockito.when(cs.getCommandStationSoftwareVersionBCD()).thenReturn(3.2f);
        Mockito.when(cs.getCommandStationType()).thenReturn(0x02);
        XNetInitializationManager m = new XNetInitializationManager(memo) {
            @Override
            protected int getInitTimeout() {
                return 50;   // shorten, because this will fail & delay test
            }
        };
        assertThat(m).withFailMessage("exists").isNotNull();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(memo.getCommandStation()).isNull();
        softly.assertThat(memo.getPowerManager()).isExactlyInstanceOf((XNetPowerManager.class));
        softly.assertThat(memo.getThrottleManager()).isExactlyInstanceOf(XNetThrottleManager.class);
        softly.assertThat(memo.getProgrammerManager()).isNull();
        softly.assertThat(memo.getTurnoutManager()).isExactlyInstanceOf(XNetTurnoutManager.class);
        softly.assertThat(memo.getSensorManager()).isNull();
        softly.assertThat(memo.getLightManager()).isExactlyInstanceOf(XNetLightManager.class);
        softly.assertThat(memo.getConsistManager()).isExactlyInstanceOf(XNetConsistManager.class);
        softly.assertAll();
    }

    @Test
    public void testVersion3LokMausResponse() {
        Mockito.when(cs.getCommandStationSoftwareVersion()).thenReturn(3.0f);
        Mockito.when(cs.getCommandStationSoftwareVersionBCD()).thenReturn(3.0f);
        Mockito.when(cs.getCommandStationType()).thenReturn(0x04);
        XNetInitializationManager m = new XNetInitializationManager(memo) {
            @Override
            protected int getInitTimeout() {
                return 50;   // shorten, because this will fail & delay test
            }
        };
        assertThat(m).withFailMessage("exists").isNotNull();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(memo.getCommandStation()).isEqualTo(cs);
        softly.assertThat(memo.getPowerManager()).isExactlyInstanceOf((XNetPowerManager.class));
        softly.assertThat(memo.getThrottleManager()).isExactlyInstanceOf(RocoXNetThrottleManager.class);
        softly.assertThat(memo.getProgrammerManager()).isExactlyInstanceOf(XNetProgrammerManager.class);
        softly.assertThat(memo.getProgrammerManager().getGlobalProgrammer()).isExactlyInstanceOf(XNetProgrammer.class);
        softly.assertThat(memo.getProgrammerManager().getAddressedProgrammer(false,42)).isExactlyInstanceOf(XNetOpsModeProgrammer.class);
        softly.assertThat(memo.getTurnoutManager()).isExactlyInstanceOf(XNetTurnoutManager.class);
        softly.assertThat(memo.getSensorManager()).isExactlyInstanceOf(XNetSensorManager.class);
        softly.assertThat(memo.getLightManager()).isExactlyInstanceOf(XNetLightManager.class);
        softly.assertThat(memo.getConsistManager()).isExactlyInstanceOf(NmraConsistManager.class);
        softly.assertAll();
    }

    @Test
    public void testVersion3MultiMausResponse() {
        Mockito.when(cs.getCommandStationSoftwareVersion()).thenReturn(3.0f);
        Mockito.when(cs.getCommandStationSoftwareVersionBCD()).thenReturn(3.0f);
        Mockito.when(cs.getCommandStationType()).thenReturn(0x10);
        XNetInitializationManager m = new XNetInitializationManager(memo) {
            @Override
            protected int getInitTimeout() {
                return 50;   // shorten, because this will fail & delay test
            }
        };
        assertThat(m).withFailMessage("exists").isNotNull();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(memo.getCommandStation()).isEqualTo(cs);
        softly.assertThat(memo.getPowerManager()).isExactlyInstanceOf((XNetPowerManager.class));
        softly.assertThat(memo.getThrottleManager()).isExactlyInstanceOf(RocoXNetThrottleManager.class);
        softly.assertThat(memo.getProgrammerManager()).isExactlyInstanceOf(XNetProgrammerManager.class);
        softly.assertThat(memo.getProgrammerManager().getGlobalProgrammer()).isExactlyInstanceOf(XNetProgrammer.class);
        softly.assertThat(memo.getProgrammerManager().getAddressedProgrammer(false,42)).isExactlyInstanceOf(XNetOpsModeProgrammer.class);
        softly.assertThat(memo.getTurnoutManager()).isExactlyInstanceOf(XNetTurnoutManager.class);
        softly.assertThat(memo.getSensorManager()).isExactlyInstanceOf(XNetSensorManager.class);
        softly.assertThat(memo.getLightManager()).isExactlyInstanceOf(XNetLightManager.class);
        softly.assertThat(memo.getConsistManager()).isExactlyInstanceOf(NmraConsistManager.class);
        softly.assertAll();
    }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        cs = Mockito.mock(LenzCommandStation.class);
        tc = Mockito.mock(XNetTrafficController.class);
        Mockito.when(tc.getCommandStation()).thenReturn(cs);
        memo = new XNetSystemConnectionMemo(tc);
    }

    @AfterEach
    public void tearDown() throws Exception {
        memo = null;
        tc = null;
        cs = null;
        JUnitUtil.tearDown();
    }

}
