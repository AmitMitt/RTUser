package com.roadTransport.RTUser.walletService;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "WalletService", url = "http://localhost:8020/wallet/")
public interface WalletService {

    @PostMapping("/add")
    @Headers("Content-Type: application/json")
    public ResponseEntity<WalletResponse> add(@RequestBody WalletRequest walletRequest);

    @DeleteMapping("delete/{walletId}")
    @Headers("Content-Type: application/json")
    public ResponseEntity<WalletResponse> delete(@PathVariable("walletId") long walletId);

    @PutMapping("updateBalance/{walletId}")
    @Headers("Content-Type: application/json")
    public ResponseEntity<WalletResponse> updateBalance(@RequestBody WalletRequest walletRequest);

    @GetMapping("getBalance/{walletId}")
    @Headers("Content-Type: application/json")
    public ResponseEntity<WalletResponse> getBalance(@PathVariable("walletId") long walletId);

    @PutMapping("updateWalletPin")
    @Headers("Content-Type: application/json")
    public ResponseEntity<WalletResponse> updatePin(@RequestBody WalletPinRequest walletRequest);

    @PutMapping("/update")
    @Headers("Content-Type: application/json")
    public ResponseEntity<WalletResponse> update (@RequestBody WalletRequest walletRequest);

}
