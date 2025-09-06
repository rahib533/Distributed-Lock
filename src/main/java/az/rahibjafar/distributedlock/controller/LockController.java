package az.rahibjafar.distributedlock.controller;

import az.rahibjafar.distributedlock.service.LockedWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Recalculation")
@RestController
@RequiredArgsConstructor
public class LockController {
    private final LockedWorkService service;

    @Operation(summary = "Run critical recalculation", description = "Operation protected with a distributed lock")
    @GetMapping("/recalc/{key}")
    public String recalc(@PathVariable String key) throws InterruptedException {
        return service.doCriticalWork(key);
    }
}
