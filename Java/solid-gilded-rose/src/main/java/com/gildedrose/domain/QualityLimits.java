package com.gildedrose.domain;

public final class QualityLimits {
    public static final int MIN_QUALITY = 0;
    public static final int MAX_QUALITY = 50;
    public static final int LEGENDARY_QUALITY = 80;

    private QualityLimits() {
    }

    public static int clamp(int value, int minimum, int maximum) {
        return Math.max(minimum, Math.min(maximum, value));
    }
}
