//package net.braytonks.palpatch.block;
//
//import net.braytonks.palpatch.item.ModItems;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.CropBlock;
//import net.minecraft.item.ItemConvertible;
//import net.minecraft.state.StateManager;
//import net.minecraft.state.property.IntProperty;
//
//public class TutorialCauliflowerCropBlock extends CropBlock {
//    public static final int MAX_AGE = 6;
//    public static final IntProperty AGE = IntProperty.of("age", 0, 6);
//
//    public TutorialCauliflowerCropBlock(Settings settings) {
//        super(settings);
//    }
//
//    @Override
//    protected ItemConvertible getSeedsItem() {return ModItems.SPICEWOOD_STICK;    }
//
//    @Override
//    public IntProperty getAgeProperty() { return AGE;}
//
//    @Override
//    public int getMaxAge() {
//        return MAX_AGE;
//    }
//
//    @Override
//    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
//        builder.add(AGE);
//        super.appendProperties(builder);
//    }
//}
