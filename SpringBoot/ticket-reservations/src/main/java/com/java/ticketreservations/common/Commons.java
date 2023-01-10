package com.java.ticketreservations.common;

import java.time.LocalDate;
import java.util.UUID;

public class Commons {

    public static String generateId(String id) {
        String resultId = null;
        if (id == null){
            resultId = UUID.randomUUID().toString();
        }
        return resultId;
    }

    public static LocalDate getToday(){
        LocalDate today = LocalDate.now();
        return today;
    }
}
