package kr.dotmer.group.core.settings

import kr.elroy.library.yaml.YamlConfig
import org.bukkit.Bukkit

object Settings {
    private val config by lazy {
        val plugin = Bukkit.getPluginManager().getPlugin("GroupSystem")!!
        YamlConfig.createWithDefault(plugin, "settings.yml")
    }

    fun reload() {
        config.load()
    }

    object LevelUpItem {
        val CATEGORY
            get() =
                config.findString("LevelUpItem.Category") ?: throw NullPointerException("LevelUpItem.Category is null")
        val NAME get() = config.findString("LevelUpItem.Name") ?: throw NullPointerException("LevelUpItem.Name is null")
    }

    object Guild : GroupSettings {
        override fun getMaxLevel(): Int {
            return config.findInt("Guild.MaxLevel") ?: throw NullPointerException("Guild.MaxLevel is null")
        }
    }

    object Town : GroupSettings {
        override fun getMaxLevel(): Int {
            return config.findInt("Town.MaxLevel") ?: throw NullPointerException("Town.MaxLevel is null")
        }
    }
}

interface GroupSettings {
    fun getMaxLevel(): Int
}