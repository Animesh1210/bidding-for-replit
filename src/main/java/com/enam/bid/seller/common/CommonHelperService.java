package com.enam.bid.seller.common;

import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class CommonHelperService {
    public static <T> T nullSafeGetter(Supplier<T> supplier, T defaultValue) {
        try {
            T value = supplier.get();
            return value != null ? value : defaultValue;
        } catch (NullPointerException | IllegalArgumentException n) {
            return defaultValue;
        }
    }

    public static <T> T nullSafeGetter(Supplier<T> supplier){
        return nullSafeGetter(supplier, null);
    }


}
