package com.sebastian.mustgrowaddon;

import com.sebastian.mustgrowaddon.items.LawnMowerItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MustGrowAddon.MODID);

    public static final RegistryObject<LawnMowerItem> LAWNMOWER = ITEMS.register("lawnmower",
            () -> new LawnMowerItem(new Item.Properties()));
}
