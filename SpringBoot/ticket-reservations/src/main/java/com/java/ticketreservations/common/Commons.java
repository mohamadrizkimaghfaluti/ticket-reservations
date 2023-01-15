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

    public static Character checkStatusActive(Character activeStatus) throws Exception {
        Character status;
        if (activeStatus.equals(Constants.ACTIVE_STATUS)){
            status = Constants.ACTIVE_STATUS;
        } else if (activeStatus.equals(Constants.INACTIVE_STATUS)){
            status = Constants.INACTIVE_STATUS;
        } else {
            throw new Exception("Writing 'Active Status' is Incorrect");
        }
        return status;
    }

}
