package panels.layers;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.Layer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import layers.ga.gravity.GravityLayer;
import layers.ga.magnetics.MagneticsLayer;

public class GeophysicsPanel extends JPanel
{
	private Layer gravity;
	private Layer magnetics;

	private JCheckBox gravityCheck;
	private JSlider gravitySlider;
	private JCheckBox magneticsCheck;
	private JSlider magneticsSlider;

	private WorldWindow wwd;

	public GeophysicsPanel(WorldWindow wwd)
	{
		this.wwd = wwd;
		createLayers();
		fillPanel();
	}

	private void createLayers()
	{
		gravity = new GravityLayer();
		gravity.setEnabled(false);
		wwd.getModel().getLayers().add(gravity);

		magnetics = new MagneticsLayer();
		magnetics.setEnabled(false);
		wwd.getModel().getLayers().add(magnetics);
	}

	private void fillPanel()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints c;
		JPanel panel;
		Dimension size;
		JSeparator js;
		
		int SPACING = 5;

		ActionListener al = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				updateLayers();
			}
		};
		ChangeListener cl = new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				updateLayers();
			}
		};
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 1;
		add(mainPanel, c);

		panel = new TitlePanel(new String[] { "GRAVITY ANOMALY MAP",
				"OF THE AUSTRALIAN REGION" },
				new String[] { "3rd Edition, 2008" }, 0, 0);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		mainPanel.add(panel, c);
		
		js = new JSeparator(JSeparator.HORIZONTAL);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(SPACING, 0, SPACING, 0);
		mainPanel.add(js, c);

		panel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 1;
		mainPanel.add(panel, c);

		gravityCheck = new JCheckBox("Gravity");
		gravityCheck.addActionListener(al);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		panel.add(gravityCheck, c);

		gravitySlider = new JSlider(1, 100, 100);
		gravitySlider.setPaintLabels(false);
		gravitySlider.setPaintTicks(false);
		gravitySlider.addChangeListener(cl);
		size = gravitySlider.getPreferredSize();
		size.width = 50;
		gravitySlider.setPreferredSize(size);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		panel.add(gravitySlider, c);
		
		js = new JSeparator(JSeparator.HORIZONTAL);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(SPACING * 2, 0, SPACING * 2, 0);
		mainPanel.add(js, c);

		panel = new TitlePanel(new String[] { "MAGNETIC ANOMALY MAP",
				"OF AUSTRALIA" }, new String[] { "4th Edition, 2004" }, 10, 0);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		mainPanel.add(panel, c);
		
		js = new JSeparator(JSeparator.HORIZONTAL);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(SPACING, 0, SPACING, 0);
		mainPanel.add(js, c);

		panel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		mainPanel.add(panel, c);

		magneticsCheck = new JCheckBox("Magnetics");
		magneticsCheck.addActionListener(al);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		panel.add(magneticsCheck, c);

		magneticsSlider = new JSlider(1, 100, 100);
		magneticsSlider.setPaintLabels(false);
		magneticsSlider.setPaintTicks(false);
		magneticsSlider.addChangeListener(cl);
		size = magneticsSlider.getPreferredSize();
		size.width = 50;
		magneticsSlider.setPreferredSize(size);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		panel.add(magneticsSlider, c);
		
		js = new JSeparator(JSeparator.HORIZONTAL);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 7;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(SPACING * 2, 0, SPACING * 2, 0);
		mainPanel.add(js, c);
	}

	private void updateLayers()
	{
		gravity.setEnabled(gravityCheck.isSelected());
		gravity.setOpacity(gravitySlider.getValue() / 100d);
		magnetics.setEnabled(magneticsCheck.isSelected());
		magnetics.setOpacity(magneticsSlider.getValue() / 100d);
		wwd.redraw();
	}
}