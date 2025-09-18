package com.ldb.truck.Service.Vee;

import com.ldb.truck.Dao.TestDao.TestDaoQuery;
import com.ldb.truck.Model.testModel.ItemTestModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private final TestDaoQuery testDaoQuery;

    public TestService(TestDaoQuery testDaoQuery) {
        this.testDaoQuery = testDaoQuery;
    }

    public List<ItemTestModel> getBranchSummary() {
        return testDaoQuery.getBranchSummary();
    }
}
