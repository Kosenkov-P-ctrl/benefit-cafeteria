package com.kosenkovps.benefitcafeteria.service;

import com.kosenkovps.benefitcafeteria.models.Employee;
import com.kosenkovps.benefitcafeteria.models.Product;
import com.kosenkovps.benefitcafeteria.models.PurchaseHistory;
import com.kosenkovps.benefitcafeteria.repositiry.EmployeeRepository;
import com.kosenkovps.benefitcafeteria.repositiry.ProductRepository;
import com.kosenkovps.benefitcafeteria.repositiry.PurchaseHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static com.kosenkovps.benefitcafeteria.models.Role.PERSONAL_OFFICER;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;

    public BigDecimal getCurrentBalance(Long id){
        return employeeRepository.getCurrentBalanceById(id);
    }

    @Transactional
    public void addToBalance(Long idSender, Long idTaker, BigDecimal benefit){
        Optional<Employee> sender = employeeRepository.findById(idSender);
        Optional<Employee> taker = employeeRepository.findById(idTaker);
        if(sender.isPresent() && taker.isPresent()) {
            if (sender.get().getCompanyRole().equals(PERSONAL_OFFICER)) {
                BigDecimal takerNewBalance = taker.get().getBenefitBalance().add(benefit);
                employeeRepository.changeBenefitBalance(idTaker, takerNewBalance);
            } else if (benefit.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal takerNewBalance = taker.get().getBenefitBalance().add(benefit);
                BigDecimal senderNewBalance = sender.get().getBenefitBalance().subtract(benefit);

                employeeRepository.changeBenefitBalance(idSender, senderNewBalance);
                employeeRepository.changeBenefitBalance(idTaker, takerNewBalance);
            }
        }
    }

    @Transactional
    public void subtractToBalance(Long idSender, Long idTaker, BigDecimal benefit){
        Optional<Employee> sender = employeeRepository.findById(idSender);
        Optional<Employee> taker = employeeRepository.findById(idSender);
        if(sender.isPresent() && taker.isPresent()) {
            if (sender.get().getCompanyRole().equals(PERSONAL_OFFICER)) {
                BigDecimal takerNewBalance = taker.get().getBenefitBalance().add(benefit);
                employeeRepository.changeBenefitBalance(idTaker, takerNewBalance);
            }
        }
    }

    public Iterable<PurchaseHistory> getActualProduct(Long id){
        return purchaseHistoryRepository.getHistoryById(id);
    }


    @Transactional
    public boolean buyProduct(Long employeeId, Long productId){
        Optional<Employee> buyer = employeeRepository.findById(employeeId);
        Optional<Product> product = productRepository.findById(productId);
        if(buyer.isPresent() && product.isPresent()){
            if(buyer.get().getBenefitBalance().subtract(product.get().getPrice()).compareTo(BigDecimal.ZERO) < 0){
                return false;
            }else{
                employeeRepository.changeBenefitBalance(employeeId, buyer.get().getBenefitBalance().subtract(product.get().getPrice()));
                return true;
            }
        }
        return false;
    }
}
