package com.booking;

import com.booking.data.exceptions.BookingMgtException;

public interface BusinessService {

    String createBusiness(BusinessRequest businessRequest) throws BookingMgtException;
}