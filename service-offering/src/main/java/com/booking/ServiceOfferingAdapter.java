package com.booking;

import com.booking.data.exceptions.BookingMgtException;
import com.booking.data.model.SecureUser;
import com.booking.data.model.ServiceOffering;
import com.booking.data.repository.ServiceOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceOfferingAdapter {
    private final ServiceOfferingRepository serviceOfferingRepository;
    private final ModelMapper modelMapper;
    private final BusinessService businessService;


    public String createServiceOffering(ServceOfferingRequest serviceOfferingRequest) throws BookingMgtException {
        ServiceOffering serviceOffering = modelMapper.map(serviceOfferingRequest, ServiceOffering.class);
        serviceOffering.setBusinessId(businessService.getBusiness(serviceOfferingRequest.getBusinessId()).getId());
        serviceOfferingRepository.save(serviceOffering);
        return "Service Offering created successfully";
    }

    public Page<ServiceOffering> getAllServiceOffering(int page, int size) {
        return serviceOfferingRepository.findAll(PageRequest.of(page, size));
    }

    public String bookServiceOffering(String serviceId, SecureUser secureUser) throws BookingMgtException {
        ServiceOffering serviceOffering = serviceOfferingRepository.findById(serviceId).orElseThrow(
                () -> new BookingMgtException("Service Offering not found")
        );
        serviceOffering.getBookedBy().add(secureUser.getAppUser());
        serviceOfferingRepository.save(serviceOffering);
        return "Service Offering booked successfully";
    }
}
