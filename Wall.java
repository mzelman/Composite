import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    private List<Block> blocks = new ArrayList<Block>();

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream().map((block) -> findBlockByColorRecursive(block, color))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> foundBlocks = new ArrayList<>();
        for (Block block : blocks) {
            findBlocksByMaterialRecursive(block, material, foundBlocks);
        }
        return foundBlocks;
    }

    @Override
    public int count() {
        return blocks.stream().mapToInt((block) -> countRecursive(block)).sum();
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    private Optional<Block> findBlockByColorRecursive(Block block, String color) {
        if (block.getColor().equals(color)) {
            return Optional.of(block);
        }
        if (block instanceof CompositeBlock) {
            CompositeBlock compositeBlock = (CompositeBlock) block;
            return compositeBlock.getBlocks().stream()
                    .map((inner) -> findBlockByColorRecursive(inner, color))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .findFirst();
        }
        return Optional.empty();
    }

    private void findBlocksByMaterialRecursive(Block block, String material, List<Block> foundBlocks) {
        if (block.getMaterial().equals(material)) {
            foundBlocks.add(block);
        }
        if (block instanceof CompositeBlock) {
            CompositeBlock compositeBlock = (CompositeBlock) block;
            compositeBlock.getBlocks().forEach((inner) -> findBlocksByMaterialRecursive(inner, material, foundBlocks));
        }
    }

    private int countRecursive(Block block) {
        if (block instanceof CompositeBlock) {
            CompositeBlock compositeBlock = (CompositeBlock) block;
            return compositeBlock.getBlocks().stream().mapToInt((inner) -> countRecursive(inner)).sum();
        }
        return 1;
    }

}