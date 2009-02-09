package layers.ga.radiometrics.areas;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.util.LevelSet;
import layers.ga.GALayer;
import layers.mask.MaskTiledImageLayer;

public class RatioThKAreasLayer extends GALayer
{
	public RatioThKAreasLayer()
	{
		super(makeLevels());
		this.setForceLevelZeroLoads(true);
		this.setRetainLevelZeroTiles(true);
		this.setUseMipMaps(true);
		this.setUseTransparentTextures(true);
		this.setSplitScale(GALayer.getSplitScale());
	}

	private static LevelSet makeLevels()
	{
		AVList params = AreasLayerUtil.makeParams();
		String layerName = "radioareas_ratio_tk_100m_he_rgb";

		params.setValue(AVKey.DATA_CACHE_NAME, "GA/Radiometrics/" + layerName);
		params.setValue(AVKey.DATASET_NAME, layerName);
		params.setValue(AVKey.TILE_URL_BUILDER, MaskTiledImageLayer
				.createDefaultUrlBuilder("tiles/radiometrics/" + layerName,
						"tiles/radiometrics/radioareas_mask", ".jpg", ".png"));

		return new LevelSet(params);
	}

	@Override
	public String toString()
	{
		return "Thorium/Potassium Ratio";
	}
}