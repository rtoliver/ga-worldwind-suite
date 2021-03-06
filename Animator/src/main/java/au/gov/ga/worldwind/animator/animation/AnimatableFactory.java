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
package au.gov.ga.worldwind.animator.animation;

import gov.nasa.worldwind.avlist.AVList;

import java.lang.reflect.Constructor;

import org.w3c.dom.Element;

import au.gov.ga.worldwind.animator.animation.io.AnimationFileVersion;
import au.gov.ga.worldwind.common.util.Validate;

/**
 * A factory interface for de-serialising {@link Animatable} objects from XML
 * 
 * @author James Navin (james.navin@ga.gov.au)
 * @author Michael de Hoog (michael.dehoog@ga.gov.au)
 */
public interface AnimatableFactory
{
	/**
	 * Create an instance of the {@link Animatable} that corresponds to the
	 * provided XML element in the given file version.
	 * 
	 * @param element
	 *            The element to de-serialise from
	 * @param version
	 *            The version the provided element is in
	 * @param context
	 *            The context needed to de-serialise the object.
	 * 
	 * @return an instance of the {@link Animatable} that corresponds to the
	 *         provided XML element, or <code>null</code> if one cannot be found
	 */
	Animatable fromXml(Element element, AnimationFileVersion version, AVList context);

	/**
	 * Helper class for instanciating Animatable objects using reflection.
	 */
	static class AnimatableInstanciator
	{
		/**
		 * Instantiate the provided class using the default constructor.
		 * 
		 * @param clazz
		 *            The class to instantiate
		 * 
		 * @return The instantiated object
		 */
		public static <A extends Animatable> A instantiate(Class<A> clazz)
		{
			Validate.notNull(clazz, "A class must be provided");

			try
			{
				Constructor<A> constructor = clazz.getDeclaredConstructor();
				constructor.setAccessible(true);
				return constructor.newInstance();
			}
			catch (NoSuchMethodException e)
			{
				throw new IllegalArgumentException("Class " + clazz.getSimpleName()
						+ " does not declare a no-arg constructor.");
			}
			catch (Exception e)
			{
				throw new IllegalStateException("Exception while instantiating class " + clazz.getSimpleName() + ".",
						e.getCause());
			}
		}
	}
}
