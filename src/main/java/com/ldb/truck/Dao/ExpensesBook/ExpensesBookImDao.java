package com.ldb.truck.Dao.ExpensesBook;

import java.util.List;
import com.ldb.truck.Model.Login.ExpensesBook.ExpenType;
import com.ldb.truck.Model.Login.ExpensesBook.ExpenTypeRes;
import com.ldb.truck.Model.Login.ExpensesBook.ExpenTypeReq;
import com.ldb.truck.Model.Login.ExpensesBook.ExpensesBook;
import com.ldb.truck.Model.Login.ExpensesBook.ExpensesBookRes;
import com.ldb.truck.Model.Login.ExpensesBook.ExpensesBookReq;
import com.ldb.truck.Model.Login.ResFromDateReq;

public interface ExpensesBookImDao {

    List<ExpenType>  ListExpensesType(ExpenTypeReq expenTypeReq);
    List<ExpenType>  ListExpensesTypeAll();
    public int storeExpensesType(ExpenTypeReq expenTypeReq);
    public int updateExpensesType(ExpenTypeReq expenTypeReq);
    public int delExpensesType(ExpenTypeReq expenTypeReq);

    List<ExpensesBook>  ListExpenses(ResFromDateReq expensesBookReq);
    List<ExpensesBook>  ListExpensesALL();
    public int storeExpenses(ExpensesBookReq expensesBookReq);
    public int updateExpenses(ExpensesBookReq expensesBookReq);
    public int delExpenses(ExpensesBookReq expensesBookReq);


}
