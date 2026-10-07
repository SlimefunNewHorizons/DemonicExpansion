package tsp.demonicexpansion.implementation.research;

import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import org.bukkit.NamespacedKey;
import tsp.demonicexpansion.DemonicExpansion;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicInteger;

public class DemonicResearch extends Research {

    private static final AtomicInteger rid = new AtomicInteger(666_0); // Not sure if researches are fully thread-safe, atomic just in case.

    public DemonicResearch(@Nonnull String key, @Nonnull String defaultName, int defaultCost) {
        super(new NamespacedKey(DemonicExpansion.getInstance(), "research_" + key), rid.getAndIncrement(), defaultName, defaultCost);
        DemonicExpansion.getInstance().getLog().debug("Initialized research '" + key + "' with rid: " + rid.get());
    }

}
