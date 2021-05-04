package kdan.jessica.phantommask.mockdata;

import kdan.jessica.phantommask.repository.entity.Mask;

public class MockMask {

    public static Mask mock(){
        Mask mockMask = new Mask();
        mockMask.setNumOfPack(1);
        mockMask.setColor("blue");
        mockMask.setName("MockMask");
        return mockMask;
    }
}
