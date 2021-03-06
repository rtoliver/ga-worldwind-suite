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
package au.gov.ga.worldwind.common.render;

import java.awt.Dimension;

import javax.media.opengl.GL;

/**
 * Class used by the {@link FrameBuffer} to generate/store the depth buffer.
 * 
 * @author Michael de Hoog (michael.dehoog@ga.gov.au)
 */
public class FrameBufferDepthBuffer
{
	private int id = 0;

	private boolean texture = false;

	protected void create(GL gl, Dimension dimensions)
	{
		delete(gl);

		int[] renderBuffers = new int[1];
		if (texture)
		{
			gl.glGenTextures(1, renderBuffers, 0);
		}
		else
		{
			gl.glGenRenderbuffersEXT(1, renderBuffers, 0);
		}
		if (renderBuffers[0] <= 0)
		{
			throw new IllegalStateException("Error generating depth buffer for frame buffer");
		}
		id = renderBuffers[0];

		if (texture)
		{
			gl.glBindTexture(GL.GL_TEXTURE_2D, id);

			gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
			gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
			gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
			gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
			gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_DEPTH_TEXTURE_MODE, GL.GL_INTENSITY);
			gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_COMPARE_MODE, GL.GL_NONE);

			gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_DEPTH_COMPONENT24, dimensions.width, dimensions.height, 0,
					GL.GL_DEPTH_COMPONENT, GL.GL_UNSIGNED_BYTE, null);
			gl.glBindTexture(GL.GL_TEXTURE_2D, 0);
		}
		else
		{
			gl.glBindRenderbufferEXT(GL.GL_RENDERBUFFER_EXT, id);
			gl.glRenderbufferStorageEXT(GL.GL_RENDERBUFFER_EXT, GL.GL_DEPTH_COMPONENT24, dimensions.width,
					dimensions.height);
			gl.glBindRenderbufferEXT(GL.GL_RENDERBUFFER_EXT, 0);
		}
	}

	protected void delete(GL gl)
	{
		if (isCreated())
		{
			if (texture)
			{
				gl.glDeleteRenderbuffersEXT(1, new int[] { id }, 0);
			}
			else
			{
				gl.glDeleteTextures(1, new int[] { id }, 0);
			}
			id = 0;
		}
	}

	public boolean isCreated()
	{
		return id > 0;
	}

	public int getId()
	{
		return id;
	}

	public boolean isTexture()
	{
		return texture;
	}

	public void setTexture(boolean texture)
	{
		this.texture = texture;
	}
}
