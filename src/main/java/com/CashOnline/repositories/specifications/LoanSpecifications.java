package com.CashOnline.repositories.specifications;

import com.CashOnline.dto.FilterLoanDto;
import com.CashOnline.model.Loan;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
public class LoanSpecifications {

    public static Specification<Loan> byFilter(FilterLoanDto filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getUserId().isPresent()) {
                predicates.add(cb.equal(root.get("user"), filter.getUserId().get()));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
