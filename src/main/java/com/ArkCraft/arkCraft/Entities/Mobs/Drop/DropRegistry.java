package com.ArkCraft.arkCraft.Entities.Mobs.Drop;


import java.util.HashMap;
import java.util.Map;

public final class DropRegistry {
    private static final Map<String, DropTable> TABLES = new HashMap<>();

    private DropRegistry() {}

    public static void bindOnce(String mobKey, DropTable table) {
        TABLES.putIfAbsent(mobKey, table);
    }

    public static DropTable get(String mobKey) {
        return TABLES.get(mobKey);
    }
}
