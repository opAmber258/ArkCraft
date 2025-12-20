package com.ArkCraft.arkCraft.Events.LevelTick;


/*
@Mod.EventBusSubscriber
public class LevelEvent {


    public static Map<String, Map<Mob, Integer>> playerLaserCoolDown = new HashMap<>();

    public static void tryToRemoveMobInMap() {
        playerLaserCoolDown.forEach((player, mobIntegerMap) -> {
            if (mobIntegerMap != null) {
                mobIntegerMap.entrySet().removeIf(mobIntegerEntry -> mobIntegerEntry.getKey().isDeadOrDying());
            }
        });
    }

    @SubscribeEvent
    public static void LevelTick(TickEvent.LevelTickEvent event) throws IOException, SQLException, ParseException {

        timeEvent(event);
        MobSpawn.tick(event);

        if (event.side.isServer() && event.phase.equals(TickEvent.Phase.START) && Tick.get() % 18000 == 0) {
            ServerLevel serverLevel = (ServerLevel) event.level;
            List<Entity> projectileList = new ArrayList<>();
            serverLevel.getAllEntities().forEach(entity -> {
                if (entity instanceof Projectile) projectileList.add(entity);
            });
            if (projectileList.size() > 1000) {
                projectileList.forEach(entity -> entity.remove(Entity.RemovalReason.KILLED));
            }
        } // 尝试清理

        if (event.side.isServer() && event.phase.equals(TickEvent.Phase.START)
                && event.level.equals(event.level.getServer().getLevel(Level.OVERWORLD))) {
            Level level = event.level;
            int tick = Tick.get();
            if (tick % 100 == 0) {
                tryToRemoveMobInMap();
            }
        }

    }

    public static void timeEvent(TickEvent.LevelTickEvent event) {
        if (event.side.isServer() && event.phase.equals(TickEvent.Phase.START) && event.level.equals(event.level.getServer().getLevel(Level.OVERWORLD))) {
            Calendar calendar = Calendar.getInstance();
            Level level = event.level;
            List<ServerPlayer> playerList = event.level.getServer().getPlayerList().getPlayers();
        }
    }
}
 */