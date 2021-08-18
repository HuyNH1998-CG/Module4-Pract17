package service;

import model.Customer;

public interface ICustomerService extends IGeneralService<Customer> {
    boolean insertWithStoredProcedure(Customer customer);
}
