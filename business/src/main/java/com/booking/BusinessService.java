package com.booking;

import com.booking.data.exceptions.BookingMgtException;
import com.booking.data.model.Business;

public interface BusinessService {

    String createBusiness(BusinessRequest businessRequest) throws BookingMgtException;



    Business getBusiness(String businessId) throws BookingMgtException;
}