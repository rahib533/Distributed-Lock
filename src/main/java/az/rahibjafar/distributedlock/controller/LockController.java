package az.rahibjafar.distributedlock.controller;

import az.rahibjafar.distributedlock.service.LockedWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LockController {
    private final LockedWorkService service;

    @GetMapping("/recalc")
    public String recalc() throws InterruptedException {
        return service.doCriticalWork();
    }
}
