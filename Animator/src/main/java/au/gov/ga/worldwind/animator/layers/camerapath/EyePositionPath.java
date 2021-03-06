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
package au.gov.ga.worldwind.animator.layers.camerapath;

import gov.nasa.worldwind.geom.Position;
import au.gov.ga.worldwind.animator.animation.Animation;
import au.gov.ga.worldwind.animator.animation.KeyFrame;

/**
 * An {@link AbstractCameraPositionPath} that draws the current animation's eye position along with nodes representing key frames.
 * 
 * @author James Navin (james.navin@ga.gov.au)
 */
class EyePositionPath extends AbstractCameraPositionPath
{
	
	public EyePositionPath(Animation animation)
	{
		super(animation);
	}

	@Override
	protected Position[] getPathPositions(int startFrame, int endFrame)
	{
		return getAnimation().getCamera().getEyePositionsBetweenFrames(startFrame, endFrame);
	}
	
	@Override
	protected boolean isPathFrame(KeyFrame keyFrame)
	{
		return keyFrame.hasValueForParameter(getAnimation().getCamera().getEyeLat()) || 
			   keyFrame.hasValueForParameter(getAnimation().getCamera().getEyeLon()) || 
			   keyFrame.hasValueForParameter(getAnimation().getCamera().getEyeElevation());
	}
	
}
