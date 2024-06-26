package me.concision.warcrimes.docker.swapper.transformers;

import me.concision.warcrimes.docker.swapper.api.DockerImage;
import me.concision.warcrimes.docker.swapper.api.DockerLayer;
import me.concision.warcrimes.docker.swapper.transformer.ImageState;
import me.concision.warcrimes.docker.swapper.transformer.ImageTransformer;

public class T04RelinkLastLayerConfig implements ImageTransformer {
    @Override
    public void transform(ImageState state) {
        DockerImage oldImage = state.oldImage();
        if (oldImage.layers().size() < state.inputImage().layers().size()) {
            DockerLayer lastOldInputLayer = state.inputImage().layers().get(state.oldImage().layers().size() - 1);
            DockerLayer lastNewLayer = state.outImage().layers().get(state.newImage().layers().size() - 1);

            lastNewLayer.manifest().config(null);
            lastNewLayer.manifest().containerConfig(lastOldInputLayer.manifest().containerConfig());
        }
    }
}
