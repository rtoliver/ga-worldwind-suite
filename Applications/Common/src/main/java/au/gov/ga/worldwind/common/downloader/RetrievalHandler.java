package au.gov.ga.worldwind.common.downloader;

/**
 * Represents a handler which is called after a download is completed.
 * 
 * @author Michael de Hoog (michael.dehoog@ga.gov.au)
 */
public interface RetrievalHandler
{
	/**
	 * Handle the download.
	 * 
	 * @param result
	 *            Downloaded result
	 */
	public void handle(RetrievalResult result);
}