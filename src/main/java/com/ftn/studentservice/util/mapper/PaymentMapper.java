package com.ftn.studentservice.util.mapper;

import com.ftn.studentservice.model.Payment;
import com.ftn.studentservice.web.dto.PaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface PaymentMapper {

    @Mapping(target = "studentDTO", source = "student")
    PaymentDTO toDto(Payment payment);

    @Mapping(target = "student", source = "studentDTO")
    Payment toEntity(PaymentDTO paymentDTO);
}
