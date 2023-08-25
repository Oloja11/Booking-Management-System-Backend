package com.booking;

import com.booking.data.exceptions.BookingMgtException;
import com.booking.data.model.Booking;
import com.booking.data.model.BookingStatus;
import com.booking.data.model.SecureUser;
import com.booking.data.model.ServiceOffering;
import com.booking.data.repository.BookingRepo;
import com.booking.data.repository.ServiceOfferingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceOfferingAdapter {
    private final ServiceOfferingRepository serviceOfferingRepository;
    private final ModelMapper modelMapper;
    private final BusinessService businessService;
    private final BookingRepo bookingRepo;


    public String createServiceOffering(ServiceOfferingRequest serviceOfferingRequest, SecureUser secureUser) throws BookingMgtException {
        ServiceOffering serviceOffering = modelMapper.map(serviceOfferingRequest, ServiceOffering.class);
        log.info("Service Offering: {}", serviceOffering);
        serviceOffering.setBusinessId(businessService.getBusiness(secureUser.getAppUser().getId()).getId());
        log.info("set businness id Service Offering: {}", serviceOffering);
        serviceOfferingRepository.save(serviceOffering);
        return "Service Offering created successfully";
    }

    public Page<ServiceOffering> getAllServiceOffering(int page, int size) {
        return serviceOfferingRepository.findAll(PageRequest.of(page, size));
    }

    public String bookServiceOffering(String serviceId, SecureUser secureUser) {
        if (!serviceOfferingRepository.existsById(serviceId)) {
            throw new RuntimeException("Service Offering not found");
        }
        Booking booking = new Booking();
        booking.setUserEmail(secureUser.getAppUser().getEmail());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setServiceOfferingId(serviceId);
        bookingRepo.saveAndFlush(booking);
        return "Service Offering booked successfully";
    }

    public String acceptBooking(String serviceId, String userEmail) {
        toggleStatus(serviceId, userEmail, BookingStatus.ACCEPTED);
        return "Booking accepted successfully";
    }

    private void toggleStatus(String serviceId, String userEmail, BookingStatus status) {
        if (!serviceOfferingRepository.existsById(serviceId)) {
            throw new RuntimeException("Service Offering not found");
        }
        List<Booking> bookings = bookingRepo.findByUserEmailAndServiceOfferingId(userEmail, serviceId);
        if (bookings.isEmpty()) {
            throw new RuntimeException("Booking not found");
        }
        Booking booking = bookings.get(0);
        booking.setBookingStatus(status);
        bookingRepo.saveAndFlush(booking);
    }

    public String cancelBooking(String serviceId, String userEmail) {
        toggleStatus(serviceId, userEmail, BookingStatus.REJECTED);
        return "Booking canceled successfully";
    }

    public Page<Booking> getAllServiceOfferingByUserEmail(String userEmail, int page, int size) {

        return bookingRepo.findAllByUserEmail(userEmail, PageRequest.of(page, size));

    }
}
