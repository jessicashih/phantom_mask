package kdan.jessica.phantommask.json;

import kdan.jessica.phantommask.json.model.PharmacyJson;
import kdan.jessica.phantommask.repository.entity.Pharmacy;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Getter
@Setter
public class JsonToPharmacyConvertor {
    private final String continueDayRegex ="\\w{3} - \\w{3} \\d{2}:\\d{2} - \\d{2}:\\d{2}";
    private final String twoDayRegex ="\\w{3}, \\w{3} \\d{2}:\\d{2} - \\d{2}:\\d{2}";
    private final String oneDayRegex ="\\w{3} \\d{2}:\\d{2} - \\d{2}:\\d{2}";

    public final String openingHours;
    public final Pharmacy pharmacy;

    public JsonToPharmacyConvertor(PharmacyJson json) {
        this.openingHours = json.getOpeningHours();
        pharmacy=new Pharmacy();
        pharmacy.setName(json.getName());
        pharmacy.setBalance(json.getCashBalance());
    }

    public Pharmacy convert() {
        String[] openingHoursArr = openingHours.split("/");
        for (String openingHour : openingHoursArr) {
            openingHour = openingHour.trim();
            if (openingHour.matches(continueDayRegex)) {
                log.info("OpeningHours patter of continue days:{}", openingHour);
                this.patternOfContinueDay(openingHour);
            } else if (openingHour.matches(twoDayRegex)) {
                log.info("OpeningHours patter of two days:{}", openingHour);
                this.patternOfTwoDays(openingHour);
            } else if (openingHour.matches(oneDayRegex)) {
                log.info("OpeningHours patter of one day:{}", openingHour);
                this.patternOfOneDay(openingHour);
            } else {
                log.error("OpeningHours can't find pattern:{}",openingHour);
                throw new RuntimeException("OpeningHours can't find pattern:"+openingHour);
            }
        }
        return this.pharmacy;
    }

    private void patternOfOneDay(final String openingHour){
        String dayRegex = "\\w{3}";
        String timeRegex = "\\d{2}:\\d{2} - \\d{2}:\\d{2}";
        Pattern pattern = Pattern.compile(dayRegex);
        Matcher matcher = pattern.matcher(openingHour);
        String day = null;
        if (matcher.find()) {
            day = matcher.group();
        }

        String[] times = this.split(timeRegex, openingHour," - ");
        String openTime = times[0];
        String closeTime = times[1];

        assignValueToPharmacy(day,parseTime(openTime),parseTime(closeTime));
    }
    private void patternOfTwoDays(final String openingHour){
        String dayRegex = "\\w{3}, \\w{3}";
        String timeRegex = "\\d{2}:\\d{2} - \\d{2}:\\d{2}";
        String[] days = this.split(dayRegex, openingHour,", ");
        String day1 = days[0];
        String day2 = days[1];

        String[] times = this.split(timeRegex, openingHour," - ");
        String openTime = times[0];
        String closeTime = times[1];

        assignValueToPharmacy(day1,parseTime(openTime),parseTime(closeTime));
        assignValueToPharmacy(day2,parseTime(openTime),parseTime(closeTime));
    }

    private void patternOfContinueDay(final String openingHour) {
        String dayRegex = "\\w{3} - \\w{3}";
        String timeRegex = "\\d{2}:\\d{2} - \\d{2}:\\d{2}";

        String[] days = this.split(dayRegex, openingHour," - ");
        String startDate = days[0];
        String endDate = days[1];

        String[] times = this.split(timeRegex, openingHour," - ");
        String openTime = times[0];
        String closeTime = times[1];

        assignValueToPharmacy(startDate, parseTime(openTime), LocalTime.MAX);
        assignValueToPharmacy(endDate, LocalTime.MIN, parseTime(closeTime));

    }

    private LocalTime parseTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
    }

    private String[] split(final String regex, final String openingHour,String splitStr) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(openingHour);

        String[] strs = new String[0];
        if (matcher.find()) {
            strs = matcher.group().split(splitStr);
        } else {
            throw new RuntimeException("Not Fond Patter:{}"+ openingHour);
        }
        return strs;
    }

    private void assignValueToPharmacy(String day, LocalTime open, LocalTime close) {
        switch (day) {
            case "Mon":
                pharmacy.setMonOpen(open);
                pharmacy.setMonClose(close);
                break;
            case "Tue":
                pharmacy.setTueOpen(open);
                pharmacy.setTueClose(close);
                break;
            case "Wed":
                pharmacy.setWedOpen(open);
                pharmacy.setWedClose(close);
                break;
            case "Thu":
                pharmacy.setThuOpen(open);
                pharmacy.setTueClose(close);
                break;
            case "Fri":
                pharmacy.setFriOpen(open);
                pharmacy.setFriClose(close);
                break;
            case "Sat":
                pharmacy.setSatOpen(open);
                pharmacy.setSatClose(close);
                break;
            case "Sun":
                pharmacy.setSunOpen(open);
                pharmacy.setSunClose(close);
                break;
        }
    }
}
