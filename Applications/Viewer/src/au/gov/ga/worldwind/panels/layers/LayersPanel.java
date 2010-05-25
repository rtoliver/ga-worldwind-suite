package au.gov.ga.worldwind.panels.layers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;

import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import au.gov.ga.worldwind.panels.dataset.DatasetPanel;
import au.gov.ga.worldwind.panels.layers.drag.NodeTransferHandler;
import au.gov.ga.worldwind.theme.AbstractThemePanel;
import au.gov.ga.worldwind.theme.Theme;
import au.gov.ga.worldwind.theme.ThemePanel;
import au.gov.ga.worldwind.util.BasicAction;
import au.gov.ga.worldwind.util.Icons;
import au.gov.ga.worldwind.util.Util;

public class LayersPanel extends AbstractThemePanel
{
	private static final String LAYERS_FILENAME = "layers.xml";
	private static final File layersFile = new File(Util.getUserDirectory(), LAYERS_FILENAME);

	private LayerTree tree;
	private INode root;

	private LayerEnabler layerEnabler;

	private DatasetPanel datasetPanel;

	public LayersPanel()
	{
		super(new BorderLayout());
		setDisplayName("Layers");

		try
		{
			root = LayerTreePersistance.readFromXML(layersFile);
		}
		catch (Exception e)
		{
		}
		if (root == null)
			root = new FolderNode("root", null, true);

		layerEnabler = new LayerEnabler();
		tree = new LayerTree(root, layerEnabler);
		layerEnabler.setTree(tree);

		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setPreferredSize(new Dimension(MINIMUM_LIST_HEIGHT, MINIMUM_LIST_HEIGHT));

		createToolBar();
	}

	private void createToolBar()
	{
		BasicAction newFolderAction =
				new BasicAction("Create", "Create Folder", Icons.newfolder.getIcon());
		newFolderAction.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				FolderNode node = new FolderNode("New Folder", Icons.folder.getURL(), true);
				TreePath p = tree.getSelectionPath();
				TreePath editPath;
				if (p == null)
				{
					getModel().addToRoot(node, false);
					editPath = new TreePath(new Object[] { root, node });
				}
				else
				{
					INode parent = (INode) p.getLastPathComponent();
					getModel().insertNodeInto(node, parent, parent.getChildCount(), false);
					editPath = p.pathByAddingChild(node);
				}
				tree.scrollPathToVisible(editPath);
				tree.startEditingAtPath(editPath);
			}
		});

		BasicAction renameAction =
				new BasicAction("Rename", "Rename selected", Icons.edit.getIcon());
		renameAction.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				TreePath p = tree.getSelectionPath();
				if (p != null)
				{
					tree.startEditingAtPath(p);
				}
			}
		});

		BasicAction deleteAction =
				new BasicAction("Delete", "Delete selected", Icons.delete.getIcon());
		deleteAction.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				TreePath p = tree.getSelectionPath();
				if (p != null)
				{
					INode node = (INode) p.getLastPathComponent();
					getModel().removeNodeFromParent(node, true);
					if (datasetPanel != null)
						datasetPanel.getTree().repaint();
				}
			}
		});

		JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
		toolBar.setFloatable(false);
		toolBar.add(newFolderAction);
		toolBar.add(renameAction);
		toolBar.add(deleteAction);
		add(toolBar, BorderLayout.PAGE_START);
	}

	public LayerTreeModel getModel()
	{
		return tree.getModel();
	}

	@Override
	public void setup(Theme theme)
	{
		layerEnabler.setWwd(theme.getWwd());
		linkPanels(theme.getPanels());
	}

	@Override
	public void dispose()
	{
		LayerTreePersistance.saveToXML(root, layersFile);
	}

	private void linkPanels(Collection<ThemePanel> panels)
	{
		for (ThemePanel panel : panels)
		{
			if (panel instanceof DatasetPanel)
			{
				datasetPanel = (DatasetPanel) panel;
				break;
			}
		}
		if (datasetPanel != null)
		{
			datasetPanel.registerLayerTreeModel(getModel());
		}
		setupDrag();
	}

	private void setupDrag()
	{
		JTree datasetTree = datasetPanel != null ? datasetPanel.getTree() : null;

		NodeTransferHandler handler = new NodeTransferHandler(tree, datasetTree);
		tree.setTransferHandler(handler);
		tree.setDragEnabled(true);
		tree.setDropMode(DropMode.ON_OR_INSERT);

		if (datasetTree != null)
		{
			datasetTree.setTransferHandler(handler);
			datasetTree.setDragEnabled(true);
		}
	}
}
