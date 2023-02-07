package com.kosenkovps.benefitcafeteria.repositiry;

import com.kosenkovps.benefitcafeteria.models.PurchaseHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseHistoryRepository extends CrudRepository<PurchaseHistory, Long> {
    @Query(value = "select p from PurchaseHistory p where p.employee = :employeeId and p.actual")
    Iterable<PurchaseHistory> getHistoryById(Long employeeId);
}
