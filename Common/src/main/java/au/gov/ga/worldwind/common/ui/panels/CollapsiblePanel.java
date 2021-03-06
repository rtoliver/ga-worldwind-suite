/*******************************************************************************
 * Copyright 2012 Geoscience Australia
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package au.gov.ga.worldwind.common.ui.panels;

import javax.swing.Icon;
import javax.swing.JPanel;

import au.gov.ga.worldwind.common.ui.collapsiblesplit.CollapsibleSplitLayout;
import au.gov.ga.worldwind.common.ui.collapsiblesplit.CollapsibleSplitPane;

/**
 * An interface for panels that can be collapsed using a
 * {@link CollapsibleSplitPane}.
 * 
 * @author Michael de Hoog (michael.dehoog@ga.gov.au)
 */
public interface CollapsiblePanel
{
	/**
	 * @return The {@link JPanel} backing this {@link CollapsiblePanel}
	 */
	public JPanel getPanel();

	/**
	 * @return <code>true</code> if this panel is resizable, <code>false</code>
	 *         otherwise
	 */
	public boolean isResizable();

	/**
	 * @param resizable
	 *            Whether this panel should be considered to be resizable
	 */
	public void setResizable(boolean resizable);

	/**
	 * Returns the weighting associated with this panel.
	 * <p/>
	 * A panel's weight influences how much space is allocated to a panel after
	 * resize.
	 * <p/>
	 * On the interval <code>[0,1]</code>. A higher weighting will result in
	 * more space allocated (if possible). If all panels in a given
	 * {@link CollapsibleaplitPane} have the same weighting they will be
	 * allocated the same amount of space.
	 * 
	 * @return The weighting attached to this panel.
	 * 
	 * @see CollapsibleSplitLayout
	 */
	public float getWeight();

	/**
	 * Set the weighting for this panel.
	 * 
	 * @param weight
	 *            The weighting to set.
	 * 
	 * @see #getWeight()
	 */
	public void setWeight(float weight);

	/**
	 * @return Whether this panel is in the 'expanded' state
	 */
	public boolean isExpanded();

	/**
	 * @param expanded
	 *            Whether
	 */
	public void setExpanded(boolean expanded);

	/**
	 * Returns whether this panel has been activated or not.
	 * <p/>
	 * Panel activation controls things like visibility, whether it is enabled
	 * or disabled, etc.
	 * 
	 * @return Whether this panel is has been activated or not.
	 */
	public boolean isOn();

	/**
	 * @param on
	 *            Whether this panel is activated or not
	 * 
	 * @see #isOn()
	 */
	public void setOn(boolean on);

	/**
	 * Returns the icon associated with this panel.
	 * <p/>
	 * The panel icon may be used when rendering the panel within the
	 * collapsible panel group.
	 * 
	 * @return The icon associated with this panel, or <code>null</code> if one
	 *         has not been specified.
	 */
	public Icon getIcon();

	/**
	 * @return The display name of this panel
	 */
	public String getName();
}
