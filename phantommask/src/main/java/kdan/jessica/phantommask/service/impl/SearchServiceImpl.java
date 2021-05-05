package kdan.jessica.phantommask.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kdan.jessica.phantommask.model.MaskRs;
import kdan.jessica.phantommask.model.PharmacyRs;
import kdan.jessica.phantommask.model.SearchRs;
import kdan.jessica.phantommask.repository.entity.Mask;
import kdan.jessica.phantommask.repository.entity.Pharmacy;
import kdan.jessica.phantommask.repository.service.MaskDbService;
import kdan.jessica.phantommask.repository.service.PharmacyDbService;
import kdan.jessica.phantommask.service.SearchService;
import kdan.jessica.phantommask.service.ex.RequestInputException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    @Autowired
    private PharmacyDbService pharmacyDbService;
    @Autowired
    private MaskDbService maskDbService;

    @Override
    public SearchRs search(String searchString) {
        log.info("search Start");
        if (StringUtils.isEmpty(searchString)) {
            throw new RequestInputException("SearchString can't be null, Please check input data.");
        }
        List<Pharmacy> allPharmacy = pharmacyDbService.findAll();
        List<Mask> allMask = maskDbService.findAll();

        List<Pharmacy> filterAndSortPharmacy = allPharmacy.stream()
                .filter(p -> p.getName().contains(searchString))
                .sorted(new Comparator<Pharmacy>() {
                    @Override
                    public int compare(Pharmacy o1, Pharmacy o2) {
                        if (o1.getName().equals(searchString)) {
                            return 0;
                        } else if (levenshtein(o1.getName(), searchString) > levenshtein(o2.getName(), searchString)) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                })
                .collect(Collectors.toList());

        List<Mask> filterAndSortMask = allMask.stream()
                .filter(p -> p.getName().contains(searchString))
                .sorted(new Comparator<Mask>() {
                    @Override
                    public int compare(Mask o1, Mask o2) {
                        if (o1.getName().equals(searchString)) {
                            return 0;
                        } else if (levenshtein(o1.getName(), searchString) > levenshtein(o2.getName(), searchString)) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                })
                .collect(Collectors.toList());

        SearchRs response = new SearchRs();
        List<PharmacyRs> pharamcySearchResult = new ArrayList<>();
        filterAndSortPharmacy.stream().forEach(p -> {
            PharmacyRs pharmacyRs = new PharmacyRs();
            pharmacyRs.setName(p.getName());
            pharmacyRs.setSeqNo(p.getSeqNo());
            pharamcySearchResult.add(pharmacyRs);
        });
        List<MaskRs> maskSearchResult = new ArrayList<>();
        filterAndSortMask.stream()
                .map(m -> m.getName())
                .collect(Collectors.toSet())
                .forEach(p -> {
                    MaskRs maskRs = new MaskRs();
                    maskRs.setName(p);
                    maskSearchResult.add(maskRs);
                });
        response.setPharmacyRsList(pharamcySearchResult);
        response.setMaskRsList(maskSearchResult);
        log.info("search End");
        return response;
    }

    /**
     * 計算字串相似程度
     *
     * @param str1 字串1
     * @param str2 字串2
     * @return 相似程度
     */
    private float levenshtein(String str1, String str2) {
//      計算兩個字串的長度。
        int len1 = str1.length();
        int len2 = str2.length();

//      建立上面說的陣列，比字元長度大一個空間
        int[][] dif = new int[len1 + 1][len2 + 1];

//      賦初值，步驟B。
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
//      計算兩個字元是否一樣，計算左上的值
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    temp = 0;
                } else {
                    temp = 1;
                }
//              取三個值中最小的
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                        dif[i - 1][j] + 1);
            }
        }
        float similarity = 1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
        log.debug("String【{}}】and 【{}}】is mimilarity with ：{}%", str1, str2, similarity * 100);

        return similarity;
    }

    //得到最小值
    private static int min(int... is) {
        int min = Integer.MAX_VALUE;
        for (int i : is) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }

}
