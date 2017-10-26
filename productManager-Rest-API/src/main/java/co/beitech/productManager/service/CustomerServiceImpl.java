package co.beitech.productManager.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.beitech.productManager.dao.CustomerDAO;
import co.beitech.productManager.model.Customer;


@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{

	
	
	@Autowired
	private CustomerDAO _customerDao;
	
	
	public List<Customer> getCustomers(){
		return _customerDao.getCustomers();
	}
	
 
	
}
