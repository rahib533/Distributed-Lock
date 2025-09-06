package az.rahibjafar.distributedlock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class LockedWorkService {
    private final RedissonClient redisson;

    public String doCriticalWork() throws InterruptedException {
        System.out.println(1);
        String lockName = "locks:price-recalc";
        RLock lock = redisson.getLock(lockName);
        System.out.println(2);

        // tryLock(waitTime, leaseTime): waitTime - nə qədər gözləsin, leaseTime - avtomatik açılma müddəti
        boolean acquired = lock.tryLock(5, 30, TimeUnit.SECONDS);
        System.out.println(3);
        if (!acquired) {
            log.warn("Lock əldə edilə bilmədi");
            return "Busy: lock is held";
        }

        try {
            log.info("Lock alındı, işə başlanır...");
            // Kritiki iş (simulyasiya)
            Thread.sleep(10_000);
            log.info("İş bitdi");
            return "OK";
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.info("Lock açıldı");
            }
        }
    }
}