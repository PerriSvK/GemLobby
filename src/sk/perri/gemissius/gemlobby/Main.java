package sk.perri.gemissius.gemlobby;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
    private Location spawn;

    @Override
    public void onEnable()
    {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        getConfig().options().copyDefaults(true);
        saveConfig();

        spawn = new Location(getServer().getWorld(getConfig().getString("spawn.world")),
                getConfig().getDouble("spawn.x"),
                getConfig().getDouble("spawn.y"),
                getConfig().getDouble("spawn.z"),
                (float) getConfig().getDouble("spawn.yaw"),
                (float) getConfig().getDouble("spawn.pitch"));

        spawn.getWorld().setStorm(false);
        spawn.getWorld().setThundering(false);
        spawn.getWorld().setTime(6000);
        spawn.getWorld().setGameRuleValue("doDaylightCycle", "false");

        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Plugin enabled!");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        event.setJoinMessage("");

        if (!event.getPlayer().isOp())
            event.getPlayer().setGameMode(GameMode.ADVENTURE);

        event.getPlayer().setFoodLevel(20);
        event.getPlayer().setHealth(20);
        event.getPlayer().teleport(spawn);
        for(int i = 0; i < 40; i++)
        {
            event.getPlayer().sendMessage("");
        }

        getConfig().getStringList("join-msg").forEach(l -> event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', l)));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        event.setQuitMessage("");
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event)
    {
        if (event.getEntity() instanceof Player)
            event.setCancelled(true);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event)
    {
        event.setDeathMessage("");
        event.setKeepInventory(true);
        event.setKeepLevel(true);
        event.getEntity().teleport(spawn);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event)
    {
        try
        {
            if (event.getPlayer().getLocation().getY() < 0)
                event.getPlayer().teleport(spawn);
        }
        catch (Exception ignore)
        {
        }
    }

    @EventHandler
    public void onWorldBurn(BlockBurnEvent event)
    {
        event.setCancelled(true);
    }

    @EventHandler
    public void onIgnite(BlockIgniteEvent event)
    {
        event.setCancelled(true);
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event)
    {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockChange(EntityChangeBlockEvent event)
    {
        event.setCancelled(!event.getEntity().isOp());
    }

    @EventHandler
    public void onHunge(FoodLevelChangeEvent event)
    {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event)
    {
        event.setCancelled(!event.getPlayer().isOp());
    }

    @EventHandler
    public void onItemSwap(PlayerSwapHandItemsEvent event)
    {
        event.setCancelled(!event.getPlayer().isOp());
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event)
    {
        event.setCancelled(!event.getWhoClicked().isOp());
    }

    @EventHandler
    public void onInventoryDragEvent(InventoryDragEvent event)
    {
        event.setCancelled(!event.getWhoClicked().isOp());
    }

    @EventHandler
    public void onInventoryInteractEvent(InventoryInteractEvent event)
    {
        event.setCancelled(!event.getWhoClicked().isOp());
    }

    @EventHandler
    public void onArrowPickUp(PlayerPickupArrowEvent event)
    {
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent event)
    {
        event.setCancelled(!event.getEntity().isOp());
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event)
    {
        event.setCancelled(true);
    }

    @EventHandler
    public void onExplosion(EntityExplodeEvent event)
    {
        event.setCancelled(true);
    }
}
