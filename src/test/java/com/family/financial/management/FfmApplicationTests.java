package com.family.financial.management;

import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountForm;
import com.family.financial.management.model.ConditionForm;
import com.family.financial.management.model.DefiniteAccount;
import com.family.financial.management.service.interfaces.AccountService;
import com.family.financial.management.service.interfaces.UpdateAllAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FfmApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private AccountService accountService;

	@Autowired
	private UpdateAllAccountService updateAllAccountService;
	@Test
	public void account() {
		AccountForm accountForm = new AccountForm();
		accountForm.setId((long) 2);
		accountForm.setAccountNum((long) 1);
		accountForm.setDescription("good");
		accountForm.setGmtCreate(new Date());
		accountForm.setType((long) 2);
		accountForm.setIncome((long) 1);
		accountForm.setSpending((long) 0);
		try {
			List<DefiniteAccount> accounts = accountService.getAccountList(11,null,null,2,0);
		} catch (FFMException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void update() {
		updateAllAccountService.updateAllAccount((long) 1);

	}

	@Test
	public void condition() throws FFMException {
		ConditionForm conditionForm = new ConditionForm();
		conditionForm.setFromDate("2017-10-13 16:50:44");
		conditionForm.setToDate("2017-10-13 16:50:46");
		conditionForm.setLimit(String.valueOf(10));
		conditionForm.setOffset(String.valueOf(0));
		conditionForm.setTypes("99999");
		conditionForm.setMaxAccount("2");
		conditionForm.setMinAccount("0");
		accountService.getByConditions(11,conditionForm);

	}

}
