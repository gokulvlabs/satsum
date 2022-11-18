package com.satsum.util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

class UserVisit {

    String date; //eg. “2022-11-10”
    int userId; //eg. 1

    public UserVisit(String date, int userId) {
        this.date = date;
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVisit userVisit = (UserVisit) o;
        return userId == userVisit.userId && date.equals(userVisit.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, userId);
    }
}

public class Main {

    public static void main(String[] argv) throws IOException {

        List<UserVisit> userVisitList = getUserVisits();

        LocalDate date = LocalDate.parse("2022-11-10");

        String yesterdayDate = String.valueOf(date.minusDays(1));

        String dayBeforeYesterdayDate = String.valueOf(date.minusDays(2));

        System.out.println("yesterdayDate: " + yesterdayDate + " dayBeforeYesterdayDate: " + dayBeforeYesterdayDate);

        Set<Integer> yesterday = userVisitList.stream().filter(x ->
                x.date.equals(yesterdayDate)).map(UserVisit::getUserId).collect(Collectors.toSet());

        int size = userVisitList.stream().
                filter(x -> x.date.equals(dayBeforeYesterdayDate))
                .filter(x -> !yesterday.contains(x.userId))
                .collect(Collectors.toSet()).size();

        System.out.println("active user count:" + size);

    }

    private static List<UserVisit> getUserVisits() {
        List<UserVisit> userVisitList = new ArrayList<>();

        userVisitList.add(new UserVisit("2022-11-10", 1));
        userVisitList.add(new UserVisit("2022-11-10", 1));
        userVisitList.add(new UserVisit("2022-11-10", 2));
        userVisitList.add(new UserVisit("2022-11-10", 3));
        userVisitList.add(new UserVisit("2022-11-10", 2));
        userVisitList.add(new UserVisit("2022-11-10", 3));
        userVisitList.add(new UserVisit("2022-11-10", 1));
        userVisitList.add(new UserVisit("2022-11-09", 4));
        userVisitList.add(new UserVisit("2022-11-09", 5));
        userVisitList.add(new UserVisit("2022-11-09", 6));
        userVisitList.add(new UserVisit("2022-11-09", 2));
        userVisitList.add(new UserVisit("2022-11-09", 3));
        userVisitList.add(new UserVisit("2022-11-09", 5));
        userVisitList.add(new UserVisit("2022-11-08", 7));
        userVisitList.add(new UserVisit("2022-11-08", 8));
        userVisitList.add(new UserVisit("2022-11-08", 9));
        userVisitList.add(new UserVisit("2022-11-08", 3));
        userVisitList.add(new UserVisit("2022-11-08", 6));
        userVisitList.add(new UserVisit("2022-11-08", 8));
        userVisitList.add(new UserVisit("2022-11-07", 2));
        userVisitList.add(new UserVisit("2022-11-07", 4));
        userVisitList.add(new UserVisit("2022-11-07", 3));
        userVisitList.add(new UserVisit("2022-11-07", 2));
        userVisitList.add(new UserVisit("2022-11-07", 4));
        userVisitList.add(new UserVisit("2022-11-07", 1));
        return userVisitList;
    }
}




