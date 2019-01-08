package com.ubs.opsit.interviews;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Created by Vikash.Priyadarshi on 1/4/2019.
 */
public class TimeConverterImpl implements TimeConverter {

    private static final Logger LOG = LoggerFactory.getLogger(TimeConverterImpl.class);
    private static final String timeFieldSeparator = ":";
    private static final char yellowColoredLamp = 'Y';
    private static final char redColoredLamp = 'R';
    private static final char switchedOffLamp = 'O';
    private static final int totalNumberOfLampsInHourRows = 4;
    private static final int totalNumberOfLampsInFirstMinuteRow = 11;
    private static final int totalNumberOfLampsInSecondMinuteRow = 4;
    private static final String newLine = System.getProperty("line.separator");
    private static final String messageForInValidTimeInput = "Input time message is invalid";

    public String convertTime(String aTime)
    {
        StringBuilder berlinClockFormatBuilder = new StringBuilder();
        LOG.info("Entering into public String convertTime(String aTime).."+aTime);
        if (isInputTimeValid(aTime))
        {
            int hourMinuteFieldSeparatorIdx = aTime.indexOf(timeFieldSeparator);
            int hours = Integer.parseInt(aTime.substring(0, hourMinuteFieldSeparatorIdx));
            int minuteSecondFieldSeparatorIdx = aTime.indexOf(timeFieldSeparator, hourMinuteFieldSeparatorIdx + 1);
            int minutes = Integer.parseInt(aTime.substring(hourMinuteFieldSeparatorIdx + 1, minuteSecondFieldSeparatorIdx));
            int seconds = Integer.parseInt(aTime.substring(minuteSecondFieldSeparatorIdx + 1, aTime.length()));
            berlinClockFormatBuilder.append(getFirstRowOfBerlinClock(seconds))
                    .append(newLine)
                    .append(getSecondRowOfBerlinClock(hours))
                    .append(newLine)
                    .append(getThirdRowOfBerlinClock(hours))
                    .append(newLine)
                    .append(getFourthRowOfBerlinClock(minutes))
                    .append(newLine)
                    .append(getFifthRowOfBerlinClock(minutes));
        }
        else
        {
            berlinClockFormatBuilder.append(messageForInValidTimeInput);
        }
        LOG.info("Leaving public String convertTime(String aTime).."+berlinClockFormatBuilder);
        return berlinClockFormatBuilder.toString();
    }

    private String getFirstRowOfBerlinClock(int seconds)
    {
        String firstRowOfBerlinClock = "O";
        if((seconds % 2) == 0)
        {
            firstRowOfBerlinClock = "Y";
        }
        return firstRowOfBerlinClock;
    }

    private String getSecondRowOfBerlinClock(int hours)
    {
        int numberOfRedLamps = hours / 5;
        return prepareRowOfBerlinClock(redColoredLamp, numberOfRedLamps, totalNumberOfLampsInHourRows)
               .toString();
    }

    private String getThirdRowOfBerlinClock(int hours)
    {
        int numberOfRedLamps = hours % 5;
        return prepareRowOfBerlinClock(redColoredLamp, numberOfRedLamps, totalNumberOfLampsInHourRows)
                .toString();
    }

    private String getFourthRowOfBerlinClock(int minutes)
    {
        int numberOfYellowLamps = minutes / 5;
        StringBuilder rowBuilderForBerlinClock = prepareRowOfBerlinClock(yellowColoredLamp, numberOfYellowLamps, totalNumberOfLampsInFirstMinuteRow);
        for(int redLampNumber = 2; redLampNumber < numberOfYellowLamps; redLampNumber = redLampNumber + 3)
        {
            rowBuilderForBerlinClock.replace(redLampNumber, redLampNumber + 1, "R");
        }
        return rowBuilderForBerlinClock.toString();
    }

    private String getFifthRowOfBerlinClock(int minutes)
    {
        int numberOfYellowLamps = minutes % 5;
        return prepareRowOfBerlinClock(yellowColoredLamp, numberOfYellowLamps, totalNumberOfLampsInSecondMinuteRow)
                .toString();
    }

    private StringBuilder prepareRowOfBerlinClock(char coloredLamp, int numberOfColoredLamps, int totalNumberOfLampsInARow)
    {
        StringBuilder rowBuilderForBerlinClock = new StringBuilder();
        return rowBuilderForBerlinClock.append(getStringOfGivenCharacterAndLength(coloredLamp, numberOfColoredLamps))
                .append(getStringOfGivenCharacterAndLength(switchedOffLamp, totalNumberOfLampsInARow - numberOfColoredLamps));
    }

    private String getStringOfGivenCharacterAndLength(char inputChar, int lengthOfOutput) {
        StringBuilder outputBuilder = new StringBuilder();
        for(int count = 0; count < lengthOfOutput; count++)
        {
            outputBuilder.append(inputChar);
        }
        return outputBuilder.toString();
    }

    private boolean isInputTimeValid(String aTime)
    {
        LOG.info("Entering into private boolean isInputTimeValid(String aTime).."+aTime);
        boolean isInputTimeValid = false;
        if(Objects.nonNull(aTime) && !aTime.isEmpty())
        {
            if(aTime.contains(timeFieldSeparator))
            {
                isInputTimeValid = checkTimeFieldsValidity(aTime);
            }
        }
        LOG.info("Leaving private boolean isInputTimeValid(String aTime).."+isInputTimeValid);
        return isInputTimeValid;
    }

    private boolean checkTimeFieldsValidity(String aTime)
    {
        LOG.info("Entering into private boolean checkTimeFieldsValidity(String aTime).."+aTime);
        boolean isTimeFieldsValid = false;
        StringTokenizer timeFieldTokenizer = new StringTokenizer(aTime, timeFieldSeparator);
        try
        {
            if (timeFieldTokenizer.countTokens() == 3)
            {
                int hour = Integer.parseInt(timeFieldTokenizer.nextToken());
                int minute = Integer.parseInt(timeFieldTokenizer.nextToken());
                int second = Integer.parseInt(timeFieldTokenizer.nextToken());
                isTimeFieldsValid = isInputBetweenTheLimit(hour, 0, 24) && isInputBetweenTheLimit(minute, 0, 60) && isInputBetweenTheLimit(second, 0, 60);
            }
        } catch(Exception ex)
        {
            LOG.info("Exception occured::"+ex.toString());
        }
        LOG.info("Leaving private boolean checkTimeFieldsValidity(String aTime).."+isTimeFieldsValid);
        return isTimeFieldsValid;
    }

    private boolean isInputBetweenTheLimit(int input, int lowerLimit, int upperLimit)
    {
        LOG.info("Entering into private boolean isInputBetweenTheLimit(int input, int lowerLimit, int upperLimit).." + input + " - " + lowerLimit + " - " + upperLimit);
        boolean isInputBetweenLimit = false;
        if(input >= lowerLimit && input <= upperLimit )
        {
            isInputBetweenLimit = true;
        }
        LOG.info("Leaving private boolean isInputBetweenTheLimit(int input, int lowerLimit, int upperLimit).."+isInputBetweenLimit);
        return isInputBetweenLimit;
    }
}
