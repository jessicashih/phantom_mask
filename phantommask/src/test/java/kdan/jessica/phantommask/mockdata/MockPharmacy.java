package kdan.jessica.phantommask.mockdata;

import kdan.jessica.phantommask.repository.entity.Pharmacy;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MockPharmacy {

    public static List<Pharmacy> mock() {
        List<Pharmacy> result = new ArrayList<>();
        Pharmacy pharmacy1 = new Pharmacy();
        pharmacy1.setBalance(BigDecimal.TEN);
        pharmacy1.setName("TestPharmacy1");
        pharmacy1.setMonOpen(LocalTime.of(11, 50));
        pharmacy1.setMonClose(LocalTime.of(15, 30));
        result.add(pharmacy1);

        Pharmacy pharmacy2 = new Pharmacy();
        pharmacy2.setBalance(BigDecimal.TEN);
        pharmacy2.setName("TestPharmacy2");
        pharmacy2.setTueOpen(LocalTime.of(11, 50));
        pharmacy2.setTueClose(LocalTime.of(18, 30));
        result.add(pharmacy2);

        Pharmacy pharmacy3 = new Pharmacy();
        pharmacy3.setBalance(BigDecimal.TEN);
        pharmacy3.setName("TestPharmacy3");
        pharmacy3.setTueOpen(LocalTime.of(11, 50));
        pharmacy3.setTueClose(LocalTime.of(14, 30));
        result.add(pharmacy3);
        return result;
    }
}
