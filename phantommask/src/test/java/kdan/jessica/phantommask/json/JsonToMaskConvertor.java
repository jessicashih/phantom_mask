package kdan.jessica.phantommask.json;

import kdan.jessica.phantommask.repository.entity.Mask;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class JsonToMaskConvertor {
    private final String maskNameRegex =".* \\(\\w*\\) \\(\\d{1,} per pack\\)";
    private final String maskSpitStr =" \\(";
    private final String maskFullName;

    private final Mask mask;

    public JsonToMaskConvertor(String maskFullName) {
        this.maskFullName = maskFullName;
        this.mask=new Mask();
    }

    public Mask convert(){
        if(maskFullName.matches(maskNameRegex)){
            String[] maskInfo = maskFullName.split(maskSpitStr);
            this.mask.setName(maskInfo[0]);
            this.mask.setColor(maskInfo[1].replace(")",""));
            String numOfPack = maskInfo[2].replace(" per pack)","");
            this.mask.setNumOfPack(Integer.valueOf(numOfPack));
        }else {
            log.error("mask name not match");
            throw new RuntimeException("mask name not match");
        }
        return this.mask;
    }



}
