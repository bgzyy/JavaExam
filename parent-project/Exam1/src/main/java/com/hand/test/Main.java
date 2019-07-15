package com.hand.test;

import com.hand.test.factory.NumberFactory;
import com.hand.test.factory.impl.GetNumberFactory;
import com.hand.test.factory.impl.NumberFactoryImpl;

import java.util.*;

/**
 * Created by zhao'yin
 * Date 2019/7/15.
 */
public class Main {
    public static void main(String[] args) {
        GetNumberFactory getNumberFactory = new GetNumberFactory();
        NumberFactory factory = getNumberFactory.getFactory(new NumberFactoryImpl(100));
        List<Integer> randomList = factory.randomNum();
        Map<Integer, List<Integer>> numMap = new HashMap<>();

        for (Integer num : randomList) {
            Integer key = num / 10;
            if (numMap.containsKey(key)) {
                numMap.get(key).add(num);
            } else {
                List<Integer> mapValList = new ArrayList<>();
                mapValList.add(num);
                numMap.put(key, mapValList);
            }
        }

        System.out.println("-------- 排序前 --------");

        Iterator<Map.Entry<Integer, List<Integer>>> iterator = numMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> listEntry = iterator.next();
            List<Integer> listVal = listEntry.getValue();
            System.out.print(listEntry.getKey());
            System.out.print(" [");
            for (int i = 0; i < listVal.size(); i++) {
                System.out.print(listVal.get(i) + ", ");
            }
            System.out.println("]");
        }

        System.out.println("-------- 排序后 --------");
        Iterator<Map.Entry<Integer, List<Integer>>> afterIterator = numMap.entrySet().iterator();
        while (afterIterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> afterEntry = afterIterator.next();
            List<Integer> afterVal = afterEntry.getValue();
            Collections.sort(afterVal);
            System.out.print(afterEntry.getKey() + " [");
            for (int i = 0; i < afterVal.size(); i++) {
                System.out.print(afterVal.get(i) + ", ");
            }
            System.out.println("]");
        }
    }
}