package com.ldb.truck.Dao.TestDao;

import com.ldb.truck.Model.testModel.ItemTestModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TestDaoQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ItemTestModel> getBranchSummary() {
        String sql = "select " +
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
                "order by i.houseid ";

        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> results = query.getResultList();
        List<ItemTestModel> list = new ArrayList<>();

        for (Object[] row : results) {
            ItemTestModel item = new ItemTestModel(
                    (String) row[0],                       // branchName
                    String.valueOf(row[1]),                // houseId
                    (String) row[2],                       // khname
                    (String) row[3],                       // borName
                    row[4] != null ? ((Number) row[4]).doubleValue() : 0.0, // totalLAK
                    row[5] != null ? ((Number) row[5]).doubleValue() : 0.0, // totalTHB
                    row[6] != null ? ((Number) row[6]).doubleValue() : 0.0  // totalUSD
            );
            list.add(item);
        }

        return list;
    }
}
