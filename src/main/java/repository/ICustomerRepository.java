package repository;

import model.Customer;

public interface ICustomerRepository extends IGeneralRepository<Customer> {
    boolean insertWithStoredProcedure(Customer customer);
}
