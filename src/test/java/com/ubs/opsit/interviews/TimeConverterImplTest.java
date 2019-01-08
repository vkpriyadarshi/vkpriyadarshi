package com.ubs.opsit.interviews;

import org.junit.Test;
import java.lang.reflect.Method;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acceptance test class that uses the JBehave (Gerkin) syntax for writing stories.  You should not need to
 * edit this class to complete the exercise, this is your definition of done.
 */
public class TimeConverterImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(TimeConverterImplTest.class);

    @Test
    public void testConvertTimeForValidInputTimeReturnsValidBerlinClockFormat() throws Exception
    {
        TimeConverterImpl timeConverter = new TimeConverterImpl();
        String berlinClockFormat = timeConverter.convertTime("23:59:59");
        StringBuilder berlinClockFormatBuilder = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        berlinClockFormatBuilder.append("O")
                .append(newLine)
                .append("RRRR")
                .append(newLine)
                .append("RRRO")
                .append(newLine)
                .append("YYRYYRYYRYY")
                .append(newLine)
                .append("YYYY");
        assertThat(berlinClockFormat, equalTo(berlinClockFormatBuilder.toString()));
    }

    @Test
    public void testConvertTimeForInvalidInputTimeReturnsInvalidInputMessage() throws Exception
    {
        TimeConverterImpl timeConverter = new TimeConverterImpl();
        String berlinClockFormat = timeConverter.convertTime("20:13");
        assertThat(berlinClockFormat, equalTo("Input time message is invalid"));
    }

    @Test
    public void testGetStringOfGivenCharacterAndLengthReturnsEmptyStringForZeroLength() throws Exception
    {
        TimeConverterImpl timeConverter = new TimeConverterImpl();
        Method method = timeConverter.getClass().getDeclaredMethod("getStringOfGivenCharacterAndLength", char.class, int.class);
        method.setAccessible(true);
        String returnString = (String)method.invoke(timeConverter, 'A', 0);
        assertThat(returnString, equalTo(""));
    }

    @Test
    public void testGetStringOfGivenCharacterAndLengthReturnsGivenLengthString() throws Exception
    {
        TimeConverterImpl timeConverter = new TimeConverterImpl();
        Method method = timeConverter.getClass().getDeclaredMethod("getStringOfGivenCharacterAndLength", char.class, int.class);
        method.setAccessible(true);
        String returnString = (String)method.invoke(timeConverter, 'A', 3);
        assertThat(returnString, equalTo("AAA"));
    }

    @Test
    public void testIsInputTimeValidReturnsTrueForValidInput() throws Exception
    {
        TimeConverterImpl timeConverter = new TimeConverterImpl();
        Method method = timeConverter.getClass().getDeclaredMethod("isInputTimeValid", String.class);
        method.setAccessible(true);
        boolean isInputTimeValid = (Boolean)method.invoke(timeConverter, "10:20:45");
        assertThat(isInputTimeValid, equalTo(true));
    }

    @Test
    public void testIsInputTimeValidReturnsFalseForNullInput() throws Exception
    {
        TimeConverterImpl timeConverter = new TimeConverterImpl();
        Method method = timeConverter.getClass().getDeclaredMethod("isInputTimeValid", String.class);
        method.setAccessible(true);
        Object nullInput = null;
        boolean isInputTimeValid = (Boolean)method.invoke(timeConverter, nullInput);
        assertThat(isInputTimeValid, equalTo(false));
    }

    @Test
    public void testIsInputTimeValidReturnsFalseForEmptyInput() throws Exception
    {
        TimeConverterImpl timeConverter = new TimeConverterImpl();
        Method method = timeConverter.getClass().getDeclaredMethod("isInputTimeValid", String.class);
        method.setAccessible(true);
        boolean isInputTimeValid = (Boolean)method.invoke(timeConverter, "");
        assertThat(isInputTimeValid, equalTo(false));
    }

    @Test
    public void testCheckTimeFieldsValidityReturnTrueForValidTimeFormat() throws Exception
    {
        TimeConverterImpl timeConverter = new TimeConverterImpl();
        Method method = timeConverter.getClass().getDeclaredMethod("checkTimeFieldsValidity", String.class);
        method.setAccessible(true);
        boolean isInputTimeValid = (Boolean)method.invoke(timeConverter, "20:14:14");
        assertThat(isInputTimeValid, equalTo(true));
    }

    @Test
    public void testCheckTimeFieldsValidityReturnFalseForInvalidTimeFormat() throws Exception
    {
        TimeConverterImpl timeConverter = new TimeConverterImpl();
        Method method = timeConverter.getClass().getDeclaredMethod("checkTimeFieldsValidity", String.class);
        method.setAccessible(true);
        boolean isInputTimeValid = (Boolean)method.invoke(timeConverter, "Two:14");
        assertThat(isInputTimeValid, equalTo(false));
    }

    @Test
    public void testCheckTimeFieldsValidityReturnFalseForIncompleteTimeFormat() throws Exception
    {
        TimeConverterImpl timeConverter = new TimeConverterImpl();
        Method method = timeConverter.getClass().getDeclaredMethod("checkTimeFieldsValidity", String.class);
        method.setAccessible(true);
        boolean isInputTimeValid = (Boolean)method.invoke(timeConverter, "20:14");
        assertThat(isInputTimeValid, equalTo(false));
    }

    @Test
    public void testIsInputBetweenTheLimitReturnsFalseForInValidInput() throws Exception
    {
        TimeConverterImpl timeConverter = new TimeConverterImpl();
        Method method = timeConverter.getClass().getDeclaredMethod("isInputBetweenTheLimit", int.class, int.class, int.class);
        method.setAccessible(true);
        boolean isInputBetweenTheLimit = (Boolean)method.invoke(timeConverter, 3, 1, 2);
        assertThat(isInputBetweenTheLimit, equalTo(false));
    }

    @Test
    public void testIsInputBetweenTheLimitReturnsTrueForValidInput() throws Exception
    {
        TimeConverterImpl timeConverter = new TimeConverterImpl();
        Method method = timeConverter.getClass().getDeclaredMethod("isInputBetweenTheLimit", int.class, int.class, int.class);
        method.setAccessible(true);
        boolean isInputBetweenTheLimit = (Boolean)method.invoke(timeConverter, 2, 1, 3);
        assertThat(isInputBetweenTheLimit, equalTo(true));
    }
}
