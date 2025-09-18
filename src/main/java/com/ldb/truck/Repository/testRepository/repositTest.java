//package com.ldb.truck.Repository;
package com.ldb.truck.Repository.testRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface repositTest {
    @Query(value =
            "select " +
                    " br.B_NAME as branchName, " +
                    " i.houseid as houseId, " +
                    " h.khname as khname, " +
                    " b.b_name as borName, " +
                    " sum(case when i.currency = 'LAK' then i.price * i.qty else 0 end) as totalLAK, " +
                    " sum(case when i.currency = 'THB' then i.price * i.qty else 0 end) as totalTHB, " +
                    " sum(case when i.currency = 'USD' then i.price * i.qty else 0 end) as totalUSD " +
                    "from item_inventory i " +
                    "join stock_house h on i.houseid = h.khid " +
                    "left join tb_bors b on h.key_id = b.key_id " +
                    "left join TB_BRANCH br on b.brand_no = br.KEY_ID " +
                    "group by i.houseid, h.khname, b.b_name, br.B_NAME " +
                    "order by i.houseid ",
            nativeQuery = true)
    List<Object[]> getReportSummary();
}
