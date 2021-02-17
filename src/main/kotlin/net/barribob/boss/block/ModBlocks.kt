package net.barribob.boss.block

import net.barribob.boss.Mod
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.util.registry.Registry

object ModBlocks {
    val obsidilithRune = ObsidilithRuneBlock(FabricBlockSettings.copy(Blocks.CRYING_OBSIDIAN))

    fun init(){
        Registry.register(Registry.BLOCK, Mod.identifier("obsidilith_rune"), obsidilithRune)
        Registry.register(Registry.ITEM, Mod.identifier("obsidilith_rune"), BlockItem(obsidilithRune, FabricItemSettings()))
    }
}