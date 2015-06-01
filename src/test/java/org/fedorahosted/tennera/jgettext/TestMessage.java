package org.fedorahosted.tennera.jgettext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.fedorahosted.tennera.jgettext.catalog.parse.MessageStreamParser;
import org.junit.Test;

public class TestMessage {

    @Test
    public void testIsFuzzyWhenEmptyMsgstr() {
        Message msg = new Message();
        msg.setMsgid("hello world!");
        assertFalse(msg.isFuzzy());
        msg.setMsgstr("");
        assertFalse(msg.isFuzzy());

        msg.markFuzzy();
        assertFalse("Empty msgstr should not produce fuzzy", msg.isFuzzy());
    }

    @Test
    public void testIsFuzzyWhenMarkedAsFuzzy() {
        Message msg = new Message();
        msg.setMsgid("hello world!");
        msg.setMsgstr("hei verden");
        msg.markFuzzy();
        assertTrue(msg.isFuzzy());
        msg.setFuzzy(false);
        assertFalse(msg.isFuzzy());
    }

    @Test
    public void testFlagsAreWorkingAsExpected() throws Throwable {
        File poFile = getResource("/flags.po");

        MessageStreamParser parser = new MessageStreamParser(poFile);

        Message msg = parser.next();
        assertTrue(msg.isFuzzy());
        assertTrue(msg.getFormats().contains("no-c-format"));

        msg = parser.next();
        assertTrue(msg.isFuzzy());
        assertTrue(msg.getFormats().contains("no-c-format"));

        msg = parser.next();
        assertTrue(msg.isFuzzy());
        assertTrue(msg.getFormats().contains("no-c-format"));

        msg = parser.next();
        assertTrue(msg.isFuzzy());
        assertTrue(msg.getFormats().contains("no-c-format"));

    }

    private File getResource(String file) {
        return new File(getClass().getResource(file).getFile());
    }

}
