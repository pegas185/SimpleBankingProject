package com.wrobank.banking.account.services;

import com.wrobank.banking.customer.domain.CustomerDetails;

public interface AccountDetailsService {

    CustomerDetails queryAccountDetailsFor(Integer customerId);


}
