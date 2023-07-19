package com.booking;

import com.booking.data.exceptions.BookingMgtException;
import com.booking.data.model.AppUser;
import com.booking.data.model.Business;
import com.booking.data.repository.BusinessRepository;
import com.booking.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    private final BusinessRepository businessRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    @Override
    public String createBusiness(BusinessRequest businessRequest) throws BookingMgtException {
        ensureUniqueVal(businessRequest);
        AppUser appUser = mapper.map(businessRequest, AppUser.class);
        userRepository.save(appUser);
        Business business = mapper.map(businessRequest, Business.class);
        business.setCreatorId(appUser.getId());
        businessRepository.save(business);
        return "Business created ";
    }

    private void ensureUniqueVal(BusinessRequest businessRequest) throws BookingMgtException {
        if (userRepository.existsByEmail(businessRequest.getEmail())) {
            throw new BookingMgtException("User already exists");
        }
        if (businessRepository.existsByName(businessRequest.getName())) {
            throw new BookingMgtException("Business already exists");
        }
    }
}
