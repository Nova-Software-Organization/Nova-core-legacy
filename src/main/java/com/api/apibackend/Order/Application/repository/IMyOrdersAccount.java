package com.api.apibackend.Order.Application.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface IMyOrdersAccount {
    ResponseEntity<?> getUserByEmail(@RequestParam String email);
}
