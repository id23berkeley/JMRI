package jmri.jmrit.display;

import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import jmri.InstanceManager;
import jmri.SignalMast;
import jmri.implementation.DefaultSignalHead;
import jmri.util.JUnitUtil;
import org.junit.*;

/**
 * Test the SignalMastIcon.
 *
 * @author Bob Jacobsen Copyright 2009
 */
public class SignalMastIconTest extends PositionableIconTest {

    SignalMast s = null;
    SignalMastIcon to = null;

    @Test
    public void testShowText() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        // this one is for Layout editor, which for now
        // is still in text form.
        JFrame jf = new JFrame("SignalMast Icon Text Test");
        jf.getContentPane().setLayout(new java.awt.FlowLayout());

        jf.getContentPane().add(new JLabel("Should say Approach: "));
        jf.getContentPane().add(to);

        s.setAspect("Clear");
        s.setAspect("Approach");

        jf.pack();
        jf.setVisible(true);

        // close
        JUnitUtil.dispose(jf);

    }

    @Test
    public void testShowIcon() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());

        JFrame jf = new JFrame("SignalMastIcon Icon Test");
        jf.getContentPane().setLayout(new java.awt.FlowLayout());

        SignalMastIcon to = new SignalMastIcon(editor);
        to.setShowAutoText(false);

        jf.getContentPane().add(new JLabel("Should be yellow/red: "));
        jf.getContentPane().add(to);

        s = InstanceManager.getDefault(jmri.SignalMastManager.class)
                .provideSignalMast("IF$shsm:basic:two-searchlight:IH2:IH3");

        s.setAspect("Clear");

        to.setSignalMast(s.getSystemName());

        s.setAspect("Clear");
        s.setAspect("Approach");

        jf.pack();
        jf.setVisible(true);

        // close
        JUnitUtil.dispose(jf);
    }

    @Before
    @Override
    public void setUp() {
        super.setUp();
        JUnitUtil.initInternalSignalHeadManager();
        if (!GraphicsEnvironment.isHeadless()) {
            editor = new EditorScaffold();
            p = new SignalMastIcon(editor);
            to = new SignalMastIcon(editor);
            to.setShowAutoText(true);

            // reset instance manager & create test heads
            InstanceManager.getDefault(jmri.SignalHeadManager.class).register(
                    new DefaultSignalHead("IH1") {
                        @Override
                        protected void updateOutput() {
                        }
                    });
            InstanceManager.getDefault(jmri.SignalHeadManager.class).register(
                    new DefaultSignalHead("IH2") {
                        @Override
                        protected void updateOutput() {
                        }
                    });
            InstanceManager.getDefault(jmri.SignalHeadManager.class).register(
                    new DefaultSignalHead("IH3") {
                        @Override
                        protected void updateOutput() {
                        }
                    });

            s = InstanceManager.getDefault(jmri.SignalMastManager.class)
                    .provideSignalMast("IF$shsm:basic:one-searchlight:IH1");

            to.setSignalMast(new jmri.NamedBeanHandle<>(s.getSystemName(), s));
        }
    }

    @After
    @Override
    public void tearDown() {
        to = null;
        super.tearDown();
    }

    // private final static Logger log = LoggerFactory.getLogger(SignalMastIconTest.class);
}
