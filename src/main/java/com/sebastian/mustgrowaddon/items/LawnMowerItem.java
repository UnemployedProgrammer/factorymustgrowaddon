package com.sebastian.mustgrowaddon.items;

import com.sebastian.mustgrowaddon.tags.ModTags;
import com.simibubi.create.content.kinetics.saw.SawBlock;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsBoard;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsFormatter;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

import java.util.List;

public class LawnMowerItem extends Item {
    public LawnMowerItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level lvl, Entity entity, int id, boolean selected) {
        if(lvl.isClientSide()) return;

        if(selected) {
            BlockPos entityPos = new BlockPos(entity.getBlockX(), entity.getBlockY(), entity.getBlockZ());
            BlockPos GrassMowBlock = entityPos.relative(entity.getDirection());
            BlockPos GrassMowBlock2 = entityPos.relative(entity.getDirection());
            if(sholdMow(lvl.getBlockState(GrassMowBlock))) {
                lvl.destroyBlock(GrassMowBlock, true);
            }
            if(sholdMow(lvl.getBlockState(GrassMowBlock2))) {
                lvl.destroyBlock(GrassMowBlock2, true);
            }
            if(entity instanceof Player plr) {
                plr.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10));
            }
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        BlockPos entityPos = new BlockPos(ctx.getPlayer().getBlockX(), ctx.getPlayer().getBlockY(), ctx.getPlayer().getBlockZ());

        return InteractionResult.SUCCESS;
    }

    private boolean sholdMow(BlockState block) {
        return block.is(ModTags.Blocks.LAWNMOWER_MOWABLE);
    }
}
