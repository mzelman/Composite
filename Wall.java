import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//import do wersji z flatMap
//import java.util.stream.Stream;

public class Wall implements Structure {

    private List<Block> blocks = new ArrayList<Block>();

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findBlockByColorRecursive(blocks, color);
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return findBlocksByMaterialRecursive(blocks, material);
    }

    @Override
    public int count() {
        return countRecursive(blocks);
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    private Optional<Block> findBlockByColorRecursive(List<Block> blocks, String color) {
        for (Block block : blocks) {
            if (block.getColor().equals(color)) {
                return Optional.of(block);
            } 
            if (block instanceof CompositeBlock) {
                CompositeBlock compositeBlock = (CompositeBlock) block;
                Optional<Block> result = findBlockByColorRecursive(compositeBlock.getBlocks(), color);
                if (result.isPresent()) {
                    return result;
                }
            }
        }
        return Optional.empty();
    }

    private List<Block> findBlocksByMaterialRecursive(List<Block> blocks, String material) {
        List<Block> foundBlocks = new ArrayList<>();
        for (Block block : blocks) {
            if (block.getMaterial().equals(material)) {
                foundBlocks.add(block);
            }
            if (block instanceof CompositeBlock) {
                CompositeBlock compositeBlock = (CompositeBlock) block;
                foundBlocks.addAll(findBlocksByMaterialRecursive(compositeBlock.getBlocks(), material));
            }
        }
        return foundBlocks;
    }

    private int countRecursive(List<Block> blocks) {
        int count = 0;
        for (Block block : blocks) {
            count++;
            if (block instanceof CompositeBlock) {
                CompositeBlock compositeBlock = (CompositeBlock) block;
                count += countRecursive(compositeBlock.getBlocks());
            }
        }
        return count;
    }

    // wersja z flatMap
    // public int count() {
    //     return (int) blocks.stream()
    //                        .flatMap(this::flatten)
    //                        .count();
    // }

    // private Stream<Block> flatten(Block block) {
    //     if (block instanceof CompositeBlock) {
    //         CompositeBlock compositeBlock = (CompositeBlock) block;
    //         return Stream.concat(Stream.of(block), compositeBlock.getBlocks().stream().flatMap(this::flatten));
    //     } else {
    //         return Stream.of(block);
    //     }
    // }
}