package com.kosenkovps.benefitcafeteria.repository;

import com.kosenkovps.benefitcafeteria.model.PurchaseHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseHistoryRepository extends CrudRepository<PurchaseHistory, Long> {
    @Query(value = "select * from purchase_history p where p.employee = :employeeId and p.actual", nativeQuery = true)
    Iterable<PurchaseHistory> getHistoryById(Long employeeId);
}
