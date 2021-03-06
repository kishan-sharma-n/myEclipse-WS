/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Gareth Jon Lynch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.gazbert.patterns.behavioural.observer.jdkobserver.bots;

import com.gazbert.patterns.behavioural.observer.jdkeventing.MarketType;
import com.gazbert.patterns.behavioural.observer.jdkobserver.Market;

import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

/**
 * This would not exist in real world. Here because I'm too lazy to type same code for each bot.
 * <p>
 * Uses java.util.Observer.
 * <p>
 *
 * @author gazbert
 */
abstract class AbstractTradingBot implements Observer {

    /**
     * USD GBP market bid price
     */
    private BigDecimal latestUsdGbpMarketBidPrice = new BigDecimal("0.60");

    /**
     * USD EUR market bid price
     */
    private BigDecimal latestUsdEurMarketBidPrice = new BigDecimal("0.73");


    public BigDecimal getLatestUsdGbpMarketBidPrice() {
        return latestUsdGbpMarketBidPrice;
    }

    public BigDecimal getLatestUsdEurMarketBidPrice() {
        return latestUsdEurMarketBidPrice;
    }

    /**
     * We let JDK do the work this time...
     */
    @Override
    public void update(Observable observable, Object obj) {

        final Market market = ((Market) observable);

        // Bleurgh! but you get the idea...
        if (market.getMarketType() == MarketType.USD_GBP) {
            latestUsdGbpMarketBidPrice = market.getLastBidPrice();
        } else if (market.getMarketType() == MarketType.USD_EUR) {
            latestUsdEurMarketBidPrice = market.getLastBidPrice();
        }
    }
}
