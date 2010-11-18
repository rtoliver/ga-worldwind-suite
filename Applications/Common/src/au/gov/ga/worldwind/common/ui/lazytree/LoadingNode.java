package au.gov.ga.worldwind.common.ui.lazytree;

import javax.swing.tree.DefaultMutableTreeNode;

public class LoadingNode extends DefaultMutableTreeNode implements ILoadingNode
{
	public LoadingNode(String label)
	{
		super(label, false);
	}

	@Override
	public boolean isLoading()
	{
		return true;
	}
}
