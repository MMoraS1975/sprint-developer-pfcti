package com.pfcti.sprintdevpfcti.service;

import com.pfcti.sprintdevpfcti.repository.InversionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InversionService {
    private InversionRepository inversionRepository;
}
