package com.hand.test.factory.impl;

import com.hand.test.factory.NumberFactory;

/**
 * Created by zhao'yin
 * Date 2019/7/15.
 */
public class GetNumberFactory {

    public NumberFactory getFactory(NumberFactory factory) {
        if (factory instanceof NumberFactoryImpl) {
            return new NumberFactoryImpl(((NumberFactoryImpl) factory).getBound());
        }
        return null;
    }
}