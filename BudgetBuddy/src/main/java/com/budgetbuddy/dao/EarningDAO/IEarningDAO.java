package com.budgetbuddy.dao.EarningDAO;
import com.budgetbuddy.dto.Earning;

import java.util.List;

public interface IEarningDAO {
    //Earning management
    Earning save(Earning earning);
    void deleteEarning(long id);
    Double getEarning();

}
