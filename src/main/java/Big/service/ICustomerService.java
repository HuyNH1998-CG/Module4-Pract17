package Big.service;

import Big.model.Customer;
import Big.model.Province;

public interface ICustomerService extends IGeneralService<Customer> {
Iterable<Customer> findAllByProvince(Province province);
}
