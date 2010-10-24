package au.gov.ga.worldwind.common.util;

import gov.nasa.worldwind.avlist.AVKey;

public interface AVKeyMore extends AVKey
{
	final static String CONTEXT_URL = "au.gov.ga.worldwind.AVKeyMore.ContextURL";
	final static String DELEGATE_KIT = "au.gov.ga.worldwind.AVKeyMore.DelegateKit";
	final static String DOWNLOADER_CONNECT_TIMEOUT = "au.gov.ga.worldwind.AVKeyMore.DownloaderConnectTimeout";
	final static String DOWNLOADER_READ_TIMEOUT = "au.gov.ga.worldwind.AVKeyMore.DownloaderReadTimeout";
	final static String EXPIRY_TIMESPAN = "au.gov.ga.worldwind.AVKeyMore.ExpiryTimespan";
	final static String EXPIRY_START_TIME = "au.gov.ga.worldwind.AVKeyMore.ExpiryStartTime";
	
	//curtain layer
	final static String FULL_WIDTH = "au.gov.ga.worldwind.AVKeyMore.FullWidth";
	final static String FULL_HEIGHT = "au.gov.ga.worldwind.AVKeyMore.FullHeight";
	final static String LEVEL_WIDTH = "au.gov.ga.worldwind.AVKeyMore.LevelWidth";
	final static String LEVEL_HEIGHT = "au.gov.ga.worldwind.AVKeyMore.LevelHeight";
	final static String POSITIONS = "au.gov.ga.worldwind.AVKeyMore.Positions";
	final static String CURTAIN_TOP = "au.gov.ga.worldwind.AVKeyMore.CurtainTop";
	final static String CURTAIN_BOTTOM = "au.gov.ga.worldwind.AVKeyMore.CurtainBottom";
	final static String FOLLOW_TERRAIN = "au.gov.ga.worldwind.AVKeyMore.FollowTerrain";
	final static String SUBSEGMENTS = "au.gov.ga.worldwind.AVKeyMore.Subsegments";
	final static String PATH = "au.gov.ga.worldwind.AVKeyMore.Path";
	
	//point layer
	final static String POINT_STYLES = "au.gov.ga.worldwind.AVKeyMore.PointStyles";
	final static String POINT_ATTRIBUTES = "au.gov.ga.worldwind.AVKeyMore.PointAttributes";
	final static String POINT_PROVIDER = "au.gov.ga.worldwind.AVKeyMore.PointProvider";
	final static String POINT_TYPE = "au.gov.ga.worldwind.AVKeyMore.PointType";
	
	//geometry layer
	final static String SHAPE_PROVIDER = "au.gov.ga.worldwind.AVKeyMore.ShapeProvider";
	final static String RENDER_TYPE = "au.gov.ga.worldwind.AVKeyMore.RenderType";
	final static String SHAPE_STYLES = "au.gov.ga.worldwind.AVKeyMore.ShapeStyles";
	final static String SHAPE_ATTRIBUTES = "au.gov.ga.worldwind.AVKeyMore.ShapeAttributes";
	static final String SHAPE_TYPE = "au.gov.ga.worldwind.AVKeyMore.ShapeType";
}
