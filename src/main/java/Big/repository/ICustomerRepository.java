package Big.repository;

import Big.model.Customer;
import Big.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Page<Customer> findAllByProvince(Province province, Pageable pageable);
    Page<Customer> findAllByFirstNameContaining(String firstName, Pageable pageable);
}
