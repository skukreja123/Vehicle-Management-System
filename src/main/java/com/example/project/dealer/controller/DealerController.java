package com.example.project.dealer.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dealer.dto.DealerRequest;
import com.example.project.dealer.dto.DealerResponse;
import com.example.project.dealer.service.DealerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dealers")
@RequiredArgsConstructor
public class DealerController {

    private final DealerService dealerService;

    @PostMapping
    public DealerResponse create(@Valid @RequestBody DealerRequest request) {
        return dealerService.create(request);
    }

    @GetMapping("/{id}")
    public DealerResponse get(@PathVariable UUID id) {
        return dealerService.get(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        dealerService.delete(id);
    }

    @PatchMapping("/{id}")
    public DealerResponse update(
            @PathVariable UUID id,
            @RequestBody DealerRequest request) {
        return dealerService.update(id, request);
    }

}
