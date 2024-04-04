package me.concision.warcrimes.docker.swapper.transformers;

import me.concision.warcrimes.docker.swapper.api.DockerImage;
import me.concision.warcrimes.docker.swapper.transformer.ImageState;
import me.concision.warcrimes.docker.swapper.transformer.ImageTransformer;
import me.concision.warcrimes.docker.swapper.util.Sha256Hash;

import java.util.stream.Collectors;

public class T08NewConfigName implements ImageTransformer {
    @Override
    public void transform(ImageState state) {
        DockerImage outImage = state.outImage();

        outImage.configEntry().setName(
                Sha256Hash.hexHash(outImage.layers().stream().map(image -> image.rootDirectory().getName()).collect(Collectors.joining())) + ".json"
        );
    }
}
