package tsp.demonicexpansion.implementation.item;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * Easy way of creating {@link SlimefunItemStack} for this addon.
 */
public class DemonicItemStack extends SlimefunItemStack {

    public DemonicItemStack(@Nonnull String id, @Nonnull ItemStack item) {
        super("DX_" + id, item);
    }

    public DemonicItemStack(@Nonnull String id, @Nonnull ItemStack item, @Nonnull Consumer<ItemMeta> consumer) {
        super("DX_" + id, item, consumer);
    }

    public DemonicItemStack(@Nonnull String id, @Nonnull Material type, @Nonnull Consumer<ItemMeta> consumer) {
        super("DX_" + id, type, consumer);
    }

    public DemonicItemStack(@Nonnull String id, @Nonnull Material type, @Nullable String name, @Nonnull Consumer<ItemMeta> consumer) {
        super("DX_" + id, type, name, consumer);
    }

    public DemonicItemStack(@Nonnull String id, @Nonnull ItemStack item, @Nullable String name, String... lore) {
        super("DX_" + id, item, name, lore);
    }

    public DemonicItemStack(@Nonnull String id, @Nonnull Material type, @Nullable String name, String... lore) {
        super("DX_" + id, type, name, lore);
    }

    public DemonicItemStack(@Nonnull String id, @Nonnull Material type, @Nonnull Color color, @Nullable String name, String... lore) {
        super("DX_" + id, type, color, name, lore);
    }

    public DemonicItemStack(@Nonnull String id, @Nonnull Color color, @Nonnull PotionEffect effect, @Nullable String name, String... lore) {
        super("DX_" + id, color, effect, name, lore);
    }

    public DemonicItemStack(@Nonnull SlimefunItemStack item, int amount) {
        super(item, amount);
    }

    public DemonicItemStack(@Nonnull String id, @Nonnull String texture, @Nullable String name, String... lore) {
        super("DX_" + id, texture, name, lore);
    }

    public DemonicItemStack(@Nonnull String id, @Nonnull HeadTexture head, @Nullable String name, String... lore) {
        super("DX_" + id, head, name, lore);
    }

    public DemonicItemStack(@Nonnull String id, @Nonnull String texture, @Nullable String name, @Nonnull Consumer<ItemMeta> consumer) {
        super("DX_" + id, texture, name, consumer);
    }

    public DemonicItemStack(@Nonnull String id, @Nonnull String texture, @Nonnull Consumer<ItemMeta> consumer) {
        super("DX_" + id, texture, consumer);
    }

}
