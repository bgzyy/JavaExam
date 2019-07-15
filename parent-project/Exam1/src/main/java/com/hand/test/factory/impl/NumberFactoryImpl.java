package com.hand.test.factory.impl;

import com.hand.test.factory.NumberFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhao'yin
 * Date 2019/7/15.
 */
public class NumberFactoryImpl implements NumberFactory {
    private Integer bound;

    public Integer getBound() {
        return bound;
    }

    public void setBound(Integer bound) {
        this.bound = bound;
    }

    public NumberFactoryImpl(Integer bound) {
        this.bound = bound;
    }

    @Override
    public List<Integer> randomNum() {
        List<Integer> randomList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            int num = random.nextInt(bound);
            randomList.add(num);
        }
        return randomList;
    }
}