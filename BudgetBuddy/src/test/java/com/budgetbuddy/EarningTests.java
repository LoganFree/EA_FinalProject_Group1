package com.budgetbuddy;

import com.budgetbuddy.dao.EarningDAO.IEarningDAO;
import com.budgetbuddy.dao.EarningDAO.EarningRepository;
import com.budgetbuddy.dto.Earning;
import com.budgetbuddy.service.EarningService.EarningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EarningTests {

    @InjectMocks
    private EarningService earningService;

    @Mock
    private IEarningDAO earningDAO;

    @Mock
    private EarningRepository earningRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEarning() {
        Earning earning = new Earning();
        earning.setEarnID(1);
        earning.setEarnAmount(1000.0);

        when(earningDAO.save(any(Earning.class))).thenReturn(earning);

        Earning savedEarning = earningService.save(earning);

        assertNotNull(savedEarning);
        assertEquals(1, savedEarning.getEarnID());
        assertEquals(1000.0, savedEarning.getEarnAmount());
    }

    @Test
    public void testDeleteEarning() {
        int id = 1;

        doNothing().when(earningDAO).deleteEarning(id);

        earningService.deleteEarning(id);

        verify(earningDAO, times(1)).deleteEarning(id);
    }

    @Test
    public void testGetAllEarnings() {
        List<Earning> earnings = new ArrayList<>();
        Earning earning1 = new Earning();
        earning1.setEarnID(1);
        earning1.setEarnAmount(1000.0);

        Earning earning2 = new Earning();
        earning2.setEarnID(2);
        earning2.setEarnAmount(2000.0);

        earnings.add(earning1);
        earnings.add(earning2);

        when(earningDAO.getAllEarnings()).thenReturn(earnings);

        List<Earning> allEarnings = earningService.getAllEarnings();

        assertNotNull(allEarnings);
        assertEquals(2, allEarnings.size());
        assertEquals(1000.0, allEarnings.get(0).getEarnAmount());
        assertEquals(2000.0, allEarnings.get(1).getEarnAmount());
    }

    @Test
    public void testGetMostRecentEarning() {
        Earning recentEarning = new Earning();
        recentEarning.setEarnID(3);
        recentEarning.setEarnAmount(3000.0);

        when(earningRepository.findMostRecentEarning()).thenReturn(recentEarning);

        Earning foundEarning = earningService.getMostRecentEarning();

        assertNotNull(foundEarning);
        assertEquals(3, foundEarning.getEarnID());
        assertEquals(3000.0, foundEarning.getEarnAmount());
    }
}
