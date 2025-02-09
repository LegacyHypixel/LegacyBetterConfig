package dev.xpple.betterconfig.command;

import dev.xpple.betterconfig.impl.ModConfigImpl;
import dev.xpple.betterconfig.util.TranslatableFallbackText;
import net.legacyfabric.fabric.api.command.v2.lib.sponge.CommandManager;
import net.legacyfabric.fabric.api.command.v2.lib.sponge.CommandNotFoundException;
import net.legacyfabric.fabric.api.command.v2.lib.sponge.CommandResult;
import net.legacyfabric.fabric.api.permission.v1.PermissibleCommandSource;
import net.minecraft.text.LiteralText;

public class ConfigCommand extends ConfigCommandHelper<PermissibleCommandSource> {
    public void register(CommandManager manager) {
        manager.register(this.create().permission("xpple.betterconfig.config").build(), "config");
    }

    @Override
    protected CommandResult comment(PermissibleCommandSource source, String config, String comment) {
        source.sendMessage(new TranslatableFallbackText("betterconfig.commands.config.comment", "Comment for %s:", config));
        source.sendMessage(new LiteralText(comment));
        return CommandResult.success();
    }

    @Override
    protected CommandResult get(PermissibleCommandSource source, ModConfigImpl modConfig, String config) {
        source.sendMessage(new TranslatableFallbackText("betterconfig.commands.config.get", "%s is currently set to %s.", config, modConfig.asString(config)));
        return CommandResult.success();
    }

    @Override
    protected CommandResult reset(PermissibleCommandSource source, ModConfigImpl modConfig, String config) {
        modConfig.reset(config);
        source.sendMessage(new TranslatableFallbackText("betterconfig.commands.config.reset", "%s has been reset to %s.", config, modConfig.asString(config)));
        return CommandResult.success();
    }

    @Override
    protected CommandResult set(PermissibleCommandSource source, ModConfigImpl modConfig, String config, Object value) throws CommandNotFoundException {
        modConfig.set(config, value);
        source.sendMessage(new TranslatableFallbackText("betterconfig.commands.config.set", "%s has been set to %s.", config, modConfig.asString(config)));
        return CommandResult.success();
    }

    @Override
    protected CommandResult add(PermissibleCommandSource source, ModConfigImpl modConfig, String config, Object value) throws CommandNotFoundException {
        modConfig.add(config, value);
        source.sendMessage(new TranslatableFallbackText("betterconfig.commands.config.add", "%s has been added to %s.", modConfig.asString(value), config));
        return CommandResult.success();
    }

    @Override
    protected CommandResult put(PermissibleCommandSource source, ModConfigImpl modConfig, String config, Object key, Object value) throws CommandNotFoundException {
        modConfig.put(config, key, value);
        source.sendMessage(new TranslatableFallbackText("betterconfig.commands.config.put", "The mapping %s=%s has been added to %s.", modConfig.asString(key), modConfig.asString(value), config));
        return CommandResult.success();
    }

    @Override
    protected CommandResult remove(PermissibleCommandSource source, ModConfigImpl modConfig, String config, Object value) throws CommandNotFoundException {
        modConfig.remove(config, value);
        source.sendMessage(new TranslatableFallbackText("betterconfig.commands.config.remove", "%s has been removed from %s.", modConfig.asString(value), config));
        return CommandResult.success();
    }
}
