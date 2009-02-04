package layers.radiometry;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.util.LevelSet;
import layers.GALayerUtil;
import layers.mask.MaskTiledImageLayer;

public class RatioUThLayer extends MaskTiledImageLayer
{
	public RatioUThLayer()
	{
		super(makeLevels());
		this.setForceLevelZeroLoads(true);
		this.setRetainLevelZeroTiles(true);
		this.setUseMipMaps(true);
		this.setUseTransparentTextures(true);
		this.setSplitScale(GALayerUtil.getSplitScale());
	}

	private static LevelSet makeLevels()
	{
		AVList params = RadioLayerUtil.makeParams();
		String layerName = "radio_ratio_ut_100m_he_rgb";

		params.setValue(AVKey.DATA_CACHE_NAME, "GA/Radiometrics/" + layerName);
		params.setValue(AVKey.DATASET_NAME, layerName);
		params.setValue(AVKey.TILE_URL_BUILDER, MaskTiledImageLayer
				.createDefaultUrlBuilder("tiles/radiometrics/" + layerName,
						"tiles/radiometrics/radio_mask", ".jpg", ".png"));

		return new LevelSet(params);
	}

	@Override
	public String toString()
	{
		return "Uranium Thorium Ratio";
	}
}
